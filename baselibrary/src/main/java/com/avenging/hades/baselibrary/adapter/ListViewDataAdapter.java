package com.avenging.hades.baselibrary.adapter;

import java.util.ArrayList;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/23.
 */

public abstract class ListViewDataAdapter<ItemDataType> extends ListViewDataAdapterBase<ItemDataType>{
    protected ArrayList<ItemDataType> mItemDataList=new ArrayList<>();

    public ListViewDataAdapter(){}

    public ListViewDataAdapter(ViewHolderCreator<ItemDataType> viewHolderCreator){
        super(viewHolderCreator);
    }

    public ArrayList<ItemDataType> getDataList(){
        return mItemDataList;
    }

    @Override
    public int getCount() {
        return mItemDataList.size();
    }

    @Override
    public ItemDataType getItem(int position) {
        if(mItemDataList.size()<=position||position<0){
            return null;
        }
        return mItemDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
