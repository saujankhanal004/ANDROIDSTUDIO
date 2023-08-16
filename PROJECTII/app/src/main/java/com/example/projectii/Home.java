package com.example.projectii;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button detail = view.findViewById(R.id.detail);
        ListView studentList = view.findViewById(R.id.listofproduct);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> id = new ArrayList<Integer>();
                ArrayList<String> productname = new ArrayList<String>();
                ArrayList<Integer> price = new ArrayList<Integer>();
                ArrayList<Integer> productqty = new ArrayList<Integer>();
                ArrayList<String> productdes = new ArrayList<String>();

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
                listAdp adapter = new listAdp(getActivity(), id, productname,price,productqty,productdes);
                studentList.setAdapter(adapter);
            }
        });


        return view;
    }
}
