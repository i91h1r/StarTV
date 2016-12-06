package com.github.hyr0318.baselibrary.loading;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.github.hyr0318.baselibrary.R;

/**
 * Description:
 * 作者：hyr on 2016/9/2 15:12
 * 邮箱：2045446584@qq.com
 */
public class LoadingViewController {
    private ILoadingViewHelper helper;
    private LinearLayout llLoading;


    public LoadingViewController(View view) {
        this(new LoadingViewHelper(view));
    }


    public LoadingViewController(ILoadingViewHelper helper) {
        this.helper = helper;
    }


    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }


    public void showLoading() {
        View layout = helper.inflate(R.layout.loading);

        ImageView loading = (ImageView) layout.findViewById(R.id.loading);
        llLoading = (LinearLayout) layout.findViewById(R.id.ll_loading);
        loading.setBackgroundResource(R.drawable.loading);

        AnimationDrawable animationDrawable = (AnimationDrawable) loading.getBackground();

        animationDrawable.start();

        helper.showLayout(layout);
    }


    public void restore() {
        helper.restoreView();
    }

    public  void  setLoadingBgColor(int color){
        if(llLoading != null ){
            llLoading.setBackgroundColor(color);
        }
    }
}
