package com.example.solofutbol.datos;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SoloFutbolDb_Impl extends SoloFutbolDb {
  private volatile UsuarioDao _usuarioDao;

  private volatile ProductoDao _productoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuario` (`rut` TEXT NOT NULL, `usuario` TEXT NOT NULL, `nombre` TEXT NOT NULL, `apellido` TEXT NOT NULL, `direccion` TEXT NOT NULL, `clave` TEXT NOT NULL, `esAdmin` INTEGER NOT NULL, PRIMARY KEY(`rut`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `producto` (`sku` TEXT NOT NULL, `nombre` TEXT NOT NULL, `talla` TEXT NOT NULL, `color` TEXT NOT NULL, `stock` INTEGER NOT NULL, PRIMARY KEY(`sku`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f5a245ed53cc6a69eb7281a15bff60d5')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuario`");
        db.execSQL("DROP TABLE IF EXISTS `producto`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuario = new HashMap<String, TableInfo.Column>(7);
        _columnsUsuario.put("rut", new TableInfo.Column("rut", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("usuario", new TableInfo.Column("usuario", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("apellido", new TableInfo.Column("apellido", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("direccion", new TableInfo.Column("direccion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("clave", new TableInfo.Column("clave", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("esAdmin", new TableInfo.Column("esAdmin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuario = new TableInfo("usuario", _columnsUsuario, _foreignKeysUsuario, _indicesUsuario);
        final TableInfo _existingUsuario = TableInfo.read(db, "usuario");
        if (!_infoUsuario.equals(_existingUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "usuario(com.example.solofutbol.datos.Usuario).\n"
                  + " Expected:\n" + _infoUsuario + "\n"
                  + " Found:\n" + _existingUsuario);
        }
        final HashMap<String, TableInfo.Column> _columnsProducto = new HashMap<String, TableInfo.Column>(5);
        _columnsProducto.put("sku", new TableInfo.Column("sku", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducto.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducto.put("talla", new TableInfo.Column("talla", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducto.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducto.put("stock", new TableInfo.Column("stock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProducto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProducto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProducto = new TableInfo("producto", _columnsProducto, _foreignKeysProducto, _indicesProducto);
        final TableInfo _existingProducto = TableInfo.read(db, "producto");
        if (!_infoProducto.equals(_existingProducto)) {
          return new RoomOpenHelper.ValidationResult(false, "producto(com.example.solofutbol.datos.Producto).\n"
                  + " Expected:\n" + _infoProducto + "\n"
                  + " Found:\n" + _existingProducto);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f5a245ed53cc6a69eb7281a15bff60d5", "c770ae268497d0dd6849c336d52a4bee");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuario","producto");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `usuario`");
      _db.execSQL("DELETE FROM `producto`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductoDao.class, ProductoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public ProductoDao productoDao() {
    if (_productoDao != null) {
      return _productoDao;
    } else {
      synchronized(this) {
        if(_productoDao == null) {
          _productoDao = new ProductoDao_Impl(this);
        }
        return _productoDao;
      }
    }
  }
}
