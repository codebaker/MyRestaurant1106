package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private DBOpenHelper dbHelper;
    private SQLiteDatabase mdb;
    Cursor cursor;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ((Button)findViewById(R.id.btnCheck)).setOnClickListener(this);

        dbHelper = DBOpenHelper.getInstance(this);

        setMenu();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,MainActivity.class).putExtras(bundle);
        startActivityForResult(intent,RESULT_FIRST_USER);


    }
    private void setMenu(){
        mdb = dbHelper.getWritableDatabase();
        sql = "SELECT * FROM MENU";
        cursor = mdb.rawQuery(sql,null);
        List<Menu> menuList = new ArrayList<>();

        String[] MENUS = new String[5];
        int i=MENUS.length-1;

        String seq,name;int cost;
        while(cursor.moveToNext()){
            seq = cursor.getString(cursor.getColumnIndex("menu_seq"));
            name = cursor.getString(cursor.getColumnIndex("menu_name"));
            cost = cursor.getInt(cursor.getColumnIndex("menu_cost"));

            menuList.add(new Menu(seq,name,cost));
            MENUS[i] = seq + " : " + name + " : " + String.valueOf(cost);
            --i;
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.listview_order,MENUS);
        ListView listView = findViewById(R.id.listViewMenu);
        listView.setAdapter(arrayAdapter);
    }
}
