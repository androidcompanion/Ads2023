package com.myads2023.ads.adutils;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import static com.myads2023.ads.adutils.BaseSimpleClass.isConnected;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.myads2023.ads.gmodels.AdsPref;
import com.myads2023.ads.gmodels.ConstantAds;

import java.util.Date;


public class AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private final Application myApplication;
    private static final String LOG_TAG = "AppOpenManager";
//    private static String AD_UNIT_ID = "";
    private AppOpenAd appOpenAd = null;
    private static boolean isShowingAd = false;
    private long loadTime = 0;
    AdsPref adsPref;


    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private Activity currentActivity;


    /**
     * Constructor
     */
    public AppOpenManager(Application myApplication) {
        this.myApplication = myApplication;
        adsPref = new AdsPref(myApplication.getApplicationContext());
//        AD_UNIT_ID = adsPref.gAppopen2();

        this.myApplication.registerActivityLifecycleCallbacks(this);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }


    /**
     * Creates and returns ad request.
     */
    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */
//    public boolean isAdAvailable() {
//        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(2);
//    }
    public boolean isAdAvailable() {
        return appOpenAd != null;
    }

    public void     fetchAd() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return;
        }

        loadCallback =
                new AppOpenAd.AppOpenAdLoadCallback() {
                    /**
                     * Called when an app open ad has loaded.
                     *
                     * @param ad the loaded app open ad.
                     */
                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        AppOpenManager.this.appOpenAd = ad;
                        AppOpenManager.this.loadTime = (new Date()).getTime();
                    }

                    /**
                     * Called when an app open ad has failed to load.
                     *
                     * @param loadAdError the error.
                     */
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        // Handle the error.
                    }

                };
        AdRequest request = getAdRequest();

        if (adsPref.appRunCount() == 1){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isConnected(myApplication.getApplicationContext()) ){
                        AppOpenAd.load(
                                myApplication, adsPref.gAppopen2(), request,
                                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

                    }

                }
            }, 5000);

        }else{
            if (isConnected(myApplication.getApplicationContext())){
                AppOpenAd.load(
                        myApplication, adsPref.gAppopen2(), request,
                        AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

            }

        }

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivity = null;
    }

  public void showAdIfAvailable() {
    // Only show ad if there is not already an app open ad currently showing
    // and an ad is available.
    if (!isShowingAd && isAdAvailable()) {
      Log.d(LOG_TAG, "Will show ad.");

      FullScreenContentCallback fullScreenContentCallback =
              new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                  // Set the reference to null so isAdAvailable() returns false.
                  AppOpenManager.this.appOpenAd = null;
                  isShowingAd = false;
                  fetchAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {}

                @Override
                public void onAdShowedFullScreenContent() {
                  isShowingAd = true;
                }
              };

      appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
        if (!ConstantAds.IS_APP_KILLED){
            appOpenAd.show(currentActivity);
        }
        if (!ConstantAds.IS_INTER_SHOWING){
            ConstantAds.IS_APP_KILLED = false;
        }

    } else {
      Log.d(LOG_TAG, "Can not show ad.");
      fetchAd();
    }
  }

    /** LifecycleObserver methods */
    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
        Log.d(LOG_TAG, "onStart");
    }
    /** Utility method to check if ad was loaded more than n hours ago. */
    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }


}

