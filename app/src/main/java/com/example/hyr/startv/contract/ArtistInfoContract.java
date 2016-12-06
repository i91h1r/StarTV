package com.example.hyr.startv.contract;

import com.example.hyr.startv.result.ArtistInfoResult;

/**
 * Description:
 * 作者：hyr on 2016/11/16 11:08
 * 邮箱：2045446584@qq.com
 */
public class ArtistInfoContract {
    public interface View {

        void refreshArtistInfo(ArtistInfoResult artistInfoResult);
    }


    public interface Presenter {

        void getArtistInfoData(int artistId);
    }


    public interface Model {

        void loadArtistInfoData(int artistId);
    }

}