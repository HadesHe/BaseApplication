package com.avenging.hades.baseapplication;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Hades on 2017/6/7.
 */

public class MyApplication extends Application {

    private RefWatcher refWatcher;

    public RefWatcher getRefWatcher(){
        return refWatcher;
    }

    public static RefWatcher getRefWatcher(Context context){
        MyApplication application= (MyApplication) context.getApplicationContext();
        return application.getRefWatcher();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= LeakCanary.install(this);
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }
}
