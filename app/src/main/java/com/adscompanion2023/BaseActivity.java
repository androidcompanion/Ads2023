package com.adscompanion2023;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.myads2023.ads.gmodels.ConstantAds;
import com.myads2023.ads.adutils.BaseSimpleClass;

public class BaseActivity extends BaseSimpleClass {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstantAds.setAdsUrlID("https://script.google.com/macros/s/AKfycbwPKJzbHsLkXmOZKOlnamtX-DQA81qfZDgvV1pRiADsJGY-WMfdagOa1K78YNsCXr_0LA/");
        ConstantAds.setIHAdsID("https://script.google.com/macros/s/AKfycbyEuopQ8PhFo5yFcmtmT9rLVT34JEybsSGUKntSZFLSEMlS-2YiKsXqXZWiCr3TKY9vzg/");
        ConstantAds.setCtColor(getResources().getColor(R.color.ctcolor));
        ConstantAds.setNativeButtonBg(R.drawable.sample_bg);
    }
}
