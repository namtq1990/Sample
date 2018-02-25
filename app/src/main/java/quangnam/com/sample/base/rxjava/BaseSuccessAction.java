package quangnam.com.sample.base.rxjava;

import io.reactivex.functions.Consumer;

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

public class BaseSuccessAction<T> implements Consumer<T> {
    @Override
    public void accept(T t) throws Exception {
        return;
    }
}
