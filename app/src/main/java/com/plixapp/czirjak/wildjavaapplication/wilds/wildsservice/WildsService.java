package com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice;

import android.content.Context;

import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsResponse;
import com.rainy.networkhelper.future.ExecutionFuture;

public interface WildsService {
    void cleanDatabase();

    ExecutionFuture<WildsResponse> getWildsAndLoadToDatabase(String hunterId, String password, Context context);
}
