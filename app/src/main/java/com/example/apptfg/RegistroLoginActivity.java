package com.example.apptfg;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.provider_tipe.ProviderType;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroLoginActivity extends AppCompatActivity {
    EditText email;
    EditText c;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        setup();
        sesion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.authLayout).setVisibility(View.VISIBLE);
    }

    private void sesion() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String proveedor = prefs.getString("proveedor", null);

        if(email != null && proveedor != null) {
            findViewById(R.id.authLayout).setVisibility(View.INVISIBLE);
            irHome(email, ProviderType.valueOf(proveedor));
        }
    }

    private void setup() {
        email = findViewById(R.id.emailEditText);
        c = findViewById(R.id.contrasenaEditText);
        email.setOnClickListener(v -> vaciarCampos(email));
        c.setOnClickListener(v -> vaciarCampos(c));
        findViewById(R.id.btnRegistrar).setOnClickListener(v -> irTerminarRegistro());
        findViewById(R.id.btnAcceder).setOnClickListener(v -> acceder());
    }

    private void irTerminarRegistro() {
        Intent i = new Intent(this, TerminarRegistroActivity.class);
        i.putExtra("email", email.getText().toString());
        startActivity(i);
    }

    private void acceder() {
        if (!email.getText().toString().isEmpty() && !c.getText().toString().isEmpty()) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.getText().toString(), c.getText().toString())
                    .addOnCompleteListener((task) -> {
                        if(task.isSuccessful()){
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

    private void irHome(String email, ProviderType proveedor){
        Intent i = new Intent(this, ResultadosActivity.class);
        i.putExtra("email",email);
        i.putExtra("proveedor",proveedor + "");
        startActivity(i);
    }

    private void vaciarCampos(EditText e){
        e.setText("");
    }
}
