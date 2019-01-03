package quangnam.com.sample.data.exception

import quangnam.com.base.exception.BaseException

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

class ApiException : BaseException {

    constructor(message: String) : super(message) {}

    constructor(errorCode: Int, message: String) : super(errorCode, message) {}

    constructor(errorCode: Int, message: String, throwable: Throwable) : super(errorCode, message, throwable) {}
}
