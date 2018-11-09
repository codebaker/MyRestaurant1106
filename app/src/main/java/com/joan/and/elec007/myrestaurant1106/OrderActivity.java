package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private DBOpenHelper dbHelper;
    private SQLiteDatabase mdb;
    Cursor cursor;
    String sql;
    Spinner spinner;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MenuRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ((Button)findViewById(R.id.btnCheck)).setOnClickListener(this);

        dbHelper = DBOpenHelper.getInstance(this);

        setTableseat();
        setMenuRecyclerView();
        //setMenu();
    }

    private void setMenuRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMenuList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MenuRecyclerAdapter(dbHelper);
        recyclerView.setAdapter(adapter);
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
        //spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,MainActivity.class).putExtras(bundle);
        startActivityForResult(intent,RESULT_FIRST_USER);
    }
}
