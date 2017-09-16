package com.example.gnecco.trabalhotcc.product;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnecco.trabalhotcc.R;

public class RegisterViewHolder extends RecyclerView.ViewHolder {

    public TextView modelo;
    public ImageView deleteItem;
    public  ImageView editItem;

    public RegisterViewHolder(View itemView) {
        super(itemView);
        modelo = (TextView)itemView.findViewById(R.id.tvModeloLista);
        editItem = (ImageView)itemView.findViewById(R.id.ivEditItem);
        deleteItem = (ImageView)itemView.findViewById(R.id.ivDeleteItem);
    }
}