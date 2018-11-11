package com.joan.and.elec007.myrestaurant1106;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder>{
    private DBOpenHelper db;
    private SQLiteDatabase mdb;
    private Cursor cursor;
    private String sql;

    ArrayList<Menu> arrayList = null;
//    MenuViewHolder holder = null;
//    Menu menu= null;


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
    public void onBindViewHolder(@NonNull MenuViewHolder holder,int position) {
//        this.holder = h;
        Menu menu = arrayList.get(position);
        holder.textMenuSeq.setText(menu.getMenuSeq());
        holder.textMenuCost.setText(String.valueOf(menu.getMenuCost()));
        holder.textItemMenu.setText(menu.getMenuName());

        /*holder.btnItemMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //minus button 클릭
                if (!"".equals(holder.textItemCount.getText().toString())){
                    int count = Integer.parseInt(holder.textItemCount.getText().toString());
                    if (count > -1){
                        String cnt = String.valueOf(count - 1);
                        holder.textItemCount.setText(cnt);
                    }
                }
            }
        });
        holder.btnItemPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //minus button 클릭
                int count=0;
                if (!"".equals(holder.textItemCount.getText().toString())){
                    count = Integer.parseInt(holder.textItemCount.getText().toString());
                }
                String cnt = String.valueOf(count + 1);
                holder.textItemCount.setText(cnt);
            }
        });*/

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

            btnItemMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //minus button 클릭
                    if (!"".equals(textItemCount.getText().toString())){
                        int count = Integer.parseInt(textItemCount.getText().toString());
                        if (count > 0){
                            String cnt = String.valueOf(count - 1);
                            textItemCount.setText(cnt);
                        }
                    }
                }
            });

            btnItemPlus.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //minus button 클릭
                    int count=0;
                    if (!"".equals(textItemCount.getText().toString())){
                        //count를 db에서 가져올것
                        count = Integer.parseInt(textItemCount.getText().toString());
                    }
                    String cnt = String.valueOf(count + 1);
                    //db에 바로 저장할것.
                    textItemCount.setText(cnt);
                }
            });

        }
    }
}
