package com.plixapp.czirjak.wildjavaapplication.login.requests;

public class LoginResponse {
    //TODO DATA-N belül a Data nullként jön sikeres belépésnél is
    private Data data;


    public LoginResponse() {
    }


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
