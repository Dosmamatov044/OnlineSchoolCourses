package com.example.onlineschoolcourses.room;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SaveDataDataBase_Impl extends SaveDataDataBase {
  private volatile SaveDataDao _saveDataDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `saveDataModel` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `courseNames` TEXT NOT NULL, `course_image` TEXT NOT NULL, `description` TEXT NOT NULL, `file` TEXT NOT NULL, `homeWork` TEXT NOT NULL, `price` TEXT NOT NULL, `profession` TEXT NOT NULL, `test` TEXT NOT NULL, `youtubeUrl` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'aa7c4da2d26c74cc7761d642ad6b0e1b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `saveDataModel`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSaveDataModel = new HashMap<String, TableInfo.Column>(10);
        _columnsSaveDataModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("courseNames", new TableInfo.Column("courseNames", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("course_image", new TableInfo.Column("course_image", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("file", new TableInfo.Column("file", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("homeWork", new TableInfo.Column("homeWork", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("price", new TableInfo.Column("price", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("profession", new TableInfo.Column("profession", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("test", new TableInfo.Column("test", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSaveDataModel.put("youtubeUrl", new TableInfo.Column("youtubeUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSaveDataModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSaveDataModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSaveDataModel = new TableInfo("saveDataModel", _columnsSaveDataModel, _foreignKeysSaveDataModel, _indicesSaveDataModel);
        final TableInfo _existingSaveDataModel = TableInfo.read(_db, "saveDataModel");
        if (! _infoSaveDataModel.equals(_existingSaveDataModel)) {
          return new RoomOpenHelper.ValidationResult(false, "saveDataModel(com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.PersonalAreaSaveDataModel).\n"
                  + " Expected:\n" + _infoSaveDataModel + "\n"
                  + " Found:\n" + _existingSaveDataModel);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "aa7c4da2d26c74cc7761d642ad6b0e1b", "27659d6633825c1b1bed3f76341ce888");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "saveDataModel");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `saveDataModel`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SaveDataDao.class, SaveDataDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public SaveDataDao saveDataDao() {
    if (_saveDataDao != null) {
      return _saveDataDao;
    } else {
      synchronized(this) {
        if(_saveDataDao == null) {
          _saveDataDao = new SaveDataDao_Impl(this);
        }
        return _saveDataDao;
      }
    }
  }
}
