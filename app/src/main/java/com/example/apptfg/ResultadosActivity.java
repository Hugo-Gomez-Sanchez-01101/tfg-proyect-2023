package com.example.apptfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ResultadosActivity extends AppCompatActivity {
    String email;
    String proveedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        proveedor = bundle.getString("proveedor");
        System.out.println(email);
        System.out.println(proveedor);
        setup(email,proveedor);

        guardarDatosUsuario();
    }

    private void guardarDatosUsuario() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        prefs.putString("email", email);
        prefs.putString("proveedor", proveedor);
        prefs.apply();
    }

    private void setup(String email, String proveedor) {
        TextView e = findViewById(R.id.emailTextView);
        e.setText(email);
        TextView p = findViewById(R.id.proovedorTextView);
        p.setText(proveedor);
        findViewById(R.id.btnCerrarSesion).setOnClickListener(v -> cerrarSesion());
    }

    private void cerrarSesion() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.apply();
        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }
}