package com.example.hyr.startv.ui.video;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.hyr.startv.R;
import com.example.hyr.startv.cache.SettingManager;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.VideoPlayContract;
import com.example.hyr.startv.listener.OnMoreItemClickListener;
import com.example.hyr.startv.listener.OnVideoMoreClickListener;
import com.example.hyr.startv.presenter.VideoPlayPresenterImpl;
import com.example.hyr.startv.result.ArtistsBean;
import com.example.hyr.startv.result.EveryoneWatchResult;
import com.example.hyr.startv.result.MoreMvResult;
import com.example.hyr.startv.result.MorePlayVideoResult;
import com.example.hyr.startv.result.SimilartyPlayListResult;
import com.example.hyr.startv.result.VideoBarrageResult;
import com.example.hyr.startv.result.VideoDetailResult;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.example.hyr.startv.result.VideoPlayResult;
import com.example.hyr.startv.result.YueDanResult;
import com.example.hyr.startv.ui.artist.ArtistInfoActivity;
import com.example.hyr.startv.ui.video.section.ArtistOtherVideoSection;
import com.example.hyr.startv.ui.video.section.ArtistSectionAdapter;
import com.example.hyr.startv.ui.video.section.BottomMostPeopleAdapter;
import com.example.hyr.startv.ui.video.section.BottomSectionAdapter;
import com.example.hyr.startv.ui.video.section.EveryOneWatchSection;
import com.example.hyr.startv.ui.video.section.MorePlayListSectionAdapter;
import com.example.hyr.startv.ui.video.section.MorePlayListVideoSeationAdapter;
import com.example.hyr.startv.ui.video.section.MostPeopleSection;
import com.example.hyr.startv.ui.video.section.RelatePlayListSetionAdapter;
import com.example.hyr.startv.ui.video.section.RelatedVideoSectionAdapter;
import com.example.hyr.startv.ui.video.section.SimilarityPlayListSection;
import com.example.hyr.startv.ui.video.section.SimilartyPlayListAdapter;
import com.example.hyr.startv.ui.video.section.yuedanAdapter;
import com.example.hyr.startv.utils.GlideCircleImage;
import com.example.hyr.startv.utils.VideoManager;
import com.example.hyr.startv.widget.FloatingVideoMeun;
import com.example.hyr.startv.widget.sectioned.Section;
import com.example.hyr.startv.widget.sectioned.SectionedRecyclerViewAdapter;
import com.example.hyr.startv.widget.sectioned.StatelessSection;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.loading.LoadingViewController;
import com.github.hyr0318.baselibrary.net.NetUtils;
import com.google.gson.Gson;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.mediaplayer.onVideoPlayListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import static android.provider.MediaStore.Audio.AudioColumns.ARTIST_ID;
import static com.example.hyr.startv.R.id.iv_hint_des;

/**
 * Description:
 * 作者：hyr on 2016/11/9 15:23
 * 邮箱：2045446584@qq.com
 */
