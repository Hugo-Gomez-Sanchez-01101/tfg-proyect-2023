package com.example.apptfg.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.ListaOrdenadoresActivity;
import com.example.apptfg.R;
import com.example.apptfg.VistaOrdenadorActivity;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.listaPc.ListaOrdenadoresSingleton;

import java.util.List;

public class AdaptadorOrdenadorVH extends RecyclerView.Adapter<AdaptadorOrdenadorVH.ViewHolder> {

   private List<Ordenador> listaOrdenadores;

    public AdaptadorOrdenadorVH(List<Ordenador> listaOrdenadores) {
        this.listaOrdenadores = listaOrdenadores;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Contructor:
        private Button btnEliminar;
        private Button btnVer;
        private TextView nombre;
        public ViewHolder(View v) {
            super(v);
              nombre = v.findViewById(R.id.nombrePc);
              btnEliminar = v.findViewById(R.id.btnEliminarOrdenador);
              btnVer = v.findViewById(R.id.btnVerOrdenador);
        }
    }


   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordenador_view_holder, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
   }


   @Override
   public void onBindViewHolder(ViewHolder holder, int position) {
        //holder es una clase que contiene los atributos del viewHolderXML
       holder.nombre.setText(ListaOrdenadoresSingleton.getInstance().getListaOrdenadores().get(position).getNombre());
       holder.btnEliminar.setOnClickListener(view -> eliminar(holder, position));
       holder.btnVer.setOnClickListener(view -> ver(holder, position));
   }

   private void eliminar(ViewHolder holder, int id){
       Toast.makeText(holder.itemView.getContext(), "Eliminando ordenador ", Toast.LENGTH_SHORT).show();
       ListaOrdenadoresSingleton.getInstance().borrar(listaOrdenadores.get(id));
       notifyDataSetChanged();
   }
    private void ver(ViewHolder holder, int id) {
        Intent i = new Intent(holder.itemView.getContext(), VistaOrdenadorActivity.class);
        i.putExtra("id", id);
        holder.itemView.getContext().startActivity(i);
    }

   @Override
   public int getItemCount() {
       return listaOrdenadores.size();
   }

}