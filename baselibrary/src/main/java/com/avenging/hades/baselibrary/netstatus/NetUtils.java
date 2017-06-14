package com.avenging.hades.baselibrary.netstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/14.
 */

public class NetUtils {
    public static final int NET_TYPE_WIFI=0;
    public static final int NET_TYPE_CMNET=1;
    public static final int NET_TYPE_CMWAP=2;
    public static final int NET_TYPE_NONE=3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NET_TYPE_WIFI,NET_TYPE_CMNET,NET_TYPE_CMWAP,NET_TYPE_NONE})
    public @interface NetType{}

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager mgr= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info=mgr.getAllNetworkInfo();
        if(info!=null){
            for (int i = 0; i < info.length; i++) {
                if(info[i].getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo != null){
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(mMobileNetworkInfo != null){
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isMobileConnected(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(mMobileNetworkInfo != null){
                return mMobileNetworkInfo.isAvailable();
            }
        }

        return false;

    }

    public static int getConnectedType(Context context){
        if(context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    public static @NetUtils.NetType int getAPNType(Context context){
        ConnectivityManager connMgr= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return NET_TYPE_NONE;
        }
        int nType=networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            if(networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")){
                return NET_TYPE_CMNET;
            }else{
                return NET_TYPE_CMWAP;
            }

        }else if(nType==ConnectivityManager.TYPE_WIFI){
            return NET_TYPE_WIFI;
        }
        return NET_TYPE_NONE;

    }




}
