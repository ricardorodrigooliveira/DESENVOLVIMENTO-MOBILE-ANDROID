package br.com.ricardooliveira.exemplodesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MeuDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MeuDB";
    public static final int DATABASE_VERSION = 1;

    public MeuDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE cliente (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL," +
                " email TEXT NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nome", cliente.getNome());
        cv.put("email", cliente.getEmail());

        db.insert("cliente", null, cv);
    }

    public void atualizar(Cliente cliente){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nome", cliente.getNome());
        cv.put("email", cliente.getEmail());

        db.update("cliente", cv, "id = ?", new String[]{ String.valueOf(cliente.getId())});
    }

    public void apagar(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("cliente", "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("cliente",
                                  new String[]{"id", "nome", "email"},
                                 null,
                                 null,
                                 null,
                                 null,
                                 "nome");

        cursor.moveToFirst();
        do {
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setEmail(cursor.getString(2));

            clientes.add(cliente);

        } while (cursor.moveToNext());

        return clientes;
    }

    public Cliente selecionar(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("cliente",
                 new String[]{"id","nome","email"},
                "id = ?",
                 new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setEmail(cursor.getString(2));

            return  cliente;
        } // fim if

        return null;
    }

}
