package com.example.jiaxingmei0120.reg.model;

import com.example.jiaxingmei0120.network.OKHttp;

public class RegModelImp implements IRegModel {
    @Override
    public void getRegModelData(String url, final String acc, String pwd, final GetRegModelInterface getRegModelInterface) {
        OKHttp.doPost(url, acc, pwd, new OKHttp.GetPostInterface() {
            @Override
            public void postSuccess(String o) {
                getRegModelInterface.success(o);
            }
        });
    }
}
