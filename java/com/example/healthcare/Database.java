package com.example.healthcare;

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
        String q1="create table users(un text,em text,pw text)";
        sqLiteDatabase.execSQL(q1);

        String q2="create table cart(un text,pd text,price float,otype text)";
        sqLiteDatabase.execSQL(q2);

        String q3 = "create table orderplace (un text, fullname text, address text, contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(q3);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  void register(String un,String em,String pw)
    {
        ContentValues cv=new ContentValues();
        cv.put("un",un);
        cv.put("em",em);
        cv.put("pw",pw);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int login(String un,String pw)
    {
        int result=0;
        String str[]=new String[2];
        str[0]=un;
        str[1]=pw;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where un=? and pw=?",str);
        if(c.moveToFirst())
        {
            result=1;
        }
        return result;
    }

    public  void addcart(String un,String pd,float price,String otype)
    {
        ContentValues cv=new ContentValues();
        cv.put("un",un);
        cv.put("pd",pd);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    public int checkcart(String un,String pd)
    {
        int result=0;
        String str[]=new String[2];
        str[0]=un;
        str[1]=pd;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where un=? and pd=?",str);
        if(c.moveToFirst())
        {
            result=1;
        }
        db.close();
        return result;
    }

    public void removecart(String un,String otype)
    {
        String str[]=new String[2];
        str[0]=un;
        str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","un=? and otype=?",str);
        db.close();
    }

    public ArrayList getCartData(String un,String otype)
    {
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=un;
        str[1]=otype;
        Cursor c=db.rawQuery("select * from cart where un=? and otype=?",str);
        if(c.moveToFirst()){
            do{
                String pd=c.getString(1);
                String price=c.getString(2);
                arr.add(pd+"$"+price);

            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }


    public void addOrder(String un,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype)
    {

        ContentValues cv=new ContentValues();
        cv.put("un",un);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();


    }

    public ArrayList getOrderData(String un)
    {
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[1];
        str[0]=un;
        Cursor c=db.rawQuery("Select * from orderplace where un=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }
            while(c.moveToNext());

        }
        db.close();
        return arr;
    }


    public int checkAppointmentExists(String un,String fullname,String address,String contact,String date,String time)
    {
        int result=0;
        String str[]=new String[6];
        str[0]=un;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from orderplace where un=? and fullname=? and address=? and contactno=? and date=? and time=?",str);
        if(c.moveToFirst()){
            result=1;

        }
        db.close();
        return result;
    }

}
