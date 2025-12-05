package com.example.solofutbol.ui.producto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto(
    @PrimaryKey val sku: String,
    val nombre: String,
    val talla: String,
    val color: String,
    val stock: Int = 0
)