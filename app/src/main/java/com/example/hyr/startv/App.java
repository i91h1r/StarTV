package com.example.hyr.startv;

import android.app.Application;
import com.example.hyr.startv.utils.VideoManager;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Description:
 * 作者：hyr on 2016/11/3 10:14
 * 邮箱：2045446584@qq.com
 */
public class App extends Application {

    private static App app;


    public static App getInstance() {

        return app;
    }


    @Override public void onCreate() {
        super.onCreate();

        app = this;

        FileDownloader.init(this);

        VideoManager.getInstance();
    }
}
