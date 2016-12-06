package com.example.hyr.startv.model;

import com.example.hyr.startv.api.BaseObserver;
import com.example.hyr.startv.api.StarApi;
import com.example.hyr.startv.contract.MoreMvContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.result.HomeMoreMvResult;
import com.example.hyr.startv.utils.DeviceInfoUtils;
import rx.schedulers.Schedulers;

/**
 * Created by MVPHelper on 2016/12/02
 */

public class MoreMvModelImpl implements MoreMvContract.Model {

    private BaseMultiLoadedListener<HomeMoreMvResult> listener;
    private final StarApi starApi;


    public MoreMvModelImpl(BaseMultiLoadedListener<HomeMoreMvResult> listener) {

        this.listener = listener;

        starApi = new StarApi();
    }


    @Override public void loadHomeMoreMvList(int event_tag,String url, String area, int offset, int size) {

        starApi.getMoreMvList(url, area, offset, size, DeviceInfoUtils.getDeviceInfo()).subscribeOn(
            Schedulers.immediate()).subscribe(new BaseObserver<HomeMoreMvResult>() {
            @Override protected void onSucceed(HomeMoreMvResult result) {
                listener.onSuccess(event_tag, result);
            }


            @Override protected void onError(String e) {

            }
        });
    }
}