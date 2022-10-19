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
        return "33OkryzDZsLade6RGpBoKkbC4sk4VkaOoFlxvYMpkOKLKViBnKiWq3A2xLnLBTSyjo6-49v6r4-e0a_6GZwJKg==";
    }

    private String getAtlasClientSecret() {
        return "lrFxI-iSEg8QyPb2DRSrrhWu4pM_X4pvUrf2iEW9b3wGSc-U66MfXYEDHXVMWZ7DjX0CS-0j3nAcloE-ldFBk3SEhnoGsFXo";
    }

    private String getMapSDKKey() {
        return "74220f65786b1ef375b985d857ccfeb1";
    }

    private String getRestAPIKey() {
        return "74220f65786b1ef375b985d857ccfeb1";
    }
}
