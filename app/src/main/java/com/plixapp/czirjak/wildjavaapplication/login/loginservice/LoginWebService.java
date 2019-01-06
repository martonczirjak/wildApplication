package com.plixapp.czirjak.wildjavaapplication.login.loginservice;

import android.content.Context;

import com.plixapp.czirjak.wildjavaapplication.login.requests.LoginRequest;
import com.plixapp.czirjak.wildjavaapplication.login.requests.LoginResponse;
import com.rainy.networkhelper.future.ExecutionFuture;

public class LoginWebService implements  LoginService{

    @Override
    public ExecutionFuture<LoginResponse> login(String email,String password,Context context){
        return new LoginRequest(email,password).getParsedFuture(context);
    }
}
