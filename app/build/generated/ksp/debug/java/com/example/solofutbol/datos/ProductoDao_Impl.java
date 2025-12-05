package com.example.solofutbol.datos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
public final class ProductoDao_Impl implements ProductoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Producto> __insertionAdapterOfProducto;

  private final EntityDeletionOrUpdateAdapter<Producto> __updateAdapterOfProducto;

  public ProductoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProducto = new EntityInsertionAdapter<Producto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `producto` (`sku`,`nombre`,`talla`,`color`,`stock`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Producto entity) {
        statement.bindString(1, entity.getSku());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getTalla());
        statement.bindString(4, entity.getColor());
        statement.bindLong(5, entity.getStock());
      }
    };
    this.__updateAdapterOfProducto = new EntityDeletionOrUpdateAdapter<Producto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `producto` SET `sku` = ?,`nombre` = ?,`talla` = ?,`color` = ?,`stock` = ? WHERE `sku` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Producto entity) {
        statement.bindString(1, entity.getSku());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getTalla());
        statement.bindString(4, entity.getColor());
        statement.bindLong(5, entity.getStock());
        statement.bindString(6, entity.getSku());
      }
    };
  }

  @Override
  public Object guardar(final Producto p, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProducto.insert(p);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object actualizar(final Producto p, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProducto.handle(p);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object listar(final Continuation<? super List<Producto>> $completion) {
    final String _sql = "SELECT * FROM producto ORDER BY nombre";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Producto>>() {
      @Override
      @NonNull
      public List<Producto> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSku = CursorUtil.getColumnIndexOrThrow(_cursor, "sku");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfTalla = CursorUtil.getColumnIndexOrThrow(_cursor, "talla");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
          final List<Producto> _result = new ArrayList<Producto>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Producto _item;
            final String _tmpSku;
            _tmpSku = _cursor.getString(_cursorIndexOfSku);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpTalla;
            _tmpTalla = _cursor.getString(_cursorIndexOfTalla);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            _item = new Producto(_tmpSku,_tmpNombre,_tmpTalla,_tmpColor,_tmpStock);
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
  public Object existe(final String sku, final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM producto WHERE sku = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, sku);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
