package com.rq.practice.activities.practice.complex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.rq.practice.R;

public class ComplexActivity extends AppCompatActivity {

    private RecyclerView mComplexList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_pra);
        mComplexList = findViewById(R.id.complex_list);
    }
}
