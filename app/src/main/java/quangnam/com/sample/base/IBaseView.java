package quangnam.com.sample.base;

import android.support.annotation.StringRes;

/**
 * Created by quangnam on 11/23/17.
 * Project Sample
 */

public interface IBaseView {
    void showLoading();
    void hideLoading();
    void onError(@StringRes int resID);
    void onError(String message);
    void onErrorCode(int errorCode);
    void hideKeyboard();
}
