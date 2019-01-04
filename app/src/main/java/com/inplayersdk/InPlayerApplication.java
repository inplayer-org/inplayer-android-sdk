package com.inplayersdk;

import android.app.Application;

import com.s.inplayer.InPlayer;

/**
 * Created by victor on 12/24/18
 */
public class InPlayerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InPlayer.initialize(new InPlayer.Configuration.Builder(this, BuildConfig.UUID, "https://services.inplayer.com")
                .isStaging(true).build());

    }
}
