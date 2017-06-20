package com.avenging.hades.client.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.avenging.hades.baselibrary.base.BaseAppcompatActitvity;
import com.avenging.hades.client.R;
import com.avenging.hades.client.SimplifyReaderApplication;

/**
 * Created by Hades on 2017/6/12.
 */

public abstract class BaseActivity extends BaseAppcompatActitvity implements BaseView{
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isApplyKitKatTransluency()){

            setSystemBarTintDrawable(getResources().getDrawable(com.avenging.hades.baselibrary.R.drawable.sr_primary));
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar=(Toolbar)findViewById(R.id.common_toolbar);
        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected SimplifyReaderApplication getBaseApplication(){
        return (SimplifyReaderApplication)getApplication();
    }

    protected abstract boolean isApplyKitKatTransluency();

    @Override
    public void showError(String msg) {
        toggleShowError(true,msg,null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true,msg,null);
    }

    @Override
    public void showNetError() {
        toggleNetError(true,null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true,msg);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false);
    }
}
