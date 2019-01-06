package com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice;

import android.content.Context;

import com.plixapp.czirjak.wildjavaapplication.wilds.model.Wild;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsResponse;
import com.rainy.networkhelper.future.ExecutionFuture;

import java.util.List;

public interface WildsService {
    void cleanDatabase();

    ExecutionFuture<WildsResponse> getWildsAndLoadToDatabase(String hunterId, String password, Context context);

    List<Wild> getWildsFromDataBase();

    void closeDatabase();
}
