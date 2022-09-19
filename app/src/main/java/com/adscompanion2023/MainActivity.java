package com.adscompanion2023;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.Callable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBanner(View view) {
    }

    public void showInter(View view) {
        showInterstitialAd(MainActivity.this, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                toast("AdClosed");
                return null;
            }
        });
    }

    public void showNative(View view) {
        showNativeAd(findViewById(R.id.lay_native));
    }

    public void showAppServiceDialog(View view) {
        AppService(BuildConfig.VERSION_NAME);
    }

    public void showSmallNative(View view) {
        showNativeAd2Extra(findViewById(R.id.lay_smallnative));
    }
}