package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOrderTotal:
                startIntent(OrderTotalActivity.class);
                break;
            case R.id.btnManageMenus:
                startIntent(EditMenuActivity.class);
                break;
            case R.id.btnManageTables:
                startIntent(EditTableActivity.class);
                break;
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
