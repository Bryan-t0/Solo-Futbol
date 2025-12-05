package com.example.solofutbol.ui.stock

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.solofutbol.R
import com.example.solofutbol.datos.BaseDatos
import com.example.solofutbol.datos.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerStockActivity : AppCompatActivity() {

    private var pendingCsv: String? = null

    private val createCsvLauncher = registerForActivityResult(
        ActivityResultContracts.CreateDocument("text/csv")
    ) { uri: Uri? ->
        val csv = pendingCsv
        if (uri == null || csv == null) {
            Toast.makeText(this, "Descarga cancelada", Toast.LENGTH_SHORT).show()
            return@registerForActivityResult
        }
        contentResolver.openOutputStream(uri)?.use { it.write(csv.toByteArray(Charsets.UTF_8)) }
        Toast.makeText(this, "CSV guardado", Toast.LENGTH_SHORT).show()
        pendingCsv = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_stock)

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val btnDescargar = findViewById<Button>(R.id.btnExportarExcel)

        btnVolver.setOnClickListener { finish() }

        btnDescargar.setOnClickListener {
            lifecycleScope.launch {
                val lista: List<Producto> = withContext(Dispatchers.IO) {
                    BaseDatos.obtener(this@VerStockActivity).productoDao().listar()
                }
                pendingCsv = buildCsv(lista)
                val nombre = "solo_futbol_stock.csv"
                createCsvLauncher.launch(nombre)
            }
        }
    }

    private fun buildCsv(items: List<Producto>): String {
        fun esc(s: String) = "\"" + s.replace("\"", "\"\"") + "\""
        val sb = StringBuilder().appendLine("Producto,SKU,Talla,Color,Stock")
        for (p in items) {
            sb.append(esc(p.nombre)).append(',')
                .append(esc(p.sku)).append(',')
                .append(esc(p.talla)).append(',')
                .append(esc(p.color)).append(',')
                .append(p.stock.toString())
                .append('\n')
        }
        return sb.toString()
    }
}
