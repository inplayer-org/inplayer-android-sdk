package com.sdk.inplayer.configuration;

import android.content.Context;
import android.util.Log;

import com.sdk.inplayer.BuildConfig;
import com.sdk.inplayer.api.Asset;
import com.sdk.inplayer.api.Notification;
import com.sdk.inplayer.api.Payment;
import com.sdk.inplayer.api.Subscription;
import com.sdk.inplayer.di.InjectModules;

import static org.koin.java.standalone.KoinJavaComponent.inject;


/**
 * Initialize SDK with Configuration.
 */
public class InPlayer {

    public static com.sdk.inplayer.api.Account Account;
    public static Asset Assets;
    public static Notification Notification;
    public static Payment Payment;
    public static Subscription Subscription;

    private static boolean isInitialized = false;

    // Suppress constructor to prevent subclassing
    private InPlayer() {
        throw new AssertionError();
    }

    /**
     * Authenticates this client as belonging to your application.
     * This must be called before your
     * application can use the InPlayer SDK . The recommended way is to put a call to
     * {@code InPlayer.initialize} in your {@code Application}'s {@code onCreate} method:
     * <p/>
     * <p>
     * public class MyApplication extends Application {
     * public void onCreate() {
     * InPlayer.initialize(configuration);
     * }
     * }
     *
     * @param configuration The configuration for your application.
     */
    public static void initialize(Configuration configuration) {
        init(configuration);
    }

    /**
     * Init.
     *
     * @param configuration the configuration
     */
    private static void init(Configuration configuration) {

        if (isInitialized) {
            Log.e("InPlayer", "InPlayer is already initialized");
            return;
        }

        InjectModules.INSTANCE.init(configuration);

        Account = inject(com.sdk.inplayer.api.Account.class).getValue();

        Assets = inject(Asset.class).getValue();

        Notification = inject(com.sdk.inplayer.api.Notification.class).getValue();

        Payment = inject(Payment.class).getValue();

        Subscription = inject(Subscription.class).getValue();

        isInitialized = true;

    }

    /**
     * The enum Enviorment type.
     */
    public enum EnvironmentType {
        /**
         * Define the Production environment. (Default)
         */
        PRODUCTION,

        /**
         * Define the Staging environment.
         */
        STAGING,

        /**
         * Define the Debug environment.
         */
        DEBUG
    }

    /**
     * Represents an opaque configuration for the {@code InPlayer} SDK configuration.
     */
    public static final class Configuration {


        public final String referrer;
        public final Context context;
        public final String mServerUrl;
        public final String mMerchantUUID;
        public final Boolean isDebug;
        final EnvironmentType environmentType;

        private Configuration(Builder builder) {
            this.mServerUrl = getServerUrl(builder.environmentType);
            this.referrer = builder.referrer;
            this.context = builder.context;
            this.mMerchantUUID = builder.merchantUUID;
            this.environmentType = builder.environmentType;
            this.isDebug = isDebug(builder.environmentType);
        }

        private String getServerUrl(EnvironmentType environmentType) {
            switch (environmentType) {
                case STAGING:
                case DEBUG:
                    return BuildConfig.BASE_URL_STAGING;
                case PRODUCTION:
                    return BuildConfig.BASE_URL_PRODUCTION;
            }
            return BuildConfig.BASE_URL_PRODUCTION;
        }

        private boolean isDebug(EnvironmentType environmentType) {
            switch (environmentType) {
                case STAGING:
                case DEBUG:
                    return true;
                case PRODUCTION:
                    return false;
            }
            return false;
        }


        /**
         * Allows for simple constructing of a {@code Configuration} object.
         */
        public static final class Builder {
            private String referrer;
            private String merchantUUID;
            private Context context;
            private EnvironmentType environmentType = EnvironmentType.STAGING;

            /**
             * Initialize a bulider with a given context.
             * <p>
             * This context will then be passed through to the rest of the InPlayer SDK for use during
             * initialization.
             *
             * @param context      The active {@link Context} for your application. Cannot be null.
             * @param merchantUUID The Merchant UUID used for your InPlayer Account
             */
            public Builder(Context context, String merchantUUID) {
                this.merchantUUID = merchantUUID;
                this.context = context;
            }

            /**
             * Set the Enviorment type used for your application
             *
             * @param environment The {@link EnvironmentType}
             * @return The same builder, for easy chaining.
             */
            public Builder withEnvironment(EnvironmentType environment) {
                this.environmentType = environment;
                return this;
            }

            /**
             * Set the Referrer string used for your application
             *
             * @param referrer The Referrer String used to describe the installation of the user.
             * @return The same builder, for easy chaining.
             */
            public Builder withReferrer(String referrer) {
                this.referrer = referrer;
                return this;
            }


            /**
             * Construct this builder into a concrete {@code Configuration} instance.
             *
             * @return A constructed {@code Configuration} object.
             */
            public Configuration build() {
                return new Configuration(this);
            }
        }
    }

}
