package com.example.onlineschoolcourses.room;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.model.PersonalAreaSaveDataModel;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SaveDataDao_Impl implements SaveDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PersonalAreaSaveDataModel> __insertionAdapterOfPersonalAreaSaveDataModel;

  private final EntityDeletionOrUpdateAdapter<PersonalAreaSaveDataModel> __deletionAdapterOfPersonalAreaSaveDataModel;

  private final EntityDeletionOrUpdateAdapter<PersonalAreaSaveDataModel> __updateAdapterOfPersonalAreaSaveDataModel;

  public SaveDataDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPersonalAreaSaveDataModel = new EntityInsertionAdapter<PersonalAreaSaveDataModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `saveDataModel` (`id`,`courseNames`,`course_image`,`description`,`file`,`homeWork`,`price`,`profession`,`test`,`youtubeUrl`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PersonalAreaSaveDataModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getCourseNames() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCourseNames());
        }
        if (value.getCourse_image() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCourse_image());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getFile() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFile());
        }
        if (value.getHomeWork() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getHomeWork());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrice());
        }
        if (value.getProfession() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getProfession());
        }
        if (value.getTest() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTest());
        }
        if (value.getYoutubeUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getYoutubeUrl());
        }
      }
    };
    this.__deletionAdapterOfPersonalAreaSaveDataModel = new EntityDeletionOrUpdateAdapter<PersonalAreaSaveDataModel>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `saveDataModel` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PersonalAreaSaveDataModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfPersonalAreaSaveDataModel = new EntityDeletionOrUpdateAdapter<PersonalAreaSaveDataModel>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `saveDataModel` SET `id` = ?,`courseNames` = ?,`course_image` = ?,`description` = ?,`file` = ?,`homeWork` = ?,`price` = ?,`profession` = ?,`test` = ?,`youtubeUrl` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PersonalAreaSaveDataModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getCourseNames() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCourseNames());
        }
        if (value.getCourse_image() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCourse_image());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getFile() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFile());
        }
        if (value.getHomeWork() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getHomeWork());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPrice());
        }
        if (value.getProfession() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getProfession());
        }
        if (value.getTest() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTest());
        }
        if (value.getYoutubeUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getYoutubeUrl());
        }
        if (value.getId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final List<PersonalAreaSaveDataModel> mAdd,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPersonalAreaSaveDataModel.insert(mAdd);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final PersonalAreaSaveDataModel mDelete,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPersonalAreaSaveDataModel.handle(mDelete);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateId(final PersonalAreaSaveDataModel mInsert,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPersonalAreaSaveDataModel.handle(mInsert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<PersonalAreaSaveDataModel>> getAll() {
    final String _sql = "SELECT * FROM saveDataModel ORDER BY profession  ASC ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"saveDataModel"}, new Callable<List<PersonalAreaSaveDataModel>>() {
      @Override
      public List<PersonalAreaSaveDataModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseNames = CursorUtil.getColumnIndexOrThrow(_cursor, "courseNames");
          final int _cursorIndexOfCourseImage = CursorUtil.getColumnIndexOrThrow(_cursor, "course_image");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfFile = CursorUtil.getColumnIndexOrThrow(_cursor, "file");
          final int _cursorIndexOfHomeWork = CursorUtil.getColumnIndexOrThrow(_cursor, "homeWork");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfProfession = CursorUtil.getColumnIndexOrThrow(_cursor, "profession");
          final int _cursorIndexOfTest = CursorUtil.getColumnIndexOrThrow(_cursor, "test");
          final int _cursorIndexOfYoutubeUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "youtubeUrl");
          final List<PersonalAreaSaveDataModel> _result = new ArrayList<PersonalAreaSaveDataModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PersonalAreaSaveDataModel _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpCourseNames;
            if (_cursor.isNull(_cursorIndexOfCourseNames)) {
              _tmpCourseNames = null;
            } else {
              _tmpCourseNames = _cursor.getString(_cursorIndexOfCourseNames);
            }
            final String _tmpCourse_image;
            if (_cursor.isNull(_cursorIndexOfCourseImage)) {
              _tmpCourse_image = null;
            } else {
              _tmpCourse_image = _cursor.getString(_cursorIndexOfCourseImage);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpFile;
            if (_cursor.isNull(_cursorIndexOfFile)) {
              _tmpFile = null;
            } else {
              _tmpFile = _cursor.getString(_cursorIndexOfFile);
            }
            final String _tmpHomeWork;
            if (_cursor.isNull(_cursorIndexOfHomeWork)) {
              _tmpHomeWork = null;
            } else {
              _tmpHomeWork = _cursor.getString(_cursorIndexOfHomeWork);
            }
            final String _tmpPrice;
            if (_cursor.isNull(_cursorIndexOfPrice)) {
              _tmpPrice = null;
            } else {
              _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            }
            final String _tmpProfession;
            if (_cursor.isNull(_cursorIndexOfProfession)) {
              _tmpProfession = null;
            } else {
              _tmpProfession = _cursor.getString(_cursorIndexOfProfession);
            }
            final String _tmpTest;
            if (_cursor.isNull(_cursorIndexOfTest)) {
              _tmpTest = null;
            } else {
              _tmpTest = _cursor.getString(_cursorIndexOfTest);
            }
            final String _tmpYoutubeUrl;
            if (_cursor.isNull(_cursorIndexOfYoutubeUrl)) {
              _tmpYoutubeUrl = null;
            } else {
              _tmpYoutubeUrl = _cursor.getString(_cursorIndexOfYoutubeUrl);
            }
            _item = new PersonalAreaSaveDataModel(_tmpId,_tmpCourseNames,_tmpCourse_image,_tmpDescription,_tmpFile,_tmpHomeWork,_tmpPrice,_tmpProfession,_tmpTest,_tmpYoutubeUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
