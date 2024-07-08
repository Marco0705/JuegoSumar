package es.ifp.juegosuma;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;
    protected int resultado = 0;
    protected MediaPlayer mpCorrect;
    protected MediaPlayer mpIncorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos los elementos de la interfaz
        label1 = (TextView) findViewById(R.id.label1_start);
        label2 = (TextView) findViewById(R.id.label2_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        boton2 = (Button) findViewById(R.id.boton2_start);
        boton3 = (Button) findViewById(R.id.boton3_start);

        // Creamos los reproductores de audio para respuestas correctas e incorrectas
        mpCorrect = MediaPlayer.create(this, R.raw.correct);
        mpIncorrect = MediaPlayer.create(this, R.raw.incorrect);

        // Generamos una nueva suma
        nuevaSuma();

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(Integer.parseInt(boton1.getText().toString()));
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(Integer.parseInt(boton2.getText().toString()));
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respuesta(Integer.parseInt(boton3.getText().toString()));
            }
        });
    }

    // Método para generar una nueva suma con números aleatorios
    public void nuevaSuma() {
        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(20);
        int num4 = random.nextInt(20);

        // Mostramos la operación en la etiqueta de texto
        label2.setText(String.valueOf(num1) + " + " + String.valueOf(num2));
        resultado = num1 + num2;

        // Creamos una lista de botones para mezclar las opciones de respuesta
        List<Button> botones = new ArrayList<>();
        botones.add(boton1);
        botones.add(boton2);
        botones.add(boton3);

        // Mezclamos los botones
        Collections.shuffle(botones);

        // Asignamos las opciones de respuesta a los botones mezclados
        botones.get(0).setText(String.valueOf(resultado));
        botones.get(1).setText(String.valueOf(num3));
        botones.get(2).setText(String.valueOf(num4));
    }

    // Método para reproducir el sonido de respuesta correcta
    public void sonidoCorrecto() {
        if (mpCorrect != null) {
            mpCorrect.start();
        }
    }

    // Método para reproducir el sonido de respuesta incorrecta
    public void sonidoIncorrecto() {
        if (mpIncorrect != null) {
            mpIncorrect.start();
        }
    }

    // Método para manejar la respuesta del usuario
    public void respuesta (int respuesta) {
        String textoBoton = String.valueOf(respuesta);
        if (textoBoton.equals(String.valueOf(resultado))) {
            Toast.makeText(this, "Respuesta Correcta", Toast.LENGTH_SHORT).show();
            sonidoCorrecto();
            nuevaSuma();
        } else {
            Toast.makeText(this, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
            sonidoIncorrecto();
        }
    }
}