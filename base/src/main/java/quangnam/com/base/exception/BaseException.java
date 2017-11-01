/*
 * MIT License
 *
 * Copyright (c) 2017 Tran Quang Nam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package quangnam.com.base.exception;

/**
 * Created by quangnam on 3/4/16.
 * Common exception for all system
 */
public class BaseException extends RuntimeException {

    public static final int RK_UNKNOWN = 1;
    public static final int RK_NETWORK_ERROR = 10;

    private int mErrCode;
    private String mMessage;

    public BaseException(String message) {
        this(RK_UNKNOWN, message);
    }

    public BaseException(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public BaseException(int errorCode, String message, Throwable throwable) {
        super(message, throwable);

        mErrCode = errorCode;
        mMessage = message;
    }

    public int getErrorCode() {
        return mErrCode;
    }

    public void setErrorCode(int errCode) {
        mErrCode = errCode;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    @Override
    public String toString() {
        return "ErrorCode: " + mErrCode + "\n" + super.toString();
    }

    @Override
    public void printStackTrace() {
        System.err.println("Exception with error code "
                + mErrCode);
        System.err.println(mMessage);
        super.printStackTrace();
    }
}
