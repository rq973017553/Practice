package com.rq.practice.config;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;

@GlideModule
public class GlideConfig extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            File externalFilesDir = Environment.getExternalStorageDirectory();
            if (externalFilesDir != null){
                builder.setDiskCache(new DiskLruCacheFactory(externalFilesDir.getAbsolutePath(),"imageCache", 100*1024*1024));
            }
        }
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    }
}
