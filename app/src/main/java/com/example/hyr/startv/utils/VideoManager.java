package com.example.hyr.startv.utils;

import android.text.TextUtils;
import com.example.hyr.startv.dao.VideoDao;
import com.example.hyr.startv.listener.VideoDownloadListener;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.example.hyr.startv.result.VideoDownloadResult.DataBean.DOWNLOAD_COMPLETE;
import static com.example.hyr.startv.result.VideoDownloadResult.DataBean.DOWNLOAD_ING;
import static com.example.hyr.startv.result.VideoDownloadResult.DataBean.DOWNLOAD_NONE;

/**
 * Description:
 * 作者：hyr on 2016/11/24 17:54
 * 邮箱：2045446584@qq.com
 */
public class VideoManager {

    private static VideoManager videoManager;
    private String path;
    private Map<String, BaseDownloadTask> taskMap;
    private Map<String, VideoDownloadResult.DataBean> videoLibrary;
    private  VideoDao videoDao;

    private List<VideoDownloadListener> downloadListeners;


    public Map<String, BaseDownloadTask> getTaskMap() {
        return taskMap;
    }


    public static VideoManager getInstance() {

        if (videoManager == null) {
            videoManager = new VideoManager();
        }
        return videoManager;
    }


    public VideoManager() {

        videoLibrary = new LinkedHashMap<>();

        videoDao = new VideoDao();

        updateVideoLibrary();

        downloadListeners = new ArrayList<>();

        taskMap = new HashMap<>();

    }


    private void updateVideoLibrary() {

        Observable.create(
            (Observable.OnSubscribe<List<VideoDownloadResult.DataBean>>) subscriber -> subscriber.onNext(
                videoDao.queryAll()))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .concatMap(
                new Func1<List<VideoDownloadResult.DataBean>, Observable<VideoDownloadResult.DataBean>>() {
                    @Override
                    public Observable<VideoDownloadResult.DataBean> call(List<VideoDownloadResult.DataBean> dataBeanList) {
                        return Observable.from(dataBeanList);
                    }
                })
            .map(dataBean -> {

                if (dataBean.getDownload() == DOWNLOAD_COMPLETE &&
                    !TextUtils.isEmpty(dataBean.getPath())) {

                    if (!FileUtils.existFile(dataBean.getPath())) {

                        dataBean.setDownload(DOWNLOAD_NONE);

                        insertOrUpdateSong(dataBean);
                    }
                }
                return dataBean;
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<VideoDownloadResult.DataBean>() {

                @Override public void call(VideoDownloadResult.DataBean dataBean) {
                    videoLibrary.put(dataBean.getVideoId()+"_"+dataBean.getDowloadType(),dataBean);
                }
            });
    }


    /**
     * 视频下载
     */

    public int download(List<VideoDownloadResult.DataBean> dataBeanList, int videoDownloadType) {

        if (dataBeanList == null) {
            return VideoDownloadResult.DataBean.DOWNLOAD_NONE;
        }

        for (int i = 0; i < dataBeanList.size(); i++) {

            VideoDownloadResult.DataBean dataBean = dataBeanList.get(i);

            dataBean.setDowloadType(videoDownloadType);

            videoLibrary.put(dataBean.getVideoId() + "_" + videoDownloadType, dataBean);

            if (videoDownloadType == 2) {
                path = FileUtils.getVideoDir() + File.separator +
                    FileUtils.filenameFilter(dataBean.getTitle()) + "_" + "标清" + ".mp4";

            } else if (videoDownloadType == 3) {
                path = FileUtils.getVideoDir() + File.separator +
                    FileUtils.filenameFilter(dataBean.getTitle()) + "_" + "高清" + ".mp4";
            }

            dataBean.setPath(path);

            if (FileUtils.existFile(path)) {
                dataBean.setDownload(DOWNLOAD_COMPLETE);
                insertOrUpdateSong(dataBean);
                return VideoDownloadResult.DataBean.DOWNLOAD_COMPLETE;
            }

            if (taskMap.containsKey(dataBean.getVideoId() + "_" + videoDownloadType)) {

                BaseDownloadTask baseDownloadTask = taskMap.get(
                    dataBean.getVideoId() + "_" + videoDownloadType);

                baseDownloadTask.pause();

                taskMap.remove(dataBean.getVideoId() + "_" + videoDownloadType);

            }

            if (dataBean.getUri().toString() != null) {
                BaseDownloadTask baseDownloadTask = FileDownloader.getImpl()
                    .create(dataBean.getUri().toString());

                baseDownloadTask
                    .setListener(downloadListener)
                    .setPath(path)
                    .setTag(0, dataBean)
                    .setAutoRetryTimes(1)
                    .asInQueueTask()
                    .enqueue();

                taskMap.put(dataBean.getVideoId() + "_" + videoDownloadType, baseDownloadTask);

                dataBean.setDownload(DOWNLOAD_ING);

                updateSongFromLibrary(dataBean);

                insertOrUpdateSong(dataBean);

            }
        }
        FileDownloader.getImpl().start(downloadListener, true);

        return DOWNLOAD_ING;
    }


    public void insertOrUpdateSong(final VideoDownloadResult.DataBean dataBean) {
        Observable.create(new Observable.OnSubscribe<VideoDownloadResult.DataBean>() {
            @Override
            public void call(Subscriber<? super VideoDownloadResult.DataBean> subscriber) {
                updateSongFromLibrary(dataBean);
                videoDao.insertOrUpdateSong(dataBean);
                subscriber.onNext(dataBean);

            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<VideoDownloadResult.DataBean>() {
                @Override
                public void call(VideoDownloadResult.DataBean dataBean) {
                    videoLibrary.put(dataBean.getVideoId()+"_"+dataBean.getDowloadType(), dataBean);
                }

            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Logger.e(throwable.toString());
                }
            });
    }


    public void updateSongFromLibrary(VideoDownloadResult.DataBean dataBean) {
        if (videoLibrary.containsKey(dataBean.getVideoId()+"_"+dataBean.getDowloadType())) {
            VideoDownloadResult.DataBean cacheVideo = videoLibrary.get(dataBean.getVideoId()+"_"+dataBean.getDowloadType());
            dataBean.setDownload(cacheVideo.getDownload());
            dataBean.setPath(cacheVideo.getPath());
        }
    }


    private final FileDownloadListener downloadListener = new FileDownloadListener() {

        @Override protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Logger.d("pending:" + task.getPath());
        }


        @Override protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Logger.d("download:" + soFarBytes * 100 / totalBytes);

            for (VideoDownloadListener videoDownloadListener : downloadListeners) {
                videoDownloadListener.onDownloadProgress(
                    ((VideoDownloadResult.DataBean) task.getTag(0)), soFarBytes, totalBytes);
            }
        }


        @Override protected void completed(BaseDownloadTask task) {
            Logger.d("path:" + task.getPath());

            FileUtils.mp3Scanner(task.getPath());

            VideoDownloadResult.DataBean dataBean = (VideoDownloadResult.DataBean) task.getTag(0);

            dataBean.setDownload(DOWNLOAD_COMPLETE);

            videoLibrary.put(dataBean.getVideoId()+"_"+dataBean.getDowloadType(), dataBean);

            taskMap.remove(dataBean.getVideoId()+"_"+dataBean.getDowloadType());

            insertOrUpdateSong(dataBean);

            for (VideoDownloadListener videoDownloadListener : downloadListeners) {
                videoDownloadListener.onCompleted(dataBean);
            }
        }


        @Override protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Logger.d("paused:" + task.getPath());
        }


