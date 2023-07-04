package com.example.healthcare_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qry1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text, product text,price float, otype text)";//otype is order type like we have 3 kinds of order ,lab test and book appointment,buy medicines
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderPlace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)";//otype is order type like we have 3 kinds of order ,lab test and book appointment,buy medicines
        sqLiteDatabase.execSQL(qry3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //adding to database
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    //now this fn needs to be called in register activity

    //one for login checking
    public int login(String username,String password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]= password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password = ?",str);
        //if login record found
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    //add to cart adding to database
    public void addToCart(String username,String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    //check cart checking
    public int checkCart(String username,String product){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]= product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where username=? and product = ?",str);
        //if login record found
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    //remove from cart ,delete from database
    public void removeCart(String username,String otype){
        String str[]=new String[2];
        str[0]=username;
        str[1]= otype;
        SQLiteDatabase db = getReadableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        String str[]=new String[2];
        str[0]=username;
        str[1]= otype;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where username= ? and otype = ?",str);
        //if login record found
        if (c.moveToFirst()){//since anyone can have more than 1 element in cart
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username,String fullname,String address,String contact, int pincode, String date, String time, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
//now booking is done but we need to confirm it under order details
public ArrayList getOrderData(String username){
    ArrayList<String> arr = new ArrayList<>();
    String str[]=new String[1];
    str[0]=username;
    SQLiteDatabase db = getReadableDatabase();
    Cursor c=db.rawQuery("select * from orderPlace where username= ?",str);
    //if login record found
    if (c.moveToFirst()){//since anyone can have more than 1 element in cart
        do{
            arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));//any variable can be taken for concatinating ,once array is made then we can split the array based on this special character
        }while (c.moveToNext());
    }
    db.close();
    return arr;
}
public int checkAppointmentExists(String username,String fullName,String address,String contactno,String date, String time){
    int result=0;
    String str[]=new String[6];
    str[0]=username;
    str[1]= fullName;
    str[2]=address;
    str[3]=contactno;
    str[4]=date;
    str[5]= time;
    SQLiteDatabase db = getReadableDatabase();
    Cursor c=db.rawQuery("select * from orderPlace where username=? and fullName = ? and address = ? and contactno = ? and date = ? and time = ?",str);
    //if login record found
    if (c.moveToFirst()){
        result=1;
    }
    db.close();
    return result;
}
}
