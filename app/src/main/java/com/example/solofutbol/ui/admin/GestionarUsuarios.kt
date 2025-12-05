package com.example.solofutbol.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.solofutbol.databinding.ActivityGestionarUsuariosBinding
import com.example.solofutbol.datos.BaseDatos
import com.example.solofutbol.datos.Usuario
import com.example.solofutbol.ui.usuarios.CrearUsuarioActivity
import com.example.solofutbol.ui.usuario.EditarUsuario
import com.example.solofutbol.ui.usuarios.UsuariosAdapter
import kotlinx.coroutines.*

class GestionarUsuarios : AppCompatActivity(), UsuariosAdapter.Listener {
    private lateinit var b: ActivityGestionarUsuariosBinding
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val adapter = UsuariosAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityGestionarUsuariosBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.rvUsuarios.layoutManager = LinearLayoutManager(this)
        b.rvUsuarios.adapter = adapter

        b.btnVolver.setOnClickListener { finish() }
        b.btnCrear.setOnClickListener { startActivity(Intent(this, CrearUsuarioActivity::class.java)) }
    }

    override fun onResume() {
        super.onResume()
        cargarUsuarios()
    }

    private fun cargarUsuarios() {
        scope.launch {
            val lista: List<Usuario> = withContext(Dispatchers.IO) {
                BaseDatos.obtener(this@GestionarUsuarios).usuarioDao().listarTodos()
            }
            if (lista.isEmpty()) {
                b.txtVacio.visibility = View.VISIBLE
                b.rvUsuarios.visibility = View.GONE
            } else {
                b.txtVacio.visibility = View.GONE
                b.rvUsuarios.visibility = View.VISIBLE
                adapter.submit(lista)
            }
        }
    }

    override fun onEditar(item: Usuario) {
        val i = Intent(this, EditarUsuario::class.java)
        i.putExtra("rut", item.rut)
        startActivity(i)
    }

    override fun onEliminar(item: Usuario) {
        scope.launch {
            withContext(Dispatchers.IO) {
                BaseDatos.obtener(this@GestionarUsuarios).usuarioDao().eliminar(item)
            }
            cargarUsuarios()
            Toast.makeText(this@GestionarUsuarios, "Eliminado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
