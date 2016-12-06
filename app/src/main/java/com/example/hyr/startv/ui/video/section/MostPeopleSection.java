package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 * 作者：hyr on 2016/11/15 12:09
 * 邮箱：2045446584@qq.com
 */
public class MostPeopleSection extends StatelessSection {
    private Context mContext;
    private List<YueDanResult.DataBean.MostPeopleVideosBean> mostPeopleVideos;

    private OnItemClickListener onItemClickListener;

    private OnVideoMoreClickListener onVideoMoreClickListener;


    public MostPeopleSection(Context mContext, List<YueDanResult.DataBean.MostPeopleVideosBean> mostPeopleVideos) {
        super(R.layout.every_one_watch_header, R.layout.every_one_watch_item);

        this.mContext = mContext;
        this.mostPeopleVideos = mostPeopleVideos;
    }


    public void setOnVideoMoreClickListener(OnVideoMoreClickListener onVideoMoreClickListener) {
        this.onVideoMoreClickListener = onVideoMoreClickListener;
    }


    public List<YueDanResult.DataBean.MostPeopleVideosBean> getMostPeopleVideos() {
        return mostPeopleVideos;
    }


    public void setMostPeopleVideos(List<YueDanResult.DataBean.MostPeopleVideosBean> mostPeopleVideos) {
        this.mostPeopleVideos = mostPeopleVideos;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override public int getContentItemsTotal() {
        return mostPeopleVideos.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(view1 -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view1, (Integer) view1.getTag());
            }
        });
        return new ArtistOtherVideoSection.ItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        YueDanResult.DataBean.MostPeopleVideosBean mostPeopleVideosBean = mostPeopleVideos.get(
            position);

        ((ArtistOtherVideoSection.ItemViewHolder) holder).tvMvTitle.setText(
            mostPeopleVideosBean.getTitle());

        ((ArtistOtherVideoSection.ItemViewHolder) holder).tvPlayCount.setText(
            String.valueOf(mostPeopleVideosBean.getTotalView()));

        List<YueDanResult.DataBean.MostPeopleVideosBean.ArtistsBean> artists
            = mostPeopleVideosBean.getArtists();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        ((ArtistOtherVideoSection.ItemViewHolder) holder).tvArtists.setText(stringBuffer);

        Glide.with(mContext)
            .load(mostPeopleVideosBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((ArtistOtherVideoSection.ItemViewHolder) holder).ivMv);

    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {

        view.findViewById(R.id.ll_more).setOnClickListener(view1 -> {
            if (onVideoMoreClickListener != null) {
                onVideoMoreClickListener.onMoreClick(view1, MostPeopleSection.this);
            }
        });

        return new ArtistOtherVideoSection.HeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((ArtistOtherVideoSection.HeaderViewHolder) holder).tvTitle.setText("大部分人看了");
    }

}
