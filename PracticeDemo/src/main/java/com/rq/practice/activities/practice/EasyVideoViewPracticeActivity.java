package com.rq.practice.activities.practice;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;

/**
 * 研究VideoView和MediaController的Sample
 */
public class EasyVideoViewPracticeActivity extends BaseToolBarActivity {

    private VideoView mVideoView;

    private MediaController mMediaController;

    private Button mVideoViewPrepare;

    @Override
    public int getLayoutID() {
        return R.layout.activity_easy_video_view_pra;
    }

    @Override
    public void bindView() {
        mVideoView = findViewById(R.id.video_view);
        mVideoViewPrepare = findViewById(R.id.video_view_prepare);
        mVideoViewPrepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
                mVideoView.setVideoURI(Uri.parse(path));
            }
        });
    }

    @Override
    public void initData() {
        mMediaController = new MediaController(EasyVideoViewPracticeActivity.this);
        mVideoView.setMediaController(mMediaController);
        mMediaController.setMediaPlayer(mVideoView);
    }
}
