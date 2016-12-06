package com.example.hyr.startv.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.result.HomeMoreMvResult;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/12/5 15:00
 * 邮箱：2045446584@qq.com
 */
public class MoreMvAdapter extends RecyclerView.Adapter<MoreMvAdapter.MoreMvItemViewHolder> {

    private List<HomeMoreMvResult.DataBean> data;
    private Context mContext;
    private final LayoutInflater layoutInflater;

    private OnMoreItemClickListener onMoreItemClickListener;


    public MoreMvAdapter(Context mContext) {
        this.mContext = mContext;

        layoutInflater = LayoutInflater.from(mContext);
    }


    public List<HomeMoreMvResult.DataBean> getData() {
        return data;
    }


    public void setData(List<HomeMoreMvResult.DataBean> data) {
        this.data = data;
    }


    public void setOnMoreItemClickListener(OnMoreItemClickListener onMoreItemClickListener) {
        this.onMoreItemClickListener = onMoreItemClickListener;
    }


    @Override
    public MoreMvItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = layoutInflater.inflate(R.layout.more_mv_item_layout, parent, false);

        return new MoreMvItemViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(MoreMvItemViewHolder holder, int position) {
        HomeMoreMvResult.DataBean dataBean = data.get(position);

        Glide.with(mContext)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default)
            .into(
                (holder).ivMv);

        (holder).tvTitle.setText(dataBean.getTitle());

        StringBuffer stringBuffer = new StringBuffer();

        List<HomeMoreMvResult.DataBean.ArtistsBean> artists = dataBean.getArtists();
        for (int i = 0; i < artists.size(); i++) {

            String artistName = artists.get(i).getArtistName();

            stringBuffer.append(artistName);
            if (i > 1 && i < artists.size()) {

                stringBuffer.append("&");
            }
        }

        (holder).tvAuthor.setText(stringBuffer);

        (holder).tvCount.setText(String.valueOf(dataBean.getTotalView()));

        holder.cardView.setOnClickListener(view -> {
            if (onMoreItemClickListener != null) {
                onMoreItemClickListener.onItemClick(view, position, 0);
            }
        });
    }


    @Override public int getItemCount() {
        return data != null ? data.size() :0;
    }


    public static class MoreMvItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCount;
        private final TextView tvAuthor;
        private final TextView tvTitle;
        private final ImageView ivMv;
        private final CardView cardView;


        public MoreMvItemViewHolder(View itemView) {
            super(itemView);

            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);

            tvCount = (TextView) itemView.findViewById(R.id.tv_count);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
