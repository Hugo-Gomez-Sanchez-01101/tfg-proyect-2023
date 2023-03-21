package com.example.apptfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ActividadMenu extends AppCompatActivity {
    private ImageButton iUsuario,iCasa, iOrdenador;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_menu);
        iCasa = findViewById(R.id.iCasa);
        iOrdenador = findViewById(R.id.iOrdenador);
        iUsuario = findViewById(R.id.iUsuario);


        iCasa.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaOrdenadoresActivity.class);
            startActivity(intent);

        });
        iOrdenador.setOnClickListener(v ->{
            Intent intent = new Intent(this, ActividadMenu.class);
            startActivity(intent);
        } );

        iUsuario.setOnClickListener(v ->{
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        });


    }
}

