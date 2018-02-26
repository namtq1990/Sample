package quangnam.com.sample.data.network.response;

import com.google.gson.annotations.SerializedName;

import quangnam.com.sample.util.Constant;

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

public class ResponseWrapper<T> {
    public static final String STATUS = "status";
    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    @SerializedName(STATUS)
    private String status;

    @SerializedName(CODE)
    private String code;

    @SerializedName(MESSAGE)
    private T object;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public T getObject() {
        return object;
    }

    public boolean isResponseSuccess() {
        return status.equalsIgnoreCase(Constant.ResponseStatus.success);
    }
}
