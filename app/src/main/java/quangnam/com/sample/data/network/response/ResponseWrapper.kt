package quangnam.com.sample.data.network.response

import com.google.gson.annotations.SerializedName

import quangnam.com.sample.util.Constant

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

class ResponseWrapper<T> {

    @SerializedName(STATUS)
    val status: String? = null

    @SerializedName(CODE)
    val code: String? = null

    @SerializedName(MESSAGE)
    val `object`: T? = null

    val isResponseSuccess: Boolean
        get() = status!!.equals(Constant.ResponseStatus.success, ignoreCase = true)

    companion object {
        const val STATUS = "status"
        const val CODE = "code"
        const val MESSAGE = "message"
    }
}
