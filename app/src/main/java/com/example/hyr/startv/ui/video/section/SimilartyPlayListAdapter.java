package com.example.hyr.startv.ui.video.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/22 11:25
 * 邮箱：2045446584@qq.com
 */
public class SimilartyPlayListAdapter extends StatelessSection {
    private List<SimilartyPlayListResult.DataBean> data;
    private Context mContext;
    private int type;
    private OnMoreItemClickListener onMoreItemClickListener;


    public SimilartyPlayListAdapter(Context mContext, List<SimilartyPlayListResult.DataBean> data) {
        super(R.layout.more_play_list_layout);

        this.data = data;

        this.mContext = mContext;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    public List<SimilartyPlayListResult.DataBean> getData() {
        return data;
    }


    public void setData(List<SimilartyPlayListResult.DataBean> data) {
        this.data = data;
    }


    @Override public int getContentItemsTotal() {
        return data != null ? data.size() :0;
    }


    @Override public RecyclerView.ViewHolder getItemViewHolder(View view) {

        return new MorePlayListSectionAdapter.MorePlayListViewHolder(view);
    }


    @Override public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (onMoreItemClickListener != null) {
                    onMoreItemClickListener.onItemClick(view, position, type);
                }
            }
        });
        SimilartyPlayListResult.DataBean dataBean = data.get(position);

        Glide.with(mContext)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .into(
                ((MorePlayListSectionAdapter.MorePlayListViewHolder) holder).ivPosterPic);

        ((MorePlayListSectionAdapter.MorePlayListViewHolder) holder).tvTitle.setText(
            dataBean.getTitle());

    }
}
