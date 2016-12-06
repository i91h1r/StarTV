package com.example.hyr.startv.presenter;

import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.HomeMoreContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.model.HomeMoreModelImpl;
import com.example.hyr.startv.result.BaseResult;
import com.example.hyr.startv.result.TabResult;

/**
 * Created by MVPHelper on 2016/12/02
 */

public class HomeMorePresenterImpl
    implements HomeMoreContract.Presenter, BaseMultiLoadedListener<BaseResult> {

    private HomeMoreContract.View moreMvActivity;

    private final HomeMoreModelImpl homeMoreModel;


    public HomeMorePresenterImpl(HomeMoreContract.View moreMvActivity) {
        this.moreMvActivity = moreMvActivity;

        homeMoreModel = new HomeMoreModelImpl(this);

    }


    @Override public void getTabLayoutList(String position) {

        homeMoreModel.loadTabLayoutList(position);
    }


    @Override public void onSuccess(int event_tag, BaseResult data) {

        switch (event_tag) {
            case Constants.TAB_LAYOUT_DATA:

                moreMvActivity.refreshTabData(((TabResult) data));

                break;
        }
    }


    @Override public void onError(String msg) {

    }


    @Override public void onException(String msg) {

    }
}