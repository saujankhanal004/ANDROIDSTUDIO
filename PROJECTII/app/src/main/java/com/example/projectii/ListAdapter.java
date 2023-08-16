package com.example.projectii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<Integer> id;
    ArrayList<String> name;
    ArrayList<String> address;
    ArrayList<String> phone;
    ArrayList<String> email;
    ArrayList<String> message;

    public ListAdapter(Context context, ArrayList<Integer> id, ArrayList<String> name,
                       ArrayList<String> address, ArrayList<String> phone,
                       ArrayList<String> email, ArrayList<String> message) {
        super(context, R.layout.item_list, name);
        this.context = context;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.message = message;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_list, parent, false);

        TextView sid = rowView.findViewById(R.id.sid);
        TextView sname = rowView.findViewById(R.id.sname);
        TextView saddress = rowView.findViewById(R.id.sadd);
        TextView sphone = rowView.findViewById(R.id.sphone);
        TextView semail = rowView.findViewById(R.id.semail);
        TextView smessage = rowView.findViewById(R.id.smessage);

        sid.setText(id.get(position).toString());
        sname.setText(name.get(position));
        saddress.setText(address.get(position));
        sphone.setText(phone.get(position));
        semail.setText(email.get(position));
        smessage.setText(message.get(position));

        Button deleteButton = rowView.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int recordId = id.get(position);

                // Connect to the database and delete the record
                DBConnection dbConnect = new DBConnection(context); // Initialize your DBConnect instance
                dbConnect.deleteRecord(recordId);

                // Remove data from the ArrayLists
                id.remove(position);
                name.remove(position);
                address.remove(position);
                phone.remove(position);
                email.remove(position);
                message.remove(position);

                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}
