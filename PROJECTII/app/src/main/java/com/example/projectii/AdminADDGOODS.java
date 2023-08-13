package com.example.projectii;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AdminADDGOODS extends Fragment {

    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQtyEditText;
    private EditText productDescriptionEditText;
    private Button addButton;
    private DBConnection College;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_a_d_d_g_o_o_d_s, container, false);

        productNameEditText = view.findViewById(R.id.product_name_editText);
        productPriceEditText = view.findViewById(R.id.product_price_editText);
        productQtyEditText = view.findViewById(R.id.product_qty_editText);
        productDescriptionEditText = view.findViewById(R.id.product_description_editText);

        addButton = view.findViewById(R.id.add_button);
        College = new DBConnection(getContext());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        return view;
    }

    private void insertData() {
        String productName = productNameEditText.getText().toString().trim();
        String productPrice = productPriceEditText.getText().toString().trim();
        String productQty = productQtyEditText.getText().toString().trim();
        String productDescription = productDescriptionEditText.getText().toString().trim();

        if (productName.isEmpty() || productPrice.isEmpty() || productQty.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SQLiteDatabase db = College.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("productname", productName);
            values.put("price", Integer.parseInt(productPrice)); // Use appropriate data type
            values.put("productqty", Integer.parseInt(productQty));
            values.put("productdes", productDescription);

            long newRowId = db.insert("Goods", null, values);

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Product added successfully", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(requireContext(), "Failed to add product", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


}
}
