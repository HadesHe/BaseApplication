package com.example.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.avenging.hades.baselibrary.adapter.ListViewDataAdapter;
import com.avenging.hades.baselibrary.base.BaseAppcompatActitvity;
import com.avenging.hades.baselibrary.bean.NavigationEntity;
import com.avenging.hades.baselibrary.widgets.XViewPager;


public class MainActivity extends BaseAppcompatActitvity implements MainContract.MainView{

    private DrawerLayout dlMain;
    private ListView lvMainNavilist;
    private XViewPager vpagerMainContainer;
    private MainPresenterImp mMainPresenter;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void initViewAndEvents() {
        dlMain=(DrawerLayout)findViewById(R.id.dlMain);
        lvMainNavilist=(ListView)findViewById(R.id.lvMainNavilist);
        vpagerMainContainer=(XViewPager)findViewById(R.id.vpagerMainContainer);
        mToolbar=(Toolbar)findViewById(R.id.tbBase);
        mMainPresenter=new MainPresenterImp();
        mMainPresenter.attachView(this);

        mActionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,dlMain,
                mToolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(null!=mNavListAdapter){
                    setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getName());
                }
            }
        };

        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        dlMain.addDrawerListener(mActionBarDrawerToggle);

        mNavListAdapter=new ListViewDataAdapter<NavigationEntity>

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void onNetWorkConnected(int type) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void getBundleExtras(Bundle extra) {

    }

    @Override
    protected int getOverridePendingTransition() {
        return TRANSITION_MODE_NULL;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }
}
