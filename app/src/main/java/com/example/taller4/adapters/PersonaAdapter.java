package com.example.taller4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.taller4.R;
import com.example.taller4.models.Persona;
import com.example.taller4.models.Persona;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {
    int mLayoutId;

    public PersonaAdapter(Context context, int layoutId, List<Persona> personas) {
        super(context, layoutId, personas);
        mLayoutId = layoutId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Persona persona = getItem(position);
        String name = persona.getName();
        String lastName = persona.getLastName();
        String email = persona.getEmail();
        String phone = persona.getPhoneNumber();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayoutId, parent, false);
        }
        TextView nameView = (TextView) view.findViewById(R.id.txtName);
        TextView lastNameView = (TextView) view.findViewById(R.id.txtLastName);
        TextView emailView = (TextView) view.findViewById(R.id.txtEmail);
        TextView phoneView = (TextView) view.findViewById(R.id.txtPhone);

        nameView.setText(name);
        lastNameView.setText(lastName);
        emailView.setText(email);
        phoneView.setText(phone);
        return view;
    }
}

