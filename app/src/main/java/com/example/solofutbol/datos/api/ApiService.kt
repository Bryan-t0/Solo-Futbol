package com.example.solofutbol.datos.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun obtenerUsuarios(): Call<List<UsuarioApi>>
}
