package com.example.hyr.startv.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import com.example.hyr.startv.R;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.MoreMvContract;
import com.example.hyr.startv.listener.OnPagerSelectedListener;
import com.example.hyr.startv.presenter.MoreMvPresenterImpl;
import com.example.hyr.startv.result.HomeMoreMvResult;
import com.example.hyr.startv.ui.home.adapter.MoreMvAdapter;
import com.example.hyr.startv.ui.video.VideoPlayActivity;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;

/**
 * Description:
 * 作者：hyr on 2016/12/2 11:22
 * 邮箱：2045446584@qq.com
 */
public class HomeMoreMvFragment extends BaseFragment
    implements OnPagerSelectedListener, MoreMvContract.View,
    BGARefreshLayout.BGARefreshLayoutDelegate {

    private MoreMvPresenterImpl moreMvPresenter;

    private String type_url = null;

    private String area = null;

    private static HomeMoreMvFragment homeMoreMvFragment;
    private RecyclerView recyclerView;
    private MoreMvAdapter moreMvAdapter;
    private BGARefreshLayout bgaRefreshLayout;

    private int default_size = 0;


    @Override protected View getRefreshLayoutView() {

        bgaRefreshLayout.setDelegate(this);

        return bgaRefreshLayout;
    }


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type_url = getResources().getString(R.string.url);

        area = getResources().getString(R.string.all);
    }


    @Override
    protected void initRefreshLayout(BGAMeiTuanRefreshViewHolder bgaMeiTuanRefreshViewHolder) {
        bgaMeiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(
            R.drawable.refresh_loading2);

        bgaMeiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.refresh_1_024);

        bgaMeiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.refresh_loading2);

        bgaRefreshLayout.setRefreshViewHolder(bgaMeiTuanRefreshViewHolder);
    }


    @Override protected void getViewById(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        bgaRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.bga);
    }


    @Override protected void onUserInvisible() {

    }


    @Override protected void onFirstUserVisible() {

        moreMvPresenter = new MoreMvPresenterImpl(this);

        moreMvPresenter.getHomeMoreMvList(Constants.EVENT_REFRESH_DATA, type_url, this.area,
            default_size, 20);

    }


    @Override protected void onUserVisible() {

    }


    @Override protected void initViewsAndEvents() {
        moreMvAdapter = new MoreMvAdapter(mContext);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(moreMvAdapter);

    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.fragment_home_more_mv_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    @Override public void onPagerSelected(int position, String url, String type) {

        this.type_url = url;

        this.area = type;

    }


    @Override public void refreshMoreMvData(HomeMoreMvResult homeMoreMvResult) {

        bgaRefreshLayout.endRefreshing();

        moreMvAdapter.setData(homeMoreMvResult.getData());

        moreMvAdapter.notifyDataSetChanged();

        moreMvAdapter.setOnMoreItemClickListener(
            (view, position, type) -> VideoPlayActivity.open(getActivity(),
                homeMoreMvResult.getData().get(position).getVideoTypes().get(0),
                homeMoreMvResult.getData().get(position).getVideoId(),"play"));

    }


    @Override public void loadMoreData(HomeMoreMvResult homeMoreMvResult) {

        bgaRefreshLayout.endLoadingMore();

        moreMvAdapter.getData().addAll(homeMoreMvResult.getData());

        moreMvAdapter.notifyDataSetChanged();

    }


    public static HomeMoreMvFragment getInstance() {
        if (homeMoreMvFragment == null) {

            homeMoreMvFragment = new HomeMoreMvFragment();

        }

        return homeMoreMvFragment;
    }


    @Override public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        moreMvPresenter.getHomeMoreMvList(Constants.EVENT_REFRESH_DATA, type_url, this.area, 0, 20);

    }


    @Override public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        default_size = default_size + 20;

        moreMvPresenter.getHomeMoreMvList(Constants.EVENT_LOAD_MORE_DATA, type_url, this.area,
            default_size, 20);

        return true;
    }
}
