package com.avenging.hades.client;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.avenging.hades.baselibrary.base.BaseAppManager;

/**
 * Created by zhanghehe on 2017/6/19.
 */

public class SimplifyReaderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();

    }

    public void exitApp(){
        BaseAppManager.getInstance().clear();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
