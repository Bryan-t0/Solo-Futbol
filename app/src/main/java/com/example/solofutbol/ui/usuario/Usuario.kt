package com.example.solofutbol.ui.usuario

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usuario",
    indices = [Index(value = ["usuario"], unique = true)]
)
data class Usuario(
    @PrimaryKey val rut: String,
    val usuario: String,
    val nombre: String,
    val apellido: String,
    val direccion: String,
    val clave: String,
    val esAdmin: Boolean = false
)
