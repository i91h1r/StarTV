package com.example.hyr.startv.ui.artist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.OnItemClickListener;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 14:07
 * 邮箱：2045446584@qq.com
 */
public class tabFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> artistFragments;
    List<String> tabNameList;

    private Context mContext;

    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public tabFragmentAdapter(Context mContext, FragmentManager fm, List<Fragment> artistFragments, List<String> tabNameList) {
        super(fm);

        this.artistFragments = artistFragments;

        this.tabNameList = tabNameList;

        this.mContext = mContext;
    }


    @Override public Fragment getItem(int position) {
        return artistFragments.get(position);
    }


    @Override public int getCount() {
        return artistFragments!= null ? artistFragments.size() : 0;
    }


    @Override public CharSequence getPageTitle(int position) {
        return null;
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tab_name);
        tv.setText(tabNameList.get(position));
        return view;
    }
}
