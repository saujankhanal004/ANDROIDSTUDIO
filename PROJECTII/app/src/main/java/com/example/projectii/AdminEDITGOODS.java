package com.example.projectii;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminEDITGOODS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminEDITGOODS extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminEDITGOODS() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminEDITGOODS.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminEDITGOODS newInstance(String param1, String param2) {
        AdminEDITGOODS fragment = new AdminEDITGOODS();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_e_d_i_t_g_o_o_d_s, container, false);
        Button detailButton = view.findViewById(R.id.detailButton); // Replace with the actual ID of your button
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
                listAdp_admingoods adapter = new listAdp_admingoods(getActivity(), id, productname, price, productqty, productdes);
                studentList.setAdapter(adapter);
            }
        });

        return view;
    }
}
