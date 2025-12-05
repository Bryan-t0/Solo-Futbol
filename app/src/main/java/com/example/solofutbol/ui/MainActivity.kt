package com.example.solofutbol.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityMainBinding
import com.example.solofutbol.datos.BaseDatos
import kotlinx.coroutines.*
import com.example.solofutbol.ui.admin.MenuAdminActivity
import com.example.solofutbol.ui.usuario.MenuUsuarioActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnIngresar.setOnClickListener {
            val user = b.etUsuario.text?.toString()?.trim().orEmpty()
            val pass = b.etClave.text?.toString()?.trim().orEmpty()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Faltan usuario o clave", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            scope.launch {
                val u = withContext(Dispatchers.IO) {
                    BaseDatos.obtener(this@MainActivity).usuarioDao().login(user, pass)
                }

                if (u == null) {
                    Toast.makeText(this@MainActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                } else {
                    val destino = if (u.esAdmin) MenuAdminActivity::class.java else MenuUsuarioActivity::class.java
                    startActivity(Intent(this@MainActivity, destino))
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
