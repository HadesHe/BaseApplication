package com.avenging.hades.baseapplication;

import android.graphics.YuvImage;

/**
 * Created by Hades on 2017/6/8.
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public final void attachView(T mView){
        this.mView=mView;
    }

    public final void detachView(){
        mView=null;
    }

    public boolean isAttached(){
        return mView!=null;
    }




}
