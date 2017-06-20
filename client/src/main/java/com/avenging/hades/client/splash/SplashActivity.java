package com.avenging.hades.client.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.avenging.hades.client.R;
import com.avenging.hades.client.base.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
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
