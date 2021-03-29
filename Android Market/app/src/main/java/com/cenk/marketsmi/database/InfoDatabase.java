package com.cenk.marketsmi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqllite_database_info";//database adı



    private static final String TABLE_NAME = "bilgi";
    private static String ID = "id";
    private static String NAME = "name";
    private static String ADDRESS = "address";
    private static String NUMBER = "number";

    public InfoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  // Databesi oluşturuyoruz.Bu methodu biz çağırmıyoruz. Databese de obje oluşturduğumuzda otamatik çağırılıyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + ADDRESS + " TEXT,"
                + NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void addressDelete(int id) { //id si belli olan row u silmek için

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void addressAdd(String name, String address, String number) {
        //kitapEkle methodu ise adı üstünde Databese veri eklemek için
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(ADDRESS, address);
        values.put(NUMBER, number);

        db.insert(TABLE_NAME, null, values);
        db.close(); //Database Bağlantısını kapattık*/
    }



    public ArrayList<HashMap<String, String>> getAddress() {

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> kitaplist = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                kitaplist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return kitaplist;
    }

    public void resetTables() {
        //Bunuda uygulamada kullanmıyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void addressEdit(String name, String address, String number, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(ADDRESS, address);
        values.put(NUMBER, number);
        // updating row
        db.update(TABLE_NAME, values, ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }


}