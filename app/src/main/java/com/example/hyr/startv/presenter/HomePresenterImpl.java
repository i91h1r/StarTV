package com.example.hyr.startv.presenter;

import android.content.Context;
import com.example.hyr.startv.contract.HomeContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.model.HomeModelImpl;
import com.example.hyr.startv.result.HomeResult;

/**
 * Created by MVPHelper on 2016/11/04
 */

public class HomePresenterImpl
    implements HomeContract.Presenter, BaseMultiLoadedListener<HomeResult> {
    private Context mContext;

    private HomeContract.View homeView;
    private final HomeModelImpl homeModel;


    public HomePresenterImpl(Context context, HomeContract.View homeView) {

        this.homeView = homeView;

        this.mContext = context;

        homeModel = new HomeModelImpl(this);

    }


    @Override public void loadListData() {

            homeModel.getHomeListData();

    }


    @Override public void onSuccess(int event_tag, HomeResult data) {

        homeView.refreshListData(data);


    }


    @Override public void onError(String msg) {

    }


    @Override public void onException(String msg) {

    }
}