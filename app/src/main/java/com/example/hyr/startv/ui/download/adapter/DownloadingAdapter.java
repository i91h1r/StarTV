package com.example.hyr.startv.ui.download.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.VideoDownloadListener;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.utils.FileUtils;
import com.example.hyr.startv.utils.GlideRoundTransform;
import com.example.hyr.startv.utils.VideoManager;
import com.liulishuo.filedownloader.BaseDownloadTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 作者：hyr on 2016/11/28 14:28
 * 邮箱：2045446584@qq.com
 */
public class DownloadingAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private final LayoutInflater inflater;

    private static Map<String, BaseDownloadTask> taskMap;

    private List<VideoDownloadResult.DataBean> downloadingVideo;
    private static List<VideoDownloadResult.DataBean> tempDownloadList;

    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CENTER = 1;


    public DownloadingAdapter(Context mContext) {

        this.mContext = mContext;

        downloadingVideo = VideoManager.getInstance().getDownloadingVideo();

        inflater = LayoutInflater.from(mContext);

        taskMap = VideoManager.getInstance().getTaskMap();

        tempDownloadList = new ArrayList<>();

        VideoManager.getInstance().registerDownloadListener(videoDownloadingListener);
    }


    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_HEADER) {

            View inflate = inflater.inflate(R.layout.downloading_header_layout, parent, false);

            return new DownloadingHeaderViewHolder(inflate);
        } else {
            View inflate = inflater.inflate(R.layout.downloading_item_layout, parent, false);

            return new DownloadingViewHolder(inflate);

        }

    }


    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof DownloadingViewHolder) {
            VideoDownloadResult.DataBean dataBean = downloadingVideo.get(position - 1);

            int dowloadType = dataBean.getDowloadType();

            if(dataBean.isDownloading()){
                ((DownloadingViewHolder) holder).tvStatus.setVisibility(View.INVISIBLE);

                ((DownloadingViewHolder) holder).ivStatus.setImageResource(R.drawable.pause_download_selector);
            }else {
                ((DownloadingViewHolder) holder).tvStatus.setVisibility(View.VISIBLE);

                ((DownloadingViewHolder) holder).ivStatus.setImageResource(R.drawable.start_download_selector);
            }

            if (dowloadType == 2) {
                ((DownloadingViewHolder) holder).ivType.setBackgroundResource(
                    R.mipmap.setting_definition_x);
            } else if (dowloadType == 3) {
                ((DownloadingViewHolder) holder).ivType.setBackgroundResource(
                    R.mipmap.setting_definition_xx);
            }

            Glide.with(mContext)
                .load(dataBean.getPosterPic())
                .placeholder(R.drawable.ad_video_default)
                .transform(new GlideRoundTransform(mContext))
                .into(((DownloadingViewHolder) holder).ivMv);

            ((DownloadingViewHolder) holder).tvArtist.setText(
                dataBean.getArtists().get(0).getArtistName());

            ((DownloadingViewHolder) holder).cardView.setOnClickListener(
                view -> ((DownloadingViewHolder) holder).onDownloadClick(dataBean,
                    dataBean.getDowloadType()));

            ((DownloadingViewHolder) holder).tvTitle.setText(dataBean.getTitle());

            if (taskMap.containsKey(dataBean.getVideoId() + "_" + dataBean.getDowloadType())) {

                BaseDownloadTask baseDownloadTask = taskMap.get(
                    dataBean.getVideoId() + "_" + dataBean.getDowloadType());

                int progress = FileUtils.getProgress(baseDownloadTask.getSmallFileSoFarBytes(),
                    baseDownloadTask.getSmallFileTotalBytes());

                String progressSize = FileUtils.getProgressSize(
                    baseDownloadTask.getSmallFileSoFarBytes(),
                    baseDownloadTask.getSmallFileTotalBytes());

                int speed = baseDownloadTask.getSpeed();

                if (baseDownloadTask.isRunning()) {
                    ((DownloadingViewHolder) holder).tvStatus.setVisibility(View.INVISIBLE);

                    ((DownloadingViewHolder) holder).ivStatus.setImageResource(
                        R.drawable.pause_download_selector);

                    dataBean.setDownloading(true);

                } else {
                    ((DownloadingViewHolder) holder).tvStatus.setVisibility(View.VISIBLE);
                }

                if (progress > 0) {

                    ((DownloadingViewHolder) holder).progressBar.setIndeterminate(false);

                    ((DownloadingViewHolder) holder).progressBar.setProgress(progress);

                    ((DownloadingViewHolder) holder).tvCurrentDownload.setText(progressSize);

                    ((DownloadingViewHolder) holder).tvSpeed.setText(
                        String.format("%dkb/s", speed));
                }

            }
        } else if (holder instanceof DownloadingHeaderViewHolder) {

            DownloadingHeaderViewHolder headerViewHolder = (DownloadingHeaderViewHolder) holder;

            headerViewHolder.allPause.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    for (VideoDownloadResult.DataBean dataBean : downloadingVideo) {

                        if (taskMap.containsKey(
                            dataBean.getVideoId() + "_" + dataBean.getDowloadType())) {

                            BaseDownloadTask baseDownloadTask = taskMap.get(
                                dataBean.getVideoId() + "_" + dataBean.getDowloadType());

                            baseDownloadTask.pause();

                            dataBean.setDownloading(false);

                            notifyDataSetChanged();
                        }

                    }
                }
            });

            headerViewHolder.allStart.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    for (VideoDownloadResult.DataBean dataBean : downloadingVideo) {
                        tempDownloadList.clear();

                        tempDownloadList.add(dataBean);

                        dataBean.setDownloading(true);

                        int download = VideoManager.getInstance().download(tempDownloadList,
                            dataBean.getDowloadType());

                        notifyDataSetChanged();
                    }
                }
            });
        }

    }


    @Override public int getItemCount() {
        return downloadingVideo.size() + 1;
    }


    @Override public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_CENTER;
        }
    }


    public class DownloadingHeaderViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout allPause;
        private final LinearLayout allStart;


        public DownloadingHeaderViewHolder(View itemView) {
            super(itemView);

            allPause = (LinearLayout) itemView.findViewById(R.id.ll_pause);

            allStart = (LinearLayout) itemView.findViewById(R.id.ll_start);
        }
    }


    public static class DownloadingViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMv;
        public TextView tvTitle;
        public ImageView ivType;
        public TextView tvArtist;
        public TextView tvSpeed;
        public TextView tvCurrentDownload;
        public ProgressBar progressBar;
        public TextView tvStatus;
        public LinearLayout cardView;
        public ImageView ivStatus;


        public DownloadingViewHolder(View itemView) {
            super(itemView);

            ivMv = (ImageView) itemView.findViewById(R.id.iv_mv);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            ivType = (ImageView) itemView.findViewById(R.id.iv_video_type);

            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);

            tvSpeed = (TextView) itemView.findViewById(R.id.tv_download_speed);

            tvCurrentDownload = (TextView) itemView.findViewById(R.id.tv_download);

            progressBar = (ProgressBar) itemView.findViewById(R.id.download_progress);

            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);

            cardView = (LinearLayout) itemView.findViewById(R.id.card_view);

            ivStatus = (ImageView) itemView.findViewById(R.id.iv_status);

        }


        public void onDownloadClick(VideoDownloadResult.DataBean dataBean, int dowloadType) {

            boolean downloading = dataBean.isDownloading();

            if (downloading) {

                dataBean.setDownloading(false);

                ivStatus.setImageResource(R.drawable.start_download_selector);

                tvStatus.setVisibility(View.VISIBLE);

                if (taskMap.containsKey(dataBean.getVideoId() + "_" + dataBean.getDowloadType())) {
                    BaseDownloadTask baseDownloadTask = taskMap.get(
                        dataBean.getVideoId() + "_" + dataBean.getDowloadType());
                    baseDownloadTask.pause();

                }
            } else {
                tempDownloadList.clear();

                tempDownloadList.add(dataBean);

                dataBean.setDowloadType(dowloadType);

                dataBean.setDownloading(true);

                ivStatus.setImageResource(R.drawable.pause_download_selector);

                tvStatus.setVisibility(View.INVISIBLE);

                int download = VideoManager.getInstance().download(tempDownloadList,
                    dowloadType);

            }
        }

    }


    private VideoDownloadListener videoDownloadingListener = new VideoDownloadListener() {
        @Override
        public void onDownloadProgress(VideoDownloadResult.DataBean video, int soFarBytes, int totalBytes) {

            int position = downloadingVideo.indexOf(video);

            if (position+1 >= 0 && position +1< getItemCount()) {

                notifyItemChanged(position+1);
            }
        }


        @Override public void onError(VideoDownloadResult.DataBean video, Throwable e) {
            int position = downloadingVideo.indexOf(video);

            if (position +1>= 0 && position+1 < getItemCount()) {
                notifyItemChanged(position+1);
            }
        }


        @Override public void onCompleted(VideoDownloadResult.DataBean video) {
            int position = downloadingVideo.indexOf(video);

            if (position+1 >= 0 && position +1< getItemCount()) {

                downloadingVideo.remove(position);

                notifyItemRangeRemoved(position+1, 1);

            }
        }


        @Override public void onWarn(VideoDownloadResult.DataBean video) {

        }
    };


    public void onDestroy() {
        VideoManager.getInstance().unRegisterDownloadListener(videoDownloadingListener);
    }

}

