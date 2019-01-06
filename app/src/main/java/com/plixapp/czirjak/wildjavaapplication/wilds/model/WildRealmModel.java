package com.plixapp.czirjak.wildjavaapplication.wilds.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WildRealmModel extends RealmObject {
    /*@PrimaryKey
   private  id: Long = 0
*/
   private String serverId;
   private String name;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
