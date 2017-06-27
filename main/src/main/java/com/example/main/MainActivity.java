package com.example.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avenging.hades.baselibrary.adapter.ListViewDataAdapter;
import com.avenging.hades.baselibrary.adapter.ViewHolderBase;
import com.avenging.hades.baselibrary.adapter.ViewHolderCreator;
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
    private int[] mCheckedListItemColorResIds={
            R.color.navigation_checked_picture_text_color,
            R.color.navigation_checked_video_text_color,
            R.color.navigation_checked_music_text_color
    };
    private ListViewDataAdapter<NavigationEntity> mNavListAdapter;
    private int mCurrentMenuCheckedPos=0;

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
        if()

        mNavListAdapter=new ListViewDataAdapter<NavigationEntity>(new ViewHolderCreator<NavigationEntity>() {
            @Override
            public ViewHolderBase<NavigationEntity> createViewHolder(int position) {
                return new ViewHolderBase<NavigationEntity>() {
                    public TextView tvNavItemName;
                    public ImageView ivNavItemIcon;

                    @Override
                    public View createView(LayoutInflater layoutInflater) {
                        View convertView=layoutInflater.inflate(R.layout.list_item_navigation,null);
                        ivNavItemIcon=(ImageView)convertView.findViewById(R.id.ivNavItemIcon);
                        tvNavItemName=(TextView)convertView.findViewById(R.id.tvNavItemName);
                        return convertView;
                    }

                    @Override
                    public void showData(int position, NavigationEntity itemData) {
                        ivNavItemIcon.setImageResource(itemData.getIconResId());
                        tvNavItemName.setText(itemData.getName());

                        if(mCurrentMenuCheckedPos==position){
                            tvNavItemName.setTextColor(getResources().getColor(mCheckedListItemColorResIds[i]));
                        }else{
                            tvNavItemName.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                };
            }
        });

        lvMainNavilist.setAdapter(mNavListAdapter);
        mNavListAdapter.getDataList().add(navigationList);
        mNavListAdapter.notifyDataSetChanged();
        setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getName());

        lvMainNavilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCurrentMenuCheckedPos=position;
                mNavListAdapter.notifyDataSetChanged();
                dlMain.closeDrawer(Gravity.LEFT);
                vpagerMainContainer.setCurrentItem(mCurrentMenuCheckedPos,false);
            }
        });

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
