package com.example.apptfg;

import static com.example.apptfg.R.id.lblPhone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {
    private ImageButton iUsuario,iCasa, iOrdenador;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        findViewById(R.id.btnListaOrdenadores).setOnClickListener(view -> verListaOrdenadores());
        findViewById(R.id.btnCerrarSesion).setOnClickListener(View -> cerrarSesion());

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

    private void cerrarSesion() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.apply();
        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }

    private void verListaOrdenadores() {
        Intent i = new Intent(this,ListaOrdenadoresActivity.class);
        startActivity(i);
    }
}
