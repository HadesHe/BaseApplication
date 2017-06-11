package com.avenging.hades.baseapplication.bean;

import android.text.TextUtils;

/**
 * Created by Hades on 2017/6/11.
 */

public class User {
    String name;

    String password;

    public User(String name,String password){
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUserValid(){
        return !TextUtils.isEmpty(name) &&!TextUtils.isEmpty(password);
    }
}
