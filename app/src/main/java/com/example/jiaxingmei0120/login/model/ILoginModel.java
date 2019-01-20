package com.example.jiaxingmei0120.login.model;

import com.example.jiaxingmei0120.network.OKHttp;

public interface ILoginModel {
    void getLoginModelData(String url, String acc, String pwd, GetLoginModelInterface getLoginModelInterface);

    public interface GetLoginModelInterface{
        void success(String s);
    }
}
