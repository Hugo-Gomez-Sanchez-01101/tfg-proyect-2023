package com.example.apptfg.listaPc;

import com.example.apptfg.entidad.Ordenador;

import java.util.ArrayList;
import java.util.List;

public class ListaOrdenadoresSingleton {

    private static ListaOrdenadoresSingleton instance;
    private List<Ordenador> listaOrdenadores;
    private int contador = 1;

    private ListaOrdenadoresSingleton(){
        super();
    }

    public static ListaOrdenadoresSingleton getInstance() {
        if(instance == null){
            instance = new ListaOrdenadoresSingleton();
        }
        return instance;
    }

    public void inicializar(){
        listaOrdenadores = new ArrayList<>();
        Ordenador n1 = new Ordenador(1,"mi Ordenador 1");
        listaOrdenadores.add(n1);
        Ordenador n2 = new Ordenador(2,"mi Ordenador 2");
        listaOrdenadores.add(n2);
        Ordenador n3 = new Ordenador(2,"mi Ordenador 3");
        listaOrdenadores.add(n3);
        Ordenador n4 = new Ordenador(2,"mi Ordenador 4");
        listaOrdenadores.add(n4);
        Ordenador n5 = new Ordenador(2,"mi Ordenador 5");
        listaOrdenadores.add(n5);
        Ordenador n6 = new Ordenador(2,"mi Ordenador 6");
        listaOrdenadores.add(n6);
        Ordenador n7 = new Ordenador(2,"mi Ordenador 7");
        listaOrdenadores.add(n7);
    }

    public List<Ordenador> getListaOrdenadores() {
        return listaOrdenadores;
    }

    public int añadirId(){
        return contador++;
    }
    public void borrar(Ordenador ordenador){
        listaOrdenadores.remove(ordenador);
    }
}
