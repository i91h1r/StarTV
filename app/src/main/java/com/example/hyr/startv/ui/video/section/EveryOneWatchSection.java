package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.EveryoneWatchResult;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/11 16:45
 * 邮箱：2045446584@qq.com
 */
public class EveryOneWatchSection extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    implements View.OnClickListener {

    private OnItemClickListener onItemCliclListener;
    private List<EveryoneWatchResult.DataBean> data;
    LayoutInflater mLayoutInflater;
    private Context context;


    public void clearPlaying() {

        if (null != data) {
            for (EveryoneWatchResult.DataBean dataBean : data) {
                dataBean.setPlaying(false);
            }
        }
    }


    public void setOnItemCliclListener(OnItemClickListener onItemCliclListener) {
        this.onItemCliclListener = onItemCliclListener;
    }


    public EveryOneWatchSection(Context mContext, List<EveryoneWatchResult.DataBean> data) {
        this.data = data;

        this.context = mContext;

        mLayoutInflater = LayoutInflater.from(context);
    }


    public List<EveryoneWatchResult.DataBean> getData() {
        return data;
    }


    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.every_one_watch_item, parent, false);
        inflate.setOnClickListener(this);
        return new ItemViewHolder(inflate);

    }


    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        EveryoneWatchResult.DataBean dataBean = data.get(position);

        if (dataBean.isPlaying()) {

            ((ItemViewHolder) holder).ivPlaying.setVisibility(View.VISIBLE);

        } else {

            ((ItemViewHolder) holder).ivPlaying.setVisibility(View.GONE);
        }

        Glide.with(context)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .into(
                ((ItemViewHolder) holder).ivMv);

        ((ItemViewHolder) holder).tvMvTitle.setText(dataBean.getTitle());

        ((ItemViewHolder) holder).tvPlayCount.setText(String.valueOf(dataBean.getTotalView()));

        List<EveryoneWatchResult.DataBean.ArtistsBean> artists = dataBean.getArtists();

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


    @Override public int getItemCount() {
        return data != null ? data.size() :0;
    }


    @Override public void onClick(View view) {
        if (null != onItemCliclListener) {
            onItemCliclListener.onItemClick(view, ((Integer) view.getTag()));
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMv;
        private final TextView tvMvTitle;
        private final TextView tvArtists;
        private final TextView tvPlayCount;
        private final ImageView ivPlaying;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);
            tvMvTitle = (TextView) itemView.findViewById(R.id.tv_mv_title);
            tvArtists = (TextView) itemView.findViewById(R.id.tv_artists);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);
            ivPlaying = (ImageView) itemView.findViewById(R.id.iv_playing);

        }

    }

}
