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
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/21 15:14
 * 邮箱：2045446584@qq.com
 */
public class MorePlayListSectionAdapter extends StatelessSection {

    private List<MorePlayVideoResult.DataBean> morePlayList;

    private Context mContext;

    private OnMoreItemClickListener onMoreItemClickListener ;

    private  int type ;


    public List<MorePlayVideoResult.DataBean> getMorePlayList() {
        return morePlayList;
    }


    public void setType(int type) {
        this.type = type;
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    public MorePlayListSectionAdapter(Context mContext, List<MorePlayVideoResult.DataBean> data) {
        super(R.layout.more_play_list_layout);

        this.morePlayList = data;

        this.mContext = mContext;
    }


    @Override public int getContentItemsTotal() {
        return morePlayList != null ? morePlayList.size() :0;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        view.setOnClickListener(view1 -> {
            if(onMoreItemClickListener != null){
                onMoreItemClickListener.onItemClick(view1,(Integer) view1.getTag(),type);
            }
        });
        return new MorePlayListViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        MorePlayVideoResult.DataBean dataBean = morePlayList.get(position);

        Glide.with(mContext)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(((MorePlayListViewHolder) holder).ivPosterPic);

        ((MorePlayListViewHolder) holder).tvTitle.setText(dataBean.getTitle());
    }


    public static class MorePlayListViewHolder extends RecyclerView.ViewHolder {

        public final ImageView ivPosterPic;
        public final TextView tvTitle;


        public MorePlayListViewHolder(View itemView) {
            super(itemView);

            ivPosterPic = (ImageView) itemView.findViewById(R.id.iv_posterPic);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
