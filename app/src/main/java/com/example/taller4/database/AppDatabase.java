package com.example.taller4.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.taller4.interfaces.PersonaDAO;
import com.example.taller4.models.Persona;

@Database(entities = {Persona.class}, version = 1)
public abstract class AppDatabase extends
        RoomDatabase {
    public abstract PersonaDAO getPersonaDAO();
}
