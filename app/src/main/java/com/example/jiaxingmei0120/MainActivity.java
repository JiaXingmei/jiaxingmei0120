package com.example.jiaxingmei0120;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaxingmei0120.bean.LoginBean;
import com.example.jiaxingmei0120.login.presenter.LoginPresenterImp;
import com.example.jiaxingmei0120.login.view.ILoginView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.login_edit_acc)
    EditText loginEditAcc;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_checkbox_remember)
    CheckBox loginCheckboxRemember;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_btn_reg)
    Button loginBtnReg;
    private LoginPresenterImp loginPresenterImp;
    private String acc;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenterImp = new LoginPresenterImp(this);
        //记住密码
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag", false);
        loginCheckboxRemember.setChecked(flag);
        if (flag) {
            String phone = sharedPreferences.getString("phone", "");
            String pwd1 = sharedPreferences.getString("pwd", "");
            loginEditAcc.setText(phone);
            loginEditPwd.setText(pwd1);
        }
    }

    @Override
    public void getLoginViewData(final String viewData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(viewData, LoginBean.class);
                String message = loginBean.getMessage();
                if (message.equals("登录成功")){
                    //记住密码---传值修改
                    SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    if (loginCheckboxRemember.isChecked()) {
                        edit.putBoolean("flag", true);
                        edit.putString("phone", acc);
                        edit.putString("pwd", pwd);
                    } else {
                        edit.putBoolean("flag", false);
                    }
                    edit.commit();
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                }
            }
        });
    }

    @OnClick({R.id.login_checkbox_remember, R.id.login_btn_login, R.id.login_btn_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_checkbox_remember:
                break;
            case R.id.login_btn_login:
                //非空
                acc = loginEditAcc.getText().toString();
                pwd = loginEditPwd.getText().toString();
                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenterImp.getLoginPresenterData(acc,pwd);
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                }
                break;
            case R.id.login_btn_reg:
                startActivity(new Intent(MainActivity.this,RegActivity.class));
                break;
        }
    }
}
