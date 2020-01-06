package com.rq.practice.view.playerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.rq.practice.R;
import com.rq.practice.view.playerview.help.PlayerControl;

/**
 * 播放器
 *
 * @author qiangrao
 * @version [1.0.0, 2018.11.09]
 */
public abstract class BasePlayerView extends FrameLayout implements PlayerControl {
    private View mParentView;

    private ViewGroup mPlayerView;

    protected Context mContext;

    public BasePlayerView(@NonNull Context context)
    {
        this(context, null);
    }

    public BasePlayerView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BasePlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context)
    {
        this.mContext = context;
        mParentView = LayoutInflater.from(getContext()).inflate(R.layout.player_view, null);
        addView(mParentView);
        mPlayerView = findViewById(R.id.player);
        initPlayerView(mPlayerView);
    }

    protected abstract void initPlayerView(ViewGroup playerView);
}
