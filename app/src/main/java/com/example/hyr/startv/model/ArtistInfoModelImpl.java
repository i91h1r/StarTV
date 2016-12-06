package com.example.hyr.startv.model;

import com.example.hyr.startv.api.BaseObserver;
import com.example.hyr.startv.api.StarApi;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.contract.ArtistInfoContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.utils.DeviceInfoUtils;
import rx.schedulers.Schedulers;

/**
 * Created by MVPHelper on 2016/11/16
 */

public class ArtistInfoModelImpl implements ArtistInfoContract.Model {

    private BaseMultiLoadedListener<ArtistInfoResult> listener;
    private final StarApi starApi;


    public ArtistInfoModelImpl(BaseMultiLoadedListener<ArtistInfoResult> listener) {
        this.listener = listener;

        starApi = new StarApi();

    }


    @Override public void loadArtistInfoData(int artistId) {
        starApi.getArtistInfo(artistId, DeviceInfoUtils.getDeviceInfo())
            .subscribeOn(Schedulers.immediate())
            .subscribe(new BaseObserver<ArtistInfoResult>() {

                @Override protected void onSucceed(ArtistInfoResult result) {

                    listener.onSuccess(Constants.EVENT_BEGIN, result);
                }


                @Override protected void onError(String e) {

                }
            });
    }
}