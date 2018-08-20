package com.karmaestudio.com.reproductormusica_geek;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playPause, btnRepetir;
    MediaPlayer mp;
    ImageView portada;
    int repetir =2, posicion = 0;
    MediaPlayer vectormp [] = new MediaPlayer [3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPause = findViewById(R.id.btnPlay);
        btnRepetir = findViewById(R.id.btnRepetir);
        portada = findViewById(R.id.ivPortada);

        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);
    }

    public void PlayPause (View view){
        if (vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            playPause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this,"Pausa", Toast.LENGTH_SHORT).show();
        } else {
            vectormp[posicion].start();
            playPause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this,"Reproducir", Toast.LENGTH_SHORT).show();
        }
    }

    public void Detener (View view){
        if (vectormp[posicion] != null){
            vectormp[posicion].stop();
            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create(this, R.raw.sound);
            vectormp[2] = MediaPlayer.create(this, R.raw.tea);
            posicion = 0;
            portada.setImageResource(R.drawable.portada1);
            playPause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this,"Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void Repetir (View view){
        if (repetir == 1){
            btnRepetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
            repetir = 2;
        } else {
            btnRepetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }
    }

    public void Siguiente (View view){
        if (posicion < vectormp.length -1){
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].pause();
                posicion++;
                vectormp[posicion].start();
            } else {
                                posicion++;
            }
        } else {
            Toast.makeText(this,"No hay más canciones", Toast.LENGTH_SHORT).show();
        }

        if (posicion == 0){
            portada.setImageResource(R.drawable.portada1);
        } else if (posicion ==1){
            portada.setImageResource(R.drawable.portada2);
        } else if (posicion ==2){
            portada.setImageResource(R.drawable.portada3);
        }


    }

    public void Anterior (View view){
        if (posicion > 0){
            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].pause();
                posicion--;
                vectormp[posicion].start();
            } else {
                posicion--;

            }
        } else {
            Toast.makeText(this,"Ya estás en la primera canción", Toast.LENGTH_SHORT).show();
        }

        if (posicion == 0){
            portada.setImageResource(R.drawable.portada1);
        } else if (posicion ==1){
            portada.setImageResource(R.drawable.portada2);
        } else if (posicion ==2){
            portada.setImageResource(R.drawable.portada3);
        }
    }
}
