package com.example.hyr.startv.contract;

import com.example.hyr.startv.result.HomeResult;
import com.github.hyr0318.baselibrary.view.BaseView;

/**
 * Description:
 * 作者：hyr on 2016/11/4 14:20
 * 邮箱：2045446584@qq.com
 */
public class HomeContract {
    public interface View extends BaseView {
        void refreshListData(HomeResult homeResult);

    }


    public interface Presenter {

        void loadListData();
    }


    public interface  Model   {

         void getHomeListData();

    }

}