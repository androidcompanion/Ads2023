package com.myads2023.ads.adsmodels;

import android.app.Activity;
import android.app.ProgressDialog;

public class ConstantAds {

    public static boolean IS_APP_KILLED = false;
    public static boolean IS_INTER_SHOWING = false;
    public static ProgressDialog pDialogDelay = null;
    public static int adCountDefault = 3;
    public static int AD_DELAY = 1500;
    public static boolean PRELOAD_REWARD = false;
    public static String AD_MESSAGE = "Showing Ad...";
    public static String adUrlId = "";
    public static String ihAdsID = "";
    public static String fbAdsID = "";
    public static String ihInterID = "";
    public static String ihNativeID = "";
    public static String setIHAdsID(String url){
        return ihAdsID = url;
    }
    public static String setFBAdsID(String url){
        return fbAdsID = url;
    }
    public static String setIHInterID(String url){
        return ihInterID = url;
    }
    public static String setIHNativeID(String url){
        return ihNativeID = url;
    }
    public static String setAdsUrlID(String url){
        return adUrlId = url;
    }
    public static Boolean preloadRewarded(Boolean preload){
        return PRELOAD_REWARD = preload;
    }

    public static void showProgress(Activity context){
        pDialogDelay = new ProgressDialog(context);
        pDialogDelay.show();
    }
    public static void showProgress(Activity context, String text){
        pDialogDelay = new ProgressDialog(context);
        pDialogDelay.setMessage(text);
        pDialogDelay.show();
    }

    public static void dismisProgress(Activity context){
        if (pDialogDelay != null && !context.isFinishing() && !context.isDestroyed()){
            pDialogDelay.dismiss();
        }
    }
}


