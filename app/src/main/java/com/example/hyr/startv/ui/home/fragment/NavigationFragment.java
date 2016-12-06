package com.example.hyr.startv.ui.home.fragment;

import android.view.View;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import com.example.hyr.startv.R;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;

/**
 * Description:
 * 作者：hyr on 2016/11/4 12:04
 * 邮箱：2045446584@qq.com
 */
public class NavigationFragment extends BaseFragment {
    private static NavigationFragment navigationFragment;


    @Override protected View getRefreshLayoutView() {
        return null;
    }


    @Override
    protected void initRefreshLayout(BGAMeiTuanRefreshViewHolder bgaMeiTuanRefreshViewHolder) {

    }


    @Override protected void getViewById(View view) {

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
        return R.layout.fragment_navigation_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }




    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    public static NavigationFragment getInstance() {

        if (navigationFragment == null) {
            navigationFragment = new NavigationFragment();
        }

        return navigationFragment;
    }
}
