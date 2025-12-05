package com.example.solofutbol.ui.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityTestApiBinding
import com.example.solofutbol.datos.api.RetrofitClient
import com.example.solofutbol.datos.api.UsuarioApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestApiActivity : AppCompatActivity() {
    private lateinit var b: ActivityTestApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityTestApiBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnConsultarApi.setOnClickListener {
            b.tvResultadoApi.text = "Cargando..."
            
            val call = RetrofitClient.instance.obtenerUsuarios()
            call.enqueue(object : Callback<List<UsuarioApi>> {
                override fun onResponse(call: Call<List<UsuarioApi>>, response: Response<List<UsuarioApi>>) {
                    if (response.isSuccessful) {
                        val usuarios = response.body()
                        val texto = usuarios?.take(3)?.joinToString("\n") { "${it.id}: ${it.name} (${it.email})" }
                        b.tvResultadoApi.text = "Éxito:\n$texto"
                    } else {
                        b.tvResultadoApi.text = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<UsuarioApi>>, t: Throwable) {
                    b.tvResultadoApi.text = "Fallo: ${t.message}"
                }
            })
        }
        
        b.btnVolver.setOnClickListener { finish() }
    }
}
