package com.example.projectii;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class S_fragmenthome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_fragmenthome, container, false);

        Button detailButton = view.findViewById(R.id.detail); // Replace with the actual ID of your button
        ListView studentList = view.findViewById(R.id.listofproduct); // Replace with the actual ID of your ListView

        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> id = new ArrayList<>();
                ArrayList<String> productname = new ArrayList<>();
                ArrayList<Integer> price = new ArrayList<>();
                ArrayList<Integer> productqty = new ArrayList<>();
                ArrayList<String> productdes = new ArrayList<>();

                // Create an instance of DBConnection
                DBConnection dbConnection = new DBConnection(getActivity());

                Cursor cursor = dbConnection.selectProducts(); // Call the method on the instance
                while (cursor.moveToNext()) {
                    id.add(cursor.getInt(0));
                    productname.add(cursor.getString(1));
                    price.add(cursor.getInt(2));
                    productqty.add(cursor.getInt(3));
                    productdes.add(cursor.getString(4));
                }

                // Create and set your custom adapter here
                listAdp adapter = new listAdp(getActivity(), id, productname, price, productqty, productdes);
                studentList.setAdapter(adapter);
            }
        });

        return view;
    }
}
