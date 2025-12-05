package com.example.solofutbol.util

object Validaciones {

    fun validarSku(sku: String): Boolean {
        return sku.matches(Regex("^[A-Z0-9-_.]{3,32}$"))
    }

    fun validarStock(stock: Int): Boolean {
        return stock >= 0
    }

    fun validarNombreProducto(nombre: String): Boolean {
        return nombre.isNotBlank() && nombre.length <= 50
    }

    fun validarRut(rut: String): Boolean {
        // Validación simple de formato RUT chileno (sin dígito verificador complejo para este ejemplo)
        // Ej: 12345678-9
        return rut.matches(Regex("^\\d{7,8}-[0-9kK]$"))
    }

    fun validarClave(clave: String): Boolean {
        // Mínimo 4 caracteres
        return clave.length >= 4
    }
}
