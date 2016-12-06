package com.example.hyr.startv.contract;

import com.example.hyr.startv.result.HomeMoreMvResult;

/**
 * Description:
 * 作者：hyr on 2016/12/2 12:22
 * 邮箱：2045446584@qq.com
 */
public class MoreMvContract {

    public interface View {

        void refreshMoreMvData(HomeMoreMvResult homeMoreMvResult);

        void loadMoreData(HomeMoreMvResult homeMoreMvResult);
    }


    public interface Presenter {

        void getHomeMoreMvList(int event_tag,String url, String area, int offset, int size);
    }


    public interface Model {

        void loadHomeMoreMvList(int event_tag,String url, String area, int offset, int size);
    }

}