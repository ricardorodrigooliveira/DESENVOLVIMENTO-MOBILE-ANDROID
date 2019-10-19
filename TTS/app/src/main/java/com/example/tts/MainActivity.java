package com.example.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

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

                tts.speak(
                        "Exemplo de fala pelo android",
                         TextToSpeech.QUEUE_FLUSH,
                        null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
