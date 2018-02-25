package quangnam.com.sample.ui.test.activity;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import quangnam.com.base.exception.BaseException;
import quangnam.com.base.utils.Log;
import quangnam.com.sample.base.BasePresenter;
import quangnam.com.sample.data.DataManager;
import quangnam.com.sample.base.rxjava.BaseCompleteAction;
import quangnam.com.sample.base.rxjava.BaseErrorAction;
import quangnam.com.sample.base.rxjava.BaseSuccessAction;
import quangnam.com.sample.data.network.response.ResponseWrapper;
import quangnam.com.sample.data.network.response.test.DogResponse;

/**
 * Created by quangnam on 10/16/17.
 * Project base
 */

class TestPresenter extends BasePresenter<ITestActivity.IView> implements ITestActivity.IPresenter {

    @Inject
    TestPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getTestingData() {
        getDisposable().add(getDataManager().getAllTestingData()
                .subscribe(new BaseSuccessAction<ResponseWrapper<ArrayList<DogResponse>>>() {
                               @Override
                               public void accept(ResponseWrapper<ArrayList<DogResponse>> arrayListResponseWrapper) throws Exception {
                                   super.accept(arrayListResponseWrapper);

                                   for (DogResponse dog: arrayListResponseWrapper.getObject()) {
                                       Log.d("Dog: %s", dog.getValue());
                                   }
                               }
                           },
                new BaseErrorAction() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        super.accept(throwable);

                        if (throwable instanceof BaseException) {
                            getView().onError((BaseException) throwable);
                        }
                    }
                }, new BaseCompleteAction()));
    }
}
