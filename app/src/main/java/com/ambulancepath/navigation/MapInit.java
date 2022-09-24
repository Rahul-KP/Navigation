package com.ambulancepath.navigation;

import android.app.Application;

import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.services.account.MapplsAccountManager;

public class MapInit extends Application {

    public void onCreate() {
        super.onCreate();

        MapplsAccountManager.getInstance().setRestAPIKey(getRestAPIKey());
        MapplsAccountManager.getInstance().setMapSDKKey(getMapSDKKey());
        MapplsAccountManager.getInstance().setAtlasClientId(getAtlasClientId());
        MapplsAccountManager.getInstance().setAtlasClientSecret(getAtlasClientSecret());
        Mappls.getInstance(this);
    }

    private String getAtlasClientId() {
        return "33OkryzDZsIF6Th65B3OtwQBV2cLHeHmkqmlbO7mUgIvlUbMv1EqNZOljG6FxgI9kf3ie-5913Vum_TyV_ePH1hLixZX-wGZ";
    }

    private String getAtlasClientSecret() {
        return "lrFxI-iSEg9iRJEYp5U1-l5oZb48qwNSbJMSaCwH3nINClkwMl0U8NX0GXiTwP_3COR-T3-HjmnRpc7VnjQWRWW4bsCsPhPbnYLUp705ZmY=";
    }

    private String getMapSDKKey() {
        return "c5723395063fc4a3ba072456fe05978c";
    }

    private String getRestAPIKey() {
        return "c5723395063fc4a3ba072456fe05978c";
    }
}
