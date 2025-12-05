# SoloFutbol ⚽

**SoloFutbol** es una aplicación móvil Android desarrollada en Kotlin diseñada para la gestión de inventario y usuarios de una tienda deportiva. Permite a los administradores controlar el stock de productos, escanear códigos QR para identificar artículos y gestionar las cuentas de los empleados.

## 🚀 Características Principales

*   **Autenticación de Usuarios:** Sistema de Login con roles diferenciados (Administrador y Usuario).
*   **Gestión de Inventario:**
    *   Agregar y editar productos (SKU, Nombre, Talla, Color, Stock).
    *   Validación de datos de entrada.
*   **Control de Stock:** Visualización del listado de productos disponibles.
*   **Escáner QR:** Integración con la cámara para escanear códigos de productos (SKU) automáticamente.
*   **Gestión de Usuarios:** CRUD completo (Crear, Leer, Actualizar) para administrar el personal de la tienda.
*   **Persistencia de Datos:** Base de datos local utilizando **Room**.

---

## 📋 Tercera Evaluación: Avances e Implementaciones

Para cumplir con los requisitos de la Tercera Evaluación, se han integrado las siguientes funcionalidades clave:

### 1. Consumo de API Externa 🌐
Se ha implementado la funcionalidad de consumo de servicios web RESTful.
*   **Librería utilizada:** Retrofit 2 + GSON.
*   **Funcionalidad:** Se conecta a una API pública (`jsonplaceholder.typicode.com`) para obtener datos de usuarios externos.
*   **Demostración:** En el **Menú de Administrador**, se agregó el botón **"Test API"** que abre una pantalla exclusiva para verificar la conexión y la recepción de datos en tiempo real.

### 2. Test Unitarios 🧪
Se han desarrollado pruebas unitarias funcionales para validar la lógica de negocio crítica de la aplicación.
*   **Ubicación:** `app/src/test/java/com/example/solofutbol/ValidacionesTest.kt`
*   **Cobertura (5 Tests):**
    1.  `validarSku`: Verifica el formato correcto del código de producto.
    2.  `validarStock`: Asegura que el stock no sea negativo.
    3.  `validarRut`: Comprueba el formato básico del RUT.
    4.  `validarClave`: Valida la longitud mínima de contraseñas.
    5.  Validaciones de casos de error (SKU muy cortos, etc.).

### 3. Generación de APK 📱
El proyecto está completamente configurado y listo para la generación del archivo APK firmado (`Build > Build Bundle(s) / APK(s) > Build APK(s)`).
*   **Permisos:** Se agregaron los permisos de `INTERNET` en el Manifiesto para asegurar el funcionamiento de la API en la versión compilada.

---

## 🛠️ Tecnologías Utilizadas

*   **Lenguaje:** Kotlin
*   **Arquitectura:** MVVM (con ViewBinding)
*   **Base de Datos:** Room Database
*   **Red:** Retrofit 2
*   **Asincronía:** Coroutines (Kotlinx Coroutines)
*   **Cámara/QR:** CameraX + ML Kit
*   **Testing:** JUnit 4

## 👤 Autor
Desarrollado para la asignatura de Desarrollo de Aplicaciones Móviles.
