package com.example.solofutbol.datos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun guardar(u: Usuario)

    @Delete
    suspend fun eliminar(u: Usuario)

    @Query("SELECT * FROM usuario WHERE usuario = :user AND clave = :pass LIMIT 1")
    fun login(user: String, pass: String): Usuario?

    @Query("SELECT * FROM usuario ORDER BY usuario")
    suspend fun listarTodos(): List<Usuario>

    @Query("SELECT COUNT(*) FROM usuario WHERE usuario = :user")
    suspend fun existe(user: String): Int
    @Query("SELECT * FROM usuario WHERE rut = :rut LIMIT 1")
    suspend fun obtenerPorRut(rut: String): Usuario?
    @Update
    suspend fun actualizar(u: Usuario)


}
