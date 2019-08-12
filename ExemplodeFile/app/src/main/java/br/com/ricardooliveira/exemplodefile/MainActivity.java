package br.com.ricardooliveira.exemplodefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText edtObservacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtObservacoes = findViewById(R.id.edtObservacoes);
    }

    public void salvar(View view) throws FileNotFoundException {
        String txt = edtObservacoes.getText().toString().trim();
        if (txt.isEmpty()){
            Toast.makeText(this, getString(R.string.informe_um_texto_abaixo),Toast.LENGTH_LONG).show();
        }

        try {
            FileOutputStream fos = openFileOutput("observacoes.txt", MODE_PRIVATE);
            fos.write(txt.getBytes());
            fos.close();

            Toast.makeText(this, getString(R.string.arquivo_criado_com_sucesso), Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ler(View view) {
        try {
            FileInputStream fis = openFileInput("observacoes.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String txt  = br.readLine();
            fis.close();

            Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
