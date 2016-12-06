package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.ArtistsBean;
import com.example.hyr.startv.utils.GlideCircleImage;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/15 17:31
 * 邮箱：2045446584@qq.com
 */
public class ArtistSectionAdapter extends StatelessSection {
    private Context mContext;
    List<ArtistsBean> artists;


    public ArtistSectionAdapter(Context mContext, List<ArtistsBean> artists) {
        super(R.layout.author_item_layout);

        this.mContext = mContext;

        this.artists = artists;

    }


    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public List<ArtistsBean> getArtists() {
        return artists;
    }


    public void setArtists(List<ArtistsBean> artists) {
        this.artists = artists;
    }


    @Override public int getContentItemsTotal() {
        return artists.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(view1 -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        });
        return new ItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);
        ArtistsBean artistsBean = artists.get(position);

        Glide.with(mContext)
            .load(artistsBean.getArtistAvatar())
            .transform(new GlideCircleImage(mContext))
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(
                ((ItemViewHolder) holder).ivArtist);

        ((ItemViewHolder) holder).tvArtist.setText(artistsBean.getArtistName());

    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivArtist;
        private final TextView tvArtist;


        public ItemViewHolder(View itemView) {
            super(itemView);

            ivArtist = (ImageView) itemView.findViewById(R.id.iv_artist);

            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
        }
    }

}
