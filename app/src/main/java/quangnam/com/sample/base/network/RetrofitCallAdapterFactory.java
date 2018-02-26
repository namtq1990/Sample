package quangnam.com.sample.base.network;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import quangnam.com.base.exception.BaseException;
import quangnam.com.base.utils.Log;
import quangnam.com.sample.data.exception.ApiException;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

public class RetrofitCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory mOriginal;

    private RetrofitCallAdapterFactory() {
        mOriginal = RxJava2CallAdapterFactory.create();
    }

    public static RetrofitCallAdapterFactory create() {
        return new RetrofitCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final Retrofit mRetrofit;
        private final CallAdapter<R, Object> mWrapped;

//        RxCallAdapterWrapper(CallAdapter<R, Object> wrapped) {
//            mWrapped = wrapped;
//        }


        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<R, Object> wrapped) {
            mRetrofit = retrofit;
            mWrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrapped.responseType();
        }

        @Override
        public Object adapt(@android.support.annotation.NonNull Call<R> call) {
            return ((Observable<R>) mWrapped.adapt(call))
                    .subscribeOn(Schedulers.io())
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends R>>() {
                        @Override
                        public ObservableSource<? extends R> apply(@NonNull Throwable throwable) throws Exception {
                            return Observable.error(toRetrofitException(throwable));
                        }
                    });
        }

        BaseException toRetrofitException(Throwable throwable) {
            if (throwable instanceof HttpException) {
                HttpException exception = (HttpException) throwable;

                Log.e("Network error happened. Check server status.");
                throwable.printStackTrace();

                return new BaseException(BaseException.RK_NETWORK_ERROR, exception.response().message(), exception);
            }

            if (throwable instanceof IOException) {
                Log.e("Network error happened. Check network connection.");

                return new BaseException(BaseException.RK_NETWORK_ERROR, throwable.getMessage(), throwable);
            }

            if (throwable instanceof ApiException) {
                Log.e("Api Exception with code: %d, error: %s", ((ApiException) throwable).getErrorCode(), throwable.getMessage());
                return (BaseException) throwable;
            }

            return new BaseException(BaseException.RK_UNKNOWN, throwable.getMessage(), throwable);
        }
    }
}
