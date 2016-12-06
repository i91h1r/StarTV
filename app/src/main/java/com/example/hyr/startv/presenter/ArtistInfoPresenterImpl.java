package com.example.hyr.startv.presenter;

import com.example.hyr.startv.contract.ArtistInfoContract;
import com.example.hyr.startv.listener.BaseMultiLoadedListener;
import com.example.hyr.startv.model.ArtistInfoModelImpl;
import com.example.hyr.startv.result.ArtistInfoResult;

/**
 * Created by MVPHelper on 2016/11/16
 */

public class ArtistInfoPresenterImpl
    implements ArtistInfoContract.Presenter, BaseMultiLoadedListener<ArtistInfoResult> {

    private ArtistInfoContract.View view;
    private final ArtistInfoModelImpl artistInfoModel;


    public ArtistInfoPresenterImpl(ArtistInfoContract.View view) {
        this.view = view;

        artistInfoModel = new ArtistInfoModelImpl(this);

    }


    @Override public void getArtistInfoData(int artistId) {

        artistInfoModel.loadArtistInfoData(artistId);
    }


    @Override public void onSuccess(int event_tag, ArtistInfoResult data) {

        if(null != data){
            view.refreshArtistInfo(data);
        }
    }


    @Override public void onError(String msg) {

    }


    @Override public void onException(String msg) {

    }
}