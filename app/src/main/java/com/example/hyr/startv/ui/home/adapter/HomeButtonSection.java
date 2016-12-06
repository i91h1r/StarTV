package com.example.hyr.startv.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;

/**
 * Description:
 * 作者：hyr on 2016/11/9 10:53
 * 邮箱：2045446584@qq.com
 */
public class HomeButtonSection extends StatelessSection {

    private HomeResult.DataBean dataBean;

    private Context mContext;


    public HomeButtonSection(Context mContext, HomeResult.DataBean dataBean) {
        super(R.layout.home_button_layout);

        this.dataBean = dataBean;
        this.mContext = mContext;
    }


    @Override public int getContentItemsTotal() {
        return dataBean.getSize();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ButtonViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeResult.DataBean.CommonBean commonBean = dataBean.getData().get(position);

        ((ButtonViewHolder) holder).tvYuedan.setText(commonBean.getTitle());

        Glide.with(mContext)
            .load(commonBean.getIcon()).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(((ButtonViewHolder) holder).ivYuedan);
    }


    private class ButtonViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvYuedan;
        private final ImageView ivYuedan;


        public ButtonViewHolder(View itemView) {
            super(itemView);

            tvYuedan = (TextView) itemView.findViewById(R.id.tv_yuedan);

            ivYuedan = (ImageView) itemView.findViewById(R.id.iv_yuedan);

        }
    }
}
