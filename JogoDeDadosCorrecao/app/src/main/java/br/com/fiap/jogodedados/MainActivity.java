package br.com.fiap.jogodedados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //
    // Criamos as variáveis que representarão os componentes
    // que estão no XML utilizado por esta Activity
    //
    ImageView imgJogador1;
    ImageView imgJogador2;

    //
    // Criamos um array representando as imagens do projeto
    //
    // Lembrando que a classe R é gerada automaticamente e
    // tudo que estiver mapeado através dela será do tipo inteiro
    //
    int[] imagens = {
       R.drawable.d1,
       R.drawable.d2,
       R.drawable.d3,
       R.drawable.d4,
       R.drawable.d5,
       R.drawable.d6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Informa qual será o XML de layout utilizado
        setContentView(R.layout.activity_main);

        //
        // Inicializa as variáveis criadas acima com as atributos
        // setados no XML. Identifica cada componente pelo atributo ID.
        //
        imgJogador1 = findViewById(R.id.imgJogador1);
        imgJogador2 = findViewById(R.id.imgJogador2);
    }

    /**
     * Método atrelado no onClick do botão jogar
     *
     * Sorteia as imagens e verifica quem é o ganhador
     * dando o feedback para o usuário através do Toast.
     *
     * @param view
     */
    public void jogar(View view) {
        // Classe responsável para sortear números
        Random r = new Random();

        // Sorteia um número para cada jogador
        int a = r.nextInt(imagens.length); //Jogador 1
        int b = r.nextInt(imagens.length); //Jogador 2

        // Troca pelas imagens do array na posição sorteada
        imgJogador1.setImageResource( imagens[a] );
        imgJogador2.setImageResource( imagens[b] );

        // Verifica quem ganhou ou se deu empate.
        if (a > b) {
            Toast.makeText(this, "Jogador 1 venceu!", Toast.LENGTH_SHORT).show();
        } else if (b > a) {
            Toast.makeText(this, "Jogador 2 venceu!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Deu empate!", Toast.LENGTH_SHORT).show();
        }
    }
}
