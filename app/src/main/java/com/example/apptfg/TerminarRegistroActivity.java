package com.example.apptfg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptfg.provider_tipe.ProviderType;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TerminarRegistroActivity extends AppCompatActivity {
    EditText email;
    EditText contraseña1;
    EditText contraseña2;
    List<EditText> campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminar_registro);
        setup();
    }

    private void setup() {
        Bundle bundle = getIntent().getExtras();
        email = findViewById(R.id.emailParaRegistroEditText);
        contraseña1 = findViewById(R.id.contraseña1EditText);
        contraseña2 = findViewById(R.id.contraseña2EditText);
        email.setText(bundle.getString("email"));
        email.setOnClickListener(v -> vaciarCampos(email));
        contraseña1.setOnClickListener(v -> vaciarCampos(contraseña1));
        contraseña2.setOnClickListener(v -> vaciarCampos(contraseña2));
        campos = new ArrayList<>();
        campos.add(email);
        campos.add(contraseña1);
        campos.add(contraseña2);
        findViewById(R.id.btnTerminrRegistro).setOnClickListener(v -> checkearCamposRellenos());
    }

    private void checkearCamposRellenos() {
        ColorStateList rojo = ColorStateList.valueOf(getResources().getColor(R.color.red_1));
        ColorStateList verde = ColorStateList.valueOf(getResources().getColor(R.color.verdeCorrecto));
        boolean todoOk = true;
        for (EditText e :
                campos) {
            if (e.getText().toString().equals("")) {
                e.setBackgroundTintList(rojo);
                todoOk = false;
            } else {
                e.setBackgroundTintList(verde);
            }
        }
        if (todoOk)
            comprobarContraseña();
        else
            mostrarToastCamposVacios();
    }

    private void comprobarContraseña() {
        if(contraseña1.getText().toString().equals(contraseña2.getText().toString()))
            registrar(email.getText().toString(), contraseña1.getText().toString());
        else
            mostrarToastContraseña();
    }

    private void registrar(String e, String c) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(e, c)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                    } else {
                        mostrarAlerta();
                    }
                });
    }

    private void mostrarToastCamposVacios() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_campos_vacios, (ViewGroup) findViewById(R.id.toastCamposVacios));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    private void mostrarToastContraseña() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_contrasenas_iguales, (ViewGroup) findViewById(R.id.toastContrasenaDif));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar", null);
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void irHome(String email, ProviderType proveedor) {
        Intent i = new Intent(this, ResultadosActivity.class);
        i.putExtra("email", email);
        i.putExtra("proveedor", proveedor + "");
        startActivity(i);
    }

    private void vaciarCampos(EditText e){
        e.setText("");
    }
}