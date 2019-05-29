package br.com.ricardooliveira.jogodedados;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

class Sorteio{
    private String nome;
    private int num;

    public int geraNumero() {
        Random sort = new Random();
        int r = sort.nextInt(6)+1;
        return r;
    }

    public String numeroTexto(int num){
        switch (num){
            case 1:
                this.nome = "um";
                break;
            case 2:
                this.nome = "dois";
                break;
            case 3:
                this.nome = "tres";
                break;
            case 4:
                this.nome = "quatro";
                break;
            case 5:
                this.nome = "cinco";
                break;
            default:
                this.nome = "seis";
                break;
        }
        return this.nome;
    }
}

public class MainActivity extends AppCompatActivity {

    ImageView imgJogador1, imgJogador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgJogador1 = findViewById(R.id.imgJogador1);
        imgJogador2 = findViewById(R.id.imgJogador2);
    }

    public void jogar(View view) {

        Sorteio dado1 = new Sorteio();
        Sorteio dado2 = new Sorteio();

        int r1 = dado1.geraNumero();
        int r2 = dado2.geraNumero();
        String n1 = dado1.numeroTexto(r1);
        String n2 = dado1.numeroTexto(r2);

        int id1 = (getResources().getIdentifier(n1 , "drawable", getPackageName()));
        imgJogador1.setImageResource(id1);
        int id2 = (getResources().getIdentifier(n2 , "drawable", getPackageName()));
        imgJogador2.setImageResource(id2);

        if(r1 > r2){
            Toast.makeText(this, "Jogador 1 venceu!", Toast.LENGTH_LONG).show();
        }
        if(r1 < r2){
            Toast.makeText(this, "Jogador 2 venceu!", Toast.LENGTH_LONG).show();
        }
        if(r1 == r2){
            Toast.makeText(getApplicationContext(), "Empate!!! Jogue novamente", Toast.LENGTH_LONG).show();
        }
    }
}