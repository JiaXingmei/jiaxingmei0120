package com.example.jiaxingmei0120.reg.model;

public interface IRegModel {
    void getRegModelData(String url, String acc, String pwd, GetRegModelInterface getRegModelInterface);

    public interface GetRegModelInterface{
        void success(String s);
    }
}
