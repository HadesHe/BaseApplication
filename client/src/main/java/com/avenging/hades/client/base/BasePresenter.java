package com.avenging.hades.client.base;

/**
 * Created by zhanghehe on 2017/6/19.
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    public void attachView(T view){
        this.mView=view;
    }

    public void detachView(){
        mView=null;
    }

    public boolean isAttached(){
        return mView != null;
    }

}
