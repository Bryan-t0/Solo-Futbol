package com.example.solofutbol.datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Usuario::class, Producto::class],
    version = 1,
    exportSchema = false
)
abstract class SoloFutbolDb : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun productoDao(): ProductoDao
}

object BaseDatos {
    @Volatile private var instancia: SoloFutbolDb? = null

    fun obtener(ctx: Context): SoloFutbolDb =
        instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(
                ctx.applicationContext,
                SoloFutbolDb::class.java,
                "solo_futbol.db"
            )
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val admin = Usuario(
                            rut = "1-9",
                            usuario = "admin",
                            nombre = "Admin",
                            apellido = "SoloFutbol",
                            direccion = "N/A",
                            clave = "admin",
                            esAdmin = true
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            kotlin.runCatching { obtener(ctx).usuarioDao().guardar(admin) }
                        }
                    }
                })
                .build()
                .also { instancia = it }
        }
}
