package com.avenging.hades.baselibrary.base;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.avenging.hades.baselibrary.R;
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
}
