package com.example.jiaxingmei0120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jiaxingmei0120.adapter.ShowAdapter;
import com.example.jiaxingmei0120.bean.ShowBean;
import com.example.jiaxingmei0120.show.presenter.ShowPresenterImp;
import com.example.jiaxingmei0120.show.view.IShowView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.OrientationHelper.VERTICAL;

public class ShowActivity extends AppCompatActivity implements IShowView {

    @BindView(R.id.show_recycler)
    RecyclerView showRecycler;
    @BindView(R.id.show_checkbox_all)
    CheckBox showCheckboxAll;
    @BindView(R.id.show_text_price)
    TextView showTextPrice;
    @BindView(R.id.show_text_clearing)
    TextView showTextClearing;
    private ShowPresenterImp showPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        showPresenterImp = new ShowPresenterImp(this);
        showPresenterImp.getShowPresenterData();
    }

    @Override
    public void getShowViewData(Object viewData) {
        ShowBean showBean = (ShowBean) viewData;
        showRecycler.setLayoutManager(new LinearLayoutManager(ShowActivity.this,VERTICAL,false));
        List<ShowBean.DataBean> list = showBean.getData();
        showRecycler.setAdapter(new ShowAdapter(ShowActivity.this,list));
    }
}
