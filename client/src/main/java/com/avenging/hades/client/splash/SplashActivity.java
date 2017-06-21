package com.avenging.hades.client.splash;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenging.hades.client.R;
import com.avenging.hades.client.base.BaseActivity;

import java.util.Calendar;

public class SplashActivity extends BaseActivity implements SplashContract.SplashView{


    private ImageView ivSplashImage;
    private TextView tvSplashVersionName;
    private TextView tvSplashCopyright;
    private SplashPresenterImp mPresenter;

    @Override
    protected void initViewAndEvents() {
        ivSplashImage=(ImageView)findViewById(R.id.ivSplashImage);
        tvSplashVersionName=(TextView)findViewById(R.id.tvSplashVersionName);
        tvSplashCopyright=(TextView)findViewById(R.id.tvSplashCopyright);
        mPresenter=new SplashPresenterImp();
        mPresenter.attachView(this);

        tvSplashCopyright.setText(getVersionName(SplashActivity.this));
        tvSplashCopyright.setText(getCopyRight(SplashActivity.this));
        ivSplashImage.setImageResource(getBackgroundResId());


        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                navigateToHomePage();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivSplashImage.startAnimation(animation);

    }

    private void navigateToHomePage() {
        // TODO: 2017/6/21 navigateToHomePage
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private int getBackgroundResId() {
        int resId;
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(calendar.HOUR_OF_DAY);
        if(hour>=6&&hour<=12){
            resId=R.drawable.morning;
        }else if(hour>12&&hour<=18){
            resId=R.drawable.afternoon;
        }else{
            resId=R.drawable.night;
        }

        return resId;
    }

    private String getCopyRight(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }

    private String getVersionName(Context context) {
        String versionName=null;
        try {
            versionName=context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(getString(R.string.splash_version),versionName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }


    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void onNetWorkConnected(int type) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getBundleExtras(Bundle extra) {

    }

    @Override
    protected @TransitionMode int getOverridePendingTransition() {
        return TRANSITION_MODE_NULL;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected boolean isApplyKitKatTransluency() {
        return true;
    }
}
