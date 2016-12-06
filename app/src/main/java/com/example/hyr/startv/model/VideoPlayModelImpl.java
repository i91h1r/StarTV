package com.example.hyr.startv.model;

import com.example.hyr.startv.api.BaseObserver;
import com.example.hyr.startv.api.StarApi;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.VideoPlayContract;
import com.example.hyr.startv.listener.BaseMvPlayListListener;
import com.example.hyr.startv.result.BaseResult;
import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.result.VideoPlayResult;
import com.example.hyr.startv.result.YueDanResult;
import com.example.hyr.startv.utils.DeviceInfoUtils;
import rx.schedulers.Schedulers;

/**
 * Created by MVPHelper on 2016/11/09
 */

public class VideoPlayModelImpl implements VideoPlayContract.Model {
    private final StarApi starApi;
    BaseMvPlayListListener<BaseResult> baseMvPlayListListener;


    public VideoPlayModelImpl(
        BaseMvPlayListListener<BaseResult> baseMvPlayListListener) {

        this.baseMvPlayListListener = baseMvPlayListListener;
        starApi = new StarApi();
    }


    @Override public void loadVideoPlayData(int id, int type) {

        starApi.getVideoBarrageData(id, DeviceInfoUtils.getDeviceInfo()).flatMap(
            videoBarrageResult -> {

                baseMvPlayListListener.onMvPlayListSuccess(Constants.VIDEO_BARRAGE_LIST,
                    videoBarrageResult);

                return starApi.getVideoPlayUrl(id, type, DeviceInfoUtils.getDeviceInfo());
            }).observeOn(Schedulers.immediate()).subscribe(new BaseObserver<VideoPlayResult>() {
            @Override protected void onSucceed(VideoPlayResult result) {
                baseMvPlayListListener.onMvPlayListSuccess(Constants.VIDEO_PLAY_DATA, result);

            }


            @Override protected void onError(String e) {
                baseMvPlayListListener.onMvPlayError(Constants.VIDEO_PLAY_DATA,
                    e.toString());
            }
        });
    }


    /**
     * 获取视频详情
     */

    @Override public void loadVideoDetial(int id, boolean isRefresh, boolean isAdd) {

        starApi.getVideoDetail(id, DeviceInfoUtils.getDeviceInfo()).flatMap(
            videoDetailResult -> {
                baseMvPlayListListener.onMvPlayListSuccess(Constants.VIDEO_DETAIL_DATA,
                    videoDetailResult);

                return starApi.getEveryoneWatchData(36, id, 1,
                    DeviceInfoUtils.getDeviceInfo());
            }).observeOn(Schedulers.immediate()).subscribe(
            new BaseObserver<EveryoneWatchResult>() {
                @Override protected void onSucceed(EveryoneWatchResult result) {

                    if (isRefresh) {

                        baseMvPlayListListener.onMvPlayListSuccess(Constants.EVERY_ONE_WATCH,
                            result);

                    }

                }


                @Override protected void onError(String e) {
                    baseMvPlayListListener.onMvPlayError(Constants.EVERY_ONE_WATCH,
                        e.toString());
                }
            });
    }


    @Override public void loadPlayListDetail(int playListId) {

        starApi.getPlayListData(playListId, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<YueDanResult>() {
                    @Override protected void onSucceed(YueDanResult yueDanResult) {
                        baseMvPlayListListener.onMvPlayListSuccess(Constants.PLAY_LIST_DATA,
                            yueDanResult);

                    }


                    @Override protected void onError(String e) {
                        baseMvPlayListListener.onMvPlayError(Constants.PLAY_LIST_DATA,
                            e.toString());
                    }
                });

    }


    @Override public void loadDetailMvMore(int type, int videoId, int size) {
        starApi.getMoreMvList(type, videoId, size, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(new BaseObserver<MoreMvResult>() {

                @Override protected void onSucceed(MoreMvResult result) {
                    baseMvPlayListListener.onMvPlayListSuccess(Constants.MORE_MV_PLAY_LIST, result);
                }


                @Override protected void onError(String e) {
                    baseMvPlayListListener.onMvPlayError(type,
                        e.toString());
                }

            });

    }


    @Override public void loadMorePlayList(int videoId, int size) {

        starApi.getMorePlayVideoList(videoId, size, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<MorePlayVideoResult>() {
                    @Override protected void onSucceed(MorePlayVideoResult result) {

                        baseMvPlayListListener.onMvPlayListSuccess(Constants.MORE_PLAY_LIST,
                            result);

                    }


                    @Override protected void onError(String e) {
                        baseMvPlayListListener.onMvPlayError(Constants.MORE_PLAY_LIST,
                            e.toString());
                    }

                });

    }


    @Override public void loadSimiPlayList(int playListId, int offset, int videoId, int size) {

        starApi.getSimiList(playListId, offset, videoId, size, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<SimilartyPlayListResult>() {
                    @Override protected void onSucceed(SimilartyPlayListResult result) {

                        baseMvPlayListListener.onMvPlayListSuccess(Constants.SIMILARTY_PLAY_LIST,
                            result);

                    }


                    @Override protected void onError(String e) {
                        baseMvPlayListListener.onMvPlayError(Constants.SIMILARTY_PLAY_LIST,
                            e.toString());
                    }

                });

    }


    @Override public void loadOneVideoPlayList(int playListId, int videoId) {
        starApi.getOneVideoPlayList(playListId, videoId, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<YueDanResult>() {
                    @Override protected void onSucceed(YueDanResult result) {
                        baseMvPlayListListener.onMvPlayListSuccess(Constants.ONE_VIDEO_PLAY_LIST,
                            result);
                    }


                    @Override protected void onError(String e) {
                        baseMvPlayListListener.onMvPlayError(Constants.ONE_VIDEO_PLAY_LIST,
                            e.toString());
                    }
                });
    }


    @Override public void loadMoreMostPeople(int offset, int type, int videoId, int size) {
        starApi.getEveryoneWatchData(size, videoId, type, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<EveryoneWatchResult>() {
                    @Override protected void onSucceed(EveryoneWatchResult result) {
                        baseMvPlayListListener.onMvPlayListSuccess(Constants.MORE_MOST_PEOPLE_LIST,
                            result);
                    }


                    @Override protected void onError(String e) {
                        baseMvPlayListListener.onMvPlayError(Constants.MORE_MOST_PEOPLE_LIST,
                            e.toString());
                    }
                });
    }


    /**
     * 获取下载视频信息
     *
     * @param videoIds 下载视频 videoid
     * @param videoType 下载类型
     */
    @Override public void loadVideoDownloadInfo(String videoIds, int videoType) {

        starApi.getVideoDownloadInfo(videoIds, videoType, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(new BaseObserver<VideoDownloadResult>() {

                @Override protected void onSucceed(VideoDownloadResult result) {
                    baseMvPlayListListener.onMvPlayListSuccess(Constants.VIDEO_DOWNLOAD_INFO,
                        result);
                }


                @Override protected void onError(String e) {
                    baseMvPlayListListener.onMvPlayError(Constants.VIDEO_DOWNLOAD_INFO,
                        e.toString());
                }
            });
    }

}