package com.example.zy2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zy2.R;
import com.example.zy2.bean.CollBean;

import java.util.ArrayList;

public class CollAdapter extends RecyclerView.Adapter {
    private ArrayList<CollBean.DataBean.DatasBean> list;
    private Context context;

    public CollAdapter(ArrayList<CollBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rcy, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CollBean.DataBean.DatasBean datasBean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.tv.setText(datasBean.getChapterName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
