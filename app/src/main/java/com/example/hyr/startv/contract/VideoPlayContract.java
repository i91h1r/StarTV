package com.example.hyr.startv.contract;

import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.result.VideoBarrageResult;
import com.example.hyr.startv.result.VideoDetailResult;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.result.VideoPlayResult;
import com.example.hyr.startv.result.YueDanResult;
import com.github.hyr0318.baselibrary.view.BaseView;

/**
 * Description:
 * 作者：hyr on 2016/11/9 16:34
 * 邮箱：2045446584@qq.com
 */
public class VideoPlayContract {

    public interface View extends BaseView {

        void refreshVideoPlayData(VideoPlayResult videoPlayResult);

        void refreshVideoBarragrData(VideoBarrageResult videoBarrageResult);

        void refreshVideoDetailData(VideoDetailResult videoDetailResult);

        void refreshEveryWatchData(EveryoneWatchResult everyoneWatchResult);

        void refreshPlayListData(YueDanResult yueDanResult);

        void refreshMoreMvData(MoreMvResult moreMvResult);

        void refreshMorePlayListData(MorePlayVideoResult morePlayVideoResult);

        void refreshSimilartyPlayListData(SimilartyPlayListResult similartyPlayListResult);

        void refreshOneVideoPlayList(YueDanResult yueDanResult);

        void downLoadVideo(VideoDownloadResult videoDownloadResult);

        void refreshMoreMostPeople(EveryoneWatchResult everyoneWatchResult);

        void reloadData(int type);

    }


    public interface Presenter {

        void getVideoPlayData(int id, int type);

        void getVideoDetil(int id, boolean isRefresh, boolean isAdd);

        void getPlayListDetail(int playListId);

        void getDetailMvMore(int type, int videoId, int size);

        void getMorePlayList(int videoId, int size);

        void getSimiPlayList(int playListId, int offset, int videoId, int size);

        void getOneVideoPlayList(int playListId, int videoId);

        void getMostPeopleList(int offset, int type, int videoId, int size);

        void getVideoDownloadInfo(String videoIds, int videoType);
    }


    public interface Model {

        void loadVideoPlayData(int id, int type);

        void loadVideoDetial(int id, boolean isRefresh, boolean isAdd);

        void loadPlayListDetail(int playListId);

        void loadDetailMvMore(int type, int videoId, int size);

        void loadMorePlayList(int videoId, int size);

        void loadSimiPlayList(int playListId, int offset, int videoId, int size);

        void loadOneVideoPlayList(int playListId, int videoId);

        void loadMoreMostPeople(int offset, int type, int videoId, int size);

        void loadVideoDownloadInfo(String videoIds, int videoType);
    }

}