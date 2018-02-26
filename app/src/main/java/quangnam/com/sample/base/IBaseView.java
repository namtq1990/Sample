package quangnam.com.sample.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import quangnam.com.base.exception.BaseException;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void onError(@NonNull BaseException exception);
    void onError(@StringRes int resID);
    void onError(String message);
    void onErrorCode(int errorCode);
    void hideKeyboard();
}