        @Override protected void error(BaseDownloadTask task, Throwable e) {
            Logger.d("error:" + task.getPath());

            VideoDownloadResult.DataBean dataBean = (VideoDownloadResult.DataBean) task.getTag(0);

            taskMap.remove(dataBean.getVideoId()+"_"+dataBean.getDowloadType());

            for (VideoDownloadListener videoDownloadListener : downloadListeners) {
                videoDownloadListener.onError(((VideoDownloadResult.DataBean) task.getTag(0)), e);
            }
        }


        @Override protected void warn(BaseDownloadTask task) {
            Logger.d("warn:" + task.getPath());

            //本地已经存在视频

            if (FileUtils.existFile(task.getPath())) {
                VideoDownloadResult.DataBean video = (VideoDownloadResult.DataBean) task.getTag(0);
                video.setDownload(DOWNLOAD_COMPLETE);

                videoLibrary.put(video.getVideoId()+"_"+video.getDowloadType(), video);

                taskMap.remove(video.getVideoId()+"_"+video.getDowloadType());

                insertOrUpdateSong(video);

            }

            for (VideoDownloadListener videoDownloadListener : downloadListeners) {
                videoDownloadListener.onWarn(((VideoDownloadResult.DataBean) task.getTag(0)));
            }
        }
    };


    /**
     * 获取正在下载中的视频
     */

    public List<VideoDownloadResult.DataBean> getDownloadingVideo() {

        List<VideoDownloadResult.DataBean> downliadingVideo = new ArrayList<>();

        Set<String> longs = videoLibrary.keySet();

        for (String key : longs) {

            VideoDownloadResult.DataBean dataBean = videoLibrary.get(key);

            if (dataBean.getDownload() == DOWNLOAD_ING) {
                downliadingVideo.add(dataBean);
            }
        }

        return downliadingVideo;
    }


    /**
     * 获取下载完成的视频
     */

    public List<VideoDownloadResult.DataBean> getDownloadedVideo() {
        List<VideoDownloadResult.DataBean> downloadedVideo = new ArrayList<>();

        Set<String> longs = videoLibrary.keySet();

        for (String key : longs) {
            VideoDownloadResult.DataBean dataBean = videoLibrary.get(key);

            if (dataBean.getDownload() == DOWNLOAD_COMPLETE) {
                downloadedVideo.add(dataBean);
            }
        }
        return downloadedVideo;
    }


    public void registerDownloadListener(VideoDownloadListener listener) {
        if (listener == null) return;
        downloadListeners.add(listener);
    }


    public void unRegisterDownloadListener(VideoDownloadListener listener) {
        if (listener == null) return;
        downloadListeners.remove(listener);
    }
}
