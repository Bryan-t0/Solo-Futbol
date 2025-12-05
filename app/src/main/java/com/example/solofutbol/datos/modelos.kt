package com.example.solofutbol.datos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey val rut: String,
    val usuario: String,
    val nombre: String,
    val apellido: String,
    val direccion: String,
    val clave: String,
    val esAdmin: Boolean = false
)

@Entity(tableName = "producto")
data class Producto(
    @PrimaryKey val sku: String,
    val nombre: String,
    val talla: String,
    val color: String,
    val stock: Int = 0
)
