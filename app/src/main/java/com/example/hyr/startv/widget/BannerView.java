package com.example.hyr.startv.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hyr.startv.R;
import com.example.hyr.startv.listener.BannerImgClickListener;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.ui.home.BannerAdapter;
import com.example.hyr.startv.ui.home.activity.WebViewActivity;
import com.example.hyr.startv.ui.video.VideoPlayActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 自定义Banner无限轮播控件
 */
public class BannerView extends RelativeLayout {

    private CompositeSubscription compositeSubscription;

    //默认轮播时间，10s
    private int delayTime = 10;

    private List<ImageView> imageViewList;

    private Context context;

    List<HomeResult.DataBean.CommonBean> bannerList;

    private TextView tvTitle;
    private ImageView ivType;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvTotal;
    private ViewPager viewPager;

    private int mCurrent = 0;

    private BannerImgClickListener bannerImgClickListener;


    public BannerView(Context context) {
        this(context, null);
    }


    public void setBannerImgClickListener(BannerImgClickListener bannerImgClickListener) {
        this.bannerImgClickListener = bannerImgClickListener;
    }


    public BannerView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }


    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        this.context = context;
        View banner = LayoutInflater.from(context).inflate(R.layout.banner_layout, this, true);

        viewPager = (ViewPager) banner.findViewById(R.id.layout_banner_viewpager);

        tvTitle = (TextView) banner.findViewById(R.id.tv_title);

        ivType = (ImageView) banner.findViewById(R.id.image_type);

        tvName = (TextView) banner.findViewById(R.id.tv_name);

        tvPosition = (TextView) banner.findViewById(R.id.tv_position);

        tvTotal = (TextView) banner.findViewById(R.id.tv_total);

        imageViewList = new ArrayList<>();

    }


    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {

        this.delayTime = time;
        return this;
    }


    /**
     * 图片轮播需要传入参数
     */
    public void build(List<HomeResult.DataBean.CommonBean> list) {

        destory();

        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }

        bannerList = new ArrayList<>();
        bannerList.addAll(list);

        for (int i = 0; i < bannerList.size(); i++) {
            ImageView mImageView = new ImageView(context);

            Glide.with(context)
                .load(bannerList.get(i).getPosterPic())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
            imageViewList.add(mImageView);
        }

        initView(mCurrent);

        //监听图片轮播，改变指示器状态
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int pos) {

                pos = pos % bannerList.size();

                initView(pos);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        compositeSubscription.unsubscribe();
                        break;
                }
            }
        });

        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();

        bannerAdapter.setBannerImgClickListener(new BannerImgClickListener() {
            @Override public void onImgClick(int position) {
                String type = list.get(position).getType();

                if(type.equals("web")){

                    Intent intent = new Intent();
                    HomeResult.DataBean.CommonBean commonBean = list.get(position);
                    intent.setClass(context,WebViewActivity.class);
                    intent.putExtra("source_url",commonBean.getUrl());

                    intent.putExtra("title",commonBean.getTitle());

                    context.startActivity(intent);

                }else if (type.equals("play")){
                    VideoPlayActivity.open(context,1,list.get(position).getVideoId(),type);
                }else if(type.equals("playlist")){
                    VideoPlayActivity.open(context,1,list.get(position).getVideoId(),type);
                }
            }
        });

        //图片开始轮播
        startScroll();
    }


    private void initView(int pos) {
        HomeResult.DataBean.CommonBean commonBean = bannerList.get(pos);

        tvTitle.setText(commonBean.getTitle());

        tvPosition.setText(String.valueOf(pos + 1));

        tvTotal.setText(String.valueOf(bannerList.size()));
        StringBuffer s = new StringBuffer();

        if (bannerList.get(pos).getArtists() != null) {
            for (int i = 0; i < bannerList.get(pos).getArtists().size(); i++) {

                s.append(bannerList.get(pos).getArtists().get(i).getArtistName());

                if (i > 1 && i < bannerList.get(pos).getArtists().size()) {
                    s.append("&");
                }
            }
        }
        tvName.setText(s);

        Glide.with(getContext())
            .load(commonBean.getDataTypeUrl())
            .placeholder(R.drawable.ad_video_default)
            .into(ivType);
    }


    private boolean isStopScroll = false;


    /**
     * 图片开始轮播
     */
    private void startScroll() {

        compositeSubscription = new CompositeSubscription();
        isStopScroll = false;
        Subscription subscription = Observable.timer(delayTime, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Long>() {

                @Override
                public void onCompleted() {

                }


                @Override
                public void onError(Throwable e) {

                }


                @Override
                public void onNext(Long aLong) {

                    if (isStopScroll) {
                        return;
                    }
                    isStopScroll = true;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            });
        compositeSubscription.add(subscription);
    }


    /**
     * 图片停止轮播
     */
    private void stopScroll() {
        isStopScroll = true;
    }


    public void destory() {

        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

}
