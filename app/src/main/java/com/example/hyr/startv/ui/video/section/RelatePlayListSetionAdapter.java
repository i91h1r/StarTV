package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.listener.OnVideoMoreClickListener;
import com.example.hyr.startv.result.VideoDetailResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/14 14:07
 * 邮箱：2045446584@qq.com
 */
public class RelatePlayListSetionAdapter extends StatelessSection {

    private List<VideoDetailResult.DataBean.RelatedPlayListBean> relatedPlayList;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    private int type;

    private OnVideoMoreClickListener onVideoMoreClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setOnVideoMoreClickListener(OnVideoMoreClickListener onVideoMoreClickListener) {
        this.onVideoMoreClickListener = onVideoMoreClickListener;
    }


    public List<VideoDetailResult.DataBean.RelatedPlayListBean> getRelatedPlayList() {
        return relatedPlayList;
    }


    public void setRelatedPlayList(List<VideoDetailResult.DataBean.RelatedPlayListBean> relatedPlayList) {
        this.relatedPlayList = relatedPlayList;
    }


    public RelatePlayListSetionAdapter(Context mContext, List<VideoDetailResult.DataBean.RelatedPlayListBean> relatedPlayList) {
        super(R.layout.every_one_watch_header, R.layout.relate_play_item_layout);

        this.relatedPlayList = relatedPlayList;

        this.mContext = mContext;
    }


    @Override public int getContentItemsTotal() {
        return relatedPlayList.size() > 3 ? 3:relatedPlayList.size();
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

        VideoDetailResult.DataBean.RelatedPlayListBean relatedPlayListBean = relatedPlayList.get(
            position);

        Glide.with(mContext)
            .load(relatedPlayListBean.getPosterPic())
            .into(((ItemViewHolder) holder).ivMv);

        ((ItemViewHolder) holder).tvMvTitle.setText(relatedPlayListBean.getTitle());

        ((ItemViewHolder) holder).tvPlayCount.setText(
            String.valueOf(relatedPlayListBean.getTotalView()));

        VideoDetailResult.DataBean.RelatedPlayListBean.CreatorBean creator
            = relatedPlayListBean.getCreator();

        ((ItemViewHolder) holder).tvArtists.setText(creator.getNickName());

    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {

        view.findViewById(R.id.ll_more).setOnClickListener(view1 -> {
            if (onVideoMoreClickListener != null) {
                onVideoMoreClickListener.onMoreClick(view1, RelatePlayListSetionAdapter.this);
            }
        });
        return new HeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderViewHolder) holder).tvMore.setText("更多");

        ((HeaderViewHolder) holder).tvTitle.setText("收录这首MV的悦单");
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
