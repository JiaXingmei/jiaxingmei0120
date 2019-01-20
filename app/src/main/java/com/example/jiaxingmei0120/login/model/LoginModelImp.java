package com.example.jiaxingmei0120.login.model;

import com.example.jiaxingmei0120.network.OKHttp;

public class LoginModelImp implements ILoginModel {
    @Override
    public void getLoginModelData(String url, final String acc, String pwd, final GetLoginModelInterface getLoginModelInterface) {
        OKHttp.doPost(url, acc, pwd, new OKHttp.GetPostInterface() {
            @Override
            public void postSuccess(String o) {
                getLoginModelInterface.success(o);
            }
        });
    }
}
