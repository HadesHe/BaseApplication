package com.avenging.hades.baseapplication.home;

import java.util.List;

/**
 * Created by Hades on 2017/6/8.
 */

public interface HomeContract {

    interface HomePresenter{

        void onItemClick(int position);

        void loadDatas();

    }

    interface HomeView{

        void onGetDataList(List<String> nameList);

        void intentActivity(Class activity);
    }


}
