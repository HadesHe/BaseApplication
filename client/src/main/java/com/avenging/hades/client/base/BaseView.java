package com.avenging.hades.client.base;

/**
 * Created by zhanghehe on 2017/6/19.
 */

public interface BaseView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg);

    void showException(String msg);

    void showNetError();
}
