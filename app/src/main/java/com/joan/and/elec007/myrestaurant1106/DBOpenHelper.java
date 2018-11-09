package com.joan.and.elec007.myrestaurant1106;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static DBOpenHelper instance;
    //private static SQLiteDatabase mdb;

    public static final String DB_NAME = "restaurant.db";
    private static final SQLiteDatabase.CursorFactory FACTORY = null;
    public static final int VERSION = 1;

    public static DBOpenHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBOpenHelper(context);
            //mdb = instance.getWritableDatabase();
        }
        return instance;
    }

    private DBOpenHelper(Context context) {
        super(context, DB_NAME, FACTORY, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE menu" +
                        " (menu_seq TEXT PRIMARY KEY" +
                        ", menu_name TEXT" +
                        ", menu_cost INTEGER" +
                        //", menu_available TEXT, " +
                        //", menu_regdate TEXT, " +
                        //", menu_moddate" +
                        ")";
        db.execSQL(sql);

        sql = "CREATE TABLE tableseat" +
                " (tableseat_seq Text PRIMARY KEY" +
                ", tableseat_name TEXT" +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE order_list" +
                " (ordered_seq TEXT PRIMARY KEY" +
                ", ordered_count INTEGER" +
                ", ordered_date TEXT" +
                ", tableseat_seq TEXT NOT NULL" +
                ", menu_seq TEXT NOT NULL" +
                ", paid_flag TEXT" +
                ")";
        db.execSQL(sql);

        //초기에 메뉴와 테이블 테이터 5개씩 자동 삽입
        autoInsert(db);
    }

    private void autoInsert(@NonNull SQLiteDatabase db){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String seq;
        //0번 테이블은 Takeout
        db.execSQL("INSERT INTO TABLESEAT VALUES(" + format.format(new Date()) + ", 'T0')");
        for (int i = 1; i < 6; i++) {
            int cost = i*1000;
            seq = format.format(new Date());
            db.execSQL("INSERT INTO MENU VALUES(" + seq + ",'menu " + i + "',"+ cost + ")");
            db.execSQL("INSERT INTO TABLESEAT VALUES( " + seq + ", 'T" + i +"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE menu");
        db.execSQL("DROP TABLE orders");
        db.execSQL("DROP TABLE tableseat");
        onCreate(db);
    }

    public static class OderRecyclerAdapter {
        public static class OderItemViewHolder extends RecyclerView.ViewHolder{
            TextView textItemMenu;
            EditText textItemCount;
            Button btnItemPlus,btnItemMinus;

            public OderItemViewHolder(@NonNull View itemView) {
                super(itemView);
                textItemMenu = (TextView)itemView.findViewById(R.id.textItemMenu);
            }
        }


    }
}
