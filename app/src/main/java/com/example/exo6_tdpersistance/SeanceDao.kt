package com.example.exo6_tdpersistance

import androidx.room.*

@Dao
interface SeanceDao {

    @Query("SELECT * FROM seance")
    fun getAll(): List<Seance>

    @Query("SELECT * FROM seance WHERE jour LIKE :jour")
    fun findByJour(jour: Int): Seance

    @Query("SELECT * FROM seance WHERE heure LIKE :heure")
    fun findByHeure(heure: Int): Seance

    @Query("SELECT * FROM seance WHERE salle LIKE :salle")
    fun findBySalle(salle: Int): Seance

    @Query("SELECT * FROM seance WHERE enseignant LIKE :ens")
    fun findByEns(ens: String): Seance

    @Query("SELECT * FROM seance WHERE module LIKE :module")
    fun findByModule(module: String): Seance


    @Query("SELECT * FROM seance WHERE groupe LIKE :groupe")
    fun findByGroupe(groupe: Int): Seance

    @Query("SELECT * FROM seance WHERE anee LIKE :year")
    fun findByAnee(year: Int): Seance

    @Insert
    fun insert( seance: Seance)



    /**
     * Insert a list in the database. If the item already exists, replace it.
     *
     * @param list the items to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(list: List<Seance>)


    @Delete
    fun delete(seance: Seance)

    @Update
    fun updateSeance(vararg seance: Seance)


}