package com.example.solofutbol.datos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UsuarioDao_Impl implements UsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Usuario> __insertionAdapterOfUsuario;

  private final EntityDeletionOrUpdateAdapter<Usuario> __deletionAdapterOfUsuario;

  private final EntityDeletionOrUpdateAdapter<Usuario> __updateAdapterOfUsuario;

  public UsuarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `usuario` (`rut`,`usuario`,`nombre`,`apellido`,`direccion`,`clave`,`esAdmin`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Usuario entity) {
        statement.bindString(1, entity.getRut());
        statement.bindString(2, entity.getUsuario());
        statement.bindString(3, entity.getNombre());
        statement.bindString(4, entity.getApellido());
        statement.bindString(5, entity.getDireccion());
        statement.bindString(6, entity.getClave());
        final int _tmp = entity.getEsAdmin() ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__deletionAdapterOfUsuario = new EntityDeletionOrUpdateAdapter<Usuario>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `usuario` WHERE `rut` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Usuario entity) {
        statement.bindString(1, entity.getRut());
      }
    };
    this.__updateAdapterOfUsuario = new EntityDeletionOrUpdateAdapter<Usuario>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `usuario` SET `rut` = ?,`usuario` = ?,`nombre` = ?,`apellido` = ?,`direccion` = ?,`clave` = ?,`esAdmin` = ? WHERE `rut` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Usuario entity) {
        statement.bindString(1, entity.getRut());
        statement.bindString(2, entity.getUsuario());
        statement.bindString(3, entity.getNombre());
        statement.bindString(4, entity.getApellido());
        statement.bindString(5, entity.getDireccion());
        statement.bindString(6, entity.getClave());
        final int _tmp = entity.getEsAdmin() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindString(8, entity.getRut());
      }
    };
  }

  @Override
  public Object guardar(final Usuario u, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUsuario.insert(u);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object eliminar(final Usuario u, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUsuario.handle(u);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object actualizar(final Usuario u, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUsuario.handle(u);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Usuario login(final String user, final String pass) {
    final String _sql = "SELECT * FROM usuario WHERE usuario = ? AND clave = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, user);
    _argIndex = 2;
    _statement.bindString(_argIndex, pass);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfRut = CursorUtil.getColumnIndexOrThrow(_cursor, "rut");
      final int _cursorIndexOfUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "usuario");
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfApellido = CursorUtil.getColumnIndexOrThrow(_cursor, "apellido");
      final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
      final int _cursorIndexOfClave = CursorUtil.getColumnIndexOrThrow(_cursor, "clave");
      final int _cursorIndexOfEsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "esAdmin");
      final Usuario _result;
      if (_cursor.moveToFirst()) {
        final String _tmpRut;
        _tmpRut = _cursor.getString(_cursorIndexOfRut);
        final String _tmpUsuario;
        _tmpUsuario = _cursor.getString(_cursorIndexOfUsuario);
        final String _tmpNombre;
        _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
        final String _tmpApellido;
        _tmpApellido = _cursor.getString(_cursorIndexOfApellido);
        final String _tmpDireccion;
        _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
        final String _tmpClave;
        _tmpClave = _cursor.getString(_cursorIndexOfClave);
        final boolean _tmpEsAdmin;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEsAdmin);
        _tmpEsAdmin = _tmp != 0;
        _result = new Usuario(_tmpRut,_tmpUsuario,_tmpNombre,_tmpApellido,_tmpDireccion,_tmpClave,_tmpEsAdmin);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Object listarTodos(final Continuation<? super List<Usuario>> $completion) {
    final String _sql = "SELECT * FROM usuario ORDER BY usuario";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Usuario>>() {
      @Override
      @NonNull
      public List<Usuario> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRut = CursorUtil.getColumnIndexOrThrow(_cursor, "rut");
          final int _cursorIndexOfUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "usuario");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfApellido = CursorUtil.getColumnIndexOrThrow(_cursor, "apellido");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
          final int _cursorIndexOfClave = CursorUtil.getColumnIndexOrThrow(_cursor, "clave");
          final int _cursorIndexOfEsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "esAdmin");
          final List<Usuario> _result = new ArrayList<Usuario>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Usuario _item;
            final String _tmpRut;
            _tmpRut = _cursor.getString(_cursorIndexOfRut);
            final String _tmpUsuario;
            _tmpUsuario = _cursor.getString(_cursorIndexOfUsuario);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpApellido;
            _tmpApellido = _cursor.getString(_cursorIndexOfApellido);
            final String _tmpDireccion;
            _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            final String _tmpClave;
            _tmpClave = _cursor.getString(_cursorIndexOfClave);
            final boolean _tmpEsAdmin;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsAdmin);
            _tmpEsAdmin = _tmp != 0;
            _item = new Usuario(_tmpRut,_tmpUsuario,_tmpNombre,_tmpApellido,_tmpDireccion,_tmpClave,_tmpEsAdmin);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object existe(final String user, final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, user);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerPorRut(final String rut, final Continuation<? super Usuario> $completion) {
    final String _sql = "SELECT * FROM usuario WHERE rut = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, rut);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Usuario>() {
      @Override
      @Nullable
      public Usuario call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRut = CursorUtil.getColumnIndexOrThrow(_cursor, "rut");
          final int _cursorIndexOfUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "usuario");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfApellido = CursorUtil.getColumnIndexOrThrow(_cursor, "apellido");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
          final int _cursorIndexOfClave = CursorUtil.getColumnIndexOrThrow(_cursor, "clave");
          final int _cursorIndexOfEsAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "esAdmin");
          final Usuario _result;
          if (_cursor.moveToFirst()) {
            final String _tmpRut;
            _tmpRut = _cursor.getString(_cursorIndexOfRut);
            final String _tmpUsuario;
            _tmpUsuario = _cursor.getString(_cursorIndexOfUsuario);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpApellido;
            _tmpApellido = _cursor.getString(_cursorIndexOfApellido);
            final String _tmpDireccion;
            _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            final String _tmpClave;
            _tmpClave = _cursor.getString(_cursorIndexOfClave);
            final boolean _tmpEsAdmin;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsAdmin);
            _tmpEsAdmin = _tmp != 0;
            _result = new Usuario(_tmpRut,_tmpUsuario,_tmpNombre,_tmpApellido,_tmpDireccion,_tmpClave,_tmpEsAdmin);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
