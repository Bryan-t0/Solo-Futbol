package com.example.solofutbol.ui.usuario

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityMenuUsuarioBinding
import com.example.solofutbol.ui.MainActivity
import com.example.solofutbol.ui.inventario.InventarioActivity

class MenuUsuarioActivity : AppCompatActivity() {
    private lateinit var b: ActivityMenuUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMenuUsuarioBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnInventario.setOnClickListener {
            startActivity(Intent(this, InventarioActivity::class.java))
        }
        b.btnLogout.setOnClickListener {
            getSharedPreferences("session", MODE_PRIVATE).edit().clear().apply()
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)

        }
    }
}
