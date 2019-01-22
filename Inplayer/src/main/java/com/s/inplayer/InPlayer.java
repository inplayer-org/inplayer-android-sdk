package com.s.inplayer;

import android.content.Context;

import com.s.inplayer.api.Asset;
import com.s.inplayer.api.Notification;
import com.s.inplayer.api.Payment;
import com.s.inplayer.di.InjectModules;

import static org.koin.java.standalone.KoinJavaComponent.inject;


/**
 * Created by victor on 12/24/18
 */
public class InPlayer {

    public static com.s.inplayer.api.Account Account;

    public static Asset Assets;

    public static Notification Notification;

    public static Payment Payment;

    // Suppress constructor to prevent subclassing
    private InPlayer() {
        throw new AssertionError();
    }

    public static void initialize(Configuration configuration) {
        init(configuration);
    }

    static void init(Configuration configuration) {

        /**
         * Setup Account
         * */
        InjectModules.INSTANCE.init(configuration);

        Account = inject(com.s.inplayer.api.Account.class).getValue();

        Assets = inject(Asset.class).getValue();

        Notification = inject(com.s.inplayer.api.Notification.class).getValue();

        Payment = inject(Payment.class).getValue();

    }

    public enum EnviormentType {
        // Sets project environment to production (default)
        PRODUCTION,

        // Sets project environment to staging
        STAGING
    }

    public static final class Configuration {

        public final String referrer;
        public final Context context;
        public final String mServerUrl;
        public final String mMerchantUUID;
        public final Boolean isDebug;
        final EnviormentType enviormentType;

        private Configuration(Builder builder) {
            this.mServerUrl = getServerUrl(builder.enviormentType);
            this.referrer = builder.mReferrer;
            this.context = builder.context;
            this.mMerchantUUID = builder.mMerchantUUID;
            this.enviormentType = builder.enviormentType;
            this.isDebug = isDebug(builder.enviormentType);
        }

        private String getServerUrl(EnviormentType enviormentType) {
            switch (enviormentType) {
                case STAGING:
                    return BuildConfig.BASE_URL_STAGING;
                case PRODUCTION:
                    return BuildConfig.BASE_URL_PRODUCTION;
            }
            return BuildConfig.BASE_URL_PRODUCTION;
        }

        private boolean isDebug(EnviormentType enviormentType) {
            switch (enviormentType) {
                case STAGING:
                    return true;
                case PRODUCTION:
                    return false;
            }
            return false;
        }


        public static final class Builder {
            private String mReferrer;
            private String mMerchantUUID;
            private Context context;
            private EnviormentType enviormentType = EnviormentType.PRODUCTION;

            public Builder(Context context, String mMerchantUUID, String mReferrer) {
                this.mReferrer = mReferrer;
                this.mMerchantUUID = mMerchantUUID;
                this.context = context;
            }

            public Builder withEnvironment(EnviormentType environment) {
                this.enviormentType = environment;
                return this;
            }

            public Configuration build() {
                return new Configuration(this);
            }
        }
    }

}
