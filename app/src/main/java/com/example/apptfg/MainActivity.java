package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(View -> cambiar());
    }

    private void cambiar() {
        Intent i = new Intent(this,RegistroInicioActivity.class);
        startActivity(i);
    }

}
