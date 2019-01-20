package com.example.jiaxingmei0120.show.model;

import com.example.jiaxingmei0120.network.OKHttp;

public class ShowModelImp implements IShowModel {
    @Override
    public void getShowModelData(String url, Class clazz, final GetShowModelInterface getShowModelInterface) {
        OKHttp.doGet(url, clazz, new OKHttp.GetMessInterface() {
            @Override
            public void success(Object o) {
                getShowModelInterface.success(o);
            }
        });
    }
}
