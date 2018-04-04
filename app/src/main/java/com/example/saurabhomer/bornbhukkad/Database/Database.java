package com.example.saurabhomer.bornbhukkad.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.CalendarContract;

import com.example.saurabhomer.bornbhukkad.FoodDetail;
import com.example.saurabhomer.bornbhukkad.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh omer on 29-Mar-18.
 */


public class Database extends SQLiteAssetHelper {
    private static final  String DB_NAME="/assets/databases/eititDB.db";
    private  static final int DB_VER=1;
    CreateDB db ;
    //SQLiteDatabase db =getReadableDatabase();
    public Database(Context context) {
        super(context, DB_NAME, null,DB_VER);
    }
    public void createTable( CreateDB db)
    {
        this.db=db;
    }


    public List<Order> getCarts()
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb= new SQLiteQueryBuilder();
        String[] sqlSelect = {"ProductId","ProductName","Quantity","Price","Discount"};
        String sqlTable = "OrderDetail";
        qb.setTables(sqlTable);
        Cursor c =qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result=new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                ));
            } while (c.moveToNext());
        }
        return  result;
    }
    public void addToCard(Order order)
    {
        SQLiteDatabase db =getWritableDatabase();
        String query =String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getDiscount());
        db.execSQL(query);

    }
    public void cleanCart()
    {
        SQLiteDatabase db =getReadableDatabase();
        String query =String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }
}
