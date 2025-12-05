package com.example.solofutbol.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun guardar(p: Producto)

    @Update
    suspend fun actualizar(p: Producto)

    @Query("SELECT * FROM producto ORDER BY nombre")
    suspend fun listar(): List<Producto>

    @Query("SELECT COUNT(*) FROM producto WHERE sku = :sku")
    suspend fun existe(sku: String): Int
}
