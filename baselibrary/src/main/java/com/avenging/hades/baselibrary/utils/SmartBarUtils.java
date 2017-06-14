package com.avenging.hades.baselibrary.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
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

    public static void setActionBarViewCollapsable(ActionBar actionBar,
                                                   boolean collapsable){
        try {
            Method method=Class.forName("android.app.ActionBar").getMethod(
                    "setActionBarViewCollapsable",
                    new Class[]{boolean.class});
            try {
                method.invoke(actionBar,collapsable);
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

    public static void setActionModeHeaderHidden(ActionBar actionbar,
                                                 boolean hidden) {
        try {
            Method method = Class.forName("android.app.ActionBar").getMethod(
                    "setActionModeHeaderHidden", new Class[]{boolean.class});
            try {
                method.invoke(actionbar, hidden);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setBackIcon(ActionBar actionbar, Drawable backIcon) {
        try {
            Method method = Class.forName("android.app.ActionBar").getMethod(
                    "setBackButtonDrawable", new Class[]{Drawable.class});
            try {
                method.invoke(actionbar, backIcon);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
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

    public static void hide(View decorView){
        if(!hasSmartBar()){
            return ;
        }

        Class[] arrayOfClass=new Class[1];
        arrayOfClass[0]=Integer.TYPE;
        try {
            Method localMethod=View.class.getMethod("setSystemUiVisibility", arrayOfClass);
            Field localFiled=View.class.getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION");
            Object[] arrayOfObject=new Object[1];
            arrayOfObject[0]=localFiled.get(null);
            localMethod.invoke(decorView,arrayOfObject);
            return;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void hide(Context context, Window window){
        hide(context,window,0);
    }

    private static int getStatusBarHeight(Context context){
        int result=0;
        int resourceId=context.getResources().getIdentifier(
                "status_bar_height","dimen","android"
        );
        if(resourceId>0){
            result=context.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }

    public static void hide(Context context,Window window,int smartBarHeight){
        if(!hasSmartBar()){
            return;
        }
        if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            return;
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        int statusBarHeight=getStatusBarHeight(context);
        window.getDecorView()
                .setPadding(0,statusBarHeight,0,-smartBarHeight);
    }

    public static boolean hasSmartBar(){
        try {
            Method method=Class.forName("android.os.Build").getMethod("hasSmartBar");
            return ((Boolean) method.invoke(null)).booleanValue();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(Build.DEVICE.equals("mx2")){
            return true;
        }else if(Build.DEVICE.equals("mx")||Build.DEVICE.equals("m9")){
            return false;
        }
        return false;
    }

}
