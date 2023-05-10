package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDB.db";
    public static final String TABLE_EMPLOYEE = "Employee";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String employeeTableCreate = "CREATE TABLE '" + TABLE_EMPLOYEE + "' (" + "'empNo' TEXT " +
                "PRIMARY KEY," + "'empName' TEXT);";
        sqLiteDatabase.execSQL(employeeTableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table If Exists " + TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }

    public void insert(String empNo, String empName) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("empNo", empNo);
            contentValues.put("empName", empName);

            db.insert(TABLE_EMPLOYEE, null, contentValues);
        } catch (Exception ignored) {

        }
    }

    public void delete(String empNo) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_EMPLOYEE, "empNo = ?", new String[]{empNo});
        } catch (Exception ex) {

        }
    }

    public void update(String empNo, String empName) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("empName", empName);
            db.update(TABLE_EMPLOYEE, contentValues, "empNo = ?", new String[]{empNo});
        } catch (Exception ex) {

        }
    }

    public String search(String empNo) {
        try {
            String value="";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(TABLE_EMPLOYEE, new String[]{"empName"}, "empNo Like ?",
                    new String[]{empNo}, null, null, null, null);
            if(cursor != null && cursor.moveToNext()) {
                cursor.moveToFirst();
                value = cursor.getString(0);
                cursor.close();
            }
            return value;
        } catch (Exception ex) {

        }
        return "";
    }
}
