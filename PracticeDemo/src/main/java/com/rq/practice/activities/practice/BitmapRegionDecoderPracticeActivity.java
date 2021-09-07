package com.rq.practice.activities.practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.widget.ImageView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Android 高清加载巨图方案 拒绝压缩图片
 * https://blog.csdn.net/lmj623565791/article/details/49300989
 */
public class BitmapRegionDecoderPracticeActivity extends BaseToolBarActivity {

    private ImageView imageView;


    @Override
    public int getLayoutID() {
        return R.layout.activity_bitmap_region_decoder_pra;
    }

    @Override
    public void bindView() {
        imageView = findViewById(R.id.bitmap_region_image_view);
    }

    @Override
    public void initData() {
        try {
            InputStream inputStream = getAssets().open("world.jpg");
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int bitmapWidth = tmpOptions.outWidth;
            int bitmapHeight = tmpOptions.outHeight;

            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(bitmapWidth / 2 - 100,
                            bitmapHeight / 2 - 100, bitmapWidth / 2 + 100,
                            bitmapHeight / 2 + 100),
                            options);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
