package com.avenging.hades.baselibrary.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/14.
 */

public class BaseAppManager {

    private static final String TAG=BaseAppManager.class.getSimpleName();
    private static BaseAppManager instance;

    private static List<Activity> mActivities=new LinkedList<Activity>();

    private BaseAppManager(){

    }

    public static BaseAppManager getInstance(){
        if(null==instance){
            synchronized (BaseAppManager.class){
                if (null==instance){
                    instance=new BaseAppManager();
                }
            }
        }

        return instance;
    }

    public int size(){
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity(){
        return size()>0?mActivities.get(size()-1):null;
    }

    public synchronized void addActivity(Activity activity){
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity){
        if(mActivities.contains(activity)){
            mActivities.remove(activity);
        }
    }

    public synchronized void clear(){
        for (int i = mActivities.size() - 1; i >= 0; i--) {
            Activity activity=mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i=mActivities.size();
        }
    }

    public synchronized void clearToTop(){
        for (int i = mActivities.size() - 2; i >= 0; i--) {
            Activity activity=mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i=mActivities.size();
        }
    }




}
