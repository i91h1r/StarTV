package com.example.hyr.startv.ui.artist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 16:16
 * 邮箱：2045446584@qq.com
 */
public class MvSectionAdapter extends StatelessSection {
    private Context mContext;

    private List<ArtistInfoResult.DataBean.ArtistMVsBean.ListBean> artistMVs;

    private String headerTitleName;

    private OnItemClickListener onItemClickListener ;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public MvSectionAdapter(Context mContext, String name, List<ArtistInfoResult.DataBean.ArtistMVsBean.ListBean> artistMVs) {
        super(R.layout.artist_info_header_layout, R.layout.artist_info_mv_layout);
        this.headerTitleName = name;
        this.mContext = mContext;

        this.artistMVs = artistMVs;
    }


    @Override public int getContentItemsTotal() {
        return artistMVs.size() > 4 ? 4 :artistMVs.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(view1 -> {
            if(onItemClickListener != null ){
                onItemClickListener.onItemClick(view1,(Integer) view1.getTag());
            }
        });
        return new MvItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        ArtistInfoResult.DataBean.ArtistMVsBean.ListBean listBean = artistMVs.get(position);

        Glide.with(mContext)
            .load(listBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((MvItemViewHolder) holder).ivMv);

        ((MvItemViewHolder) holder).tvTitle.setText(listBean.getTitle());

        ((MvItemViewHolder) holder).tvRegdate.setText(listBean.getRegdate());

        List<ArtistInfoResult.DataBean.ArtistMVsBean.ListBean.ArtistsBean> artists
            = listBean.getArtists();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append(",");
            }
        }
        ((MvItemViewHolder) holder).tvArtist.setText(stringBuffer);
    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

        ((HeaderViewHolder) holder).tvTitle.setText(headerTitleName);
    }


    public static class MvItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final ImageView ivMv;
        private final TextView tvArtist;
        private final TextView tvRegdate;


        public MvItemViewHolder(View itemView) {
            super(itemView);
            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_mv_title);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artists);
            tvRegdate = (TextView) itemView.findViewById(R.id.tv_regdate);
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
