package com.example.hyr.startv.ui.home.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.hyr.startv.R;
import com.example.hyr.startv.cache.SettingManager;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.widget.CustomVideoView;
import java.util.Date;

/**
 * Description:
 * 作者：hyr on 2016/11/3 09:24
 * 邮箱：2045446584@qq.com
 */
public class WelecomeActivity extends AppCompatActivity {

    CustomVideoView welecomeVideo;

    RelativeLayout rl;

    final long WELCOME_TIME = Constants.WELCOME_TIME;
    private Date mStartDate;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome_layout);

        welecomeVideo = (CustomVideoView) findViewById(R.id.welecome_video);

        mStartDate = new Date();

        rl = (RelativeLayout) findViewById(R.id.rl);

        goToMain();

    }


    private void goToMain() {

        if (getWaitTime() <= 0) {
            goMain();
        } else {
            rl.postDelayed(this::goMain, getWaitTime());
        }
    }


    private void goMain() {

        Boolean isFirstStart = SettingManager.getInstance()
            .getSetting(SettingManager.FIRST_APP_START, false);

        if (!isFirstStart) {

            welecomeVideo.setVisibility(View.VISIBLE);

            welecomeVideo.setVideoURI(
                Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide_video));

            welecomeVideo.start();

            welecomeVideo.setOnCompletionListener(mp -> welecomeVideo.setOnTouchListener(
                (v, event) -> {
                    goHome();
                    return false;
                }));

        } else {

            goHome();

        }

    }


    private void goHome() {

        SettingManager.getInstance().setSetting(SettingManager.FIRST_APP_START, true);

        HomeActivity.open(this);

        finish();
    }


    private int getWaitTime() {

        long waitTime = WELCOME_TIME - ((new Date().getTime()) - mStartDate.getTime());

        return (int) waitTime;
    }

}
