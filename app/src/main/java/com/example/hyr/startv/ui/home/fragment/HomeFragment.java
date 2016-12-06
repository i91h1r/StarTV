package com.example.hyr.startv.ui.home.fragment;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import com.example.hyr.startv.R;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.HomeContract;
import com.example.hyr.startv.listener.HomeMoreClickListener;
import com.example.hyr.startv.presenter.HomePresenterImpl;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.ui.home.activity.MoreMvActivity;
import com.example.hyr.startv.ui.home.adapter.HomeBannerSection;
import com.example.hyr.startv.ui.home.adapter.HomeButtonSection;
import com.example.hyr.startv.ui.home.adapter.HomeRecommendedSection;
import com.example.hyr.startv.ui.video.VideoPlayActivity;
import com.example.hyr.startv.widget.sectioned.Section;
import com.example.hyr.startv.widget.sectioned.SectionedRecyclerViewAdapter;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/4 11:57
 * 邮箱：2045446584@qq.com
 */
public class HomeFragment extends BaseFragment
    implements HomeContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, HomeMoreClickListener {

    private static final int HOME_TYPE_RECOMMEND = 6;
    private static final int HOME_TYPE_BANNER = 1;
    private static final int HOME_TYPE_BUTTON = 2;
    private static HomeFragment homeFragment;
    private HomePresenterImpl homePresenter;
    private BGARefreshLayout bgaRefreshLayout;
    private RecyclerView mRecyclerView;
    private SectionedRecyclerViewAdapter mSectionedAdapter;


    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }

        return homeFragment;
    }


    @Override protected View getRefreshLayoutView() {

        bgaRefreshLayout.setDelegate(this);

        return bgaRefreshLayout;
    }


    @Override protected void getViewById(View view) {

        bgaRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.bga);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycle);
    }


    @Override protected void onUserInvisible() {

    }


    @Override protected void onFirstUserVisible() {

    }


    @Override protected void onUserVisible() {

    }


    @Override protected void initViewsAndEvents() {

        bgaRefreshLayout.beginRefreshing();

        initRecycleView();

        homePresenter = new HomePresenterImpl(mContext, this);
        if (NetUtils.isNetworkConnected(mContext)) {
            bgaRefreshLayout.postDelayed(() -> homePresenter.loadListData(),
                Constants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);
        }

    }


    private void initRecycleView() {

        mSectionedAdapter = new SectionedRecyclerViewAdapter();

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 6);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override public int getSpanSize(int position) {

                Section sectionForPosition = mSectionedAdapter.getSectionForPosition(position);

                switch (mSectionedAdapter.getSectionItemViewType(position)) {

                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 6;

                    case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return 2;

                    default:

                        if (sectionForPosition instanceof HomeButtonSection) {

                            return 2;
                        } else if (sectionForPosition instanceof HomeBannerSection) {
                            return 6;
                        } else {
                            return 3;
                        }

                }
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mSectionedAdapter);
    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_home_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    @Override
    protected void initRefreshLayout(BGAMeiTuanRefreshViewHolder bgaMeiTuanRefreshViewHolder) {
        bgaMeiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(
            R.drawable.refresh_loading2);

        bgaMeiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.refresh_1_024);

        bgaMeiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.refresh_loading2);

        bgaRefreshLayout.setRefreshViewHolder(bgaMeiTuanRefreshViewHolder);
    }


    @Override public void refreshListData(HomeResult homeResult) {

        bgaRefreshLayout.endRefreshing();

        mSectionedAdapter.removeAllSections();

        List<HomeResult.DataBean> data = homeResult.getData();

        for (int i = 0; i < data.size(); i++) {

            int type = data.get(i).getType();

            switch (type) {

                case HOME_TYPE_BANNER:

                    mSectionedAdapter.addSection(new HomeBannerSection(data.get(i), getActivity().getApplicationContext()));

                    break;

                case HOME_TYPE_BUTTON:

                    mSectionedAdapter.addSection(new HomeButtonSection(mContext, data.get(i)));

                    break;

                case HOME_TYPE_RECOMMEND:

                    String title = data.get(i).getTitle();

                    String enTitle = data.get(i).getEnTitle();

                    String more = data.get(i).getMoreData().getTitle();

                    HomeResult.DataBean dataBean = data.get(i);

                    String statisticMark = dataBean.getMoreData().getStatisticMark();

                    String url = dataBean.getMoreData().getUrl();

                    HomeRecommendedSection homeRecommendedSection = new HomeRecommendedSection(
                        mContext, more, data.get(i).getSize(), title,
                        enTitle, dataBean, statisticMark, url);

                    mSectionedAdapter.addSection(
                        homeRecommendedSection);

                    int finalI = i;

                    homeRecommendedSection.setOnItemCliclListener(
                        (view, position) -> VideoPlayActivity.open((Activity) mContext, data.get(
                            finalI).getType(),
                            data.get(finalI).getData().get(position).getVideoId(),"play"));

                    homeRecommendedSection.setHomeMoreClickListener(this);

                    break;
            }
        }

        mSectionedAdapter.notifyDataSetChanged();
    }


    @Override public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        if (NetUtils.isNetworkConnected(mContext)) {

            bgaRefreshLayout.postDelayed(() -> homePresenter.loadListData(),
                Constants.Integers.PAGE_LAZY_LOAD_DELAY_TIME_MS);

        }

    }


    @Override public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override public void onMoreClick(String statisticMark, String url,String title) {
        switch (statisticMark) {
            case "MV":

                MoreMvActivity.open(getActivity(), url, 14,title);

                break;
        }
    }
}
