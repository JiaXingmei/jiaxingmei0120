package com.example.jiaxingmei0120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiaxingmei0120.bean.LoginBean;
import com.example.jiaxingmei0120.bean.RegBean;
import com.example.jiaxingmei0120.reg.presenter.RegPresenterImp;
import com.example.jiaxingmei0120.reg.view.IRegView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements IRegView {

    @BindView(R.id.reg_edit_acc)
    EditText regEditAcc;
    @BindView(R.id.reg_edit_pwd)
    EditText regEditPwd;
    @BindView(R.id.reg_edit_pwd_ok)
    EditText regEditPwdOk;
    @BindView(R.id.login_btn_reg)
    Button loginBtnReg;
    private RegPresenterImp regPresenterImp;
    private String acc;
    private String pwd;
    private String pwd_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        regPresenterImp = new RegPresenterImp(this);
    }

    @Override
    public void getRegViewData(final String viewData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RegBean loginBean = gson.fromJson(viewData, RegBean.class);
                String message = loginBean.getMessage();
                if (message.equals("注册成功")){
                    startActivity(new Intent(RegActivity.this,MainActivity.class));
                }
            }
        });
    }

    @OnClick(R.id.login_btn_reg)
    public void onViewClicked() {
        //非空
        acc = regEditAcc.getText().toString();
        pwd = regEditPwd.getText().toString();
        pwd_ok = regEditPwdOk.getText().toString();
        if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd_ok)){
            Toast.makeText(RegActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
        } else {
            regPresenterImp.getRegPresenterData(acc,pwd);
            startActivity(new Intent(RegActivity.this,MainActivity.class));
        }
    }
}
