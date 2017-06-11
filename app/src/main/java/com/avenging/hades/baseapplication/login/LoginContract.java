package com.avenging.hades.baseapplication.login;

import com.avenging.hades.baseapplication.bean.User;

/**
 * Created by Hades on 2017/6/11.
 */

public interface LoginContract {

    interface LoginView{
        void showToast(String message);

        void onLoginSuccess();

    }

    interface LoginPresenter{
        void doLogin(String username, String password);

        void initUser(User user);
    }
}
