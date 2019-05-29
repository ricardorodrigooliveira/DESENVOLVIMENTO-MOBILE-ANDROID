package br.com.fiap.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lstContatos;

    //Criar lista de contatos
    Contato[] contatos = {
        new Contato("João","11 96905-1743","Ocupado",R.drawable.p2),
        new Contato("Maria","11 96905-1744","Disponivel",R.drawable.p1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstContatos = findViewById(R.id.lstContatos);

        ContatoAdapter adapter = new ContatoAdapter(this, contatos);

        lstContatos.setAdapter(adapter);
    }
}
