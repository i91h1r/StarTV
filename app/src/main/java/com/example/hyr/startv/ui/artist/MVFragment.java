package com.example.hyr.startv.ui.artist;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import com.example.hyr.startv.R;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.ui.artist.adapter.MvSectionAdapter;
import com.example.hyr.startv.ui.video.VideoPlayActivity;
import com.example.hyr.startv.widget.sectioned.SectionedRecyclerViewAdapter;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 14:02
 * 邮箱：2045446584@qq.com
 */
public class MVFragment extends BaseFragment {
    private ArtistInfoResult artistInfoResult;
    private RecyclerView mvRecycleView;
    private SectionedRecyclerViewAdapter mvSection;
    private MvSectionAdapter mvSectionAdapter;
    private List<ArtistInfoResult.DataBean.ArtistMVsBean> artistMVs;


    @Override protected View getRefreshLayoutView() {
        return null;
    }


    @Override
    protected void initRefreshLayout(BGAMeiTuanRefreshViewHolder bgaMeiTuanRefreshViewHolder) {

    }


    @Override protected void getViewById(View view) {

        mvRecycleView = (RecyclerView) view.findViewById(R.id.mvReycle);
    }


    @Override protected void onUserInvisible() {

    }


    @Override protected void onFirstUserVisible() {

    }


    @Override protected void onUserVisible() {

    }


    @Override protected void initViewsAndEvents() {

    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_mv_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return true;
    }


    @Override protected void onEventComming(EventCenter eventCenter) {

        int eventCode = eventCenter.getEventCode();

        if (eventCode == Constants.EVENT_SEND_ARTIST_INFO) {

            artistInfoResult = (ArtistInfoResult) eventCenter.getData();

            refreshData();
        }
    }


    public static MVFragment getInstance() {
        MVFragment mvFragment = new MVFragment();

        return mvFragment;
    }


    public void refreshData() {
        mvSection = new SectionedRecyclerViewAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                int sectionItemViewType = mvSection.getSectionItemViewType(position);

                switch (sectionItemViewType) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:

                        return 2;

                    default:
                        return 1;
                }

            }
        });

        mvRecycleView.setLayoutManager(gridLayoutManager);

        mvRecycleView.setAdapter(mvSection);

        if (artistInfoResult != null) {
            artistMVs = artistInfoResult.getData()
                .getArtistMVs();
        }
        for (int i = 0; i < artistMVs.size(); i++) {
            mvSectionAdapter = new MvSectionAdapter(mContext,
                artistMVs.get(i).getName(),
                artistInfoResult.getData().getArtistMVs().get(i).getList());

            mvSection.addSection(mvSectionAdapter);

            int finalI = i;
            mvSectionAdapter.setOnItemClickListener((view, position) -> {
                Intent intent = new Intent();
                intent.setClass(mContext, VideoPlayActivity.class);
                intent.putExtra(VideoPlayActivity.VIDEO_ID,
                    artistMVs.get(finalI).getList().get(position).getVideoId());
                intent.putExtra(VideoPlayActivity.VIDEO_TYPE,
                    artistMVs.get(finalI).getList().get(position).getVideoTypes().get(1));
                startActivity(intent);
            });
        }

        mvSection.notifyDataSetChanged();
    }
}
