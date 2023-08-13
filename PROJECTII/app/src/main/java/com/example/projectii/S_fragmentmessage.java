package com.example.projectii;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class S_fragmentmessage extends Fragment {

    private EditText etName, etAddress, etPhone, etEmail, etMessage;
    private Button btnSend;
    private DBConnection dbConnection;

    public S_fragmentmessage() {
        // Required empty public constructor
    }

    public static S_fragmentmessage newInstance() {
        return new S_fragmentmessage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_fragmentmessage, container, false);

        etName = view.findViewById(R.id.et_name);
        etAddress = view.findViewById(R.id.et_address);
        etPhone = view.findViewById(R.id.et_phone);
        etEmail = view.findViewById(R.id.et_email);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);

        dbConnection = new DBConnection(getContext());

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        return view;
    }

    private void insertData() {
        SQLiteDatabase db = dbConnection.getWritableDatabase();

        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String message = etMessage.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("address", address);
        values.put("phone", phone);
        values.put("email", email);
        values.put("message", message);

        long newRowId = db.insert("AdminMessage", null, values);
        if (newRowId != -1) {
            Toast.makeText(getContext(), "Message SEND successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error While Sendind Message", Toast.LENGTH_SHORT).show();
        }
    }
}
