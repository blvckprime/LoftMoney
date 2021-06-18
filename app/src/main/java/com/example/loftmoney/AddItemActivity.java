package com.example.loftmoney;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loftmoney.LoftApp;
import com.example.loftmoney.R;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText priceEditText;
    private Button mAddButton;

    private String name;
    private String price;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        nameEditText = findViewById(R.id.edit_name);
        nameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    final CharSequence charSequence,
                    final int i,
                    final int i1,
                    final int i2
            ) {

            }

            @Override
            public void onTextChanged(
                    final CharSequence charSequence,
                    final int i,
                    final int i1,
                    final int i2
            ) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                name = editable.toString();
                checkEditTextHasText();
            }
        });
        priceEditText = findViewById(R.id.edit_price);
        priceEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    final CharSequence charSequence,
                    final int i,
                    final int i1,
                    final int i2
            ) {

            }

            @Override
            public void onTextChanged(
                    final CharSequence charSequence,
                    final int i,
                    final int i1,
                    final int i2
            ) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                price = editable.toString();
                checkEditTextHasText();
            }
        });

        mAddButton = findViewById(R.id.b_button_add_item);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(price)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
                    return;
                }


                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)) {
                    Disposable disposable = ((LoftApp) getApplication()).moneyAPI.postMoney(Integer.parseInt(price.toString()),
                            name.toString(),
                            "income",
                            getSharedPreferences(getString(R.string.app_name), 0).getString(LoftApp.AUTH_KEY, "")
                    )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> {
                                Toast.makeText(getApplicationContext(), getString(R.string.success_added), Toast.LENGTH_LONG).show();
                                finish();
                            }, throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            });
                }
            }
        });
    }

            public void checkEditTextHasText() {
                mAddButton.setEnabled(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price));
            }

        }
