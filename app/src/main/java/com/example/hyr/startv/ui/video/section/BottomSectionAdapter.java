package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/18 15:22
 * 邮箱：2045446584@qq.com
 */
public class BottomSectionAdapter extends StatelessSection {
    List<MoreMvResult.DataBean> data  = new ArrayList<>();
    private Context mContext;

    private OnMoreItemClickListener onMoreItemClickListener;

    private  int type ;

    public BottomSectionAdapter(Context mContext) {
        super(R.layout.video_bottom_item_layout);

        this.mContext = mContext;
    }


    public void setType(int type) {
        this.type = type;
    }


    public List<MoreMvResult.DataBean> getData() {
        return data;
    }


    public void setData(List<MoreMvResult.DataBean> data) {
        this.data = data;
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    @Override public int getContentItemsTotal() {
        return data != null ? data.size() :0;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        view.setOnClickListener(view1 -> {
            if (null != onMoreItemClickListener) {
                onMoreItemClickListener.onItemClick(view1,(Integer)view1.getTag(), type);
            }
        });
        return new VideoBottomItem(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        MoreMvResult.DataBean dataBean = data.get(position);


        Glide.with(mContext)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((VideoBottomItem) holder).ivMv);

        ((VideoBottomItem) holder).tvPlayCount.setText(String.valueOf(dataBean.getTotalView()));

        ((VideoBottomItem) holder).tvMvTitle.setText(dataBean.getTitle());

        List<MoreMvResult.DataBean.ArtistsBean> artists = dataBean.getArtists();

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        ((VideoBottomItem) holder).tvArtist.setText(stringBuffer);
    }


    public static class VideoBottomItem extends RecyclerView.ViewHolder {

        public final ImageView ivMv;
        public final TextView tvMvTitle;
        public final TextView tvArtist;
        public final TextView tvPlayCount;


        public VideoBottomItem(View itemView) {
            super(itemView);

            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);
            tvMvTitle = (TextView) itemView.findViewById(R.id.tv_mv_title);

            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);
        }
    }
}
