package com.example.solofutbol.ui.inventario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityInventarioBinding
import com.example.solofutbol.datos.BaseDatos
import com.example.solofutbol.datos.Producto
import kotlinx.coroutines.*
import java.util.Locale

class InventarioActivity : AppCompatActivity() {
    private lateinit var b: ActivityInventarioBinding
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val scanLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { res ->
        if (res.resultCode == RESULT_OK) {
            val sku = res.data?.getStringExtra("sku").orEmpty()
            b.etSku.setText(sku)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityInventarioBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnVolver.setOnClickListener { finish() }

        b.btnEscanearQr.setOnClickListener {
            val i = Intent(this, com.example.solofutbol.ui.qr.EscaneoQRActivity::class.java)
            scanLauncher.launch(i)
        }

        b.btnGuardarProducto.setOnClickListener {
            val sku = b.etSku.text?.toString()?.trim()?.uppercase(Locale.ROOT) ?: ""
            val nombre = b.etNombre.text?.toString()?.trim().orEmpty()
            val talla = b.etTalla.text?.toString()?.trim()?.uppercase(Locale.ROOT) ?: ""
            val color = b.etColor.text?.toString()?.trim().orEmpty()
            val stockStr = b.etStock.text?.toString()?.trim().orEmpty()

            if (!sku.matches(Regex("^[A-Z0-9-_.]{3,32}$"))) { b.etSku.error = "SKU inválido"; b.etSku.requestFocus(); return@setOnClickListener }
            if (nombre.isEmpty()) { b.etNombre.error = "Requerido"; b.etNombre.requestFocus(); return@setOnClickListener }
            val stock = stockStr.toIntOrNull()
            if (stock == null || stock < 0) { b.etStock.error = "Stock entero ≥ 0"; b.etStock.requestFocus(); return@setOnClickListener }

            scope.launch {
                val dao = BaseDatos.obtener(this@InventarioActivity).productoDao()
                val existe = withContext(Dispatchers.IO) { dao.existe(sku) }
                withContext(Dispatchers.IO) {
                    val p = Producto(sku = sku, nombre = nombre, talla = talla, color = color, stock = stock)
                    if (existe > 0) dao.actualizar(p) else dao.guardar(p)
                }
                Toast.makeText(this@InventarioActivity, "Guardado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
