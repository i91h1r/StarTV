package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/22 16:30
 * 邮箱：2045446584@qq.com
 */
public class BottomMostPeopleAdapter extends StatelessSection {
    private  Context mContext;

    private  List<EveryoneWatchResult.DataBean> data ;

    private  int type ;
    public BottomMostPeopleAdapter(Context mContext, List<EveryoneWatchResult.DataBean> data) {
        super(R.layout.video_bottom_item_layout);

        this.mContext = mContext ;

        this.data = data ;
    }


    public void setType(int type) {
        this.type = type;
    }


    public List<EveryoneWatchResult.DataBean> getData() {
        return data;
    }


    private OnMoreItemClickListener onMoreItemClickListener ;

    @Override public int getContentItemsTotal() {
        return data.size();
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(onMoreItemClickListener != null){
                    onMoreItemClickListener.onItemClick(view,(Integer)view.getTag(),type);
                }
            }
        });
        return new BottomSectionAdapter.VideoBottomItem(view);
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        EveryoneWatchResult.DataBean dataBean = data.get(position);

        Glide.with(mContext).load(dataBean.getPosterPic()).placeholder(R.drawable.ad_video_default).into(
            ((BottomSectionAdapter.VideoBottomItem) holder).ivMv);


        ((BottomSectionAdapter.VideoBottomItem) holder).tvMvTitle.setText(dataBean.getTitle());

        ((BottomSectionAdapter.VideoBottomItem) holder).tvPlayCount.setText(dataBean.getTotalView()+"");

        List<EveryoneWatchResult.DataBean.ArtistsBean> artists = dataBean.getArtists();

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
