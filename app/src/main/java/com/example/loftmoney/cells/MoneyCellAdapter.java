package com.example.loftmoney.cells;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loftmoney.R;

import java.util.ArrayList;
import java.util.List;

public class MoneyCellAdapter extends RecyclerView.Adapter<MoneyCellAdapter.MoneyViewHolder> {

    private List<MoneyItem> moneyItemList = new ArrayList<>();
    private int colorId;

    public MoneyCellAdapterClick moneyCellAdapterClick;

    public void setData(List<MoneyItem> moneyItems) {
        moneyItemList.clear();
        moneyItemList.addAll(moneyItems);
        notifyDataSetChanged();
    }

    public List<MoneyItem> getMoneyItemList() {
        return moneyItemList;
    }

    public void updateItem(MoneyItem moneyItem) {
        int itemPosition = moneyItemList.indexOf(moneyItem);
        moneyItemList.set(itemPosition, moneyItem);
        notifyItemChanged(itemPosition);
    }

    public void deleteSelectedItems() {
        List<MoneyItem> selectedItems = new ArrayList<>();
        for (MoneyItem moneyItem : moneyItemList) {
            if (moneyItem.isSelected()) {
                selectedItems.add(moneyItem);
            }
        }

        moneyItemList.removeAll(selectedItems);
        notifyDataSetChanged();
    }

    public void setMoneyCellAdapterClick(MoneyCellAdapterClick moneyCellAdapterClick) {
        this.moneyCellAdapterClick = moneyCellAdapterClick;
    }

    @NonNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MoneyViewHolder(layoutInflater.inflate(R.layout.rv_items_layout, parent, false),
                moneyCellAdapterClick);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyViewHolder holder, int position) {
        holder.bind(moneyItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return moneyItemList.size();
    }

//    public MoneyCellAdapter(final int colorId) {
//        this.colorId = colorId;

    static class MoneyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView priceTextView;
        private MoneyCellAdapterClick moneyCellAdapterClick;

        public MoneyViewHolder(@NonNull final View itemView, MoneyCellAdapterClick moneyCellAdapterClick) {
            super(itemView);

            this.moneyCellAdapterClick = moneyCellAdapterClick;

            nameTextView = itemView.findViewById(R.id.rv_name);
            priceTextView = itemView.findViewById(R.id.rv_price);
        }

        public void bind(final MoneyItem moneyItem) {

            nameTextView.setText(moneyItem.getName());
            priceTextView.setText(moneyItem.getPrice());

            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                    moneyItem.isSelected() ? R.color.cellSelectionColor : android.R.color.white));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moneyCellAdapterClick != null) {
                        moneyCellAdapterClick.onCellClick(moneyItem);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (moneyCellAdapterClick != null) {
                        moneyCellAdapterClick.onLongCellClick(moneyItem);
                    }
                    return true;
                }
            });
        }
    }
}



//            final Context context = price.getContext();
//            price.setTextColor(ContextCompat.getColor(context, colorId));
//        }
//
//        public void bind(final MoneyItem moneyItem) {
//
//            name.setText(moneyItem.getName());
//            price.setText(
//                    price.getContext().getResources().getString(R.string.price_with_currency, String.valueOf(moneyItem.getPrice()))
//            );

