package com.rq.practice.activities.practice;

import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.view.playerview.NativePlayerView;

/**
 * 研究VideoView和MediaController的Sample
 */
public class EasyVideoViewPracticeActivity extends BaseToolBarActivity {

    private NativePlayerView mVideoView;

    private MediaController mMediaController;

    private static final String PLAY_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    public int getLayoutID() {
        return R.layout.activity_easy_video_view_pra;
    }

    @Override
    public void bindView() {
        mVideoView = findViewById(R.id.video_view);
        mVideoView.setVideoURI(Uri.parse(PLAY_URL));
    }

    @Override
    public void initData() {
        mMediaController = new MediaController(EasyVideoViewPracticeActivity.this);
        mVideoView.setMediaController(mMediaController);
        mMediaController.setMediaPlayer(mVideoView);
    }
}
