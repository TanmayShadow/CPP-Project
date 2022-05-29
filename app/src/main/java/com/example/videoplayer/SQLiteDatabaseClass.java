package com.example.videoplayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseClass extends SQLiteOpenHelper{
    SQLiteDatabase sdb = getWritableDatabase();
    SQLiteDatabaseClass(Context c)
    {
        super(c,"LoginDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE login(id INTEGER);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertLogin(int id)
    {
        String sql = "INSERT INTO login values("+id+");";
        sdb.execSQL(sql);
    }
    public int getLogin()
    {
        String fetch = "SELECT * FROM login";
        Cursor c =sdb.rawQuery(fetch,null);
        if(c.moveToFirst())
        {
            int id = c.getInt(0);
            return id;
        }
        return -1;
    }
    public void deleteLogin()
    {
        String delete = "DELETE FROM login";
        sdb.execSQL(delete);
    }
}
