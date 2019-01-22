package com.sdk.inplayer.callback;

import com.sdk.inplayer.model.error.InPlayerException;

/**
 * Created by victor on 12/22/18
 */
public interface InPlayerCallback<V, E extends InPlayerException> {

    void done(V value, E exception);

}
