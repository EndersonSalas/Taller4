package com.example.taller4.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.taller4.R;
import com.example.taller4.adapters.PersonaAdapter;
import com.example.taller4.models.Persona;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private  ArrayList<String> mItems;
    public  ArrayList<Persona> mPersonas;
    private ArrayAdapter<String>mAdapter;
    private ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        //FirstList(view);
        btnClick(view);
        Bundle bundle = getArguments();
        SecondList(view);
        if(bundle != null){
            mPersonas = (ArrayList<Persona>) bundle.getSerializable("personas");
            /*mPersonas.add(persona);*/
            int layoutId = android.R.layout.simple_list_item_1;
            PersonaAdapter adapter= new PersonaAdapter(view.getContext(), R.layout.list_element_persona,mPersonas);
            ListView listView= (ListView) view.findViewById(R.id.listView2);
            listView.setAdapter(adapter);
        }

        return view;
    }

    public void btnClick(View view){
        Button btn = (Button)  view.findViewById(R.id.btn_add_persona);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listPersona", mPersonas);
                FormFragment fragment2 = new FormFragment();
                fragment2.setArguments(bundle);
                ft.replace(android.R.id.content, fragment2);
                ft.commit();
                /*Intent intent= new Intent(getContext(), FormFragment.class);
                getActivity().startActivity(intent);
                getActivity().finish();*/
            }
        });
    }



    private void FirstList(View view){
        mItems= new ArrayList<String>();
        for (int i = 0; i <=10 ; i++) {
            mItems.add("Item :"+i);
        }
        int layoutId = android.R.layout.simple_list_item_1;
        mAdapter= new ArrayAdapter<String>(view.getContext(), layoutId,mItems);
        mListView= (ListView) view.findViewById(R.id.listView2);
        mListView.setAdapter(mAdapter);
    }
    private void SecondList(View view){
        mPersonas= new ArrayList<Persona>();
        if(mPersonas!=null)
        for (int i = 0; i <=2 ; i++) {
            String name = "User " +i;
            String lastName = "Apellido:  " +i;
            String email = "Email: prueba  " +i+ "@gmail.com";
            String phone = "Teléfono: 12323 " +i;
            mPersonas.add(new Persona(name, lastName, email,phone));
        }
        int layoutId = android.R.layout.simple_list_item_1;
        PersonaAdapter adapter= new PersonaAdapter(view.getContext(), R.layout.list_element_persona,mPersonas);
        ListView listView= (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);
    }

}
