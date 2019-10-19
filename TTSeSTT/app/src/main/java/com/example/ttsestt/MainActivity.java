package com.example.ttsestt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(new Locale("pt-BR"));
                Toast.makeText(
                        MainActivity.this,
                        "TTS Iniciado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });
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
                "O Ricardo é lindo mesmo!"
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

                tts.speak(
                        primeiro,
                        TextToSpeech.QUEUE_FLUSH,
                        null);
            }
        }
    }
}
