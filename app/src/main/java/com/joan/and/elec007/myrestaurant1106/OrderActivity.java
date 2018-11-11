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

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper dbHelper;
    Spinner spinner;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MenuRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dbHelper = DBOpenHelper.getInstance(this);
        Bundle bundle = getIntent().getExtras();
        /*if(!bundle.isEmpty() && "new".equals(bundle.getString("orderStatus"))){
          // 새 오더임을 인텐트로 전해받음.
        }*/

        ((Button)findViewById(R.id.btnCheck)).setOnClickListener(this);



        setTableseatSpinner();
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

    private void setTableseatSpinner() {
        spinner = findViewById(R.id.spinnerTableseat);
        ArrayList<String> arrayList = dbHelper.selectTableseatTable();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);
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
