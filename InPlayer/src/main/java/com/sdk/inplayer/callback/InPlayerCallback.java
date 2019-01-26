package com.sdk.inplayer.callback;

import com.sdk.inplayer.model.error.InPlayerException;


public interface InPlayerCallback<V, E extends InPlayerException> {

    void done(V value, E exception);

}
