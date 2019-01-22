package com.sdk.inplayer.configuration;

import android.content.Context;

import com.sdk.inplayer.BuildConfig;
import com.sdk.inplayer.api.Asset;
import com.sdk.inplayer.api.Notification;
import com.sdk.inplayer.api.Payment;
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
     *
     * public class MyApplication extends Application {
     *   public void onCreate() {
     *     InPlayer.initialize(configuration);
     *   }
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
    static void init(Configuration configuration) {

        InjectModules.INSTANCE.init(configuration);

        Account = inject(com.sdk.inplayer.api.Account.class).getValue();

        Assets = inject(Asset.class).getValue();

        Notification = inject(com.sdk.inplayer.api.Notification.class).getValue();

        Payment = inject(Payment.class).getValue();

    }

    /**
     * The enum Enviorment type.
     */
    public enum EnvironmentType {
        /**
         * The Production. (Default)
         */
        PRODUCTION,

        /**
         * The Staging.
         */
        STAGING
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
            this.referrer = builder.mReferrer;
            this.context = builder.context;
            this.mMerchantUUID = builder.mMerchantUUID;
            this.environmentType = builder.environmentType;
            this.isDebug = isDebug(builder.environmentType);
        }

        private String getServerUrl(EnvironmentType environmentType) {
            switch (environmentType) {
                case STAGING:
                    return BuildConfig.BASE_URL_STAGING;
                case PRODUCTION:
                    return BuildConfig.BASE_URL_PRODUCTION;
            }
            return BuildConfig.BASE_URL_PRODUCTION;
        }

        private boolean isDebug(EnvironmentType environmentType) {
            switch (environmentType) {
                case STAGING:
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
            private String mReferrer;
            private String mMerchantUUID;
            private Context context;
            private EnvironmentType environmentType = EnvironmentType.PRODUCTION;

            /**
             * Initialize a bulider with a given context.
             * <p>
             * This context will then be passed through to the rest of the InPlayer SDK for use during
             * initialization.
             *
             * @param context The active {@link Context} for your application. Cannot be null.
             * @param merchantUUID The Merchant UUID used for your InPlayer Account
             * @param referrer The Referrer String used to describe the installation of the user.
             */
            public Builder(Context context, String merchantUUID, String referrer) {
                this.mReferrer = referrer;
                this.mMerchantUUID = merchantUUID;
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
