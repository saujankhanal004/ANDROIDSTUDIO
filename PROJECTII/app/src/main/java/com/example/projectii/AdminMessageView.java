package com.example.projectii;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AdminMessageView extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private ListView studentList;
    private DBConnection con;

    public AdminMessageView() {
        // Required empty public constructor
    }

    public static AdminMessageView newInstance(String param1, String param2) {
        AdminMessageView fragment = new AdminMessageView();
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

        con = new DBConnection(requireContext()); // Initialize DBConnection
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_message_view, container, false);

        studentList = view.findViewById(R.id.studentList);

        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> address = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> message = new ArrayList<>();

        Cursor cursor = con.selectStudents();
        while (cursor.moveToNext()) {
            id.add(cursor.getInt(0));
            name.add(cursor.getString(1));
            address.add(cursor.getString(2));
            phone.add(cursor.getString(3));
            email.add(cursor.getString(4));
            message.add(cursor.getString(5));
        }
        cursor.close();

        ListAdapter adapter = new ListAdapter(
                requireContext(), id, name, address, phone, email, message
        );
        studentList.setAdapter(adapter);

        return view;
    }
}
