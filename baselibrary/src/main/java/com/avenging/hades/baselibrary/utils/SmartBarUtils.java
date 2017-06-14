package com.avenging.hades.baselibrary.utils;

import android.support.v7.app.ActionBar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Hades on 2017/6/14.
 */
// TODO: 2017/6/14 smartbarutil
public class SmartBarUtils {

    public static  void setActionBarTabsShowAtBottom(ActionBar actionBar,
                                                     boolean showAtBottom){
        try {
            Method method=Class.forName("android.app.ActionBar").getMethod(
                    "setTabsShowAtBottom",new Class[]{boolean.class});

            try {
                method.invoke(actionBar,showAtBottom);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
