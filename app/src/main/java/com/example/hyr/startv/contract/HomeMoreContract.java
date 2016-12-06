package com.example.hyr.startv.contract;

import com.example.hyr.startv.result.TabResult;

/**
 * Description:
 * 作者：hyr on 2016/12/2 10:31
 * 邮箱：2045446584@qq.com
 */
public class HomeMoreContract {

    public interface View {

        void refreshTabData(TabResult tabResult);

    }


    public interface Presenter {

        void getTabLayoutList(String  position);
    }


    public interface Model {

        void loadTabLayoutList(String  position);
    }

}