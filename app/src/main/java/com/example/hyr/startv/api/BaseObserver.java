package com.example.hyr.startv.api;

import android.text.TextUtils;
import android.util.Log;
import com.example.hyr.startv.result.BaseResult;
import com.orhanobut.logger.Logger;
import rx.Observer;

/**
 * 网络请求返回需要的模型
 * Created by ice on 3/3/16.
 */
public abstract class BaseObserver<T extends BaseResult> implements Observer<T> {

    protected abstract void onSucceed(T result);
    protected abstract void onError(String e);


    protected void onFailed(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Logger.d(msg);
        }
    }


    @Override
    public void onCompleted() {
        Log.i("onCompleted =====", "onCompleted");
    }


    @Override
    public void onError(Throwable e) {
        Log.i("onError====", e.toString());
        onError(e.toString());
    }


    @Override
    public void onNext(T result) {
        if (result != null) {
            onSucceed(result);
        } else {
            onFailed(null);
        }

    }
}
