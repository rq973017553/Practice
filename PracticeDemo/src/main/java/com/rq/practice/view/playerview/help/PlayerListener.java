package com.rq.practice.view.playerview.help;

/**
 * 播放器监听接口 该接口是播放器监听接口的集合
 * 
 * @author qiangrao
 * @version [1.0.0, 2018.10.18]
 */
public interface PlayerListener
{
    boolean onInfo(int what, int extra);

    boolean onError(int what, int extra);

    void onVideoSizeChanged(int width, int height);

    void onSeekComplete();

    void onBufferingUpdate(int bufferingUpdate);

    void onCompletion();

    void onPrepared();

    void onStateChange(int status);
}
