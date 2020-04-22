package com.rq.practice.activities.practice.edit;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;

public class PhotoSelectActivity extends BaseToolBarActivity {

    private Button mPhotoSelect;

    private static final int EDIT_PIC_REQUEST_CODE = 0x00000001;

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
                if (data == null){
                    showErrorTips();
                    return;
                }
                Uri uri = data.getData();
                if (uri == null){
                    showErrorTips();
                    return;
                }
                String[] picColumn = new String[]{MediaStore.Images.Media.DATA};
                Cursor cursor = null;
                try {
                    cursor = getContentResolver().query(uri, picColumn, null, null, null);
                    // 由于只使用一张图片，只查询一次
                    if (cursor != null && cursor.moveToFirst()){
                        int columIndex = cursor.getColumnIndexOrThrow(picColumn[0]);
                        String path = cursor.getString(columIndex);
                        // TODO 发送图片到PictureEditActivity
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
    }

    private void showErrorTips(){
        Toast.makeText(PhotoSelectActivity.this,
                "图片获取失败!", Toast.LENGTH_SHORT).show();
    }
}
