package com.avenging.hades.baselibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/23.
 */

public abstract class ViewHolderBase<ItemDataType> {
    protected int mLastPosition;
    protected int mPosition=-1;
    protected View mCurrentView;

    public abstract View createView(LayoutInflater layoutInflater);

    public abstract void showData(int position,ItemDataType itemData);

    public void setItemData(int position,View view){
        mLastPosition=mPosition;
        mPosition=position;
        mCurrentView=view;
    }

    public boolean stillHoldLastItemData(){
        return mLastPosition==mPosition;
    }


}
