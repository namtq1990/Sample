package quangnam.com.sample.base.gson

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import quangnam.com.sample.data.exception.ApiException
import quangnam.com.sample.data.network.response.BaseErrorResponse
import quangnam.com.sample.data.network.response.ResponseWrapper
import quangnam.com.sample.util.AppUtils
import quangnam.com.sample.util.Constant
import java.io.IOException
import javax.inject.Inject

/**
 * Created by quangnam on 2/26/18.
 * Project Sample
 */

class ResponseAdapterFactory @Inject
internal constructor(private val mAppUtil: AppUtils) : TypeAdapterFactory {

    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
        val delegate = gson.getDelegateAdapter(this, type)
        val elementTypeAdapter = gson.getAdapter(JsonElement::class.java)
        val token = object : TypeToken<ResponseWrapper<BaseErrorResponse>>() {

        }
        val errorAdapter = gson.getDelegateAdapter(this, token)

        return if (!type.rawType.isAssignableFrom(ResponseWrapper::class.java)) {
            delegate
        } else object : TypeAdapter<T>() {
            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T) {
                delegate.write(out, value)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): T {
                val element = elementTypeAdapter.read(`in`)

                if (element.isJsonObject) {
                    val `object` = element.asJsonObject
                    if (`object`.has(ResponseWrapper.STATUS)) {
                        val status = `object`.get(ResponseWrapper.STATUS).asString
                        if (!status.equals(Constant.ResponseStatus.success, ignoreCase = true)) {
                            val response = errorAdapter.fromJsonTree(element)
                            throw mAppUtil.addLocalizedException(ApiException(Integer.parseInt(response.code), response.`object`!!.value ?: ""))
                        }
                    }
                }

                return delegate.fromJsonTree(element)
            }
        }

    }
}
