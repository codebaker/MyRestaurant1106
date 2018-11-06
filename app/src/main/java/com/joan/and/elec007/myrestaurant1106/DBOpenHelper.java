package com.joan.and.elec007.myrestaurant1106;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static DBOpenHelper INSTANCE;
    private static SQLiteDatabase mDB;

    public static final String DB_NAME = "RESTAURANT.DB";
    private static final SQLiteDatabase.CursorFactory FACTORY = null;
    public static final int VERSION = 1;

    public static DBOpenHelper getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new DBOpenHelper(context);
            mDB = INSTANCE.getWritableDatabase();
        }
        return INSTANCE;
    }

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, FACTORY, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MENU " +
                    "(menu_seq Text PRIMARY KEY, " +
                    "menu_name TEXT, " +
                    "menu_cost Integer)");

        db.execSQL("CREATE TABLE TABLESEAT " +
                    "(tableseat_seq Text PRIMARY KEY, " +
                    "tableseat_name TEXT)");

        db.execSQL("CREATE TABLE ORDERED_LIST " +
                    "(ordered_seq Text PRIMARY KEY, " +
                    "ordered_count Integer, " +
                    "ordered_date text,closed_flag text, " +
                    "tableseat_seq text NOT NULL," +
                    "tableseat_name text, " +
                    "menu_seq text NOT NULL, " +
                    "menu_name text, " +
                    "menu_cose Integer)");

        //초기에 메뉴와 테이블 테이터 5개씩 자동 삽입
        autoInsert(db);
    }

    private void autoInsert(SQLiteDatabase db){
        for (int i = 0; i < 5; i++) {
            int cost = i*1000;
            db.execSQL("INSERT INTO MENU VALUES( null,'menu " + i + "',"+ cost + ")");
            db.execSQL("INSERT INTO TABLESEAT VALUES( null, 'menu " + i +"')");
        }
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE menu");
        db.execSQL("DROP TABLE orders");
        db.execSQL("DROP TABLE tableseat");
        onCreate(db);
    }
}
