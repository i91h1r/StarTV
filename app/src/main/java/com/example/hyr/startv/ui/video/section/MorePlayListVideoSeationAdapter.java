package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.result.YueDanResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/21 17:27
 * 邮箱：2045446584@qq.com
 */
public class MorePlayListVideoSeationAdapter extends StatelessSection {

    List<YueDanResult.DataBean.PlayListVideosBean> playListVideos;

    private Context mContext;


    private  int type ;

    private OnMoreItemClickListener onMoreItemClickListener ;

    public MorePlayListVideoSeationAdapter(Context mContext, List<YueDanResult.DataBean.PlayListVideosBean> playListVideos) {
        super(R.layout.video_bottom_item_layout);

        this.playListVideos = playListVideos;

        this.mContext = mContext;
    }


    public void setType(int type) {
        this.type = type;
    }


    public List<YueDanResult.DataBean.PlayListVideosBean> getPlayListVideos() {
        return playListVideos;
    }


    public void setPlayListVideos(List<YueDanResult.DataBean.PlayListVideosBean> playListVideos) {
        this.playListVideos = playListVideos;
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    @Override public int getContentItemsTotal() {
        return playListVideos != null ? playListVideos.size() :0;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(view1 -> {
            if(onMoreItemClickListener != null){
                onMoreItemClickListener.onItemClick(view1,(Integer) view1.getTag(),type);
            }
        });
        return new BottomSectionAdapter.VideoBottomItem(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        YueDanResult.DataBean.PlayListVideosBean playListVideosBean = playListVideos.get(position);


        Glide.with(mContext)
            .load(playListVideosBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((BottomSectionAdapter.VideoBottomItem) holder).ivMv);

        ((BottomSectionAdapter.VideoBottomItem) holder).tvPlayCount.setText(String.valueOf(playListVideosBean.getTotalView()));

        ((BottomSectionAdapter.VideoBottomItem) holder).tvMvTitle.setText(playListVideosBean.getTitle());

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

        ((BottomSectionAdapter.VideoBottomItem) holder).tvArtist.setText(stringBuffer);


    }
}
