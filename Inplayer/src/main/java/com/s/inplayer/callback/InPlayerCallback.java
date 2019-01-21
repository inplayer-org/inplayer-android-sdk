package com.s.inplayer.callback;

import com.s.inplayer.model.error.InPlayerException;

/**
 * Created by victor on 12/22/18
 */
public interface InPlayerCallback<V, E extends InPlayerException> {

    void done(V vaue, E exception);

}
