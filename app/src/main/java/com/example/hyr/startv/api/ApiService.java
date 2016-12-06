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
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Description:
 * 作者：hyr on 2016/11/4 14:29
 * 邮箱：2045446584@qq.com
 */
public interface ApiService {

    /**
     * 首页网络请求
     */

    //http://mapiv2.yinyuetai.com/component/prefecture.json?&type=1&deviceinfo={"aid":"10201036","os":"Android","ov":"5.0.2","rn":"480*800","dn":"LGL24","cr":"46000","as":"WIFI","uid":"dbcaa6c4482bc05ecb0bf39dabf207d2","clid":110025000}
    @GET("/component/prefecture.json?&type=1&")
    Observable<HomeResult> getHomeData(@Query("deviceinfo") String deviceinfo);

    /**
     * 获取视频播放地址
     */

    //http://mapiv2.yinyuetai.com/video/play.json?&id=2716931&type=1&deviceinfo={%22aid%22:%2210201036%22,%22os%22:%22Android%22,%22uid%22:%22dbcaa6c4482bc05ecb0bf39dabf207d2%22}
    @GET("/video/play.json?")
    Observable<VideoPlayResult> getVideoPlayUrl(
        @Query("id") int id, @Query("type") int type, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取视频弹幕
     * http://mapiv2.yinyuetai.com/barrage/list.json?&videoId=2717467&deviceinfo=
     */

    @GET("/barrage/list.json?")
    Observable<VideoBarrageResult> getVideoBarrageData(
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取视频详情内容
     * http://mapiv2.yinyuetai.com/video/detail.json?&videoId=2720900&type=1&
     */

    @GET("/video/detail.json?")
    Observable<VideoDetailResult> getVideoDetail(
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取视频大家都在看视频
     * http://mapiv2.yinyuetai.com/video/detail_mv_more.json?&offset=0&size=36&videoId=2720900&type=1&deviceinfo=
     * http://mapiv2.yinyuetai.com/video/detail_mv_more.json?&size=36&videoId=2720900&type=1&
     */
    @GET("/video/detail_mv_more.json?")
    Observable<EveryoneWatchResult> getEveryoneWatchData(
        @Query("size") int size,
        @Query("videoId") int videoId,
        @Query("type") int type, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取悦单数据
     * http://mapiv2.yinyuetai.com/playlist/detail.json?&playListId=4564394&deviceinfo=
     */
    @GET("/playlist/detail.json?")
    Observable<YueDanResult> getPlayListData(
        @Query("playListId") int playListId, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取艺人信息数据
     * http://mapiv2.yinyuetai.com/artist/info.json?&artistId=9265&
     */
    @GET("/artist/info.json?")
    Observable<ArtistInfoResult> getArtistInfo(
        @Query("artistId") int artistId, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取更多MV数据
     * http://mapiv2.yinyuetai.com/video/detail_mv_more.json?&offset=0&type=1&videoId=2724108&size=36&
     */

    @GET("/video/detail_mv_more.json?")
    Observable<MoreMvResult> getMoreMvList(
        @Query("type") int type,
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取更多收录这首MV的悦单列表
     * http://mapiv2.yinyuetai.com/video/detail_playlist_more.json?&offset=0&videoId=2728594&size=36&deviceinfo
     */
    @GET("/video/detail_playlist_more.json?")
    Observable<MorePlayVideoResult> getMorePlayVideoList(
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取相似悦单列表
     * http://mapiv2.yinyuetai.com/playlist/similar.json?&playListId=4571602&offset=0&videoId=2728739&size=36
     */

    @GET("/playlist/similar.json?")
    Observable<SimilartyPlayListResult> getSimiList(
        @Query("playListId") int playListId,
        @Query("offset") int offset,
        @Query("videoId") int videoId,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取单个视频的悦单列表数据
     * http://mapiv2.yinyuetai.com/playlist/video_rec.json?&playListId=4556001&videoId=2716939&deviceinfo=
     */
    @GET("/playlist/video_rec.json?")
    Observable<YueDanResult> getOneVideoPlayList(
        @Query("playListId") int playListId,
        @Query("videoId") int videoId, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取下载视频的相关信息
     * http://mapiv2.yinyuetai.com/video/play_batch.json?&videoIds=[2711923%2C2303914%2C]&videoType=2&deviceinfo=
     */

    @GET("/video/play_batch.json?")
    Observable<VideoDownloadResult> getVideoDownloadInfo(
        @Query("videoIds") String videoIds,
        @Query("videoType") int videoType, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取首页更多 tab 类型
     * http://mapiv2.yinyuetai.com/common/params.json?&position=[14]&deviceinfo=
     */

    @GET("/common/params.json?")
    Observable<TabResult> getTabList(
        @Query("position") String position, @Query("deviceinfo") String deviceinfo);

    /**
     * 获取更多mV
     * http://mapiv2.yinyuetai.com/component/list.json?&area=ALL&offset=0&size=20&deviceinfo=
     */
    @GET
    Observable<HomeMoreMvResult> getMoreMvList(
        @Url String url,
        @Query("area") String area,
        @Query("offset") int offset,
        @Query("size") int size, @Query("deviceinfo") String deviceinfo);
}
