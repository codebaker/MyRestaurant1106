package com.joan.and.elec007.myrestaurant1106;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder> implements View.OnClickListener{

    ArrayList<Menu> menuList = null;

    public MenuRecyclerAdapter(ArrayList<Menu> arrayList){
        this.menuList = new ArrayList<>();
        this.menuList = arrayList;
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
        Menu menu = menuList.get(position);
        holder.textMenuSeq.setText(menu.getMenuName());
        holder.textMenuCost.setText(menu.getMenuName());
        holder.textItemMenu.setText(menu.getMenuName());
        holder.textItemCount.setText("0");
        holder.btnItemMinus.setOnClickListener(this);
        holder.btnItemPlus.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public void onClick(View v) {

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
