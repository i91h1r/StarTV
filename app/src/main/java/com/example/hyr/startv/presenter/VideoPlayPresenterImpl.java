package com.example.hyr.startv.presenter;

import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.VideoPlayContract;
import com.example.hyr.startv.listener.BaseMvPlayListListener;
import com.example.hyr.startv.model.VideoPlayModelImpl;
import com.example.hyr.startv.result.BaseResult;
import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.result.VideoBarrageResult;
import com.example.hyr.startv.result.VideoDetailResult;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.result.VideoPlayResult;
import com.example.hyr.startv.result.YueDanResult;
import com.github.hyr0318.baselibrary.loading.LoadingViewController;

/**
 * Created by MVPHelper on 2016/11/09
 */

public class VideoPlayPresenterImpl
    implements VideoPlayContract.Presenter,
    BaseMvPlayListListener<BaseResult> {

    private VideoPlayContract.View videoView;
    private final VideoPlayModelImpl videoPlayModel;
    LoadingViewController bottomLoadingView;


    public VideoPlayPresenterImpl(VideoPlayContract.View view, LoadingViewController bottomLoadingView) {

        this.videoView = view;

        this.bottomLoadingView = bottomLoadingView;

        videoPlayModel = new VideoPlayModelImpl(this);

    }


    @Override public void getVideoPlayData(int id, int type) {

        videoPlayModel.loadVideoPlayData(id, type);

    }


    @Override public void getVideoDetil(int id, boolean isRefresh, boolean isAdd) {

        videoPlayModel.loadVideoDetial(id, isRefresh, isAdd);
    }


    @Override public void getPlayListDetail(int playListId) {
        videoPlayModel.loadPlayListDetail(playListId);
    }


    @Override public void getDetailMvMore(int type, int videoId, int size) {

        videoPlayModel.loadDetailMvMore(type, videoId, size);
    }


    @Override public void getMorePlayList(int videoId, int size) {
        videoPlayModel.loadMorePlayList(videoId, size);
    }


    @Override public void getSimiPlayList(int playListId, int offset, int videoId, int size) {

        videoPlayModel.loadSimiPlayList(playListId, offset, videoId, size);
    }


    @Override public void getOneVideoPlayList(int playListId, int videoId) {
        videoPlayModel.loadOneVideoPlayList(playListId, videoId);
    }


    @Override public void getMostPeopleList(int offset, int type, int videoId, int size) {
        videoPlayModel.loadMoreMostPeople(offset, type, videoId, size);
    }


    @Override public void getVideoDownloadInfo(String videoIds, int videoType) {
        videoPlayModel.loadVideoDownloadInfo(videoIds, videoType);
    }


    @Override public void onMvPlayListSuccess(int type, BaseResult data) {

        switch (type) {

            case Constants.SIMILARTY_PLAY_LIST:
                videoView.refreshSimilartyPlayListData(((SimilartyPlayListResult) data));
                break;
            case Constants.ONE_VIDEO_PLAY_LIST:

                videoView.refreshOneVideoPlayList(((YueDanResult) data));

                break;
            case Constants.VIDEO_DOWNLOAD_INFO:

                videoView.downLoadVideo(((VideoDownloadResult) data));
                break;

            case Constants.VIDEO_BARRAGE_LIST:

                videoView.refreshVideoBarragrData(((VideoBarrageResult) data));

                break;

            case Constants.VIDEO_PLAY_DATA:

                videoView.refreshVideoPlayData(((VideoPlayResult) data));

                break;

            case Constants.VIDEO_DETAIL_DATA:
                videoView.refreshVideoDetailData(((VideoDetailResult) data));
                break;

            case Constants.EVERY_ONE_WATCH:
                videoView.refreshEveryWatchData(((EveryoneWatchResult) data));

                break;
            case Constants.PLAY_LIST_DATA:
                videoView.refreshPlayListData(((YueDanResult) data));
                break;

            case Constants.MORE_MV_PLAY_LIST:

                videoView.refreshMoreMvData(((MoreMvResult) data));

                break;

            case Constants.MORE_PLAY_LIST:
                videoView.refreshMorePlayListData(((MorePlayVideoResult) data));
                break;

            case Constants.MORE_MOST_PEOPLE_LIST:

                videoView.refreshMoreMostPeople(((EveryoneWatchResult) data));
                break;
        }

    }


    @Override public void onMvPlayError(int type, String msg) {

        switch (type) {
            //同艺人其他MV
            case Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH));

                break;
            //同艺人其他MV
            case Constants.Integers.MORE_MV_TYPE_ARTIST_OTHER_VIDEO:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.Integers.MORE_MV_TYPE_ARTIST_OTHER_VIDEO));

                break;

            //猜你喜欢
            case Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE));

                break;

            //收入这首MV的悦单
            case Constants.MORE_PLAY_LIST:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.MORE_PLAY_LIST));

                break;
            //相似悦单
            case Constants.SIMILARTY_PLAY_LIST:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.SIMILARTY_PLAY_LIST));

                break;
            //大部分人看了
            case Constants.MORE_MOST_PEOPLE_LIST:
                bottomLoadingView.showError("",
                    view -> videoView.reloadData(Constants.MORE_MOST_PEOPLE_LIST));

                break;
            default:
                videoView.showError("");
                break;
        }

    }


    @Override public void onMvPlayException(int type, String msg) {

        videoView.showException("");
    }
}