package com.example.hyr.startv.ui.artist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.ArtistInfoContract;
import com.example.hyr.startv.presenter.ArtistInfoPresenterImpl;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.ui.artist.adapter.tabFragmentAdapter;
import com.example.hyr.startv.utils.GlideCircleImage;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;
import com.github.hyr0318.baselibrary.rxBus.RxBus;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Audio.AudioColumns.ARTIST_ID;

/**
 * Description:
 * 作者：hyr on 2016/11/16 10:49
 * 邮箱：2045446584@qq.com
 */
public class ArtistInfoActivity extends BaseActivity implements ArtistInfoContract.View,
    TabLayout.OnTabSelectedListener, View.OnClickListener {

    private ImageView ivBack;
    private TextView tvArtistName;
    private ImageView ivArtist;
    private TextView tvMvCount;
    private TextView tvSubCount;
    private int artistId;
    private ArtistInfoPresenterImpl artistInfoPresenter;
    private TabLayout artistTab;
    private ViewPager vpArtist;

    private List<String> tabNameList = new ArrayList<>();

    private List<Fragment> artistFragments = new ArrayList<>();
    private com.example.hyr.startv.ui.artist.adapter.tabFragmentAdapter tabFragmentAdapter;


    @Override protected void getViewById() {

        ivBack = (ImageView) findViewById(R.id.iv_back);

        tvArtistName = (TextView) findViewById(R.id.tv_artist_name);

        ivArtist = (ImageView) findViewById(R.id.iv_artist);

        tvMvCount = (TextView) findViewById(R.id.tv_mv_count);
        tvSubCount = (TextView) findViewById(R.id.tv_sub_count);

        artistTab = (TabLayout) findViewById(R.id.tablayout);

        vpArtist = (ViewPager) findViewById(R.id.vp_artist);

        ivBack.setOnClickListener(this);
    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected void initViewsAndEvents() {


        artistInfoPresenter = new ArtistInfoPresenterImpl(this);

        artistInfoPresenter.getArtistInfoData(artistId);

        artistFragments.add(MVFragment.getInstance());

        artistFragments.add(ArtistInfoFrament.getInstance());

        tabNameList.add(mContext.getResources().getString(R.string.artist_info_mv));
        tabNameList.add(mContext.getResources().getString(R.string.artist_info));

        artistTab.addTab(artistTab.newTab().setText(tabNameList.get(0)));
        artistTab.addTab(artistTab.newTab().setText(tabNameList.get(1)));
        artistTab.setTabMode(TabLayout.MODE_FIXED);

        tabFragmentAdapter = new tabFragmentAdapter(mContext, getSupportFragmentManager(),
            artistFragments, tabNameList);

        vpArtist.setAdapter(tabFragmentAdapter);

        artistTab.setupWithViewPager(vpArtist);

        for (int i = 0; i < artistTab.getTabCount(); i++) {
            TabLayout.Tab tabAt = artistTab.getTabAt(i);
            tabAt.setCustomView(tabFragmentAdapter.getTabView(i));
            if (i == 0) {
                tabAt.getCustomView().findViewById(R.id.tab_name).setSelected(true);
            } else {
                tabAt.getCustomView().findViewById(R.id.tab_name).setSelected(false);
            }
        }
        artistTab.setOnTabSelectedListener(this);

    }


    @Override protected void getIntentExtras(Bundle extras) {

        artistId = extras.getInt(ARTIST_ID);
    }


    @Override protected boolean isBindRxBusHere() {
        return true;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_artist_info_layout;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected void onEventComming(EventCenter eventCenter) {

        int eventCode = eventCenter.getEventCode();

        if (eventCode == Constants.EVENT_UPDATA_ARTIST_INFO) {

            int artistId = (int) eventCenter.getData();

            artistInfoPresenter.getArtistInfoData(artistId);
        }

    }


    @Override public void refreshArtistInfo(ArtistInfoResult artistInfoResult) {

        Logger.d(artistInfoResult);

        RxBus.getDefault()
            .postSticky(new EventCenter<>(Constants.EVENT_SEND_ARTIST_INFO, artistInfoResult));

        Glide.with(mContext)
            .load(artistInfoResult.getData().getSmallAvatar())
            .transform(new GlideCircleImage(mContext))
            .diskCacheStrategy(
                DiskCacheStrategy.ALL)
            .into(ivArtist);

        tvArtistName.setText(artistInfoResult.getData().getArtistName());

        tvMvCount.setText(String.valueOf(artistInfoResult.getData().getVideoCount()) +
            mContext.getResources().getString(R.string.count_mv));

        tvSubCount.setText(String.valueOf(artistInfoResult.getData().getSubCount()) +
            mContext.getResources().getString(R.string.person_sub));
    }


    @Override public void onTabSelected(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_name).setSelected(true);

        vpArtist.setCurrentItem(tab.getPosition());
    }


    @Override public void onTabUnselected(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_name).setSelected(false);

        vpArtist.setCurrentItem(tab.getPosition());
    }


    @Override public void onTabReselected(TabLayout.Tab tab) {

        tab.getCustomView().findViewById(R.id.tab_name).setSelected(false);

        vpArtist.setCurrentItem(tab.getPosition());
    }


    @Override public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                finish();
                break;
        }
    }
}
