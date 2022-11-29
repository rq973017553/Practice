package com.rq.practice.activities.practice.complex;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
