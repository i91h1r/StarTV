package com.example.hyr.startv.ui.download.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.VideoDownloadListener;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.ui.download.FullVideoPlayActivity;
import com.example.hyr.startv.utils.GlideRoundTransform;
import com.example.hyr.startv.utils.VideoManager;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.orhanobut.logger.Logger;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 作者：hyr on 2016/11/28 11:20
 * 邮箱：2045446584@qq.com
 */
public class DownloadedAdapter
    extends RecyclerView.Adapter<DownloadedAdapter.DownloadedViewHolder> {
    private Context mContext;
    private List<VideoDownloadResult.DataBean> downloadedVideo;
    private final Map<String, BaseDownloadTask> taskMap;
    private final LayoutInflater inflater;


    public DownloadedAdapter(Context mContext) {

        this.mContext = mContext;

        downloadedVideo = VideoManager.getInstance()
            .getDownloadedVideo();

        taskMap = VideoManager.getInstance().getTaskMap();

        VideoManager.getInstance().registerDownloadListener(downloadListener);

        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public DownloadedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_downloaded_layout, parent, false);

        return new DownloadedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DownloadedViewHolder holder, int position) {

        VideoDownloadResult.DataBean dataBean = downloadedVideo.get(position);

        int dowloadType = dataBean.getDowloadType();

        if(dowloadType == 2){
            holder.ivType.setImageResource(R.mipmap.setting_definition_x);
        }else if(dowloadType ==3){
            holder.ivType.setImageResource(R.mipmap.setting_definition_xx);
        }

        Glide.with(mContext)
            .load(dataBean.getPosterPic())
            .placeholder(R.drawable.ad_video_default).transform(new GlideRoundTransform(mContext))
            .into(holder.ivMv);

        holder.tvTitle.setText(dataBean.getTitle());

        holder.tvArtist.setText(dataBean.getArtists().get(0).getArtistName());

        holder.root.setOnClickListener(view -> {

            Logger.d("dataBean.getPath()----", dataBean.getPath());

            FullVideoPlayActivity.open(mContext, dataBean.getPath(), dataBean.getTitle());
        });
    }


    @Override public int getItemCount() {
        return downloadedVideo.size();
    }


    public class DownloadedViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvArtist;
        private final ImageView ivType;
        private final ImageView ivMv;
        private final LinearLayout root;


        public DownloadedViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);

            ivType = (ImageView) itemView.findViewById(R.id.iv_type);

            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);

            root = (LinearLayout) itemView.findViewById(R.id.root);

        }
    }


    private VideoDownloadListener downloadListener = new VideoDownloadListener() {

        @Override
        public void onDownloadProgress(VideoDownloadResult.DataBean video, int soFarBytes, int totalBytes) {

        }


        @Override public void onError(VideoDownloadResult.DataBean video, Throwable e) {

        }


        @Override public void onCompleted(VideoDownloadResult.DataBean video) {

            downloadedVideo = VideoManager.getInstance().getDownloadedVideo();

            notifyDataSetChanged();
        }


        @Override public void onWarn(VideoDownloadResult.DataBean video) {

        }
    };


    public void onDestroy() {
        VideoManager.getInstance().unRegisterDownloadListener(downloadListener);
    }
}
