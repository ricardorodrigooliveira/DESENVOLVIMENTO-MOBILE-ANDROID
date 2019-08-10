package br.com.ricardooliveira.exemplosdesp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    CheckBox chkManterConectado;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario         = findViewById(R.id.edtUsuario);
        edtSenha           = findViewById(R.id.edtSenha);
        chkManterConectado = findViewById(R.id.chkManterConectado);

        sp = getPreferences(MODE_PRIVATE);

        boolean conectado = sp.getBoolean("conectado", false);

        if (conectado) {
            String usuario = sp.getString("usuario", null);
            String senha = sp.getString("senha", null);

            edtUsuario.setText(usuario);
            edtSenha.setText(senha);
            chkManterConectado.setChecked(true);
        }
    }

    public void login(View view) {
        String usuario = edtUsuario.getText().toString().trim();
        String senha = edtSenha.getText().toString();
        boolean manterConectado = chkManterConectado.isChecked();

        if (usuario.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Informe corretamente os dados de acesso!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (usuario.equals("fiap") && senha.equals("fiap")){

            SharedPreferences.Editor edit = sp.edit();

            if (manterConectado){

                edit.putString("usuario", usuario);
                edit.putString("senha", senha);
                edit.putBoolean("conectado", manterConectado);

            }else{
                edit.clear(); //remove tudo
                // ou
                /*edit.remove("usuario");
                edit.remove("senha");
                edit.remove("conectado");*/
            }
            edit.commit();

            Toast.makeText(this, "Usuário logado com sucesso!",Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();

    }
}
