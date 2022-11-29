package com.rq.practice.activities.practice;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.view.TextureView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.rq.practice.R;

public class Camera2PracticeActivity extends AppCompatActivity {

    private TextureView mCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCameraView = findViewById(R.id.camera_view);
        mCameraView.setSurfaceTextureListener(listener);
    }

    TextureView.SurfaceTextureListener listener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //当SurfaceTexture可用的时候
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            //当SurfaceTexture状态改变时调用此方法
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            //当SurfaceTexture销毁时调用此方法
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            //当SurfaceTexture状态更新时调用此方法
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupCamera(){
        getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
