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
 * 作者：hyr on 2016/11/14 14:34
 * 邮箱：2045446584@qq.com
 */
public class RelatedVideoSectionAdapter extends StatelessSection {
    private List<VideoDetailResult.DataBean.RelatedVideosBean> relatedVideos;

    private Context mContext;

    private OnItemClickListener onItemClickListener;

    private OnVideoMoreClickListener onVideoMoreClickListener ;

    public RelatedVideoSectionAdapter(Context mContext, List<VideoDetailResult.DataBean.RelatedVideosBean> relatedVideos) {
        super(R.layout.every_one_watch_header, R.layout.every_one_watch_item);

        this.mContext = mContext;

        this.relatedVideos = relatedVideos;
    }


    public void setOnVideoMoreClickListener(OnVideoMoreClickListener onVideoMoreClickListener) {
        this.onVideoMoreClickListener = onVideoMoreClickListener;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override public int getContentItemsTotal() {
        return relatedVideos != null ? relatedVideos.size() :0;
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

        VideoDetailResult.DataBean.RelatedVideosBean relatedVideosBean = relatedVideos.get(
            position);

        Glide.with(mContext)
            .load(relatedVideosBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .into(
                ((ItemViewHolder) holder).ivMv);

        List<VideoDetailResult.DataBean.RelatedVideosBean.ArtistsBean> artists
            = relatedVideosBean.getArtists();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        ((ItemViewHolder) holder).tvArtists.setText(stringBuffer);

        ((ItemViewHolder) holder).tvMvTitle.setText(relatedVideosBean.getTitle());

        ((ItemViewHolder) holder).tvPlayCount.setText(
            String.valueOf(relatedVideosBean.getTotalView()));

    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        view.findViewById(R.id.ll_more).setOnClickListener(view1 -> {

                onVideoMoreClickListener.onMoreClick(view1,RelatedVideoSectionAdapter.this);

        });

        return new HeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((HeaderViewHolder) holder).tvTitle.setText(mContext.getResources().getString(R.string.guess_you_like));
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
