package quangnam.com.sample.feature.base.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import quangnam.com.base.exception.BaseException
import quangnam.com.base.utils.Log
import quangnam.com.sample.data.exception.ApiException
import quangnam.com.sample.di.ApplicationContext
import quangnam.com.sample.util.AppUtils
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Created by quangnam on 2/19/18.
 * Project Sample
 */

class RetrofitCallAdapterFactory @Inject
internal constructor(@param:ApplicationContext private val mContext: Context) : CallAdapter.Factory() {

    private val mOriginal: RxJava2CallAdapterFactory

    init {
        mOriginal = RxJava2CallAdapterFactory.create()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit) as CallAdapter<out Any, Any>, mContext)
    }

    private class RxCallAdapterWrapper<R>
    //        RxCallAdapterWrapper(CallAdapter<R, Object> wrapped) {
    //            mWrapped = wrapped;
    //        }


    internal constructor(private val mRetrofit: Retrofit, private val mWrapped: CallAdapter<R, Any>, private val mContext: Context) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return mWrapped.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            return (mWrapped.adapt(call) as Observable<R>)
                    .subscribeOn(Schedulers.io())
                    .onErrorResumeNext(Function { throwable -> Observable.error(toRetrofitException(throwable)) })
        }

        internal fun toRetrofitException(throwable: Throwable): BaseException {
            if (throwable is HttpException) {

                Log.e("Network error happened. Check server status.")
                throwable.printStackTrace()

                return AppUtils.addLocalizedException(mContext, BaseException(BaseException.RK_NETWORK_ERROR, throwable.response().message(), throwable))
            }

            if (throwable is IOException) {
                Log.e("Network error happened. Check network connection.")

                return AppUtils.addLocalizedException(mContext, BaseException(BaseException.RK_NETWORK_ERROR, throwable.message, throwable))
            }

            if (throwable is ApiException) {
                Log.e("Api Exception with code: %d, error: %s", throwable.errorCode, throwable.message)
                return AppUtils.addLocalizedException(mContext, throwable as BaseException)
            }

            return BaseException(BaseException.RK_UNKNOWN, throwable.message, throwable)
                    .setLocalizedMessage(mContext.getString(quangnam.com.sample.R.string.error_uknown))
        }
    }
}
