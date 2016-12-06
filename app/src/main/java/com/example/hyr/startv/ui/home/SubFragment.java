package com.example.hyr.startv.ui.home;

import android.view.View;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import com.example.hyr.startv.R;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;

/**
 * Description:
 * 作者：hyr on 2016/11/4 12:20
 * 邮箱：2045446584@qq.com
 */
public class SubFragment extends BaseFragment {
    private static SubFragment subFragment;


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
        return R.layout.frament_sub_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }




    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    public static SubFragment getInstance() {

        if (subFragment == null) {
            subFragment = new SubFragment();
        }
        return subFragment;
    }
}
