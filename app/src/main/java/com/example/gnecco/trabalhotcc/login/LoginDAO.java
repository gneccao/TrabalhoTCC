package com.example.gnecco.trabalhotcc.login;


import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.gnecco.trabalhotcc.database.MeuDB;

public class LoginDAO {

    private static final String DATABASE_NAME = "TccDataBase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Login";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + "(usuario,senha) values (?,?)";

    public LoginDAO(Context context) {
        this.context = context;
        MeuDB openHelper = new MeuDB(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(String usuario, String senha) {
        try {
            this.insertStmt.bindString(1, usuario);
            this.insertStmt.bindString(2, senha);
            return this.insertStmt.executeInsert();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean IsValid(String usuario, String senha) {
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME, "Usuario=? AND Senha=?", new String[]{usuario, senha}) > 0;
    }
}