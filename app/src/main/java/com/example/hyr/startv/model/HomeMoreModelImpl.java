package com.example.hyr.startv.model;

import com.example.hyr.startv.api.BaseObserver;
import com.example.hyr.startv.api.StarApi;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.HomeMoreContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.result.BaseResult;
import com.example.hyr.startv.result.TabResult;
import com.example.hyr.startv.utils.DeviceInfoUtils;
import rx.schedulers.Schedulers;

/**
 * Created by MVPHelper on 2016/12/02
 */

public class HomeMoreModelImpl implements HomeMoreContract.Model {

    private final StarApi starApi;

    private BaseMultiLoadedListener<BaseResult> listener;


    public HomeMoreModelImpl(BaseMultiLoadedListener<BaseResult> listener) {
        this.listener = listener;

        starApi = new StarApi();

    }


    @Override public void loadTabLayoutList(String position) {

        starApi.getTabList(position, DeviceInfoUtils.getDeviceInfo())
            .observeOn(Schedulers.immediate())
            .subscribe(
                new BaseObserver<TabResult>() {
                    @Override protected void onSucceed(TabResult result) {

                        listener.onSuccess(Constants.TAB_LAYOUT_DATA,result);
                    }


                    @Override protected void onError(String e) {
                        String s = e.toString();

                    }
                });

    }
}