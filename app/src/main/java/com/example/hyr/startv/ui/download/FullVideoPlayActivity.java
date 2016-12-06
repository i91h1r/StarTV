package com.example.hyr.startv.ui.download;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import com.example.hyr.startv.R;
import com.superplayer.library.SuperPlayer;

/**
 * Description:
 * 作者：hyr on 2016/11/28 16:31
 * 邮箱：2045446584@qq.com
 */
public class FullVideoPlayActivity extends Activity {

    private static final String VIDEO_PLAY_PATH = "video_play_path";
    private static final String VIDEO_PLAY_TITLE = "video_play_title";
    private SuperPlayer superPlayer;
    private String path;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_video_play_layout);

        getViewById();

        path = getIntent().getExtras().getString(VIDEO_PLAY_PATH);

        String title = getIntent().getExtras().getString(VIDEO_PLAY_TITLE);

        superPlayer.playInFullScreen(true);

        superPlayer.play(this.path);

        superPlayer.setTitle(title);

        superPlayer.setFullVideoPlay(true);



    }


    protected void getViewById() {

        superPlayer = (SuperPlayer) findViewById(R.id.superPlayer);
    }


    public static void open(Context mContext, String path, String title) {

        Intent intent = new Intent();

        intent.putExtra(VIDEO_PLAY_PATH, path);
        intent.putExtra(VIDEO_PLAY_TITLE, title);
        intent.setClass(mContext, FullVideoPlayActivity.class);

        mContext.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (superPlayer != null) {
            superPlayer.onDestroy();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (superPlayer != null) {
            superPlayer.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (superPlayer != null) {
            superPlayer.onPause();
        }
    }

}
