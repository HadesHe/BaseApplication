package com.avenging.hades.baseapplication.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.avenging.hades.baseapplication.R;
import com.avenging.hades.baseapplication.bean.User;
import com.avenging.hades.baseapplication.home.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, View.OnClickListener {

    private EditText etLoginPassword;
    private EditText etLoginUsername;
    private Button btnLoginLogin;
    private Button btnLoginClear;
    private ProgressBar pbLoginLogin;
    private LoginPresenterIml mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginPresenter.attachView(this);
        mLoginPresenter.initUser(new User("MVP","MVP"));
        intiView();
    }

    private void intiView() {
        etLoginUsername=(EditText)findViewById(R.id.etLoginUsername);
        etLoginPassword=(EditText)findViewById(R.id.etLoginPassword);
        btnLoginLogin=(Button)findViewById(R.id.btnLoginLogin);
        btnLoginClear=(Button)findViewById(R.id.btnLoginClear);
        pbLoginLogin=(ProgressBar)findViewById(R.id.pbLoginLogin);

        btnLoginLogin.setOnClickListener(this);
        btnLoginClear.setOnClickListener(this);

        setPbLoginVisible(View.INVISIBLE);

    }

    public void setPbLoginVisible(int visible) {
        pbLoginLogin.setVisibility(visible);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginLogin:
                setPbLoginVisible(View.VISIBLE);
                btnLoginLogin.setEnabled(false);
                btnLoginClear.setEnabled(false);
                mLoginPresenter.doLogin(etLoginUsername.getText().toString(),etLoginPassword.getText().toString());
                break;
            case R.id.btnLoginClear:
                clearText();
                break;

        }

    }

    private void clearText() {
        etLoginUsername.setText("");
        etLoginPassword.setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess() {
        setPbLoginVisible(View.INVISIBLE);
        btnLoginLogin.setEnabled(true);
        btnLoginClear.setEnabled(true);
        showToast("登录成功");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }
}
