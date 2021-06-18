package com.example.loftmoney.screens.main;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loftmoney.LoftApp;
import com.example.loftmoney.cells.MoneyItem;
import com.example.loftmoney.remote.MoneyApi;
import com.example.loftmoney.remote.MoneyRemoteItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<MoneyItem>> moneyItemsList = new MutableLiveData<>();
    public MutableLiveData<String> messageString = new MutableLiveData<>("");
    public MutableLiveData<Integer> messageInt = new MutableLiveData<>(-1);

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    // public void loadIncomes(MoneyApi moneyApi, SharedPreferences sharedPreferences) {
    //     String authToken = sharedPreferences.getString(LoftApp.AUTH_KEY, "");
    //
    //     compositeDisposable.add(moneyApi.getMoneyItems("income", authToken)
    //             .subscribeOn(Schedulers.io())
    //             .observeOn(AndroidSchedulers.mainThread())
    //             .subscribe(moneyRemoteItems -> {
    //                 if (moneyResponse.getStatus().equals("success")) {
    //                 List<MoneyItem> moneyItems = new ArrayList<>();
    //
    //                 for (MoneyRemoteItem moneyRemoteItem : moneyResponse.getMoneyItemsList()) {
    //                     moneyItems.add(MoneyItem.getInstance(moneyRemoteItem));
    //                 }
    //
    //                 moneyItemsList.postValue(moneyItems);
    //             } else {
    //                     messageInt.postValue(R.string.connection_lost);
    //                 }
    //                 }, throwable -> {
    //                 messageString.postValue(throwable.getLocalizedMessage());
    //             }));
    // }
    //
    // public void loadExpense(MoneyApi moneyApi, SharedPreferences sharedPreferences) {
    //     String authToken = sharedPreferences.getString(LoftApp.AUTH_KEY, "");
    //
    //     compositeDisposable.add(moneyApi.getMoneyItems("expense", authToken)
    //             .subscribeOn(Schedulers.io())
    //             .observeOn(AndroidSchedulers.mainThread())
    //             .subscribe(moneyRemoteItems -> {
    //                 if (moneyResponse.getStatus().equals("success")) {
    //                     List<MoneyItem> moneyItems = new ArrayList<>();
    //
    //                     for (MoneyRemoteItem moneyRemoteItem : moneyResponse.getMoneyItemsList()) {
    //                         moneyItems.add(MoneyItem.getInstance(moneyRemoteItem));
    //                     }
    //
    //                     moneyItemsList.postValue(moneyItems);
    //                 } else {
    //                     messageInt.postValue(R.string.connection_lost);
    //                 }
    //             }, throwable -> {
    //                 messageString.postValue(throwable.getLocalizedMessage());
    //             }));
    // }

    public void loadIncomes(MoneyApi moneyAPI, SharedPreferences sharedPreferences) {
        String authToken = sharedPreferences.getString(LoftApp.AUTH_KEY, "");
        compositeDisposable.add(moneyAPI.getMoneyItems("income", authToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyRemoteItems -> {
                    List<MoneyItem> moneyMoneyItems = new ArrayList<>();

                    for (MoneyRemoteItem moneyRemoteItem : moneyRemoteItems) {
                        moneyMoneyItems.add(MoneyItem.getInstance(moneyRemoteItem));
                    } moneyItemsList.postValue(moneyMoneyItems);
                }, throwable -> {
                    messageString.postValue(throwable.getLocalizedMessage());
                }));
    }

    public void loadExpense(MoneyApi moneyAPI, SharedPreferences sharedPreferences) {
        String authToken = sharedPreferences.getString(LoftApp.AUTH_KEY, "");
        compositeDisposable.add(moneyAPI.getMoneyItems("expense", authToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyRemoteItems -> {
                    List<MoneyItem> moneyMoneyItems = new ArrayList<>();

                    for (MoneyRemoteItem moneyRemoteItem : moneyRemoteItems) {
                        moneyMoneyItems.add(MoneyItem.getInstance(moneyRemoteItem));
                    } moneyItemsList.postValue(moneyMoneyItems);
                }, throwable -> {
                    messageString.postValue(throwable.getLocalizedMessage());
                }));
    }
}