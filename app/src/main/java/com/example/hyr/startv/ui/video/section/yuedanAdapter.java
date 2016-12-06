package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.YueDanResult;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/15 11:23
 * 邮箱：2045446584@qq.com
 */
public class yuedanAdapter extends RecyclerView.Adapter<yuedanAdapter.ItemVideHolder> {

    private YueDanResult.DataBean dataBean;
    private final LayoutInflater layoutInflater;

    private Context mContext;


    public void clearPlay() {
        List<YueDanResult.DataBean.PlayListVideosBean> playListVideos
            = dataBean.getPlayListVideos();

        if (dataBean != null && playListVideos.size() > 0) {
            for (YueDanResult.DataBean.PlayListVideosBean playListVideosBean : playListVideos) {
                playListVideosBean.setPlaying(false);
            }
        }
    }


    private OnItemClickListener onItemClickListener;


    public yuedanAdapter(Context mContext, YueDanResult.DataBean data) {
        this.dataBean = data;

        layoutInflater = LayoutInflater.from(mContext);

        this.mContext = mContext;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ItemVideHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = layoutInflater.inflate(R.layout.every_one_watch_item, parent, false);

        inflate.setOnClickListener(view -> {
            if (null != onItemClickListener) {
                onItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });

        return new ItemVideHolder(inflate);
    }


    @Override public void onBindViewHolder(ItemVideHolder holder, int position) {

        holder.itemView.setTag(position);

        YueDanResult.DataBean.PlayListVideosBean playListVideosBean = dataBean.getPlayListVideos()
            .get(position);

        if (playListVideosBean.isPlaying()) {
            holder.ivPlaying.setVisibility(View.VISIBLE);
        } else {
            holder.ivPlaying.setVisibility(View.GONE);
        }

        Glide.with(mContext).load(playListVideosBean.getPosterPic()).placeholder(R.drawable.ad_video_default).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(holder.ivMv);

        holder.playCount.setText(String.valueOf(playListVideosBean.getTotalView()));

        holder.tvTitle.setText(playListVideosBean.getTitle());

        List<YueDanResult.DataBean.PlayListVideosBean.ArtistsBean> artists
            = playListVideosBean.getArtists();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        holder.tv_artists.setText(stringBuffer);
    }


    @Override public int getItemCount() {
        return dataBean.getPlayListVideos().size();
    }


    public class ItemVideHolder extends RecyclerView.ViewHolder {

        private final ImageView ivMv;
        private final ImageView ivPlaying;
        private final TextView tvTitle;
        private final TextView tv_artists;
        private final TextView playCount;


        public ItemVideHolder(View itemView) {
            super(itemView);

            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);

            ivPlaying = (ImageView) itemView.findViewById(R.id.iv_playing);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_mv_title);

            tv_artists = (TextView) itemView.findViewById(R.id.tv_artists);

            playCount = (TextView) itemView.findViewById(R.id.tv_play_count);
        }
    }
}
