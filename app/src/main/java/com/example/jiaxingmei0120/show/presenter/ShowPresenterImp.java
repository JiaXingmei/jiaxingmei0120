package com.example.jiaxingmei0120.show.presenter;

import com.example.jiaxingmei0120.MainActivity;
import com.example.jiaxingmei0120.ShowActivity;
import com.example.jiaxingmei0120.api.Api;
import com.example.jiaxingmei0120.bean.ShowBean;
import com.example.jiaxingmei0120.show.model.IShowModel;
import com.example.jiaxingmei0120.show.model.ShowModelImp;

public class ShowPresenterImp implements IShowPresenter {
    ShowActivity showActivity;
    private final ShowModelImp showModelImp;

    public ShowPresenterImp(ShowActivity showActivity) {
        this.showActivity = showActivity;
        showModelImp = new ShowModelImp();
    }

    @Override
    public void getShowPresenterData() {
        showModelImp.getShowModelData(Api.SHOW, ShowBean.class, new IShowModel.GetShowModelInterface() {
            @Override
            public void success(Object o) {
                showActivity.getShowViewData(o);
            }
        });
    }
}
