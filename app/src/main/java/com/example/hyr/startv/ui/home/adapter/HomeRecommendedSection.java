package com.example.hyr.startv.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.HomeMoreClickListener;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/8 18:05
 * 邮箱：2045446584@qq.com
 */
public class HomeRecommendedSection extends StatelessSection implements View.OnClickListener {

    private HomeResult.DataBean recommendedLists;

    private String title;

    private String enTitle;

    private String more;

    private Context mContext;

    private int size;

    private OnItemClickListener onItemCliclListener;

    private HomeMoreClickListener homeMoreClickListener;

    private String statisticMark;

    private String url;


    public HomeRecommendedSection(Context mContext, String more, int size, String title, String enTitle, HomeResult.DataBean recommendedLists, String statisticMark, String url) {
        super(R.layout.header, R.layout.item);

        this.recommendedLists = recommendedLists;

        this.title = title;

        this.enTitle = enTitle;

        this.more = more;

        this.mContext = mContext;

        this.size = size;

        this.statisticMark = statisticMark;

        this.url = url;
    }


    public void setHomeMoreClickListener(HomeMoreClickListener homeMoreClickListener) {
        this.homeMoreClickListener = homeMoreClickListener;
    }


    @Override public int getContentItemsTotal() {
        return size;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        ((ItemViewHolder) holder).itemView.setTag(position);

        Glide.with(mContext)
            .load(recommendedLists.getData().get(position).getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .into(
                ((ItemViewHolder) holder).ivMv);

        ((ItemViewHolder) holder).tvMvTitle.setText(
            recommendedLists.getData().get(position).getTitle());

        ((ItemViewHolder) holder).tvPlayCount.setText(
            String.valueOf(recommendedLists.getData().get(position).getTotalView()));

        List<HomeResult.DataBean.CommonBean.ArtistsBean> artists = recommendedLists.getData()
            .get(position)
            .getArtists();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        ((ItemViewHolder) holder).tvArtists.setText(stringBuffer);

    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {

        view.findViewById(R.id.more).setOnClickListener(view1 -> {
            if (homeMoreClickListener != null) {
                homeMoreClickListener.onMoreClick(statisticMark,url,title);
            }
        });
        return new HeaderViewHolder(view);

    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderViewHolder) holder).tvTitle.setText(title);
        ((HeaderViewHolder) holder).tvEnTitle.setText(enTitle);
        ((HeaderViewHolder) holder).tvMore.setText(more);
    }


    @Override public void onClick(View view) {
        if (onItemCliclListener != null) {

            onItemCliclListener.onItemClick(view, ((Integer) view.getTag()));
        }
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        TextView tvEnTitle;

        TextView tvMore;


        public HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvEnTitle = (TextView) itemView.findViewById(R.id.tv_en_title);
            tvMore = (TextView) itemView.findViewById(R.id.tv_more);
        }
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMv;
        private final TextView tvMvTitle;
        private final TextView tvArtists;
        private final TextView tvPlayCount;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);
            tvMvTitle = (TextView) itemView.findViewById(R.id.tv_mv_title);
            tvArtists = (TextView) itemView.findViewById(R.id.tv_artists);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);

        }

    }


    public void setOnItemCliclListener(OnItemClickListener onItemCliclListener) {
        this.onItemCliclListener = onItemCliclListener;
    }
}
