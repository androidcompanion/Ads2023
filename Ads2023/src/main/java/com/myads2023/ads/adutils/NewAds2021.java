package com.myads2023.ads.adutils;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.myads2023.ads.adsmodels.AdsPref;

public class NewAds2021 extends Application {
    private static AppOpenManager appOpenManager;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {}
                });
        AudienceNetworkAds.initialize(this);
//        AudienceNetworkAds.initialize(getApplicationContext());
        if (new AdsPref(this).appRunCount() != 1){
            appOpenManager = new AppOpenManager(this);
        }



    }
}
