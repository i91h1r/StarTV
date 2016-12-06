package com.example.hyr.startv.ui.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.example.hyr.startv.R;
import com.example.hyr.startv.contract.HomeMoreContract;
import com.example.hyr.startv.presenter.HomeMorePresenterImpl;
import com.example.hyr.startv.result.TabResult;
import com.example.hyr.startv.ui.home.adapter.MoreMvFragmentAdapter;
import com.example.hyr.startv.ui.home.fragment.HomeMoreMvFragment;
import com.example.hyr.startv.widget.SmartTabLayout;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;
import com.google.gson.Gson;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/12/2 10:19
 * 邮箱：2045446584@qq.com
 */
public class MoreMvActivity extends BaseActivity implements HomeMoreContract.View {
    private static final String URL = "url";
    private static final String POSITION = "position";
    private static final String TYPE_TITLE = "title";
    private String url;
    private int[] position;
    private HomeMorePresenterImpl homeMorePresenter;
    private SmartTabLayout tabLayout;
    private ViewPager viewPager;
    private String title;
    private TextView tvTitle;


    @Override protected void getViewById() {

        tabLayout = (SmartTabLayout) findViewById(R.id.tab_smart);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tvTitle = (TextView) findViewById(R.id.title);
    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected void initViewsAndEvents() {

        homeMorePresenter = new HomeMorePresenterImpl(this);

        homeMorePresenter.getTabLayoutList(new Gson().toJson(position));

    }


    @Override protected void getIntentExtras(Bundle extras) {

        url = extras.getString(URL);

        position = new int[] { extras.getInt(POSITION) };

        title = extras.getString(TYPE_TITLE);
    }


    @Override protected boolean isBindRxBusHere() {
        return false;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_home_more_mv_layout;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    public static void open(Activity activity, String url, int position, String title) {

        Intent intent = new Intent();

        intent.setClass(activity, MoreMvActivity.class);

        intent.putExtra(URL, url);

        intent.putExtra(POSITION, position);

        intent.putExtra(TYPE_TITLE, title);

        activity.startActivity(intent);
    }


    @Override public void refreshTabData(TabResult tabResult) {

        tvTitle.setText(title);

        List<TabResult.DataBean> data = tabResult.getData();

        viewPager.setAdapter(new MoreMvFragmentAdapter(getSupportFragmentManager(),url,data));

        tabLayout.setViewPager(viewPager);

        tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override public void onPageSelected(int position) {

                HomeMoreMvFragment homeMoreMvFragment = (HomeMoreMvFragment) viewPager.getAdapter()
                    .instantiateItem(viewPager, position);

                homeMoreMvFragment.onPagerSelected(position, url,
                    data.get(0).getChildren().get(position).getKey());
            }


            @Override public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
