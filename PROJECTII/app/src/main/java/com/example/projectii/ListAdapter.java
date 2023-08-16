package com.example.projectii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<Integer> id;
    ArrayList<String> name;
    ArrayList<String> address;
    ArrayList<String> phone; // New field
    ArrayList<String> email; // New field
    ArrayList<String> message; // New field

    public ListAdapter(Context context, ArrayList<Integer> id, ArrayList<String> name, ArrayList<String> address,
                       ArrayList<String> phone, ArrayList<String> email, ArrayList<String> message) {
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
        sname.setText(name.get(position).toString());
        saddress.setText(address.get(position).toString());
        sphone.setText(phone.get(position).toString()); // Set phone text
        semail.setText(email.get(position).toString()); // Set email text
        smessage.setText(message.get(position).toString()); // Set message text

        return rowView;
    }
}
