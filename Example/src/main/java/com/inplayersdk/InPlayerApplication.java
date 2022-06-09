package com.inplayersdk;

import androidx.multidex.MultiDexApplication;

import com.sdk.inplayer.configuration.InPlayer;


//7ad8a510-b720-4a18-aa38-0260e5fd1cb2
public class InPlayerApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        InPlayer.initialize(new InPlayer.Configuration.Builder(this, BuildConfig.UUID)
                //Optional
                //.withReferrer("www.android.com")
                //Default is set to Production
                .withEnvironment(InPlayer.EnvironmentType.PRODUCTION)
                .build());

    }
}
