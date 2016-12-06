package com.example.hyr.startv.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.hyr.startv.ui.download.DownloadedFragment;
import com.example.hyr.startv.ui.download.DownloadingFragment;

/**
 * Description:
 * 作者：hyr on 2016/11/25 15:35
 * 邮箱：2045446584@qq.com
 */
public class DownloadFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private DownloadedFragment downloadedFragment;

    private DownloadingFragment downloadingFragment;


    public DownloadFragmentPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        titles = new String[] { DownloadingFragment.TITLE, DownloadedFragment.TITLE };

    }


    @Override public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (downloadingFragment == null) {
                    downloadingFragment = DownloadingFragment.getInstance();
                }

                return downloadingFragment;

            case 1:
                if (downloadedFragment == null) {
                    downloadedFragment = DownloadedFragment.getInstance();
                }

                return downloadedFragment;
        }
        return null;
    }


    @Override public int getCount() {
        return titles.length;
    }


    @Override public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
