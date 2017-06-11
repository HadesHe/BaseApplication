package com.avenging.hades.baseapplication.login;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.avenging.hades.baseapplication.BasePresenter;
import com.avenging.hades.baseapplication.bean.User;

/**
 * Created by Hades on 2017/6/11.
 */

public class LoginPresenterIml extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {


    private User mUser;
    private Handler mHandler;

    @Override
    public void doLogin(String username, String password) {

        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            mView.showToast("用户名或密码为空");
        }else{
            if(mUser.isUserValid()){
                if(mUser.getName().equals(username)&&mUser.getPassword().equals(password)){

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mView.onLoginSuccess();
                        }
                    },5000);
                }else{
                    mView.showToast("用户名或密码错误");
                }
            }
        }

    }

    @Override
    public void initUser(User user) {
        this.mUser=user;
        mHandler=new Handler(Looper.getMainLooper());
    }
}
