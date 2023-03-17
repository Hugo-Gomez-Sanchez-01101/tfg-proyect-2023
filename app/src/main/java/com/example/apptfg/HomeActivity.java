package com.example.apptfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apptfg.entidad.DatosUsuarioSingleton;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String proovedor = bundle.getString("proovedor");
        setup(email,proovedor);
    }

    private void setup(String email, String proovedor) {
        TextView e = findViewById(R.id.emailTextView);
        e.setText(email);
        TextView p = findViewById(R.id.proovedorTextView);
        p.setText(proovedor);
        findViewById(R.id.btnCerrarSesion).setOnClickListener(v -> cerrarSesion());
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        DatosUsuarioSingleton.eliminarDatos();
        onBackPressed();
    }
}