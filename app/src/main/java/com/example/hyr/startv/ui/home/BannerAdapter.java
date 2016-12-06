package com.example.hyr.startv.ui.home;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.example.hyr.startv.listener.BannerImgClickListener;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/4 16:10
 * 邮箱：2045446584@qq.com
 */
public class BannerAdapter extends PagerAdapter {

    private BannerImgClickListener bannerImgClickListener;
    private List<ImageView> mList;

    private int pos;

    private ViewPagerOnItemClickListener mViewPagerOnItemClickListener;


    void setmViewPagerOnItemClickListener(ViewPagerOnItemClickListener mViewPagerOnItemClickListener) {

        this.mViewPagerOnItemClickListener = mViewPagerOnItemClickListener;
    }


    public BannerAdapter(List<ImageView> list) {

        this.mList = list;
    }


    public void setBannerImgClickListener(BannerImgClickListener bannerImgClickListener) {
        this.bannerImgClickListener = bannerImgClickListener;
    }


    @Override
    public int getCount() {

        return Integer.MAX_VALUE;
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0 == arg1;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //对ViewPager页号求模取出View列表中要显示的项
        position %= mList.size();
        if (position < 0) {
            position = mList.size() + position;
        }
        ImageView v = mList.get(position);
        pos = position;
        v.setScaleType(ImageView.ScaleType.FIT_XY);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = v.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(v);
        }
        v.setOnClickListener(v1 -> {

            if (mViewPagerOnItemClickListener != null) {
                mViewPagerOnItemClickListener.onItemClick(pos);
            }
        });

        int finalPosition = position;
        v.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (bannerImgClickListener != null) {
                    bannerImgClickListener.onImgClick(finalPosition);
                }
            }
        });

        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


    interface ViewPagerOnItemClickListener {

        void onItemClick(int position);
    }
}