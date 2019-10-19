package com.example.stt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gravar(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        intent.putExtra(
                RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName()
        );
        intent.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Fale algo legal para mim!"
        );
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                "pt-BR"
        );
        intent.putExtra(
                RecognizerIntent.EXTRA_MAX_RESULTS,
                10
        );
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == RESULT_OK ){
            ArrayList<String> palavras = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if ( palavras.size() > 0 ) {
                String primeiro = palavras.get(0);
                Toast.makeText(this, primeiro, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
