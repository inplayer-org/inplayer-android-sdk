package com.s.inplayer.callback;

/**
 * Created by victor on 12/22/18
 */
public interface InPlayerCallback<V, E extends Throwable> {

    void done(V vaue, E exception);

}
