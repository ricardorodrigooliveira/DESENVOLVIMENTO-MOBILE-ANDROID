package br.com.ricardooliveira.exemplodesqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MeuDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MeuDB(this);

        /*Cliente cliente = new Cliente();

        cliente.setNome("Ricardo");
        cliente.setEmail("ricardo@email.com");

        db.inserir(cliente);

        Log.d("CRUD", cliente.getId() + " " + cliente.getNome());*/

        /*List<Cliente> clientes = db.listarTodos();

        for(Cliente cliente : clientes){
            Log.d("CRUD", cliente.getId() + " " + cliente.getNome());
            cliente.setEmail("rodrigo@email.com");

            db.atualizar(cliente);
            Log.d("CRUD", cliente.getId() + " " + cliente.getNome() + " " + cliente.getEmail());
        }*/

        db.apagar(1);

        Cliente cliente2 = db.selecionar(1);
        Log.d("CRUD", cliente2.getId() + " " + cliente2.getNome());*/


    }

    public void inserir(View view) {
        Intent it = new Intent(this, FormCliente.class);
        startActivity(it);
    }
}
