package com.example.jiaxingmei0120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jiaxingmei0120.R;
import com.example.jiaxingmei0120.ShowActivity;
import com.example.jiaxingmei0120.bean.ShowBean;

import java.util.List;

import static android.support.v7.widget.OrientationHelper.VERTICAL;

public class ShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShowBean.DataBean> list;

    public ShowAdapter(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.showrecycler_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        //绑定数据
        ((ViewHolder)viewHolder).show_text_shangjia.setText(list.get(i).getSellerName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, VERTICAL, false);
        ((ViewHolder) viewHolder).show_recycler_shangpin.setLayoutManager(linearLayoutManager);
        List<ShowBean.DataBean.ListBean> mlist = this.list.get(i).getList();
        ShowAdapter02 showAdapter02 = new ShowAdapter02(context, mlist);
        ((ViewHolder) viewHolder).show_recycler_shangpin.setAdapter(showAdapter02);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView show_text_shangjia;
        private RecyclerView show_recycler_shangpin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_text_shangjia = itemView.findViewById(R.id.show_text_shangjia);
            show_recycler_shangpin = itemView.findViewById(R.id.show_recycler_shangpin);
        }
    }
}
