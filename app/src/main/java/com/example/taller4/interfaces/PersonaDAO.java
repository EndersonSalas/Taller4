package com.example.taller4.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.taller4.models.Persona;

import java.util.List;

@Dao
public interface PersonaDAO {

    @Delete
    public void delete(Persona... personas);

    @Update
    public void update(Persona... personas);

    @Insert
    public void insert(Persona... personas);

    @Query("SELECT * FROM personas")
    public List<Persona> getPersonas();

    @Query("SELECT Count(*) FROM personas")
    public int count();
}