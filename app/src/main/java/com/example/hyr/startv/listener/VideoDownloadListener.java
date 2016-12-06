package com.example.hyr.startv.listener;

import com.example.hyr.startv.result.VideoDownloadResult;

/**
 * Description:
 * 作者：hyr on 2016/11/25 14:39
 * 邮箱：2045446584@qq.com
 */
public interface VideoDownloadListener {
    void onDownloadProgress(VideoDownloadResult.DataBean video, int soFarBytes, int totalBytes);

    void onError(VideoDownloadResult.DataBean video, Throwable e);

    void onCompleted(VideoDownloadResult.DataBean video );

    void onWarn(VideoDownloadResult.DataBean video);

}
