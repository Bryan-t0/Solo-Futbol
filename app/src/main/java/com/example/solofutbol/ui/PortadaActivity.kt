package com.example.solofutbol.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityPortadaBinding

class PortadaActivity : AppCompatActivity() {
    private lateinit var b: ActivityPortadaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPortadaBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.btnContinuar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}