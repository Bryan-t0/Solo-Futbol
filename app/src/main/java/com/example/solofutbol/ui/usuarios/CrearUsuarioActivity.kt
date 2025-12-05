package com.example.solofutbol.ui.usuarios

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.solofutbol.databinding.ActivityCrearUsuarioBinding
import com.example.solofutbol.datos.BaseDatos
import com.example.solofutbol.datos.Usuario
import kotlinx.coroutines.*

class CrearUsuarioActivity : AppCompatActivity() {
    private lateinit var b: ActivityCrearUsuarioBinding
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCrearUsuarioBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnVolver.setOnClickListener { finish() }

        b.btnGuardar.setOnClickListener {
            val rutInput = b.etRut.text?.toString()?.trim().orEmpty()
            val norm = normalizarRut(rutInput)
            val rutConDv = when {
                norm.length >= 8 && norm.last() in "0123456789K" -> norm
                norm.all { it.isDigit() } && norm.length in 7..9 -> norm + calcularDv(norm)
                else -> ""
            }
            if (rutConDv.isEmpty() || !rutValidoCompleto(rutConDv)) {
                b.etRut.error = "RUT inválido. Ej: 12.345.678-5"
                b.etRut.requestFocus()
                return@setOnClickListener
            }
            val cuerpo = normalizarRut(rutConDv).dropLast(1)
            val dv = normalizarRut(rutConDv).last()
            val rutOk = formatearRut(cuerpo, dv)

            val usuario = b.etUsuario.text?.toString()?.trim().orEmpty()
            val nombre = b.etNombre.text?.toString()?.trim().orEmpty()
            val apellido = b.etApellido.text?.toString()?.trim().orEmpty()
            val direccion = b.etDireccion.text?.toString()?.trim().orEmpty()
            val clave = b.etClave.text?.toString()?.trim().orEmpty()
            val esAdmin = b.chkEsAdmin.isChecked

            if (!usuarioValido(usuario)) { b.etUsuario.error = "Usuario 3-20, letras/números/._"; b.etUsuario.requestFocus(); return@setOnClickListener }
            if (nombre.isEmpty()) { b.etNombre.error = "Requerido"; b.etNombre.requestFocus(); return@setOnClickListener }
            if (apellido.isEmpty()) { b.etApellido.error = "Requerido"; b.etApellido.requestFocus(); return@setOnClickListener }
            if (clave.length < 4) { b.etClave.error = "Mínimo 4 caracteres"; b.etClave.requestFocus(); return@setOnClickListener }

            scope.launch {
                val dao = BaseDatos.obtener(this@CrearUsuarioActivity).usuarioDao()
                val duplicados = withContext(Dispatchers.IO) {
                    val porRut = dao.obtenerPorRut(rutOk)
                    val existeUsuario = dao.existe(usuario)
                    Pair(porRut != null, existeUsuario > 0)
                }
                if (duplicados.first) { Toast.makeText(this@CrearUsuarioActivity, "RUT ya existe", Toast.LENGTH_SHORT).show(); return@launch }
                if (duplicados.second) { Toast.makeText(this@CrearUsuarioActivity, "Usuario ya existe", Toast.LENGTH_SHORT).show(); return@launch }

                withContext(Dispatchers.IO) {
                    dao.guardar(
                        Usuario(
                            rut = rutOk,
                            usuario = usuario,
                            nombre = nombre,
                            apellido = apellido,
                            direccion = direccion,
                            clave = clave,
                            esAdmin = esAdmin
                        )
                    )
                }
                Toast.makeText(this@CrearUsuarioActivity, "Usuario creado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun normalizarRut(input: String): String =
        input.uppercase().replace("[^0-9K]".toRegex(), "")

    private fun calcularDv(cuerpo: String): Char {
        var factor = 2
        var suma = 0
        for (ch in cuerpo.reversed()) {
            suma += (ch - '0') * factor
            factor = if (factor == 7) 2 else factor + 1
        }
        val resto = 11 - (suma % 11)
        return when (resto) {
            11 -> '0'
            10 -> 'K'
            else -> ('0'.code + resto).toChar()
        }
    }

    private fun rutValidoCompleto(rutConDv: String): Boolean {
        val s = normalizarRut(rutConDv)
        if (s.length < 2) return false
        val cuerpo = s.dropLast(1)
        val dv = s.last()
        if (!cuerpo.all { it.isDigit() } || cuerpo.length !in 7..9) return false
        return calcularDv(cuerpo) == dv
    }

    private fun formatearRut(cuerpo: String, dv: Char): String {
        val rev = cuerpo.reversed()
        val conPuntos = buildString {
            for (i in rev.indices) {
                if (i > 0 && i % 3 == 0) append('.')
                append(rev[i])
            }
        }.reversed()
        return "$conPuntos-$dv"
    }

    private fun usuarioValido(u: String) = u.matches(Regex("^[A-Za-z0-9_.]{3,20}$"))
}
