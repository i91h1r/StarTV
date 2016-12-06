package com.example.hyr.startv.ui.search.activity;

import android.os.Bundle;
import android.view.View;
import com.example.hyr.startv.R;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;

/**
 * Description:
 * 作者：hyr on 2016/11/4 09:47
 * 邮箱：2045446584@qq.com
 */
public class SearchResultActivity  extends BaseActivity{
    @Override protected void getViewById() {

    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected void initViewsAndEvents() {

    }


    @Override protected void getIntentExtras(Bundle extras) {

    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }




    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_search_result_layout;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }
}
