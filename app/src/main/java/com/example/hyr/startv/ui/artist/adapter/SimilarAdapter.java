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
import com.example.hyr.startv.utils.GlideCircleImage;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 17:45
 * 邮箱：2045446584@qq.com
 */
public class SimilarAdapter extends StatelessSection {
    private Context mContext;
    private List<ArtistInfoResult.DataBean.SimilarListBean> similarList;

    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public SimilarAdapter(Context mContext, List<ArtistInfoResult.DataBean.SimilarListBean> similarList) {
        super(R.layout.similar_header_layout, R.layout.similay_item_layout);

        this.mContext = mContext;

        this.similarList = similarList;
    }


    @Override public int getContentItemsTotal() {
        return similarList.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        view.setOnClickListener(view1 -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view1, (Integer) view1.getTag());
            }
        });
        return new SimilarItemViewHoler(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        ArtistInfoResult.DataBean.SimilarListBean similarListBean = similarList.get(position);

        Glide.with(mContext)
            .load(similarListBean.getSmallAvatar())
            .transform(new GlideCircleImage(mContext))
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.default_head_img)
            .into(((SimilarItemViewHoler) holder).ivArtist);

        ((SimilarItemViewHoler) holder).tName.setText(similarListBean.getArtistName());
    }


    @Override public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SimilarHeaderViewHolder(view);
    }


    @Override public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
    }


    public class SimilarItemViewHoler extends RecyclerView.ViewHolder {

        private final ImageView ivArtist;
        private final TextView tName;


        public SimilarItemViewHoler(View itemView) {
            super(itemView);
            ivArtist = (ImageView) itemView.findViewById(R.id.iv_artist);
            tName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }


    public class SimilarHeaderViewHolder extends RecyclerView.ViewHolder {

        public SimilarHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
