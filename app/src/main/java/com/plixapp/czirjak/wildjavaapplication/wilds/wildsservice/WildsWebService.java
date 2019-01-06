package com.plixapp.czirjak.wildjavaapplication.wilds.wildsservice;

import android.content.Context;

import com.plixapp.czirjak.wildjavaapplication.wilds.model.Wild;
import com.plixapp.czirjak.wildjavaapplication.wilds.model.WildRealmModel;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsRequest;
import com.plixapp.czirjak.wildjavaapplication.wilds.wildsrequests.WildsResponse;
import com.rainy.networkhelper.future.ExecutionFuture;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.internal.annotations.ObjectServer;

public class WildsWebService implements WildsService {

    @Override
    public void cleanDatabase() {
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.beginTransaction();
        defaultInstance.deleteAll();
        defaultInstance.commitTransaction();
    }


    @Override
    public ExecutionFuture<WildsResponse> getWildsAndLoadToDatabase(final String hunterId, final String password, Context context) {
        return new ExecutionFuture<WildsResponse>() {
            @Override
            protected WildsResponse execute(Long timeoutMs) throws Exception {
                WildsResponse wildsResponse = new WildsRequest(hunterId, password).getParsedFuture(context).get();
                Realm defaultInstance = Realm.getDefaultInstance();
                List<WildRealmModel> models = new ArrayList<>();
                for (Wild wild : wildsResponse.getWildsList().wilds) {
                    WildRealmModel wildRealmModel = new WildRealmModel();
                    wildRealmModel.setServerId(wild.getId());
                    wildRealmModel.setName(wild.getName_sk());
                    models.add(wildRealmModel);
                }
                defaultInstance.beginTransaction();
                defaultInstance.insert(models);
                defaultInstance.commitTransaction();
                return wildsResponse;
            }
        };
    }

    @Override
    public List<Wild> getWildsFromDataBase(){
        List<Wild> wilds = new ArrayList<>();
        Realm defaultInstance = Realm.getDefaultInstance();
        RealmResults<WildRealmModel> all = defaultInstance.where(WildRealmModel.class).findAll();
        for (WildRealmModel wildRealmModel : all) {
            Wild wild = new Wild();
            wild.setId(wildRealmModel.getServerId());
            wild.setName_sk(wildRealmModel.getName());
            wilds.add(wild);
        }
        return wilds;
    }

    @Override
    public void closeDatabase(){
        Realm.getDefaultInstance().close();
    }


}
