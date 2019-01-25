package com.inplayersdk;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.sdk.inplayer.configuration.InPlayer;


//7ad8a510-b720-4a18-aa38-0260e5fd1cb2
public class InPlayerApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        InPlayer.initialize(new InPlayer.Configuration.Builder(this, "YOUR_MERCHANT_UUID_HERE")
                //Optional
                .withReferrer("YOUR_REFERRER_STRING_HERE")
                //Default is set to Production
                .withEnvironment(InPlayer.EnvironmentType.STAGING)
                .build());

    }
}
