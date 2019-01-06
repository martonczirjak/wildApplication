package com.plixapp.czirjak.wildjavaapplication.wilds;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.databinding.ActivityWildsBinding;

public class WildsActivity extends AppCompatActivity {

    private ActivityWildsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_wilds);
    }
}
