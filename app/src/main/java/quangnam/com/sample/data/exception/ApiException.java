package quangnam.com.sample.data.exception;

import quangnam.com.base.exception.BaseException;

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

public class ApiException extends BaseException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ApiException(int errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }
}
