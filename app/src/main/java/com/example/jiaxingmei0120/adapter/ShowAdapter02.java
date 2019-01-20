package com.example.jiaxingmei0120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jiaxingmei0120.R;
import com.example.jiaxingmei0120.bean.ShowBean;

import java.util.List;

class ShowAdapter02 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ShowBean.DataBean.ListBean> mlist;

    public ShowAdapter02(Context context, List<ShowBean.DataBean.ListBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shangjia_layout, viewGroup, false);
        ViewHolder01 holder = new ViewHolder01(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //绑定数据
        ((ViewHolder01)viewHolder).shangpin_text_name.setText(mlist.get(i).getTitle());
        ((ViewHolder01)viewHolder).shangpin_text_price.setText(mlist.get(i).getPrice()+"");
        //Glide.with(context).load("http://www.zhaoapi.cn/product/getCarts?uid=71").into(i);
        Glide.with(context).load(mlist.get(i).getImages().split("\\|")[0].replace("https", "http")).into(((ViewHolder01) viewHolder).shangpin_img);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ViewHolder01 extends RecyclerView.ViewHolder{

        private ImageView shangpin_img;
        private TextView shangpin_text_name;
        private TextView shangpin_text_price;

        public ViewHolder01(@NonNull View itemView) {
            super(itemView);
            shangpin_img = itemView.findViewById(R.id.shangpin_img);
            shangpin_text_name = itemView.findViewById(R.id.shangpin_text_name);
            shangpin_text_price = itemView.findViewById(R.id.shangpin_text_price);
        }
    }
}
