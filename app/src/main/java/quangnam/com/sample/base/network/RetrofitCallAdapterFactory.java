package quangnam.com.sample.base.network;

import android.content.Context;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import quangnam.com.base.exception.BaseException;
import quangnam.com.base.utils.Log;
import quangnam.com.sample.data.exception.ApiException;
import quangnam.com.sample.di.ApplicationContext;
import quangnam.com.sample.util.AppUtils;
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
    private final Context mContext;

    @Inject
    RetrofitCallAdapterFactory(@ApplicationContext Context context) {
        mOriginal = RxJava2CallAdapterFactory.create();
        mContext = context;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit), mContext);
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final Retrofit mRetrofit;
        private final CallAdapter<R, Object> mWrapped;
        private final Context mContext;

//        RxCallAdapterWrapper(CallAdapter<R, Object> wrapped) {
//            mWrapped = wrapped;
//        }


        RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<R, Object> wrapped, Context context) {
            mRetrofit = retrofit;
            mWrapped = wrapped;
            mContext = context;
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

                return AppUtils.addLocalizedException(mContext, new BaseException(BaseException.RK_NETWORK_ERROR, exception.response().message(), exception));
            }

            if (throwable instanceof IOException) {
                Log.e("Network error happened. Check network connection.");

                return AppUtils.addLocalizedException(mContext, new BaseException(BaseException.RK_NETWORK_ERROR, throwable.getMessage(), throwable));
            }

            if (throwable instanceof ApiException) {
                Log.e("Api Exception with code: %d, error: %s", ((ApiException) throwable).getErrorCode(), throwable.getMessage());
                return AppUtils.addLocalizedException(mContext, (BaseException) throwable);
            }

            return new BaseException(BaseException.RK_UNKNOWN, throwable.getMessage(), throwable)
                    .setLocalizedMessage(mContext.getString(quangnam.com.sample.R.string.error_uknown));
        }
    }
}
