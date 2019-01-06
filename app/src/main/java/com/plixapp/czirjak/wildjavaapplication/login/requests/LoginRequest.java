package com.plixapp.czirjak.wildjavaapplication.login.requests;

import com.rainy.networkhelper.annotation.HeaderParam;
import com.rainy.networkhelper.annotation.RequestMethod;
import com.rainy.networkhelper.request.ParserRequest;

import static com.plixapp.czirjak.wildjavaapplication.common.Constants.ACCEPT;
import static com.plixapp.czirjak.wildjavaapplication.common.Constants.ACCEPT_LANGUAGE;
import static com.plixapp.czirjak.wildjavaapplication.common.Constants.BASE_HOST;
import static com.plixapp.czirjak.wildjavaapplication.common.Constants.CACHECONTROL;
import static com.plixapp.czirjak.wildjavaapplication.common.Constants.DEVICEID;
import static com.plixapp.czirjak.wildjavaapplication.common.Constants.TOKEN;

@RequestMethod(url = BASE_HOST +"/api/v1/login")
public class LoginRequest extends ParserRequest<LoginResponse> {

    @HeaderParam(name = "Authorization")
    private String auth = TOKEN;

    @HeaderParam(name = "deviceid")
    private String deviceid = DEVICEID;

    @HeaderParam(name = "Accept")
    private String accept = ACCEPT;

    @HeaderParam(name = "Cache-Control")
    private String cacheControl = CACHECONTROL;

    @HeaderParam(name = "Accept-Language")
    private String acceptlanguage = ACCEPT_LANGUAGE;

    @HeaderParam(name = "email")
    private String email;

    @HeaderParam(name = "password")
    private String password;


    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}