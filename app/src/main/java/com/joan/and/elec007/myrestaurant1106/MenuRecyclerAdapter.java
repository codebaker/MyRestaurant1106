package com.joan.and.elec007.myrestaurant1106;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        this.arrayList = new ArrayList<>();

        mdb = db.getWritableDatabase();
        sql = "SELECT * FROM MENU";
        cursor = mdb.rawQuery(sql,null);

        arrayList = new ArrayList<>();
        Menu menu;
        String seq,name;int cost;

        while(cursor.moveToNext()){
            seq = cursor.getString(cursor.getColumnIndex("menu_seq"));
            name = cursor.getString(cursor.getColumnIndex("menu_name"));
            cost = cursor.getInt(cursor.getColumnIndex("menu_cost"));

            menu = new Menu(seq,name,cost);
            arrayList.add(menu);
        }
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
        holder.textItemCount.setText("0");
        final String name = menu.getMenuName();
        holder.btnItemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //minus button 클릭
                //order seq 텍스트 에디터 확인하고 order seq가 없으면 새로 insert 한다.
                Toast.makeText(v.getContext(),name+"minus",LENGTH_SHORT).show();
            }
        });
        holder.btnItemPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),name+"plus",LENGTH_SHORT).show();
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
        Button btnItemPlus,btnItemMinus;

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
