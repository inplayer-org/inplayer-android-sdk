package com.s.inplayer;

import android.content.Context;


/**
 * Created by victor on 12/24/18
 */
public class InPlayer {

    public com.s.inplayer.api.Account Account;


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
        //this.Account = new Account();
    }


    public static final class Configuration {

        final Context mContext;
        final String mServerUrl;

        private Configuration(Builder builder) {
            this.mContext = builder.mContext;
            this.mServerUrl = builder.mIsStaging ? BuildConfig.BASE_URL_STAGING : BuildConfig.BASE_URL_PRODUCTION;
        }

        public static final class Builder {
            private Context mContext;
            private boolean mIsStaging;

            public Builder(Context context) {
                this.mContext = context;
            }

            public Builder isStaging(boolean isStaging) {
                this.mIsStaging = isStaging;
                return this;
            }

            public Configuration build() {
                return new Configuration(this);
            }
        }
    }

}
