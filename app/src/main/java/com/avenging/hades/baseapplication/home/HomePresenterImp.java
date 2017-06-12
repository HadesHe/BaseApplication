package com.avenging.hades.baseapplication.home;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

import com.avenging.hades.baseapplication.BasePresenter;
import com.avenging.hades.baseapplication.bean.ActivityHolder;
import com.avenging.hades.baseapplication.login.LoginActivity;
import com.avenging.hades.baseapplication.loginoptimized.LoginOptimizedActivity;
import com.avenging.hades.baseapplication.outeradapter.AdapterActivityA;

/**
 * Created by Hades on 2017/6/8.
 */

public class HomePresenterImp extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    public static ActivityHolder activityHolder;

    static {
        activityHolder=new ActivityHolder();
         activityHolder.addActivity("MVP with login Showcase",LoginActivity.class);
        // activityHolder.addActivity("MVP in Adapter B",AdapterActivityB.class);
        // TODO: 2017/6/11 EventBusActivity
//        activityHolder.addActivity("MVP in EventBus",EventBusActivity.class);

        // TODO: 2017/6/11 Fragments
//        activityHolder.addActivity("MVP in EventBus in Fragments", FragmentActivity.class);

    }

    @Override
    public void onItemClick(int position) {
        Class activity=activityHolder.getActivity(activityHolder.getNameList().get(position));
        if(activity != null){
            mView.intentActivity(activity);
        }

    }

    @Override
    public void loadDatas() {
        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.onGetDataList(activityHolder.getNameList());

            }
        },2000);

    }
}
