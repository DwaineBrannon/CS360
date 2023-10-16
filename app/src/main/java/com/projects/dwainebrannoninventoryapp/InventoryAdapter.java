package com.projects.dwainebrannoninventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class InventoryAdapter extends BaseAdapter {
    private Context context;
    private List<InventoryItem> inventoryItems;

    public InventoryAdapter(Context context, List<InventoryItem> inventoryItems) {
        this.context = context;
        this.inventoryItems = inventoryItems;
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inventory_item_layout, null);
        }

        // Get references to your UI elements in the item layout
        TextView itemNameTextView = view.findViewById(R.id.itemName);
        TextView itemDescriptionTextView = view.findViewById(R.id.itemDescription);
        TextView itemQuantityTextView = view.findViewById(R.id.itemQuantity);
        TextView itemIdTextView = view.findViewById(R.id.itemId);

        // Set the data for the current item
        InventoryItem item = inventoryItems.get(position);
        itemNameTextView.setText(item.getName());
        itemDescriptionTextView.setText(item.getDescription());
        itemQuantityTextView.setText(String.valueOf(item.getQuantity()));
        itemIdTextView.setText(String.valueOf(item.getId()));

        return view;
    }
}