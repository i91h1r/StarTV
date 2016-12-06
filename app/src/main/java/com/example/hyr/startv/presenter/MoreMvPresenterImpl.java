package com.example.hyr.startv.presenter;

import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.MoreMvContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.model.MoreMvModelImpl;
import com.example.hyr.startv.result.HomeMoreMvResult;

/**
 * Created by MVPHelper on 2016/12/02
 */

public class MoreMvPresenterImpl
    implements MoreMvContract.Presenter, BaseMultiLoadedListener<HomeMoreMvResult> {

    private MoreMvContract.View view;
    private final MoreMvModelImpl moreMvModel;


    public MoreMvPresenterImpl(MoreMvContract.View view) {
        this.view = view;

        moreMvModel = new MoreMvModelImpl(this);
    }


    @Override
    public void getHomeMoreMvList(int event_tag, String url, String area, int offset, int size) {
        moreMvModel.loadHomeMoreMvList(event_tag, url, area, offset, size);

    }


    @Override public void onSuccess(int event_tag, HomeMoreMvResult data) {

        if (event_tag == Constants.EVENT_REFRESH_DATA) {
            view.refreshMoreMvData(data);
        } else if (event_tag == Constants.EVENT_LOAD_MORE_DATA) {
            view.loadMoreData(data);
        }

    }


    @Override public void onError(String msg) {

    }


    @Override public void onException(String msg) {

    }
}