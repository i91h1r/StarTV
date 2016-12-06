package com.example.hyr.startv.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.hyr.startv.R;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.widget.BannerView;
import com.example.hyr.startv.widget.sectioned.StatelessSection;

/**
 * Description:
 * 作者：hyr on 2016/11/9 11:58
 * 邮箱：2045446584@qq.com
 */
public class HomeBannerSection extends StatelessSection {

    private Context mContext;

    private HomeResult.DataBean dataBean;


    public HomeBannerSection(HomeResult.DataBean dataBean, Context mContext) {
        super(R.layout.home_banner_layout);

        this.mContext = mContext;

        this.dataBean = dataBean;
    }


    @Override public int getContentItemsTotal() {
        return 1;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new BannnerViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((BannnerViewHolder) holder).bannerView.delayTime(5).build(dataBean.getData());
    }


    static class BannnerViewHolder extends RecyclerView.ViewHolder {

        private final BannerView bannerView;


        public BannnerViewHolder(View itemView) {
            super(itemView);

            bannerView = (BannerView) itemView.findViewById(R.id.banner);

        }
    }
}
