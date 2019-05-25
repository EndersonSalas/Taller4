package com.example.taller4.fragments;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taller4.R;
import com.example.taller4.adapters.PersonaAdapter;
import com.example.taller4.database.AppDatabase;
import com.example.taller4.models.Persona;

import java.util.ArrayList;

public class FormFragment extends Fragment {
 public ArrayList<Persona> mPersonas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_form, container, false);
        Bundle bundle = getArguments();
        mPersonas= new ArrayList<Persona>();
        if(bundle != null){
             mPersonas = (ArrayList<Persona>) bundle.getSerializable("listPersona");
        }
        setupUI(view);
        return view;
    }

    public void setupUI(final View view){
        Button btn = (Button)  view.findViewById(R.id.btn_save);
        final EditText editName= (EditText)  view.findViewById(R.id.editText_name);
        final EditText editLast= (EditText)  view.findViewById(R.id.editText_last_name);
        final EditText editEmail= (EditText)  view.findViewById(R.id.editText_email);
        final EditText editPhone= (EditText)  view.findViewById(R.id.editText_number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strEditName = editName.getText().toString();
                String strEditLast =  editLast.getText().toString();
                String strEditEmail = editEmail.getText().toString();
                String strEditPhone = editPhone.getText().toString();

                if(!strEditName.isEmpty() && !strEditLast.isEmpty() && !strEditEmail.isEmpty()&& !strEditPhone.isEmpty()){
                    Persona persona = new Persona(strEditName,strEditLast,strEditEmail,strEditPhone);
//                    mPersonas.add(persona);
                    enviandoResultado(persona);
                }else{
                    Toast.makeText(getContext(), "FALTAN DATOS DE LA PERSONA", Toast.LENGTH_LONG).show();
                }
//                getActivity().finish();
            }
        });
    }

    public void enviandoResultado(Persona persona){

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        MainFragment listFragment= new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("persona", persona);
        listFragment.setArguments(bundle);
        ft.replace(android.R.id.content, listFragment);
        ft.commit();
    }

}
