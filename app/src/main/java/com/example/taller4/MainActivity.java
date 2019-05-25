package com.example.taller4;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.taller4.database.AppDatabase;
import com.example.taller4.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new MainFragment())
                .commit();
        final AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "localDB")
                .allowMainThreadQueries() .build();
    }
}
