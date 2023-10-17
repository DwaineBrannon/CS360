package com.projects.dwainebrannoninventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private InventoryDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Button addButton = findViewById(R.id.addButton);
        Button editButton = findViewById(R.id.editButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        GridView gridView = findViewById(R.id.gridView);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        dataSource = new InventoryDataSource(this);
        dataSource.open();

        List<InventoryItem> items = dataSource.getAllItems();
        // Create the adapter
        InventoryAdapter adapter = new InventoryAdapter(this, items);

        gridView.setAdapter(adapter);
        // Set an item click listener for the GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                InventoryItem item = (InventoryItem) parent.getItemAtPosition(position);

                Toast.makeText(HomePageActivity.this, "Item selected at position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomePageActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.add_item_dialog, null);
                dialogBuilder.setView(dialogView);

                EditText editIdEditText = dialogView.findViewById(R.id.editIdEditText);
                EditText editNameEditText = dialogView.findViewById(R.id.editNameEditText);
                EditText editDescriptionEditText = dialogView.findViewById(R.id.editDescriptionEditText);
                EditText editQuantityEditText = dialogView.findViewById(R.id.editQuantityEditText);
                Button saveButton = dialogView.findViewById(R.id.saveButton);
                Button cancelButton = dialogView.findViewById(R.id.cancelButton);
                AlertDialog alertDialog = dialogBuilder.create();


                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long itemId = Long.parseLong(editIdEditText.getText().toString());
                        String name = editNameEditText.getText().toString();
                        String description = editDescriptionEditText.getText().toString();
                        String quantity = editQuantityEditText.getText().toString();

                        if (itemId < 0 || name.isEmpty() || description.isEmpty() || quantity.isEmpty()) {
                            Toast.makeText(HomePageActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            int parsedQuantity = Integer.parseInt(quantity);
                            if (parsedQuantity < 0) {
                                Toast.makeText(HomePageActivity.this, "Please enter a positive quantity", Toast.LENGTH_SHORT).show();
                            } else {
                                InventoryItem newItem = new InventoryItem(itemId, name, description, parsedQuantity);
                                items.add(newItem);
                                adapter.notifyDataSetChanged();
                                alertDialog.dismiss();
                            }
                        }
                    }


                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });
        // For the notification signup
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On Click bring in activity for notification_screen xml
                Intent intent =new Intent (HomePageActivity.this, NotificationActivity.class);

                startActivity(intent);
            }
            });

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomePageActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.edit_item_dialogue, null);
                dialogBuilder.setView(dialogView);

                EditText editQuantityEditText = dialogView.findViewById(R.id.editQuantityEditText);
                Button saveButton = dialogView.findViewById(R.id.saveButton);
                Button cancelButton = dialogView.findViewById(R.id.cancelButton);
                AlertDialog alertDialog = dialogBuilder.create();
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String editedQuantity = editQuantityEditText.getText().toString();
                        if (editedQuantity.isEmpty()) {
                            Toast.makeText(HomePageActivity.this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
                        } else {
                            int quantity = Integer.parseInt(editedQuantity);
                            if (quantity < 0) {
                                Toast.makeText(HomePageActivity.this, "Please enter a positive quantity", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle saving the edit (close the dialog)
                                alertDialog.dismiss();

                            }
                        }

                        alertDialog.dismiss();
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle canceling the edit (close the dialog)
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() > 0) {
                    // Create and configure the dialog
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomePageActivity.this);
                    dialogBuilder.setTitle("Select an item to delete");

                    // Create a list of item names for the dialog
                    final CharSequence[] itemNames = new CharSequence[items.size()];
                    for (int i = 0; i < items.size(); i++) {
                        itemNames[i] = items.get(i).getName();
                    }

                    dialogBuilder.setItems(itemNames, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Handle item deletion based on the user's selection
                            items.remove(which);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });

                    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    dialogBuilder.create().show();
                } else {
                    Toast.makeText(HomePageActivity.this, "No items to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
        @Override
        protected void onDestroy () {
            super.onDestroy();
            // Close the data source when the activity is destroyed
            dataSource.close();
        }
    }
