package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        findViewById(R.id.btnListaOrdenadores).setOnClickListener(view -> verListaOrdenadores());
    }

    private void verListaOrdenadores() {
        Intent i = new Intent(this,ListaOrdenadoresActivity.class);
        startActivity(i);
    }
}
