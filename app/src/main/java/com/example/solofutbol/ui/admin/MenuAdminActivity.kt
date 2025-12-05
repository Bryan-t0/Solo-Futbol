package com.example.solofutbol.ui.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityMenuAdminBinding
import com.example.solofutbol.ui.MainActivity
import com.example.solofutbol.ui.inventario.InventarioActivity
import com.example.solofutbol.ui.stock.VerStockActivity
import com.example.solofutbol.ui.usuarios.CrearUsuarioActivity


class MenuAdminActivity : AppCompatActivity() {
    private lateinit var b: ActivityMenuAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnCrearUsuario.setOnClickListener {
            startActivity(Intent(this, CrearUsuarioActivity::class.java))
        }
        b.btnInventario.setOnClickListener {
            startActivity(Intent(this, InventarioActivity::class.java))
        }
        b.btnVerStock.setOnClickListener {
            startActivity(Intent(this, VerStockActivity::class.java))
        }
        b.btnLogout.setOnClickListener {
            getSharedPreferences("session", MODE_PRIVATE).edit().clear().apply()
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)

        }
       b.btnGestionUsuarios.setOnClickListener {
           startActivity(Intent(this, GestionarUsuarios::class.java))
       }
        b.btnTestApi.setOnClickListener {
            startActivity(Intent(this, TestApiActivity::class.java))
        }
    }

}
