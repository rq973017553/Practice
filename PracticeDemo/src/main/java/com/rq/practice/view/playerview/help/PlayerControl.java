package com.rq.practice.view.playerview.help;

import android.widget.MediaController;

public interface PlayerControl extends MediaController.MediaPlayerControl {

    void setOnPlayerListener(PlayerListener playerListener);
}
