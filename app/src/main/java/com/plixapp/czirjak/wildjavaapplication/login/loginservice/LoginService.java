package com.plixapp.czirjak.wildjavaapplication.login.loginservice;

import android.content.Context;

import com.plixapp.czirjak.wildjavaapplication.login.requests.LoginResponse;
import com.rainy.networkhelper.future.ExecutionFuture;

public interface LoginService {
    ExecutionFuture<LoginResponse> login(String email, String password, Context context);
}
