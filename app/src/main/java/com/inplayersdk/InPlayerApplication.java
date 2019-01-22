package com.inplayersdk;

import android.app.Application;

import com.sdk.inplayer.configuration.InPlayer;

/**
 * Created by victor on 12/24/18
 */
public class InPlayerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InPlayer.initialize(new InPlayer.Configuration.Builder(this, "7ad8a510-b720-4a18-aa38-0260e5fd1cb2",
                "https://services.inplayer.com")
                .withEnvironment(InPlayer.EnvironmentType.STAGING).build());

    }
}
