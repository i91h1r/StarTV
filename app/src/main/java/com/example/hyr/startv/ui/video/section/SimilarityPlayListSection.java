package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.listener.OnVideoMoreClickListener;
import com.example.hyr.startv.result.YueDanResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/15 11:56
 * 邮箱：2045446584@qq.com
 */
public class SimilarityPlayListSection extends StatelessSection {
    private Context mContext;
    private List<YueDanResult.DataBean.SimilarityPlayListBean> similarityPlayList;

    private OnItemClickListener onItemClickListener;

    private OnVideoMoreClickListener onVideoMoreClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setOnVideoMoreClickListener(OnVideoMoreClickListener onVideoMoreClickListener) {
        this.onVideoMoreClickListener = onVideoMoreClickListener;
    }


    public List<YueDanResult.DataBean.SimilarityPlayListBean> getSimilarityPlayList() {
        return similarityPlayList;
    }


    public void setSimilarityPlayList(List<YueDanResult.DataBean.SimilarityPlayListBean> similarityPlayList) {
        this.similarityPlayList = similarityPlayList;
    }


    public SimilarityPlayListSection(Context mContext, List<YueDanResult.DataBean.SimilarityPlayListBean> similarityPlayList) {
        super(R.layout.every_one_watch_header, R.layout.relate_play_item_layout);

        this.mContext = mContext;

        this.similarityPlayList = similarityPlayList;
    }


    @Override public int getContentItemsTotal() {
        return similarityPlayList.size() > 3 ? 3 :similarityPlayList.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(view1 -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view1, (Integer) view1.getTag());
            }
        });
        return new ItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);
        YueDanResult.DataBean.SimilarityPlayListBean similarityPlayListBean
            = similarityPlayList.get(position);
        ((ItemViewHolder) holder).tvMvTitle.setText(similarityPlayListBean.getTitle());

        ((ItemViewHolder) holder).tvPlayCount.setText(
            String.valueOf(similarityPlayListBean.getTotalView()));

        ((ItemViewHolder) holder).tvArtists.setText(
            similarityPlayListBean.getCreator().getNickName());

        Glide.with(mContext)
            .load(similarityPlayListBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((ItemViewHolder) holder).ivMv);
    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        view.findViewById(R.id.ll_more).setOnClickListener(view1 -> {
            if(onVideoMoreClickListener != null){
                onVideoMoreClickListener.onMoreClick(view1,SimilarityPlayListSection.this);
            }
        });
        return new HeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderViewHolder) holder).tvTitle.setText("相似悦单");
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


    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        TextView tvMore;


        public HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvMore = (TextView) itemView.findViewById(R.id.tv_more);
        }
    }
}
