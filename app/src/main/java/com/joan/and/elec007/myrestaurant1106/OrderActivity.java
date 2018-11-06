package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ((Button)findViewById(R.id.btnGotoMain)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,MainActivity.class).putExtras(bundle);
        startActivityForResult(intent,RESULT_FIRST_USER);
    }
}
