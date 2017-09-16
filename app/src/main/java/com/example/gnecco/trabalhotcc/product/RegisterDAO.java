package com.example.gnecco.trabalhotcc.product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.gnecco.trabalhotcc.database.MeuDB;

import java.util.ArrayList;
import java.util.List;

public class RegisterDAO {

    private static final String DATABASE_NAME = "TccDataBase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Veiculos";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + "(Modelo,Valor,Ano) values (?,?,?)";


    public RegisterDAO(Context context) {
        this.context = context;
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(String modelo, String valor, String Ano) {
        try {
            this.insertStmt.bindString(1, modelo);
            this.insertStmt.bindString(2, valor);
            this.insertStmt.bindString(3, Ano);
            return this.insertStmt.executeInsert();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<RegisterModel> retornarListaProdutos() {
        String sql = "select * from " + TABLE_NAME;
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getReadableDatabase();
        List<RegisterModel> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String modelo = cursor.getString(1);
                String valor = cursor.getString(2);
                String ano = cursor.getString(3);
                storeProducts.add(new RegisterModel(id, modelo, valor, ano));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }


    public void deleteCadastro(int id) {
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "ID" + "    = ?", new String[]{String.valueOf(id)});
    }

    public void updateCadastro(RegisterModel cadastro) {
        ContentValues values = new ContentValues();
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getWritableDatabase();
        values.put("Modelo", cadastro.getModelo());
        values.put("Valor", cadastro.getValor());
        values.put("Ano", cadastro.getAno());
        db.update(TABLE_NAME, values, "ID" + "    = ?", new String[]{String.valueOf(cadastro.getId())});
    }


    public RegisterModel findProduct(Integer id) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + "ID" + " = " + id;
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getWritableDatabase();
        RegisterModel mCadastro = null;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            int id_ = Integer.parseInt(cursor.getString(0));
            String modelo = cursor.getString(1);
            String valor = cursor.getString(2);
            String ano = cursor.getString(3);
            mCadastro = new RegisterModel(id, modelo, valor, ano);

        }
        cursor.close();
        return mCadastro;
    }

    public void close() {
        this.db.close();
    }
}

