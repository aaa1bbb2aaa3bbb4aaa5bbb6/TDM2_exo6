package com.example.exo6_tdpersistance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "seance")
data class Seance(
        @PrimaryKey(autoGenerate = true)
        val groupe:Int,
        val anee: Int,
        val jour: Int,
        val heure:Int,
        val module: String,
        val salle:Int,
        val enseignant: String


)