package br.com.ricardooliveira.exemplodesqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormCliente extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    MeuDB meuDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        meuDB = new MeuDB(this);
    }

    public void salvar(View view) {
        String nome = edtNome.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if(nome.isEmpty() || email.isEmpty()){
            Toast.makeText(this, getString(R.string.valida_campos_nome_email), Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);

        meuDB.inserir(cliente);

        Toast.makeText(this, getString(R.string.sucesso_insere_cliente), Toast.LENGTH_SHORT).show();

        finish();
    }
}
