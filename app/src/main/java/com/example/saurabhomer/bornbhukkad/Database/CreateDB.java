package com.example.saurabhomer.bornbhukkad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.saurabhomer.bornbhukkad.Model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh omer on 31-Mar-18.
 */

public class CreateDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "O1";
    public static final String COL_1 = "ProductId";
    public static final String COL_2 = "Quantity";
    public static final String COL_3 = "Price";
    public static final String COL_4 = "Discount";


    public CreateDB(Context context) {
        super(context,"student2.db",null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ProductId TEXT,ProductName TEXT,Quantity INTEGER,Price INTEGER,Discount INTEGER)");
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ProductId TEXT,Quantity TEXT,Price TEXT,Discount TEXT)");
    }
    public boolean insertData(String col1,String col2,String col3,String clo4,String col5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,"2");
        contentValues.put(COL_2,"3");
        contentValues.put(COL_3,"2000");
        contentValues.put(COL_4,"100");
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
//

    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+TABLE_NAME,null);

        final List<Order> result=new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                result.add(new Order(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                ));
            } while (c.moveToNext());
        }

        return  result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public void cleanCart()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }
}
