package com.example.apptfg;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.entidad.DatosUsuarioSingleton;
import com.example.apptfg.provider_tipe.ProviderType;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    private void setup() {
        findViewById(R.id.btnRegistrar).setOnClickListener(v -> registrar());
        findViewById(R.id.btnAcceder).setOnClickListener(v -> acceder());
    }

    private void acceder() {
        EditText e = findViewById(R.id.emailEditText);
        EditText c = findViewById(R.id.contrasenaEditText);
        if (!e.getText().toString().isEmpty() && !c.getText().toString().isEmpty()) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(e.getText().toString(), c.getText().toString())
                    .addOnCompleteListener((task) -> {
                        if(task.isSuccessful()){
                            DatosUsuarioSingleton.inicializar(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                            irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                        } else{
                            mostrarAlerta();
                        }
                    });
        }
    }

    private void registrar() {
        EditText e = findViewById(R.id.emailEditText);
        EditText c = findViewById(R.id.contrasenaEditText);
        if (!e.getText().toString().isEmpty() && !c.getText().toString().isEmpty()) {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(e.getText().toString(), c.getText().toString())
                    .addOnCompleteListener((task) -> {
                if(task.isSuccessful()){
                    DatosUsuarioSingleton.inicializar(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                    irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                } else{
                    mostrarAlerta();
                }
            });
        }
    }

    private void mostrarAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar", null);
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void irHome(String email, ProviderType proovedor){
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("email",email);
        i.putExtra("proovedor",proovedor);
        startActivity(i);
    }
}
