package quangnam.com.sample.base.rxjava;

import io.reactivex.functions.Consumer;
import quangnam.com.base.utils.Log;

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

public class BaseErrorAction implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) throws Exception {
        Log.d("Error happened");
        throwable.printStackTrace();
    }
}
