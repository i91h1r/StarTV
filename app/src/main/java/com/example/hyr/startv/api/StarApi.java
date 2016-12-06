package com.example.hyr.startv.api;

import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.result.HomeMoreMvResult;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.result.TabResult;
import com.example.hyr.startv.result.VideoBarrageResult;
import com.example.hyr.startv.result.VideoDetailResult;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.result.VideoPlayResult;
import com.example.hyr.startv.result.YueDanResult;
import com.example.hyr.startv.utils.RetrofitUtil;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * 作者：hyr on 2016/11/4 14:56
 * 邮箱：2045446584@qq.com
 */
public class StarApi implements ApiService {

    @Override public Observable<HomeResult> getHomeData(@Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getHomeData(deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<VideoPlayResult> getVideoPlayUrl(
        @Query("id") int id,
        @Query("type") int type, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getVideoPlayUrl(id, type, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<VideoBarrageResult> getVideoBarrageData(
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getVideoBarrageData(videoId, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<VideoDetailResult> getVideoDetail(
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getVideoDetail(videoId, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<EveryoneWatchResult> getEveryoneWatchData(
        @Query("size") int size,
        @Query("videoId") int videoId,
        @Query("type") int type, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getEveryoneWatchData(size, videoId, type,
                deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<YueDanResult> getPlayListData(
        @Query("playListId") int playListId, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getPlayListData(playListId, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<ArtistInfoResult> getArtistInfo(
        @Query("artistId") int artistId, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getArtistInfo(artistId, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<MoreMvResult> getMoreMvList(
        @Query("type") int type,
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getMoreMvList(type, videoId, size, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<MorePlayVideoResult> getMorePlayVideoList(
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getMorePlayVideoList(videoId, size, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<SimilartyPlayListResult> getSimiList(
        @Query("playListId") int playListId,
        @Query("offset") int offset,
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getSimiList(playListId, offset, videoId, size, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<YueDanResult> getOneVideoPlayList(
        @Query("playListId") int playListId,
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getOneVideoPlayList(playListId, videoId, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<VideoDownloadResult> getVideoDownloadInfo(
        @Part("videoIds") String videoIds,
        @Query("videoType") int videoType, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getVideoDownloadInfo(videoIds, videoType, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<TabResult> getTabList(
        @Query("position") String position, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getTabList(position, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<HomeMoreMvResult> getMoreMvList(
        @Url String url,
        @Query("area") String area,
        @Query("offset") int offset,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo) {
        return RetrofitUtil.getApi(Urls.BASE_URL)
            .getMoreMvList(url, area, offset, size, deviceinfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
