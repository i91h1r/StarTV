package com.example.hyr.startv.ui.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.hyr.startv.result.TabResult;
import com.example.hyr.startv.ui.home.fragment.HomeMoreMvFragment;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/12/2 11:18
 * 邮箱：2045446584@qq.com
 */
public class MoreMvFragmentAdapter extends FragmentPagerAdapter {

    private List<TabResult.DataBean> data;

    private String url;


    public MoreMvFragmentAdapter(FragmentManager fragmentManager, String url, List<TabResult.DataBean> data) {
        super(fragmentManager);
        this.data = data;

        this.url = url;
    }


    @Override public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        HomeMoreMvFragment homeMoreMvFragment = new HomeMoreMvFragment();

        bundle.putString("url", url);

        bundle.putString("area", data.get(0).getChildren().get(0).getKey());

        homeMoreMvFragment.setArguments(bundle);

        return homeMoreMvFragment;
    }


    @Override public int getCount() {
        return data.get(0).getChildren().size();
    }


    @Override public CharSequence getPageTitle(int position) {
        return null != data ? data.get(0).getChildren().get(position).getValue() :null;
    }
}
