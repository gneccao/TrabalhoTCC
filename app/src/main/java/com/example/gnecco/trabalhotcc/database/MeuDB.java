package com.example.gnecco.trabalhotcc.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuDB extends SQLiteOpenHelper {

    public MeuDB(Context context) {
        super(context,"TccDataBase", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE Login (ID INTEGER PRIMARY KEY AUTOINCREMENT, Usuario TEXT NOT NULL UNIQUE, Senha TEXT NOT NULL);";
        final String sql2 = "CREATE TABLE Veiculos (ID INTEGER PRIMARY KEY AUTOINCREMENT, Modelo TEXT NOT NULL, Valor TEXT NOT NULL, Ano TEXT NOT NULL);";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        db.execSQL("DROP TABLE IF EXISTS Veiculos");
        onCreate(db);
    }
}