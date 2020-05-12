package com.rq.practice.database.base;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;

import com.rq.practice.utils.EasyLog;

import java.io.File;

public class DataBaseContext extends ContextWrapper {

    public DataBaseContext(Context base) {
        super(base);
    }

    @Override
    public File getDatabasePath(String name) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = getExternalFilesDir(null);
            if (file != null) {
                String rootPath = file.getAbsolutePath();
                File dataBaseFile = new File(rootPath, "database");
                if (dataBaseFile.mkdirs()) {
                    dataBaseFile = new File(dataBaseFile, name);
                    return dataBaseFile;
                }
            }
        }
        return super.getDatabasePath(name);
    }
}
