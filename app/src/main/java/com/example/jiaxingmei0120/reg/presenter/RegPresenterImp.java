package com.example.jiaxingmei0120.reg.presenter;

import com.example.jiaxingmei0120.RegActivity;
import com.example.jiaxingmei0120.api.Api;
import com.example.jiaxingmei0120.reg.model.IRegModel;
import com.example.jiaxingmei0120.reg.model.RegModelImp;

public class RegPresenterImp implements IRegPresenter {
    //MainActivity mainActivity;
    RegActivity regActivity;
    private final RegModelImp loginModelImp;

    public RegPresenterImp(RegActivity regActivity) {
        this.regActivity = regActivity;
        loginModelImp = new RegModelImp();
    }

    @Override
    public void getRegPresenterData(String acc, String pwd) {
        loginModelImp.getRegModelData(Api.LOGIN, acc, pwd, new IRegModel.GetRegModelInterface() {
            @Override
            public void success(String s) {
                regActivity.getRegViewData(s);
            }
        });
    }
}
