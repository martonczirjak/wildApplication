package com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests;

import com.google.gson.annotations.SerializedName;
import com.plixapp.czirjak.wildjavaapplication.wilds.model.WildsList;

public class WildsResponse {

    @SerializedName(value = "data" )
    private WildsList wildsList;

    public WildsList getWildsList() {
        return wildsList;
    }

    public void setWildsList(WildsList wildsList) {
        this.wildsList = wildsList;
    }
}
