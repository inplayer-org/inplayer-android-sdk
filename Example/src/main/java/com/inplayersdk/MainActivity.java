package com.inplayersdk;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sdk.inplayer.callback.InPlayerNotificationCallback;
import com.sdk.inplayer.configuration.InPlayer;
import com.sdk.inplayer.model.error.InPlayerException;
import com.sdk.inplayer.model.notification.InPlayerNotification;
import com.sdk.inplayer.model.notification.InPlayerNotificationStatus;

import org.jetbrains.annotations.NotNull;

/**
 * Created by victor on 1/24/19
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


//        InPlayer.Account.getRegisterFields((value, exception) -> {
//
//            for (InPlayerRegisterFields registerFields : value) {
//
//                if (registerFields.getType() instanceof RegisterFieldType.Country) {
//
//                    ((RegisterFieldType.Country) registerFields.getType()).getOptions().
//                }
//
//            }
//
//        });


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

        InPlayer.Notification.unsubscribe();

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

        InPlayer.Notification.unsubscribe();
    }
}
