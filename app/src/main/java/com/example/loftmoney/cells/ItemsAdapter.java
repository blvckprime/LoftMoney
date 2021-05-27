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
import java.util.Collections;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> items = new ArrayList<>();
    private final int colorId;

    public ItemsAdapter(final int colorId) {
        this.colorId = colorId;
    }

    @Override

    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.rv_items_layout, null);
        return new ItemViewHolder(itemView, colorId);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    public void setData(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;

        public ItemViewHolder(@NonNull final View itemView, final int colorId) {
            super(itemView);

            name = itemView.findViewById(R.id.rv_name);
            price = itemView.findViewById(R.id.rv_price);

            final Context context = price.getContext();
            price.setTextColor(ContextCompat.getColor(context, colorId));
        }

        public void bind(final Item item) {

            name.setText(item.getName());
            price.setText(
                    price.getContext().getResources().getString(R.string.price_with_currency, String.valueOf(item.getPrice()))
            );

        }
    }
}
