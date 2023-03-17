package com.example.apptfg;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.entidad.DatosUsuarioSingleton;
import com.example.apptfg.provider_tipe.ProviderType;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroLoginActivity extends AppCompatActivity {
    EditText e;
    EditText c;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        setup();
    }

    private void setup() {
        e = findViewById(R.id.emailEditText);
        c = findViewById(R.id.contrasenaEditText);
        e.setOnClickListener(v -> vaciarCampos(e));
        c.setOnClickListener(v -> vaciarCampos(c));
        findViewById(R.id.btnRegistrar).setOnClickListener(v -> registrar());
        findViewById(R.id.btnAcceder).setOnClickListener(v -> acceder());
    }

    private void acceder() {
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

    private void irHome(String email, ProviderType proveedor){
        Intent i = new Intent(this, ResultadosActivity.class);
        i.putExtra("email",email);
        i.putExtra("proveedor",proveedor);
        startActivity(i);
    }

    private void vaciarCampos(EditText e){
        e.setText("");
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
}
