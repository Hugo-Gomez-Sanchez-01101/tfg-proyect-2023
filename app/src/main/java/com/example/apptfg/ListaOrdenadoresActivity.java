package com.example.apptfg;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.adaptador.AdaptadorOrdenadorVH;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.listaPc.ListaOrdenadoresSingleton;

import java.util.List;


public class ListaOrdenadoresActivity extends AppCompatActivity {
    private RecyclerView recyclerViewUser;
    private AdaptadorOrdenadorVH adaptadorOrdenadorVH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenadores);
        recyclerViewUser = findViewById(R.id.rViewUsuario);
        recyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager, esta vez horizontal
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));
        ListaOrdenadoresSingleton.getInstance().inicializar();
        List<Ordenador> listaOrdenadores = ListaOrdenadoresSingleton.getInstance().getListaOrdenadores();
        adaptadorOrdenadorVH = new AdaptadorOrdenadorVH(listaOrdenadores);
        recyclerViewUser.setAdapter(adaptadorOrdenadorVH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewUser.getAdapter().notifyDataSetChanged();
    }
}
