package com.example.hyr.startv.ui.download;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.example.hyr.startv.R;
import com.example.hyr.startv.ui.home.adapter.DownloadFragmentPagerAdapter;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;

/**
 * Description:
 * 作者：hyr on 2016/11/25 15:02
 * 邮箱：2045446584@qq.com
 */
public class DownloadActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;


    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadActivity.class);
        context.startActivity(intent);
    }

    @Override protected void getViewById() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected void initViewsAndEvents() {


        DownloadFragmentPagerAdapter downloadFragmentPagerAdapter
            = new DownloadFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(downloadFragmentPagerAdapter);

        viewPager.setOffscreenPageLimit(downloadFragmentPagerAdapter.getCount());

        tabLayout.setupWithViewPager(viewPager);



    }


    @Override protected void getIntentExtras(Bundle extras) {

    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_download_layout;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
