package com.example.jiaxingmei0120.login.presenter;

import com.example.jiaxingmei0120.MainActivity;
import com.example.jiaxingmei0120.api.Api;
import com.example.jiaxingmei0120.login.model.ILoginModel;
import com.example.jiaxingmei0120.login.model.LoginModelImp;

public class LoginPresenterImp implements ILoginPresenter {
    MainActivity mainActivity;
    private final LoginModelImp loginModelImp;

    public LoginPresenterImp(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        loginModelImp = new LoginModelImp();
    }

    @Override
    public void getLoginPresenterData(String acc, String pwd) {
        loginModelImp.getLoginModelData(Api.REG, acc, pwd, new ILoginModel.GetLoginModelInterface() {
            @Override
            public void success(String s) {
                mainActivity.getLoginViewData(s);
            }
        });
    }
}
