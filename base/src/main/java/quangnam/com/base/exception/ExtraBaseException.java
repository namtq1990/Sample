package quangnam.com.base.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by quangnam on 9/20/17.
 * Project sosokan-android
 */

public class ExtraBaseException extends BaseException {
    private Map<String, Object> mExtraData;

    public ExtraBaseException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public ExtraBaseException(int errorCode,
                              String message,
                              Throwable throwable) {
        super(errorCode, message, throwable);
        mExtraData = new HashMap<>();
    }

    public ExtraBaseException put(String key, Object data) {
        mExtraData.put(key, data);
        return this;
    }

    public Object get(String key) {
        return mExtraData.get(key);
    }

    public boolean containsKey(String key) {
        return mExtraData.containsKey(key);
    }
}
