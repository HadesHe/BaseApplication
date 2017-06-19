package com.avenging.hades.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.avenging.hades.baselibrary.R;
import com.avenging.hades.baselibrary.loading.VaryViewHelperController;
import com.avenging.hades.baselibrary.netstatus.NetChangeObserver;
import com.avenging.hades.baselibrary.netstatus.NetStateReceiver;
import com.avenging.hades.baselibrary.netstatus.NetUtils;
import com.avenging.hades.baselibrary.utils.SmartBarUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BaseAppcompatActitvity extends AppCompatActivity {

    public static final int TRANSITION_MODE_LEFT=0;
    public static final int TRANSITION_MODE_RIGHT=1;
    public static final int TRANSITION_MODE_TOP=2;
    public static final int TRANSITION_MODE_BOTTOM=3;
    public static final int TRANSITION_MODE_SCALE=4;
    public static final int TRANSITION_MODE_FADE=5;
    private String TAG_LOG ;
    private Context mContext;
    private float mScreenDensity;
    private int mScreenHeight;
    private int mScreenWidth;
    private NetChangeObserver mNetChangeObserver;
    private VaryViewHelperController mVaryViewHelperController;
    private Snackbar mSnackBar;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TRANSITION_MODE_LEFT,TRANSITION_MODE_RIGHT,TRANSITION_MODE_TOP,TRANSITION_MODE_BOTTOM,TRANSITION_MODE_SCALE,TRANSITION_MODE_FADE})
    public @interface TransitionMode{}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(toggleOverridePendingTransition()){
            switch (getOverridePendingTransition()){
                case TRANSITION_MODE_LEFT:
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                    break;
                case TRANSITION_MODE_RIGHT:
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    break;
                case TRANSITION_MODE_TOP:
                    overridePendingTransition(R.anim.top_in,R.anim.top_out);
                    break;
                case TRANSITION_MODE_BOTTOM:
                    overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                    break;
                case TRANSITION_MODE_SCALE:
                    overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                    break;
                case TRANSITION_MODE_FADE:
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;

            }
        }
        super.onCreate(savedInstanceState);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            getBundleExtras(extra);
        }

        // TODO: 2017/6/14 bind eventbus
//        if(isBindEventBusHere()){
//            EventBus.getDefault().register(this);
//        }

        SmartBarUtils.hide(getWindow().getDecorView());
        setTranslucentStatus(isApplyStatusBarTranslucency());
        mContext=this;
        TAG_LOG=this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);

        initScreen();

        if(getContentViewLayoutID()!=0){
            setContentView(getContentViewLayoutID());
        }else{
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        mNetChangeObserver=new NetChangeObserver(){

            @Override
            public void onNetConnected(@NetUtils.NetType int type) {
                super.onNetConnected(type);
                onNetWorkConnected(type);

            }

            @Override
            public void onNetDisconnect() {
                super.onNetDisconnect();
                onNetworkDisConnected();

            }
        };

        NetStateReceiver.registerObserver(mNetChangeObserver);

        initViewAndEvents();

    }

    protected abstract void initViewAndEvents();

    private void setTranslucentStatus(boolean on) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            WindowManager.LayoutParams winParams=window.getAttributes();
            final int bits=WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if(on){
                winParams.flags|=bits;
            }else{
                winParams.flags&=~bits;
            }
            window.setAttributes(winParams);

        }
    }

    protected abstract boolean isApplyStatusBarTranslucency();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
        if(toggleOverridePendingTransition()){
            switch (getOverridePendingTransition()){
                case TRANSITION_MODE_LEFT:
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                    break;
                case TRANSITION_MODE_RIGHT:
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    break;
                case TRANSITION_MODE_TOP:
                    overridePendingTransition(R.anim.top_in,R.anim.top_out);
                    break;
                case TRANSITION_MODE_BOTTOM:
                    overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                    break;
                case TRANSITION_MODE_SCALE:
                    overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                    break;
                case TRANSITION_MODE_FADE:
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;
            }
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        if(null!=getLoadingTargetView()){
            mVaryViewHelperController=new VaryViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: 2017/6/19 resetButterknife
//        ButterKnife.reset(this);
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        // TODO: 2017/6/19 unregister Butterknife
//        if(isBindEventBusHere()){
//            EventBus.getDefault().unregister(this);
//        }
    }

    protected abstract void initView();

    protected abstract View getLoadingTargetView();

    protected abstract void onNetworkDisConnected();

    protected abstract void onNetWorkConnected(int type);

    protected abstract int getContentViewLayoutID();

    private void initScreen() {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity=displayMetrics.density;
        mScreenHeight=displayMetrics.heightPixels;
        mScreenWidth=displayMetrics.widthPixels;

    }

    protected abstract void getBundleExtras(Bundle extra);

    protected abstract @TransitionMode int getOverridePendingTransition();

    protected abstract boolean toggleOverridePendingTransition();

    protected void readyGo(Class<?> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz,Bundle bundle){
        Intent intent=new Intent(this,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoThenKill(Class<?> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
        finish();
    }

    protected void readyGoThenKill(Class<?> clazz,Bundle bundle){
        Intent intent=new Intent(this,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    protected void readyGoForResult(Class<?> clazz,int requestCode){
        Intent intent=new Intent(this,clazz);
        startActivityForResult(intent,requestCode);
    }

    protected void readyGoForResult(Class<?> clazz,int requestCode,Bundle bundle){
        Intent inten=new Intent(this,clazz);
        if(null != bundle){
            inten.putExtras(bundle);
        }
        startActivityForResult(inten,requestCode);
    }

    protected void showShortToast(String msg){
        if(!TextUtils.isEmpty(msg)){
            if(mSnackBar==null) {
                mSnackBar = Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT);
            }else{
                mSnackBar.setText(msg);
            }
            mSnackBar.show();
        }
    }

    protected void toggleShowLoading(boolean toggle,String msg){
        if(null==mVaryViewHelperController){
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mVaryViewHelperController.showLoading(msg);
        }else{
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowEmpty(boolean toggle,String msg,View.OnClickListener onClickListener){
        if(null==mVaryViewHelperController){
            throw new IllegalArgumentException("You must return a right target view for empty");
        }
        if (toggle) {
            mVaryViewHelperController.showEmpty(msg,onClickListener);
        }else{
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowError(boolean toggle,String msg,View.OnClickListener onClickListener){
        if(null==mVaryViewHelperController){
            throw new IllegalArgumentException("You must return a right target view for error");
        }
        if (toggle) {
            mVaryViewHelperController.showError(msg,onClickListener);
        }else{
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleNetError(boolean toggle,String msg,View.OnClickListener onClickListener){
        if(null==mVaryViewHelperController){
            throw new IllegalArgumentException("You must return a right target view for net error");
        }
        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        }else{
            mVaryViewHelperController.restore();
        }
    }

    protected void setSystemBarTintDrawable(Drawable tintDrawable){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            // TODO: 2017/6/19 start SystemBarTintManager
            SystemBarTintManager mTintManager=new SystemBarTintManager(this);
        }
    }



}
