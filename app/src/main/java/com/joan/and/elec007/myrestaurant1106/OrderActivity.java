package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    private DBOpenHelper dbHelper;
    private SQLiteDatabase mdb;
    Cursor cursor;
    String sql;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ((Button)findViewById(R.id.btnCheck)).setOnClickListener(this);

        dbHelper = DBOpenHelper.getInstance(this);

        setTableseat();
        setMenu();
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

            Menu menu = new Menu(seq,name,cost);
            menuList.add(menu);
            MENUS[i] = menu.toString();
            --i;
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.listview_order,MENUS);
        ListView listView = findViewById(R.id.listViewMenu);
        listView.setAdapter(arrayAdapter);
    }

    private void setTableseat() {
        mdb = dbHelper.getWritableDatabase();
        sql = "SELECT * FROM TABLESEAT";
        cursor = mdb.rawQuery(sql,null);

        spinner = findViewById(R.id.spinnerTableseat);
        List<String> tableList = new ArrayList<>();

        String seq,name;
        while(cursor.moveToNext()){
            seq = cursor.getString(cursor.getColumnIndex("tableseat_seq"));
            name = cursor.getString(cursor.getColumnIndex("tableseat_name"));
            Tableseat tableseat = new Tableseat(seq,name);
            tableList.add(tableseat.toString());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tableList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,MainActivity.class).putExtras(bundle);
        startActivityForResult(intent,RESULT_FIRST_USER);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
