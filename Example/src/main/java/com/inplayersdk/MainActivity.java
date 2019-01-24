package com.inplayersdk;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sdk.inplayer.callback.InPlayerCallback;
import com.sdk.inplayer.callback.InPlayerNotificationCallback;
import com.sdk.inplayer.configuration.InPlayer;
import com.sdk.inplayer.model.account.InPlayerAuthorizationModel;
import com.sdk.inplayer.model.assets.InPlayerItem;
import com.sdk.inplayer.model.error.InPlayerException;
import com.sdk.inplayer.model.notification.InPlayerNotification;
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus;

import org.jetbrains.annotations.NotNull;

/**
 * Created by victor on 1/24/19
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState,  @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);



        InPlayer.Notification.subscribe(new InPlayerNotificationCallback() {
            @Override
            public void onStatusChanged(@NotNull InPlayerNotificationStatus status) {

            }

            @Override
            public void onMessageReceived(@NotNull InPlayerNotification message) {

            }

            @Override
            public void onError(@NotNull InPlayerException t) {

            }
        });

        InPlayer.Notification.disconnect();

    }

    @Override
    protected void onResume() {
        super.onResume();

        InPlayer.Notification.subscribe(new InPlayerNotificationCallback() {
            @Override
            public void onStatusChanged(@NotNull InPlayerNotificationStatus status) {

            }

            @Override
            public void onMessageReceived(@NotNull InPlayerNotification message) {

            }

            @Override
            public void onError(@NotNull InPlayerException t) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        InPlayer.Notification.disconnect();
    }
}
