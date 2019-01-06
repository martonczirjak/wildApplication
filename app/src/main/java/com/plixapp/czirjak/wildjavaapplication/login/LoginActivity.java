package com.plixapp.czirjak.wildjavaapplication.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.plixapp.czirjak.wildjavaapplication.R;
import com.plixapp.czirjak.wildjavaapplication.databinding.ActivityLoginBinding;
/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);


    }
}
