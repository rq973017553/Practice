package com.rq.practice.activities.practice.edit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.utils.FileUtils;
import com.rq.practice.utils.Tools;

import java.io.File;

public class PhotoSelectActivity extends BaseToolBarActivity {

    private Button mPhotoSelect;

    private static final int EDIT_PIC_REQUEST_CODE = 0x00000001;

    public static final int REQUEST_CROP_PHOTO = 0x00000003;

    @Override
    public int getLayoutID() {
        return R.layout.activity_photo_select_pra;
    }

    @Override
    public void bindView() {
        mPhotoSelect = findViewById(R.id.photo_select);
    }

    @Override
    public void initData() {
        mPhotoSelect.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, EDIT_PIC_REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == EDIT_PIC_REQUEST_CODE){
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        showErrorTips();
                        return;
                    }
//                    String[] picColumn = new String[]{MediaStore.Images.Media.DATA};
//                    Cursor cursor = null;
//                    try {
//                        cursor = getContentResolver().query(uri, picColumn, null, null, null);
//                        // 由于只使用一张图片，只查询一次
//                        if (cursor != null && cursor.moveToFirst()) {
//                            int columIndex = cursor.getColumnIndexOrThrow(picColumn[0]);
//                            String path = cursor.getString(columIndex);
//                            // TODO 发送图片到PictureEditActivity
//                        }
//                    } finally {
//                        if (cursor != null) {
//                            cursor.close();
//                        }
//                    }
                    Uri selectedImage = data.getData();
                    if (selectedImage == null){
                        showErrorTips();
                        return;
                    }
                    String toPath = FileUtils.getFilesDir(this, FileUtils.CROP_IMAGE_PATH);
                    File file = new File(toPath, String.format("head_portrait_%x.jpg", System.currentTimeMillis()));
                    Uri cropBitmapUri = Uri.fromFile(file);
                    startCropBitmap(selectedImage, cropBitmapUri);
                }else {
                    showErrorTips();
                }
            }else if(requestCode == REQUEST_CROP_PHOTO){
            }
        }
    }

    private void startCropBitmap(Uri fromUri, Uri toUri){
        Intent intent = Tools.doCropPhoto(fromUri, toUri, 1, 1, 500, 500);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    private void showErrorTips(){
        Toast.makeText(PhotoSelectActivity.this,
                "图片获取失败!", Toast.LENGTH_SHORT).show();
    }
}
