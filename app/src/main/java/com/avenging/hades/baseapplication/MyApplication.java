package com.avenging.hades.baseapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Hades on 2017/6/7.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }
}
