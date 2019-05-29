package br.com.fiap.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarPrincipal = findViewById(R.id.toolbarPrincipal);
        toolbarPrincipal.setLogo(R.drawable.ic_launcher_background);
        toolbarPrincipal.setTitle("Inicio");
        toolbarPrincipal.setSubtitle("Subtitulo");

        setSupportActionBar(toolbarPrincipal); //responsável por exibir a toolbar no layout

        //colocando o icone de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mniSobre:
                Toast.makeText(this, "Clicou no menu sobre", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mniSair:
                finish();
                break;
            case android.R.id.home: //seta voltar
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}