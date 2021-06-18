
package com.example.loftmoney.screens.money;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loftmoney.LoftApp;
import com.example.loftmoney.R;
import com.example.loftmoney.cells.MoneyCellAdapterClick;
import com.example.loftmoney.cells.MoneyItem;
import com.example.loftmoney.cells.MoneyCellAdapter;
import com.example.loftmoney.AddItemActivity;
import com.example.loftmoney.screens.dashboard.EditModeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MoneyFragment extends Fragment implements MoneyEditListener {

    private MoneyViewModel moneyViewModel;
    private MoneyCellAdapter mAdapter = new MoneyCellAdapter();
    private FloatingActionButton addNewExpense;

    public static final int REQUEST_CODE = 100;
    private static final String COLOR_ID = "colorId";
    private static final String TYPE = "fragmentType";

    //  public SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.setMoneyCellAdapterClick(new MoneyCellAdapterClick() {
            @Override
            public void onCellClick(MoneyItem moneyItem) {
                if (moneyViewModel.isEditMode.getValue()) {
                    moneyItem.setSelected(!moneyItem.isSelected());
                    mAdapter.updateItem(moneyItem);
                    checkSelectedCount();
                }
            }

            @Override
            public void onLongCellClick(MoneyItem moneyItem) {
                if (!moneyViewModel.isEditMode.getValue())
                    moneyItem.setSelected(true);
                mAdapter.updateItem(moneyItem);
                moneyViewModel.isEditMode.postValue(true);
                checkSelectedCount();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_money, null);
        return inflater.inflate(R.layout.fragment_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addNewExpense = view.findViewById(R.id.add_item_fab);

        configureViews(view);
        configureViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();

        moneyViewModel.loadIncomes(
                ((LoftApp) getActivity().getApplication()).moneyAPI,
                getActivity().getSharedPreferences(getString(R.string.app_name), 0)
        );
    }

    private void configureViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);

        //mAdapter = new MoneyCellAdapter(getArguments().getInt(COLOR_ID));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FloatingActionButton addNewIncomeView = view.findViewById(R.id.add_item_fab);
        addNewIncomeView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddItemActivity.class);
            startActivity(intent);
        });
    }

    private void checkSelectedCount() {
        int selectedItemsCount = 0;
        for (MoneyItem moneyItem : mAdapter.getMoneyItemList()) {
            if (moneyItem.isSelected()) {
                selectedItemsCount++;
            }
        }

        moneyViewModel.selectedCounter.postValue(selectedItemsCount);
    }

    private void configureViewModel() {
        moneyViewModel = new ViewModelProvider(this).get(MoneyViewModel.class);
        moneyViewModel.moneyItemList.observe(this, moneyItems -> {
            mAdapter.setData(moneyItems);
        });

        moneyViewModel.isEditMode.observe(this, isEditMode -> {
            addNewExpense.setVisibility(isEditMode ? View.GONE : View.VISIBLE);

            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof EditModeListener) {
                ((EditModeListener) parentFragment).onEditModeChanged(isEditMode);
            }
        });

        moneyViewModel.selectedCounter.observe(this, newCount -> {
            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof EditModeListener) {
                ((EditModeListener) parentFragment).onCounterChanged(newCount);
            }
        });

        moneyViewModel.messageString.observe(this,message -> {
            if(!message.equals("")){
                showToast(message);
            }
        });

        moneyViewModel.messageInt.observe(this, message -> {
            if (message > 0) {
                showToast(getString(message));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClearEdit() {
        moneyViewModel.isEditMode.postValue(false);
        moneyViewModel.selectedCounter.postValue(-1);

        for (MoneyItem moneyItem : mAdapter.getMoneyItemList()) {
            if (moneyItem.isSelected()) {
                moneyItem.setSelected(false);
                mAdapter.updateItem(moneyItem);
            }
        }
    }

    @Override
    public void onCLearSelecredClick() {
        moneyViewModel.isEditMode.postValue(false);
        moneyViewModel.selectedCounter.postValue(-1);
        mAdapter.deleteSelectedItems();
    }
}










  //  public static MoneyFragment newInstance(final int colorId, final String type) {
  //      MoneyFragment budgetFragment = new MoneyFragment();
  //      Bundle bundle = new Bundle();
  //      bundle.putInt(COLOR_ID, colorId);
  //      bundle.putString(TYPE, type);
  //      budgetFragment.setArguments(bundle);
  //      return budgetFragment;
  //  }
  //

//swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
//
//swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
// @Override
// public void onRefresh() {
//     mainViewModel.loadIncomes(((LoftApp) getApplication()).moneyAPI,getSharedPreferences(getString(R.string.app_name),0)
//     );
//     mainViewModel.loadExpense(
//             ((LoftApp) getApplication()).moneyAPI,getSharedPreferences(getString(R.string.app_name),0)
//     );
// }
//



//   public void onActivityResult(
//           final int requestCode, final int resultCode, @Nullable final Intent data
//   ) {
//       super.onActivityResult(requestCode, resultCode, data);
//
//       int price;
//       try {
//           price = Integer.parseInt(data.getStringExtra("price"));
//       } catch (NumberFormatException e) {
//           price = 0;
//       }
//       if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//
//
//           mAdapter.setData((List<MoneyItem>) new MoneyItem(data.getStringExtra("name"), price));
//       }
//   }
//
//}

