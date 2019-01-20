package com.example.jiaxingmei0120.show.model;

public interface IShowModel {
    void getShowModelData(String url, Class clazz, GetShowModelInterface getShowModelInterface);

    public interface GetShowModelInterface {
        void success(Object o);
    }
}
