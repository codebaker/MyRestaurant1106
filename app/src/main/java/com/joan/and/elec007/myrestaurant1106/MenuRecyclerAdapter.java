package com.joan.and.elec007.myrestaurant1106;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder> {
    private DBOpenHelper db;
    private SQLiteDatabase mdb;
    private Cursor cursor;
    private String sql;

    ArrayList<Menu> arrayList = null;

    public MenuRecyclerAdapter(DBOpenHelper db){
        this.db=db;
        this.arrayList = db.selectMenuTable();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_menu,parent,false);
        MenuViewHolder viewHolder = new MenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = arrayList.get(position);
        holder.textMenuSeq.setText(menu.getMenuSeq());
        holder.textMenuCost.setText(String.valueOf(menu.getMenuCost()));
        holder.textItemMenu.setText(menu.getMenuName());


        holder.btnItemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //minus button 클릭
                //order seq 텍스트 에디터 확인하고 order seq가 없으면 새로 insert 한다.
            }
        });
        holder.btnItemPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView textItemMenu, textMenuSeq, textMenuCost;
        EditText textItemCount;
        ImageButton btnItemPlus,btnItemMinus;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            textMenuSeq = itemView.findViewById(R.id.textMenuSeq);
            textMenuCost = itemView.findViewById(R.id.textMenuCost);
            textItemMenu = itemView.findViewById(R.id.textItemMenu);
            textItemCount = itemView.findViewById(R.id.textItemCount);
            btnItemPlus = itemView.findViewById(R.id.btnItemPlus);
            btnItemMinus = itemView.findViewById(R.id.btnItemMinus);

        }
    }
}
