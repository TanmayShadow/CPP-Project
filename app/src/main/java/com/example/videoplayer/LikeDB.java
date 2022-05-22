package com.example.videoplayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LikeDB extends SQLiteOpenHelper {
    LikeDB(Context c)
    {
        super(c,"LikeDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE liketab(vid integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addLike(int id)
    {
        SQLiteDatabase sdb=getWritableDatabase();
        String in = "INSERT INTO liketab values("+id+");";
        sdb.execSQL(in);
    }
    public void removeLike(int id)
    {
        SQLiteDatabase sdb=getWritableDatabase();
        String in = "DELETE FROM liketab WHERE vid="+id+";";
        sdb.execSQL(in);
    }
    public String getLike()
    {
        SQLiteDatabase sdb=getWritableDatabase();
        Cursor c = sdb.rawQuery("SELECT * FROM liketab",null);
        String s = "";
        if(c.moveToFirst())
        {
            do{
                s=c.getInt(1)+","+s;
            }while(c.moveToNext());
        }
        if(s.equals(""));
        return "-1";
    }
}
