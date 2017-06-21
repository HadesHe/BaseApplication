package com.avenging.hades.baselibrary.netstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.avenging.hades.baselibrary.utils.TLog;

import java.util.ArrayList;


/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/14.
 */
public class NetStateReceiver extends BroadcastReceiver{
    public static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String CUSTOM_ANDROID_NET_CHANGE_ACTION="com.github.obsessive.library.net.conn.CONNECTIVITY_CHANGE";
    private static final String TAG = NetStateReceiver.class.getSimpleName();
    private static NetStateReceiver mBroadcastReceiver;
    private static boolean isNetAvailable;
    private static @NetUtils.NetType int mNetType;
    private static ArrayList<NetChangeObserver> mNetChangeObservers=new ArrayList<NetChangeObserver>();

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver=NetStateReceiver.this;
        if(intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)||intent.getAction()
                .equalsIgnoreCase(CUSTOM_ANDROID_NET_CHANGE_ACTION)){
            if(!NetUtils.isNetworkAvailable(context)){
                TLog.i(TAG,"<------network disconnected --->");
                isNetAvailable=false;
            }else{
                TLog.i(TAG,"<---  network connected ---->");
                isNetAvailable=true;
                mNetType=NetUtils.getAPNType(context);
            }
            notifyObserver();
        }
    }

    private void notifyObserver() {
        if(!mNetChangeObservers.isEmpty()){
            for (int i = 0; i < mNetChangeObservers.size(); i++) {
                NetChangeObserver observer=mNetChangeObservers.get(i);
                if(observer!=null){
                    if(isNetAvailable){
                        observer.onNetConnected(mNetType);
                    }else{
                        observer.onNetDisconnect();
                    }
                }
            }
        }
    }

    public static void registerNetworkStateReceiver(Context context){
        IntentFilter filter=new IntentFilter();
        filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(),filter);
    }

    private static BroadcastReceiver getReceiver() {
        if(null==mBroadcastReceiver){
            synchronized (NetStateReceiver.class){
                if(null==mBroadcastReceiver){
                    mBroadcastReceiver=new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    public static void checkNetworkState(Context context){
        Intent intent=new Intent();
        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        context.sendBroadcast(intent);
    }

    public static boolean isNetworkAvailabel(){
        return isNetAvailable;
    }

    public static @NetUtils.NetType int getAPNType(){
        return mNetType;
    }

    public static void registerObserver(NetChangeObserver observer){
        if(mNetChangeObservers==null){
            mNetChangeObservers=new ArrayList<NetChangeObserver>();
        }
        mNetChangeObservers.add(observer);
    }

    public static void removeRegisterObserver(NetChangeObserver observer){
        if(mNetChangeObservers!=null){
            if(mNetChangeObservers.contains(observer)){
                mNetChangeObservers.remove(observer);
            }
        }
    }
}
