package com.example.hyr.startv.model;

import com.example.hyr.startv.api.BaseObserver;
import com.example.hyr.startv.api.StarApi;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.result.HomeResult;
import com.example.hyr.startv.utils.DeviceInfoUtils;
import rx.schedulers.Schedulers;

import static com.example.hyr.startv.contract.HomeContract.Model;

/**
 * Created by MVPHelper on 2016/11/04
 */

public class HomeModelImpl implements Model {

    private BaseMultiLoadedListener<HomeResult> baseMultiLoadedListener;


    public HomeModelImpl(BaseMultiLoadedListener<HomeResult> listener) {
        this.baseMultiLoadedListener = listener;
    }


    @Override public void getHomeListData() {
        StarApi starApi = new StarApi();

        starApi.getHomeData(DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(new BaseObserver<HomeResult>() {

                @Override protected void onSucceed(HomeResult result) {

                    if (null != result) {
                        baseMultiLoadedListener.onSuccess(0, result);
                    }

                }


                @Override protected void onError(String e) {

                }
            });

    }
}