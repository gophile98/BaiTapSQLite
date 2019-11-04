package com.example.student.baitapandroidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StuManager extends SQLiteOpenHelper {

    public StuManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void action(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
    public Cursor queryCurso(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }
    public int delete(SinhVien sinhVien){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("SinhVien", "id=" + sinhVien.getId(), null);
    }
    public long insert(SinhVien sinhVien){
        System.out.println(sinhVien);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sinhVien.getName());
        values.put("class_name", sinhVien.getClass_name());
        values.put("subject", sinhVien.getSubject());
        return sqLiteDatabase.insert("SinhVien", null,values);
    }
    public int update(SinhVien sinhVien){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", sinhVien.getName());
        values.put("subject", sinhVien.getSubject());
        return sqLiteDatabase.update("SinhVien", values, "id="+sinhVien.getId(), null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
