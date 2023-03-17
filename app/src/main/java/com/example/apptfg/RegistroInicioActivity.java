package com.example.apptfg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroInicioActivity extends AppCompatActivity {
    private EditText correo, nombre, contrasena;
    private boolean correcto[];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_inicio);
        correcto = new boolean[3];
        findViewById(R.id.registrar).setOnClickListener(View -> registrar());
        contrasena = findViewById(R.id.contrasenaInput);
        contrasena.setOnClickListener(View -> vaciarCampos(contrasena));
        contrasena.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(contrasena.getText().toString().length() != 0) {
                    contrasena.setTextColor(Color.parseColor("#4CAF50"));
                    correcto[0] = true;
                }else {
                    contrasena.setTextColor(Color.parseColor("#FFB4241C"));
                    correcto[0] = false;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        nombre = findViewById(R.id.nombreInput);
        nombre.setOnClickListener(View -> vaciarCampos(nombre));
        nombre.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(nombre.getText().toString().length() != 0) {
                    nombre.setTextColor(Color.parseColor("#4CAF50"));
                    correcto[1] = true;
                }else {
                    nombre.setTextColor(Color.parseColor("#FFB4241C"));
                    correcto[1] = false;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        correo = findViewById(R.id.correoInput);
        correo.setOnClickListener(View -> vaciarCampos(correo));
        correo.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(correo.getText().toString().length() != 0) {
                    correo.setTextColor(Color.parseColor("#4CAF50"));
                    correcto[2] = true;
                }else {
                    correo.setTextColor(Color.parseColor("#FFB4241C"));
                    correcto[2] = true;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void registrar() {
        boolean todoOk = true;
        for (boolean c:
             correcto) {
            if(!c)
                todoOk = false;
        }
        if (todoOk) {
            terminar();
        }else{
            mostrarToast();
        }

    }

    private void mostrarToast() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_campos_vacios,(ViewGroup) findViewById(R.id.toastCamposVacios));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    private void terminar() {

    }

    private void vaciarCampos(EditText e){
        e.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(this,PerfilActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
