package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btnOrderTotal)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnManageMenus)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnManageTables)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnNewOrder)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miMenuManage:
                startIntent(EditMenuActivity.class);
                break;
            case R.id.miTableseatManage:
                startIntent(EditTableActivity.class);
                break;
            case R.id.miCalculatedList:
                startIntent(OrderTotalActivity.class);
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNewOrder:
                startIntent(OrderActivity.class);
                break;
        }
    }

    private void startIntent(Class<?> activity){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,activity).putExtras(bundle);
        startActivityForResult(intent,REQUEST_CODE);
    }
}
