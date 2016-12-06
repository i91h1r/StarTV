package com.example.hyr.startv.ui.artist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.hyr.startv.R;
import com.example.hyr.startv.result.BaseArtistInfo;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/17 11:22
 * 邮箱：2045446584@qq.com
 */
public class BaseArtistInfoSection extends StatelessSection {

    private Context mContext;

    private List<BaseArtistInfo> artistInfo ;

    public BaseArtistInfoSection(Context mContext, List<BaseArtistInfo> artistInfo) {
        super(R.layout.base_artist_info_layout);

        this.mContext = mContext;

        this.artistInfo = artistInfo;
    }


    @Override public int getContentItemsTotal() {
        return artistInfo.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        BaseArtistInfo baseArtistInfo = artistInfo.get(position);

        ((ItemViewHolder) holder).name.setText(baseArtistInfo.getValue());

        ((ItemViewHolder) holder).type.setText(baseArtistInfo.getKey());
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final TextView name;


        public ItemViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
