package br.com.fiap.trocarimagens;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img1;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
    }

    public void trocar(View view) {
        Drawable aux = img1.getDrawable();

        img1.setImageDrawable( img2.getDrawable() );
        img2.setImageDrawable( aux );
    }
}
