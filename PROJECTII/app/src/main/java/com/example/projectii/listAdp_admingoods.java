package com.example.projectii;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class listAdp_admingoods extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<Integer> id;
    private final ArrayList<String> productname;
    private final ArrayList<Integer> price;
    private final ArrayList<Integer> productqty;
    private final ArrayList<String> productdes;

    public listAdp_admingoods(Activity context, ArrayList<Integer> id, ArrayList<String> productname,
                              ArrayList<Integer> price, ArrayList<Integer> productqty, ArrayList<String> productdes) {
        super(context, R.layout.list, productname);
        this.context = context;
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.productqty = productqty;
        this.productdes = productdes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.list_admingoods, parent, false);
        TextView sid = rowview.findViewById(R.id.ssid);
        TextView productName = rowview.findViewById(R.id.ProductN);
        TextView priceTextView = rowview.findViewById(R.id.Price);
        TextView productqtyTextView = rowview.findViewById(R.id.productqty);
        TextView productdesTextView = rowview.findViewById(R.id.productdes);
        Button deleteButton = rowview.findViewById(R.id.deleteButton);
        Button editButton = rowview.findViewById(R.id.editButton);

        sid.setText(id.get(position).toString());
        productName.setText(productname.get(position));
        priceTextView.setText(String.valueOf(price.get(position)));
        productqtyTextView.setText(String.valueOf(productqty.get(position)));
        productdesTextView.setText(productdes.get(position));

        // Set click listener for Delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int recordId = id.get(position);

                // Connect to the database and delete the record
                DBConnection dbConnect = new DBConnection(context); // Initialize your DBConnect instance
                dbConnect.deleteRecordG(recordId);

                // Remove data from the ArrayLists
                id.remove(position);
                productname.remove(position);
                price.remove(position);
                productqty.remove(position);
                productdes.remove(position);

                notifyDataSetChanged();
            }
        });

        return rowview;
    }}
