package com.example.solofutbol

import com.example.solofutbol.util.Validaciones
import org.junit.Assert.*
import org.junit.Test

class   ValidacionesTest {

    @Test
    fun validarSku_formatoCorrecto_retornaTrue() {
        val resultado = Validaciones.validarSku("SKU-123")
        assertTrue(resultado)
    }

    @Test
    fun validarSku_muyCorto_retornaFalse() {
        val resultado = Validaciones.validarSku("A")
        assertFalse(resultado)
    }

    @Test
    fun validarStock_numeroNegativo_retornaFalse() {
        val resultado = Validaciones.validarStock(-5)
        assertFalse(resultado)
    }

    @Test
    fun validarRut_formatoValido_retornaTrue() {
        val resultado = Validaciones.validarRut("12345678-9")
        assertTrue(resultado)
    }

    @Test
    fun validarClave_corta_retornaFalse() {
        val resultado = Validaciones.validarClave("123")
        assertFalse(resultado)
    }
}
