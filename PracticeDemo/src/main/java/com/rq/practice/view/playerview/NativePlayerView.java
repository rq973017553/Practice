package com.rq.practice.view.playerview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rq.practice.view.playerview.help.PlayerListener;

public class NativePlayerView extends BasePlayerView{

    private VideoView mVideoView;

    public NativePlayerView(@NonNull Context context) {
        super(context);
    }

    public NativePlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NativePlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initPlayerView(ViewGroup playerView) {
        mVideoView = new VideoView(mContext);
        if (playerView != null) {
            playerView.removeAllViews();
            playerView.addView(mVideoView);
        }
    }

    @Override
    public void start() {
        if (mVideoView != null) {
            mVideoView.start();
        }
    }

    @Override
    public void pause() {
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public int getDuration() {
        if (mVideoView == null) {
            return 0;
        }
        return mVideoView.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        if (mVideoView == null) {
            return 0;
        }
        return mVideoView.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        if (mVideoView != null) {
            mVideoView.seekTo(pos);
        }
    }

    @Override
    public boolean isPlaying() {
        if (mVideoView == null) {
            return false;
        }
        return mVideoView.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        if (mVideoView == null) {
            return 0;
        }
        return mVideoView.getBufferPercentage();
    }

    @Override
    public boolean canPause() {
        if (mVideoView == null) {
            return false;
        }
        return mVideoView.canPause();
    }

    @Override
    public boolean canSeekBackward() {
        if (mVideoView == null) {
            return false;
        }
        return mVideoView.canSeekBackward();
    }

    @Override
    public boolean canSeekForward() {
        if (mVideoView == null) {
            return false;
        }
        return mVideoView.canSeekForward();
    }

    @Override
    public int getAudioSessionId() {
        if (mVideoView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return mVideoView.getAudioSessionId();
        }
        return 0;
    }

    @Override
    public void setOnPlayerListener(final PlayerListener playerListener) {
        if (mVideoView != null){
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (playerListener != null){
                        playerListener.onCompletion();
                    }
                }
            });

            mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    if (playerListener != null){
                        playerListener.onError(what, extra);
                    }
                    return false;
                }
            });

//            mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
//                @Override
//                public boolean onInfo(MediaPlayer mp, int what, int extra) {
//                    if (playerListener != null){
//                        playerListener.onInfo(what, extra);
//                    }
//                    return false;
//                }
//            });

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (playerListener != null){
                        playerListener.onPrepared();
                    }
                }
            });
        }
    }

    public void setMediaController(MediaController controller){
        if (mVideoView != null){
            mVideoView.setMediaController(controller);
        }
    }

    public void setVideoURI(Uri videoURI){
        if (mVideoView != null){
            mVideoView.setVideoURI(videoURI);
        }
    }
}