public class VideoPlayActivity extends BaseActivity implements VideoPlayContract.View,
    View.OnClickListener, OnVideoMoreClickListener, OnMoreItemClickListener {
    public static final String VIDEO_TYPE = "type";
    public static final String VIDEO_ID = "id";
    private static final String SWITCH_DANMU_PORTRAIT = "switch_danmu_portrait";
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private static final String BANNER_OPEN_TYPE = "banner_open_type";
    private int video_download_type;
    private static int mState = SHRINK_UP_STATE;//默认收起状态
    private SuperPlayer superPlayer;
    private int id;
    private int type;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;
    private EditText etDanmu;
    private ImageView ivSendDm, ivDownload, ivAuthor, ivHintDes;
    private BaseDanmaku danmaku;
    private TextView tvPhoneCount, tvPcCount, tvVideoDes, tvAuthor, tvRegdate, tvTitle,
        tvBottomTitle, tvPlayCount;
    private LinearLayout llVideoDetail, llCount, llMore, llDanmu;
    private RecyclerView rcyEoWatch, recyclerView, yuedanRecycle, authorRecycle, mBottomRecycleView;
    private VideoPlayPresenterImpl videoPlayPresenter;
    private SectionedRecyclerViewAdapter videoSection;
    private EveryOneWatchSection everyOneWatchSection;
    private RelativeLayout rlEvery;
    private com.example.hyr.startv.ui.video.section.yuedanAdapter yuedanAdapter;
    private SectionedRecyclerViewAdapter authorSection;
    private int mCurrentPosition = 0;
    private List<ArtistsBean> artistsBeanList = new ArrayList<>();
    private ArtistSectionAdapter artistSectionAdapter;
    private View mBottomSheet;
    private BottomSheetBehavior<View> mBehavior;
    private SectionedRecyclerViewAdapter mBottomRecycleSection;
    private List<EveryoneWatchResult.DataBean> everyList;
    private BottomSectionAdapter bottomSectionAdapter;
    private LoadingViewController bottomLoadingView;
    private MorePlayListSectionAdapter morePlayListSectionAdapter;
    private YueDanResult yueDanResult;
    int showType = 1;
    private List<YueDanResult.DataBean.PlayListVideosBean> playListVideos;
    private int playListId;
    private SimilartyPlayListAdapter similartyPlayListAdapter;
    private SimilarityPlayListSection similarityPlayListSection;
    private MostPeopleSection mostPeopleSection;
    private List<YueDanResult.DataBean.SimilarityPlayListBean> similarityPlayList;
    private List<YueDanResult.DataBean.MostPeopleVideosBean> mostPeopleVideos;
    private MorePlayListVideoSeationAdapter morePlayListVideoSeationAdapter;
    private BottomMostPeopleAdapter bottomMostPeopleAdapter;
    private FloatingVideoMeun floatingVideoMeun;
    private FloatingActionButton fabSuper, fabHd, fabNomal;
    private String videoPlayUrl, videoPlayTitle, shdUrl, uhdUrl, hdUrl;
    private Subscription progressSub;
    private VideoPlayResult videoPlayResult;
    private int[] videoIds;
    private RelatePlayListSetionAdapter relatePlayListSetionAdapter;
    private String bannerType;


    @Override protected void getViewById() {

        superPlayer = (SuperPlayer) findViewById(R.id.view_super_player);

        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);

        etDanmu = (EditText) findViewById(R.id.et_danmu);

        ivSendDm = (ImageView) findViewById(R.id.iv_send_danmu);

        tvPlayCount = (TextView) findViewById(R.id.tv_play_count);

        ivDownload = (ImageView) findViewById(R.id.iv_download);

        tvPhoneCount = (TextView) findViewById(R.id.tv_phone_count);

        tvPcCount = (TextView) findViewById(R.id.tv_pc_count);

        tvVideoDes = (TextView) findViewById(R.id.tv_des);

        ivAuthor = (ImageView) findViewById(R.id.civ_author);

        tvAuthor = (TextView) findViewById(R.id.tv_author);

        tvRegdate = (TextView) findViewById(R.id.tv_regdate);

        llVideoDetail = (LinearLayout) findViewById(R.id.ll_video_detail);

        rcyEoWatch = (RecyclerView) findViewById(R.id.rcy_everyWatch);

        ivHintDes = (ImageView) findViewById(iv_hint_des);

        llCount = (LinearLayout) findViewById(R.id.ll_count);

        recyclerView = (RecyclerView) findViewById(R.id.rcy_video);

        tvTitle = (TextView) findViewById(R.id.tv_title);

        yuedanRecycle = (RecyclerView) findViewById(R.id.yuedan_recycle);

        rlEvery = (RelativeLayout) findViewById(R.id.rl_every);

        authorRecycle = (RecyclerView) findViewById(R.id.authorRecycle);

        mBottomSheet = findViewById(R.id.bottomSheet);

        mBottomRecycleView = (RecyclerView) mBottomSheet.findViewById(R.id.bottom_recycle);

        mBehavior = BottomSheetBehavior.from(mBottomSheet);

        tvBottomTitle = (TextView) mBottomSheet.findViewById(R.id.tv_bottom_title);

        llMore = (LinearLayout) findViewById(R.id.more);

        llDanmu = (LinearLayout) findViewById(R.id.ll_send_danmu);

    }


    @Override protected View getLoadingView() {

        return llVideoDetail;

    }


    @Override protected void initViewsAndEvents() {

        initViews();

        initFloatingVideoButton();

        initBottomRecycleView();


        artistSectionAdapter = new ArtistSectionAdapter(mContext,
            artistsBeanList);

        videoPlayPresenter = new VideoPlayPresenterImpl(this, bottomLoadingView);

        if (bannerType.equals("play")) {
            videoPlayPresenter.getVideoDetil(id, true, false);

            videoPlayPresenter.getVideoPlayData(id, type);
        } else if (bannerType.equals("playlist")) {
            videoPlayPresenter.getPlayListDetail(id);
        }

        initDanmuku();

        showLoading();

        llMore.setOnClickListener(this);

        superPlayer.setOnVideoPlayListener(new onVideoPlayListener() {
            @Override public void onPlay() {
                if (floatingVideoMeun != null) {
                    floatingVideoMeun.rotateStart();
                }

            }


            @Override public void onPause() {
                if (floatingVideoMeun != null) {
                    floatingVideoMeun.rotateStop();
                }
            }
        });

        initListener();
    }


    private void initListener() {

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                Dialog dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);

                View inflate = LayoutInflater.from(mContext)
                    .inflate(R.layout.bottom_video_download_type_layout, null);

                TextView tvHD = (TextView) inflate.findViewById(R.id.type_hd);

                tvHD.setOnClickListener(view1 -> {

                    video_download_type = 3;

                    videoPlayPresenter.getVideoDownloadInfo(new Gson().toJson(videoIds),
                        video_download_type);

                    dialog.cancel();
                });

                TextView tvNormal = (TextView) inflate.findViewById(R.id.type_normal);
                tvNormal.setOnClickListener(view12 -> {

                    video_download_type = 2;

                    videoPlayPresenter.getVideoDownloadInfo(new Gson().toJson(videoIds),
                        video_download_type);

                    dialog.cancel();
                });

                TextView cancle = (TextView) inflate.findViewById(R.id.cancle);
                cancle.setOnClickListener(view13 -> dialog.cancel());
                dialog.setContentView(inflate);

                Window window = dialog.getWindow();

                window.setGravity(Gravity.BOTTOM);

                WindowManager.LayoutParams attributes = window.getAttributes();

                attributes.width = WindowManager.LayoutParams.MATCH_PARENT;

                attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;

                window.setAttributes(attributes);

                dialog.show();

            }
        });

    }


    private void initFloatingVideoButton() {

        floatingVideoMeun = (FloatingVideoMeun) findViewById(R.id.fvm);

        fabSuper = (FloatingActionButton) findViewById(R.id.fab_super);

        fabSuper.setOnClickListener(view -> {
            videoPlayUrl = shdUrl;

            playVideo();
        });

        fabHd = (FloatingActionButton) findViewById(R.id.fab_hd);

        fabHd.setOnClickListener(view -> {
            videoPlayUrl = uhdUrl;

            playVideo();
        });

        fabNomal = (FloatingActionButton) findViewById(R.id.fab_nomal);

        fabNomal.setOnClickListener(view -> {
            videoPlayUrl = hdUrl;

            playVideo();
        });

    }


    /**
     * 更新floating视频播放进度条
     */
    private void updateProgress() {

        progressSub = Observable.interval(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                aLong -> {
                    float progress = superPlayer.getCurrentPosition() * 1.0f /
                        superPlayer.getDuration() * 100;

                    floatingVideoMeun.setProgress(progress);
                });
    }


    /**
     * 初始化底部弹出框列表
     */
    private void initBottomRecycleView() {

        bottomLoadingView = new LoadingViewController(
            mBottomRecycleView);

        mBottomRecycleView.setLayoutManager(new LinearLayoutManager(mContext));

        mBottomRecycleView.setHasFixedSize(true);

        mBottomRecycleSection = new SectionedRecyclerViewAdapter();

        mBottomRecycleView.setAdapter(mBottomRecycleSection);

        bottomSectionAdapter = new BottomSectionAdapter(mContext);

        mBottomRecycleSection.addSection(bottomSectionAdapter);

        bottomSectionAdapter.setOnMoreItemClickListener(this);

    }


    /**
     * 初始化弹幕
     */
    private void initDanmuku() {

        danmakuContext = DanmakuContext.create();

        if (danmakuView != null) {

            BaseDanmakuParser baseDanmakuParser = new BaseDanmakuParser() {
                @Override protected IDanmakus parse() {
                    return new Danmakus();
                }
            };

            superPlayer.onPrepared(() -> danmakuView.start());

            danmakuView.prepare(baseDanmakuParser, danmakuContext);

            danmakuView.enableDanmakuDrawingCache(true);
        }

    }


    private void initViews() {

        boolean setting = SettingManager.getInstance().getSetting(SWITCH_DANMU_PORTRAIT, false);

        if (setting) {

            superPlayer.ivDanmu.setBackgroundResource(R.drawable.danmu_switch_off_portrait);

            llDanmu.setVisibility(View.VISIBLE);
        } else {
            superPlayer.ivDanmu.setBackgroundResource(
                R.drawable.danmu_switch_on_portrait);

            llDanmu.setVisibility(View.GONE);
        }
        superPlayer.ivDanmu.setOnClickListener(view -> {
            if (SettingManager.getInstance().getSetting(SWITCH_DANMU_PORTRAIT, false)) {

                SettingManager.getInstance().setSetting(SWITCH_DANMU_PORTRAIT, false);

                superPlayer.ivDanmu.setBackgroundResource(R.drawable.danmu_switch_on_portrait);

                llDanmu.setVisibility(View.GONE);

                danmakuView.hide();

            } else {
                SettingManager.getInstance().setSetting(SWITCH_DANMU_PORTRAIT, true);

                superPlayer.ivDanmu.setBackgroundResource(R.drawable.danmu_switch_off_portrait);

                llDanmu.setVisibility(View.VISIBLE);

                danmakuView.show();
            }
        });

        etDanmu.addTextChangedListener(new EditChangedListener());

        ivSendDm.setOnClickListener(this);

        ivHintDes.setOnClickListener(this);

        videoSection = new SectionedRecyclerViewAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 6);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                                @Override public int getSpanSize(int position) {

                                                    Section section
                                                        = videoSection.getSectionForPosition(
                                                        position);

                                                    switch (videoSection.getSectionItemViewType(position)) {

                                                        case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                                                            return 6;

                                                        case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                                                            return 2;

                                                        default:

                                                            if (section instanceof RelatePlayListSetionAdapter) {

                                                                return 2;
                                                            } else if (section instanceof SimilarityPlayListSection) {
                                                                return 2;
                                                            }
                                                            return 3;

                                                    }
                                                }
                                            }

        );

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(videoSection);

        //作者列表信息
        authorSection = new SectionedRecyclerViewAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        authorRecycle.setLayoutManager(layoutManager);

        authorRecycle.setAdapter(authorSection);

    }


    @Override protected void getIntentExtras(Bundle extras) {

        id = extras.getInt(VIDEO_ID);

        type = extras.getInt(VIDEO_TYPE);

        bannerType = extras.getString(BANNER_OPEN_TYPE);
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_video_play_layout;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    /**
     * 打开新的Activity
     */
    public static void open(Context context, int type, int id, String bannerType) {

        Intent intent = new Intent(context, VideoPlayActivity.class);

        intent.putExtra(VIDEO_TYPE, type);

        intent.putExtra(VIDEO_ID, id);

        intent.putExtra(BANNER_OPEN_TYPE, bannerType);

        context.startActivity(intent);
    }


    /**
     * 刷新视频播放数据
     */
    @Override public void refreshVideoPlayData(VideoPlayResult videoPlayResult) {

        videoPlayTitle = videoPlayResult.getData().getTitle();

        shdUrl = videoPlayResult.getData().getShdUrl();

        uhdUrl = videoPlayResult.getData().getUhdUrl();

        hdUrl = videoPlayResult.getData().getHdUrl();

        this.videoPlayResult = videoPlayResult;

        videoPlayUrl = videoPlayResult.getData().getUrl();

        playVideo();

    }


    /**
     * 重新播放视频
     */
    private void playVideo() {

        if (videoPlayUrl != null) {

            superPlayer.play(videoPlayUrl);

            superPlayer.setScaleType(SuperPlayer.SCALETYPE_FILLPARENT);

            superPlayer.setTitle(videoPlayTitle);

            superPlayer.setPlayerWH(0,
                superPlayer.getMeasuredHeight());

            updateProgress();

            updatePlayStatus();
        }
    }


    private void updatePlayStatus() {

        floatingVideoMeun.rotateStart();

    }


    /**
     * 刷新弹幕列表数据
     */

    @Override public void refreshVideoBarragrData(VideoBarrageResult videoBarrageResult) {

        danmakuView.removeAllDanmakus(true);

        for (VideoBarrageResult.DataBean dataBean : videoBarrageResult.getData()) {

            danmakuView.postDelayed(() -> addDanmuku(dataBean, false),
                Constants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);

        }

    }


    /**
     * 刷新视频相关的数据
     */
    @Override
    public void refreshVideoDetailData(VideoDetailResult videoDetailResult) {

        hideLoading();

        int videoId = videoDetailResult.getData().getVideoId();

        videoIds = new int[] { videoId };

        String s1 = String.valueOf(videoIds);

        Log.i("videoIds--------------", s1);

        videoSection.removeAllSections();

        authorSection.removeAllSections();

        List<VideoDetailResult.DataBean.ArtistsBean> artists = videoDetailResult.getData()
            .getArtists();

        artistsBeanList.clear();

        updateFabBackgroud(artists.get(0).getArtistAvatar());

        for (VideoDetailResult.DataBean.ArtistsBean bean : artists) {
            ArtistsBean artistsBean = new ArtistsBean();

            artistsBean.setArea(bean.getArea());

            artistsBean.setArtistAvatar(bean.getArtistAvatar());

            artistsBean.setArtistId(bean.getArtistId());

            artistsBean.setArtistName(bean.getArtistName());

            artistsBeanList.add(artistsBean);

        }

        artistSectionAdapter.getArtists().clear();

        artistSectionAdapter.setArtists(artistsBeanList);


        artistSectionAdapter.setOnItemClickListener((view, position) -> {
            int artistId = artists.get(position).getArtistId();

            Intent intent = new Intent();

            intent.putExtra(ARTIST_ID, artistId);

            intent.setClass(mContext, ArtistInfoActivity.class);

            mContext.startActivity(intent);

            finish();
        });

        authorSection.addSection(artistSectionAdapter);

        authorSection.notifyDataSetChanged();

        tvPlayCount.setText(String.valueOf(videoDetailResult.getData().getTotalView()));

        tvPcCount.setText(String.valueOf(videoDetailResult.getData().getTotalPcView()));

        tvPhoneCount.setText(String.valueOf(videoDetailResult.getData().getTotalMobileView()));

        tvVideoDes.setText(videoDetailResult.getData().getDesc());

        Glide.with(mContext)
            .load(videoDetailResult.getData().getCreator().getLargeAvatar())
            .transform(new GlideCircleImage(mContext))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.default_head_img)
            .into(ivAuthor);

        tvAuthor.setText(videoDetailResult.getData().getCreator().getNickName());

        tvRegdate.setText(videoDetailResult.getData().getRegdate());

        //同艺人其他MV
        List<VideoDetailResult.DataBean.ArtistOtherVideosBean> artistOtherVideos = videoDetailResult
            .getData()
            .getArtistOtherVideos();

        if (artistOtherVideos != null && artistOtherVideos.size() > 0) {
            ArtistOtherVideoSection artistOtherVideoSection = new ArtistOtherVideoSection(mContext,
                artistOtherVideos);

            artistOtherVideoSection.setOnItemClickListener((view, position) -> {
                id = artistOtherVideos.get(position).getVideoId();

                reloadArtistOtherVideo();

            });

            artistOtherVideoSection.setOnVideoMoreClickListener(this);

            videoSection.addSection(artistOtherVideoSection);
        }

        //收入这首MV的悦单

        List<VideoDetailResult.DataBean.RelatedPlayListBean> relatedPlayList
            = videoDetailResult.getData().getRelatedPlayList();

        if (relatedPlayList != null && relatedPlayList.size() > 0) {
            relatePlayListSetionAdapter = new RelatePlayListSetionAdapter(
                mContext, relatedPlayList);

            relatePlayListSetionAdapter.setOnItemClickListener((view, position) -> {

                playListId = relatedPlayList.get(position).getPlayListId();

                superPlayer.onPause();

                showType = 2;

                showLoading();

                videoPlayPresenter.getPlayListDetail(
                    playListId);
            });

            videoSection.addSection(relatePlayListSetionAdapter);

            relatePlayListSetionAdapter.setOnVideoMoreClickListener(this);
        }

        //猜你喜欢

        List<VideoDetailResult.DataBean.RelatedVideosBean> relatedVideos
            = videoDetailResult.getData().getRelatedVideos();

        if (null != relatedVideos && relatedPlayList.size() > 0) {

            RelatedVideoSectionAdapter relatedVideoSectionAdapter = new RelatedVideoSectionAdapter(
                mContext, relatedVideos);

            videoSection.addSection(relatedVideoSectionAdapter);

            videoSection.notifyDataSetChanged();

            relatedVideoSectionAdapter.setOnItemClickListener((view, position) -> {
                id = relatedVideos.get(position).getVideoId();

                reloadArtistOtherVideo();

            });
            relatedVideoSectionAdapter.setOnVideoMoreClickListener(this);
        }
        videoSection.notifyDataSetChanged();
    }


    private void reloadArtistOtherVideo() {
        hintYuedan();

        showLoading();

        superPlayer.onPause();

        clearPlaying();

        videoPlayPresenter.getVideoPlayData(id, type);

        videoPlayPresenter.getVideoDetil(id,
            false, true);
    }


    private void hintYuedan() {

        tvTitle.setText(mContext.getResources().getString(R.string.most_people_play));

        yuedanRecycle.setVisibility(View.GONE);

        rcyEoWatch.setVisibility(View.VISIBLE);
    }


    private void clearPlaying() {
        if (everyOneWatchSection != null) {
            everyOneWatchSection.clearPlaying();

            everyOneWatchSection.notifyDataSetChanged();

        }

        if (yuedanAdapter != null) {
            yuedanAdapter.clearPlay();

            yuedanAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 刷新大部分人看了 数据
     */
    @Override public void refreshEveryWatchData(EveryoneWatchResult everyoneWatchResult) {

        hideLoading();

        everyList = everyoneWatchResult.getData();

        if (everyoneWatchResult != null && everyoneWatchResult.getData().size() > 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            rcyEoWatch.setLayoutManager(layoutManager);

            everyOneWatchSection = new EveryOneWatchSection(mContext,
                everyoneWatchResult.getData());

            rcyEoWatch.setAdapter(everyOneWatchSection);

            everyOneWatchSection.setOnItemCliclListener((view, position) -> {

                showType = 1;

                id = everyList.get(position).getVideoId();

                playNewVideoAndRelaseRes(position);

            });

        } else {
            rlEvery.setVisibility(View.GONE);
        }

    }


    /**
     * 刷新悦单数据
     */
    @Override public void refreshPlayListData(YueDanResult yueDanResult) {

        this.yueDanResult = yueDanResult;

        hideLoading();

        playListVideos = yueDanResult.getData()
            .getPlayListVideos();

        refreshYueDanDetail(yueDanResult);

        videoSection.removeAllSections();

        id = playListVideos.get(0).getVideoId();

        updateFabBackgroud(yueDanResult.getData()
            .getPlayListVideos()
            .get(0)
            .getArtists()
            .get(0)
            .getArtistAvatar());

        superPlayer.play(yueDanResult.getData().getPlayListVideos().get(0).getUrl());

        superPlayer.setTitle(yueDanResult.getData().getPlayListVideos().get(0).getTitle());

        yueDanResult.getData().getPlayListVideos().get(0).setPlaying(true);

        hintEveryOneWatch();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        yuedanRecycle.setLayoutManager(layoutManager);

        yuedanAdapter = new yuedanAdapter(VideoPlayActivity.this, yueDanResult.getData());

        yuedanRecycle.setAdapter(yuedanAdapter);

        yuedanAdapter.setOnItemClickListener((view, position) -> {

            superPlayer.onPause();

            reloadYueDanList(position, yueDanResult);

        });

        //添加相似悦单列表

        similarityPlayList = yueDanResult.getData().getSimilarityPlayList();

        similarityPlayListSection = new SimilarityPlayListSection(
            mContext, yueDanResult.getData().getSimilarityPlayList());

        similarityPlayListSection.setOnVideoMoreClickListener(this);

        similarityPlayListSection.setOnItemClickListener((view, position) -> {
            superPlayer.onPause();

            showLoading();

            videoPlayPresenter.getPlayListDetail(
                similarityPlayList.get(position).getPlayListId());
        });

        //添加大部分人看了
        mostPeopleSection = new MostPeopleSection(mContext,
            yueDanResult.getData().getMostPeopleVideos());

        mostPeopleVideos = yueDanResult.getData().getMostPeopleVideos();

        mostPeopleSection.setOnItemClickListener((view, position) -> {

            showType = 1;

            hintYuedan();

            showLoading();

            superPlayer.pause();

            clearPlaying();

            id = mostPeopleVideos.get(position).getVideoId();

            videoPlayPresenter.getVideoPlayData(id, type);

            videoPlayPresenter.getVideoDetil(id,
                false, true);

        });
        mostPeopleSection.setOnVideoMoreClickListener(this);

        videoSection.addSection(similarityPlayListSection);

        videoSection.addSection(mostPeopleSection);

        videoSection.notifyDataSetChanged();

    }


    private void reloadYueDanList(int position, YueDanResult yueDanResult) {

        id = playListVideos.get(position).getVideoId();

        mCurrentPosition = position;

        videoPlayPresenter.getVideoPlayData(
            id, type);

        updateFabBackgroud(yueDanResult.getData()
            .getPlayListVideos()
            .get(position)
            .getArtists()
            .get(0)
            .getArtistAvatar());

        videoPlayPresenter.getOneVideoPlayList(playListId, id);
        releaseYueRes(playListVideos);

        playListVideos.get(position).setPlaying(true);

        yuedanAdapter.notifyDataSetChanged();

        if (everyOneWatchSection != null) {

            everyOneWatchSection.notifyDataSetChanged();
        }
        refreshYueDanDetail(yueDanResult);
    }


    /**
     * 更新底部更多列表MV
     */
    @Override public void refreshMoreMvData(MoreMvResult moreMvResult) {

        mBottomRecycleSection.removeAllSections();

        mBottomRecycleSection.addSection(bottomSectionAdapter);
        hideLoading();

        bottomLoadingView.restore();

        bottomSectionAdapter.getData().clear();

        List<MoreMvResult.DataBean> data = moreMvResult.getData();

        bottomSectionAdapter.setData(data);

        mBottomRecycleSection.notifyDataSetChanged();

    }


    @Override public void refreshMorePlayListData(MorePlayVideoResult morePlayVideoResult) {
        bottomLoadingView.restore();

        mBottomRecycleSection.removeAllSections();

        morePlayListSectionAdapter = new MorePlayListSectionAdapter(
            mContext, morePlayVideoResult.getData());

        morePlayListSectionAdapter.setOnMoreItemClickListener(this);

        morePlayListSectionAdapter.setType(Constants.Integers.MORE_PLAY_LIST);

        mBottomRecycleSection.addSection(morePlayListSectionAdapter);

        mBottomRecycleSection.notifyDataSetChanged();
    }


    /**
     * 刷新相似悦单列表数据
     */
    @Override
    public void refreshSimilartyPlayListData(SimilartyPlayListResult similartyPlayListResult) {
        mBottomRecycleSection.removeAllSections();

        bottomLoadingView.restore();

        similartyPlayListAdapter = new SimilartyPlayListAdapter(mContext,
            similartyPlayListResult.getData());
        similartyPlayListAdapter.setType(Constants.SIMILARTY_PLAY_LIST);
        similartyPlayListAdapter.setOnMoreItemClickListener(this);

        mBottomRecycleSection.addSection(similartyPlayListAdapter);

        mBottomRecycleSection.notifyDataSetChanged();

    }


    /**
     * 悦单列表 视频点击  界面刷新
     */
    @Override public void refreshOneVideoPlayList(YueDanResult yueDanResult) {

        similarityPlayList = yueDanResult.getData().getSimilarityPlayList();

        mostPeopleVideos = yueDanResult.getData()
            .getMostPeopleVideos();

        similarityPlayListSection.getSimilarityPlayList().clear();

        similarityPlayListSection.setSimilarityPlayList(
            yueDanResult.getData().getSimilarityPlayList());

        mostPeopleSection.getMostPeopleVideos().clear();

        mostPeopleSection.setMostPeopleVideos(yueDanResult.getData().getMostPeopleVideos());

        videoSection.notifyDataSetChanged();
    }


    /**
     * 获取下载视频信息
     */
    @Override public void downLoadVideo(VideoDownloadResult videoDownloadResult) {
        List<VideoDownloadResult.DataBean> data = videoDownloadResult.getData();

        int download = VideoManager.getInstance().download(data, video_download_type);

        Toast toast = new Toast(mContext);

        switch (download) {

            case VideoDownloadResult.DataBean.DOWNLOAD_COMPLETE:

                View inflate = LayoutInflater.from(mContext)
                    .inflate(R.layout.toast_downloading_layout, null);
                toast.setView(inflate);

                break;

            case VideoDownloadResult.DataBean.DOWNLOAD_ING:

                View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.toast_complate_layout, null);

                toast.setView(view);

                break;
        }
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }


    @Override public void refreshMoreMostPeople(EveryoneWatchResult everyoneWatchResult) {
        //添加大部分看了底部弹出列表
        mBottomRecycleSection.removeAllSections();

        bottomLoadingView.restore();

        bottomMostPeopleAdapter = new BottomMostPeopleAdapter(mContext,
            everyoneWatchResult.getData());
        mBottomRecycleSection.addSection(bottomMostPeopleAdapter);
        bottomMostPeopleAdapter.setType(Constants.Integers.MORE_MV_TYPE_MOST_PEOPLE_WATCH);
        bottomMostPeopleAdapter.setOnMoreItemClickListener(this);
        mBottomRecycleSection.notifyDataSetChanged();
    }


    /**
     * 隐藏大部分人看了列表
     */
    private void hintEveryOneWatch() {
        yuedanRecycle.setVisibility(View.VISIBLE);

        rcyEoWatch.setVisibility(View.GONE);

        tvTitle.setText(mContext.getResources().getString(R.string.yuedan_list));
    }


    private void refreshYueDanDetail(YueDanResult yueDanResult) {

        List<YueDanResult.DataBean.PlayListVideosBean.ArtistsBean> artists = yueDanResult.getData()
            .getPlayListVideos()
            .get(mCurrentPosition)
            .getArtists();

        for (YueDanResult.DataBean.PlayListVideosBean.ArtistsBean bean : artists) {

            ArtistsBean artistsBean = new ArtistsBean();

            artistsBean.setArea(bean.getArea());

            artistsBean.setArtistAvatar(bean.getArtistAvatar());

            artistsBean.setArtistId(bean.getArtistId());

            artistsBean.setArtistName(bean.getArtistName());

            artistsBeanList.clear();

            artistsBeanList.add(artistsBean);
        }

        artistSectionAdapter.setArtists(artistsBeanList);

        authorSection.notifyDataSetChanged();

        tvVideoDes.setText(yueDanResult.getData().getDesc());

        Glide.with(mContext)
            .load(yueDanResult.getData().getCreator().getSmallAvatar())
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.default_head_img)
            .transform(new GlideCircleImage(mContext))
            .into(ivAuthor);

        tvRegdate.setText(yueDanResult.getData().getCreatedTime());

        tvPlayCount.setText(String.valueOf(yueDanResult.getData().getTotalView()));

        llCount.setVisibility(View.GONE);
    }


    /**
     * 恢复大部分人看了列表正在播放状态
     */
    private void releaseRes(List<EveryoneWatchResult.DataBean> everyList) {

        for (EveryoneWatchResult.DataBean dataBean : everyList) {
            dataBean.setPlaying(false);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (superPlayer != null) {
            superPlayer.onPause();
        }
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }


    /**
     * 恢复 悦单列表正在播放状态
     */
    private void releaseYueRes(List<YueDanResult.DataBean.PlayListVideosBean> playListVideos) {

        for (YueDanResult.DataBean.PlayListVideosBean playListVideosBean : playListVideos) {
            playListVideosBean.setPlaying(false);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (superPlayer != null) {
            superPlayer.onResume();
        }

        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.resume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (superPlayer != null) {
            superPlayer.onDestroy();
        }

        if (progressSub != null) {
            if (progressSub.isUnsubscribed()) {
                progressSub.unsubscribe();
            }
        }

        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (superPlayer != null) {
            superPlayer.onConfigurationChanged(newConfig);
        }
    }


    @Override
    public void onBackPressed() {
        if (superPlayer != null && superPlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }


    /**
     * 添加弹幕
     */
    private void addDanmuku(VideoBarrageResult.DataBean dataBeen, boolean withBorder) {

        danmaku = danmakuContext.mDanmakuFactory.createDanmaku(
            BaseDanmaku.TYPE_SCROLL_RL);

        danmaku.text = dataBeen.getText();

        danmaku.padding = 5;

        danmaku.textSize = dataBeen.getTextSize();

        if (dataBeen.getTextColor().startsWith("#")) {

            danmaku.textColor = Color.parseColor(dataBeen.getTextColor());
        } else {
            danmaku.textColor = Color.parseColor("#" + dataBeen.getTextColor());
        }

        danmaku.setTime(dataBeen.getTime());

        if (withBorder) {
            danmaku.borderColor = Color.GRAY;
        }
        if (null != danmakuView) {
            danmakuView.addDanmaku(danmaku);
        }

    }


    @Override public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_send_danmu:

                if (!TextUtils.isEmpty(etDanmu.getText())) {
                    VideoBarrageResult.DataBean dataBean = new VideoBarrageResult.DataBean();

                    dataBean.setText(etDanmu.getText().toString());

                    dataBean.setTextColor(String.valueOf(
                        mContext.getResources().getString(R.string.def_danmu_text_color)));

                    dataBean.setTextSize(
                        mContext.getResources().getDimensionPixelSize(R.dimen.def_danmu_text_size));

                    dataBean.setTime((int) danmakuView.getCurrentTime());

                    addDanmuku(dataBean, true);

                    etDanmu.setText("");

                    ivSendDm.setBackgroundResource(R.mipmap.danmu_send_unclick);
                } else {
                    ivSendDm.setBackgroundResource(R.drawable.send_danmu_selector);
                }

                break;

            case R.id.iv_hint_des:

                if (mState == SPREAD_STATE) {

                    mState = SHRINK_UP_STATE;

                    tvVideoDes.setMaxLines(2);

                    ivHintDes.setBackgroundResource(R.mipmap.video_img_hide_desc);

                    llCount.setVisibility(View.GONE);

                } else {
                    tvVideoDes.setMaxLines(Integer.MAX_VALUE);

                    mState = SPREAD_STATE;

                    ivHintDes.setBackgroundResource(R.mipmap.video_img_show_desc);

                    if (showType == 2) {
                        llCount.setVisibility(View.GONE);
                    } else {
                        llCount.setVisibility(View.VISIBLE);
                    }

                }
                break;

            case R.id.more:
                if (mBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                bottomSectionAdapter.getData().clear();

                if (showType == 1) {

                    bottomLoadingView.showLoading();

                    bottomLoadingView.setLoadingBgColor(
                        mContext.getResources().getColor(R.color.white));

                    bottomSectionAdapter.setType(Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH);

                    videoPlayPresenter.getDetailMvMore(
                        Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH, id,
                        Constants.Integers.DEFAULT_SIZE);

                    setBottomBabiorTitle(
                        mContext.getResources().getString(R.string.most_people_play));

                } else if (showType == 2) {

                    setBottomBabiorTitle(
                        mContext.getResources().getString(R.string.video_play_list));

                    mBottomRecycleSection.removeAllSections();

                    morePlayListVideoSeationAdapter = new MorePlayListVideoSeationAdapter(mContext,
                        playListVideos);

                    morePlayListVideoSeationAdapter.setType(Constants.Integers.YUE_DAN_LIST);

                    mBottomRecycleSection.addSection(morePlayListVideoSeationAdapter);

                    mBottomRecycleSection.notifyDataSetChanged();

                    morePlayListVideoSeationAdapter.setOnMoreItemClickListener(this);

                }

                break;
        }
    }


    /**
     * 播放新视频和刷新界面
     */
    private void playNewVideoAndRelaseRes(int position) {
        superPlayer.onPause();

        videoPlayPresenter.getVideoPlayData(

            everyList.get(position).getVideoId(), type);

        videoPlayPresenter.getVideoDetil(
            everyList.get(position).getVideoId(), false, false);

        releaseRes(everyList);

        everyList.get(position).setPlaying(true);

        everyOneWatchSection.notifyDataSetChanged();
    }


    @Override public void onMoreClick(View view, StatelessSection statelessSection) {

        bottomSectionAdapter.getData().clear();

        bottomLoadingView.showLoading();

        bottomLoadingView.setLoadingBgColor(mContext.getResources().getColor(R.color.white));

        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        if (statelessSection instanceof ArtistOtherVideoSection) {

            bottomSectionAdapter.setType(Constants.Integers.MORE_MV_TYPE_ARTIST_OTHER_VIDEO);

            videoPlayPresenter.getDetailMvMore(
                Constants.Integers.MORE_MV_TYPE_ARTIST_OTHER_VIDEO, id,
                Constants.Integers.DEFAULT_SIZE);

            setBottomBabiorTitle(mContext.getResources().getString(R.string.artist_other_video));

        } else if (statelessSection instanceof RelatedVideoSectionAdapter) {

            bottomSectionAdapter.setType(Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE);

            videoPlayPresenter.getDetailMvMore(Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE, id,
                Constants.Integers.DEFAULT_SIZE);

            setBottomBabiorTitle(mContext.getResources().getString(R.string.guess_you_like));

        } else if (statelessSection instanceof RelatePlayListSetionAdapter) {

            setBottomBabiorTitle(mContext.getResources().getString(R.string.relate_play_list));

            videoPlayPresenter.getMorePlayList(id, Constants.Integers.DEFAULT_SIZE);

        } else if (statelessSection instanceof SimilarityPlayListSection) {
            setBottomBabiorTitle(mContext.getResources().getString(R.string.similar_play_list));

            videoPlayPresenter.getSimiPlayList(playListId, 0, id, Constants.Integers.DEFAULT_SIZE);

        } else if (statelessSection instanceof MostPeopleSection) {

            setBottomBabiorTitle(mContext.getResources().getString(R.string.most_people_play));

            videoPlayPresenter.getMostPeopleList(0, 1, id, Constants.Integers.DEFAULT_SIZE);
        }
    }


    private void setBottomBabiorTitle(String title) {tvBottomTitle.setText(title);}


    @Override public void onItemClick(View view, int position, int type) {

        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        switch (type) {

            case Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH:

                playNewVideoAndRelaseRes(position);

                break;

            case Constants.Integers.MORE_PLAY_LIST:

                showType = 2;

                playListId = morePlayListSectionAdapter.getMorePlayList()
                    .get(position)
                    .getPlayListId();

                videoPlayPresenter.getPlayListDetail(
                    morePlayListSectionAdapter.getMorePlayList().get(position).getPlayListId());

                break;

            case Constants.Integers.YUE_DAN_LIST:

                reloadYueDanList(position, yueDanResult);

                break;

            case Constants.Integers.MORE_MV_TYPE_MOST_PEOPLE_WATCH:
                showType = 1;

                hintYuedan();

                showLoading();

                superPlayer.pause();

                clearPlaying();

                id = bottomMostPeopleAdapter.getData().get(position).getVideoId();

                videoPlayPresenter.getVideoPlayData(id, type);

                videoPlayPresenter.getVideoDetil(id,
                    false, true);
                break;

            case Constants.SIMILARTY_PLAY_LIST:

                playListId = similartyPlayListAdapter.getData().get(position).getPlayListId();

                videoPlayPresenter.getPlayListDetail(
                    similarityPlayList.get(position).getPlayListId());
                break;

            default:

                id = bottomSectionAdapter.getData().get(position).getVideoId();

                reloadArtistOtherVideo();

                break;

        }
    }


    @Override public void showLoading() {
        triggerShowLoading(true);
    }


    @Override public void hideLoading() {
        triggerShowLoading(false);
    }


    @Override public void showError(String msg) {

        triggerShowError(true, msg, view -> videoPlayPresenter.getVideoDetil(id, true, false));
    }


    /**
     * 重新加载数据
     */
    @Override public void reloadData(int type) {
        switch (type) {

            case Constants.Integers.MORE_MV_TYPE_ARTIST_OTHER_VIDEO:

                videoPlayPresenter.getDetailMvMore(
                    Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH, id,
                    Constants.Integers.DEFAULT_SIZE);

                break;

            case Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE:
                videoPlayPresenter.getDetailMvMore(Constants.Integers.MORE_MV_TYPE_GUESS_YOU_LIKE,
                    id,
                    Constants.Integers.DEFAULT_SIZE);
                break;

            case Constants.MORE_PLAY_LIST:

                videoPlayPresenter.getMorePlayList(id, Constants.Integers.DEFAULT_SIZE);
                break;

            case Constants.SIMILARTY_PLAY_LIST:

                videoPlayPresenter.getSimiPlayList(playListId, 0, id,
                    Constants.Integers.DEFAULT_SIZE);
                break;

            case Constants.MORE_MOST_PEOPLE_LIST:

                videoPlayPresenter.getMostPeopleList(0, 1, id, Constants.Integers.DEFAULT_SIZE);
                break;

            case Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH:

                videoPlayPresenter.getDetailMvMore(
                    Constants.Integers.MORE_MV_TYPE_EVERY_ONE_WATCH, id,
                    Constants.Integers.DEFAULT_SIZE);
                break;

        }
    }


    @Override public void showException(String msg) {
        triggerShowError(true, msg, null);
    }


    @Override public void showNetError() {
        triggerNetworkError(true, null);
    }


    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() > 0) {
                ivSendDm.setBackgroundResource(R.drawable.send_danmu_selector);
            } else {
                ivSendDm.setBackgroundResource(R.mipmap.danmu_send_unclick);
            }
        }

    }


    private void updateFabBackgroud(String url) {

        Glide.with(getApplicationContext())
            .load(url)
            .asBitmap()
            .placeholder(R.mipmap.ic_launcher)
            .into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    floatingVideoMeun.setVideoCover(resource);
                }
            });

    }

}
