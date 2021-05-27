
package com.example.loftmoney;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loftmoney.cells.Item;
import com.example.loftmoney.cells.ItemsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private ItemsAdapter mAdapter;
    private static final String COLOR_ID = "colorId";
    private static final String TYPE = "fragmentType";

    public static BudgetFragment newInstance(final int colorId, final String type) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(COLOR_ID, colorId);
        bundle.putString(TYPE, type);
        budgetFragment.setArguments(bundle);
        return budgetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_budget, null);

        FloatingActionButton callAddButton = view.findViewById(R.id.add_item_fab);
        callAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivityForResult(new Intent(getActivity(), AddItemActivity.class),
                        REQUEST_CODE);
            }
        });

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

        mAdapter = new ItemsAdapter(getArguments().getInt(COLOR_ID));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setData(new Item("Молоко", 70));
        mAdapter.setData(new Item("Зубная щетка", 70));
        mAdapter.setData(new Item("Новый телевизор", 20000));

        return view;
    }


    @Override
    public void onActivityResult(
            final int requestCode, final int resultCode, @Nullable final Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        int price;
        try {
            price = Integer.parseInt(data.getStringExtra("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mAdapter.setData(new Item(data.getStringExtra("name"), price));
        }
    }

}