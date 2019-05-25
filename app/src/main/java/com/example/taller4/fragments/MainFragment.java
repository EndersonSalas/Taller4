package com.example.taller4.fragments;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.taller4.database.AppDatabase;
import com.example.taller4.interfaces.PersonaDAO;
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
        final AppDatabase database = Room.databaseBuilder(getContext(), AppDatabase.class, "localDB")
                .allowMainThreadQueries() .build();
        PersonaDAO personaDAO = database.getPersonaDAO();
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        //FirstList(view);
        btnClick(view,database);
        Bundle bundle = getArguments();
//        SecondList(view);
        int layoutId = android.R.layout.simple_list_item_1;
        PersonaAdapter adapter= new PersonaAdapter(getContext(), R.layout.list_element_persona,personaDAO.getPersonas());
        ListView listView= (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);
//        if(bundle != null){
//            mPersonas = (ArrayList<Persona>) bundle.getSerializable("personas");
//            /*mPersonas.add(persona);*/
//            adapter= new PersonaAdapter(view.getContext(), R.layout.list_element_persona,mPersonas);
//            listView.setAdapter(adapter);
//        }
        if(bundle != null){
            Persona persona = (Persona)bundle.getSerializable("persona");
            personaDAO.insert(persona);
            adapter= new PersonaAdapter(view.getContext(), R.layout.list_element_persona,personaDAO.getPersonas());
            listView.setAdapter(adapter);
        }

        return view;
    }

    public void btnClick(View view, final AppDatabase database ){
        Button btn = (Button)  view.findViewById(R.id.btn_add_persona);
        Button btnRand = (Button)  view.findViewById(R.id.btn_add_random);
        FloatingActionButton btnFloatingAction = (FloatingActionButton)  view.findViewById(R.id.floatingAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                Bundle bundle = new Bundle();
                bundle.putSerializable("database", mPersonas);
                FormFragment fragment2 = new FormFragment();
                fragment2.setArguments(bundle);
                ft.replace(android.R.id.content, fragment2);
                ft.commit();
                /*Intent intent= new Intent(getContext(), FormFragment.class);
                getActivity().startActivity(intent);
                getActivity().finish();*/
            }
        });

        btnRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonaDAO personaDAO = database.getPersonaDAO();
                Persona persona = new Persona();
                persona.setName("User Random");
                persona.setLastName("Apellidos Salas");
                persona.setEmail("Email random@gmail.com");
                persona.setPhoneNumber("Telefono 12312");

                personaDAO.insert(persona);
                Snackbar snackbar = Snackbar
                        .make(v, "Persona Creada \nCantidad de personas " +personaDAO.count(), Snackbar.LENGTH_LONG);

                SharedPreferences sharedPref = getContext().getSharedPreferences("preferences", getContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("countPersonas", personaDAO.count());
                editor.commit();
                snackbar.show();

                int layoutId = android.R.layout.simple_list_item_1;
                PersonaAdapter adapter= new PersonaAdapter(getContext(), R.layout.list_element_persona,personaDAO.getPersonas());
                ListView listView= (ListView) v.findViewById(R.id.listView2);
                listView.setAdapter(adapter);
            }
        });

        btnFloatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                FormFragment fragment2 = new FormFragment();
                ft.replace(android.R.id.content, fragment2);
                ft.commit();

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
            String name = "Enderson " +i;
            String lastName = "Salas  " +i;
            String email = "prueba  " +i+ "@gmail.com";
            String phone = "12323 " +i;
            mPersonas.add(new Persona(name, lastName, email,phone));
        }
        int layoutId = android.R.layout.simple_list_item_1;
        PersonaAdapter adapter= new PersonaAdapter(view.getContext(), R.layout.list_element_persona,mPersonas);
        ListView listView= (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(adapter);
    }

}
