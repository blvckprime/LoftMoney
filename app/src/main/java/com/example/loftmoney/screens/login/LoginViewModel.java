package com.example.loftmoney.screens.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loftmoney.remote.AuthApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> messageString = new MutableLiveData<>("");
    public MutableLiveData<String> authToken = new MutableLiveData<>("");

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    void makeLogin(AuthApi authAPI, String userId){
        compositeDisposable.add(authAPI.makeLogin(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authResponse -> {
                    authToken.postValue(authResponse.getAuthToken());
                }, throwable -> {
                    messageString.postValue(throwable.getLocalizedMessage());
                }));

    }

}
