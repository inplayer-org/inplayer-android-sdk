<h1 align="center">
  <a target="_blank" href="https://inplayer-org.github.io/inplayer-android-sdk/">
    <img src="https://assets.inplayer.com/images/inplayer-256.png" alt="inplayer-android-sdk" title="InPlayer Android SDK" width="300">
    <br />
    <span style="font-size: 1.5rem; color: blue">InPlayer's Android SDK</span>
  </a>
</h1>
<p align="center" style="font-size: 1.2rem;">InPlayer's Android API Client Wrapper</p>

## Requirements

* Android Studio
* Min Sdk Version 17

## Dependency

Add this in your root build.gradle file (not your module build.gradle file):

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your project `build.gradle`
```gradle
dependencies {
    implementation 'com.github.inplayer-org.inplayer-android-sdk:InPlayer:${latest_version_here}'
}
```
replacing `latest_version_here` with the latest released version (see JitPack badge above)

## Usage

### Setup
Initialize InPlayer in a custom class that extends `Application`:
```java

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        InPlayer.initialize(new InPlayer.Configuration.Builder(this, "YOUR_MERCHANT_UUID_HERE")
        //Optional
        .withReferrer("YOUR_REFERRER_STRING_HERE")
        //Optional -Default is set to Production
        .withEnvironment(InPlayer.EnvironmentType.STAGING)
        .build());
    }
}
```

The custom `Application` class must be registered in `AndroidManifest.xml`:

```xml
    <application
        android:name=".App"
    ...>
    ...
    </application>
```

### Services

InPlayerSDK consists out of four services:
`Account`, `Asset`, `Payment` and `Notification`.

#### Account
Account related methods.

##### Kotlin Example
```kotlin
import com.sdk.inplayer.configuration.InPlayer

    InPlayer.Account.authenticate("YOUR_USERNAME_HERE", "YOUR_PASSWORD_HERE", InPlayerCallback { inPlayerUser, error ->
        if (error == null) {
            //SUCCESS - Handle InPlayerUser
        } else {
            //Handle Error
        }
    })
```

#### Java Example
```java
    import com.sdk.inplayer.configuration.InPlayer

    InPlayer.Account.authenticate("YOUR_USERNAME_HERE", "YOUR_PASSWORD_HERE", new InPlayerCallback<InPlayerAuthorizationModel, InPlayerException>() {
        @Override
        public void done(InPlayerAuthorizationModel value, InPlayerException exception) {   }
    });
```

##### Asset
Asset related methods

###### Example
Get item details:

#### Kotlin Example
```kotlin
import com.sdk.inplayer.configuration.InPlayer

    InPlayer.Assets.getItemDetails(ITEM_ID_HERE, InPlayerCallback { inPlayerItem, error ->
    if (error == null) {
    //SUCCESS - Handle InPlayerItem
    } else {
    //Handle Error
    }
    })
```

#### Java Example
```java
import com.sdk.inplayer.configuration.InPlayer;

    InPlayer.Assets.getItemDetails(ITEM_ID_HERE, new InPlayerCallback<InPlayerItem, InPlayerException>() {
        @Override
        public void done(InPlayerItem value, InPlayerException exception) { }
    });
```

##### Notification

Notification service has two methods:

Used for subscribtion, For best practice please subscribe for the notifications on your Activity/Fragment  `onResume()` and call  `dissconect()` on  `onPause()` so we don't introduce  memory leaks.


#### Kotlin Example
```kotlin
import com.sdk.inplayer.configuration.InPlayer

    override fun onResume() {
        super.onResume()

        InPlayer.Notification.subscribe(object : InPlayerNotificationCallback {

        override fun onStatusChanged(status: InPlayerNotificationStatus) {  }

        override fun onMessageReceived(message: InPlayerNotification) { }

        override fun onError(t: InPlayerException) {    }

    })
    }
```

#### Java Example
```java
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
```

For disconnect
```java
    @Override
    protected void onPause() {
        super.onPause();
        InPlayer.Notification.disconnect();
    }

```

## Contributing

We are thankful for any contributions made by the community. By contributing you agree to abide by
the Code of Conduct in the [Contributing Guidelines](https://github.com/inplayer-org/inplayer-ui/blob/master/.github/CONTRIBUTING.md).

## License

Licensed under the MIT License, Copyright © 2018-present InPlayer.

See [LICENSE](https://github.com/inplayer-org/inplayer-android-sdk/blob/master/LICENSE) for more information.