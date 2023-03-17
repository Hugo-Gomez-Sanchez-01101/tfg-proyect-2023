package com.example.apptfg.entidad;

import com.example.apptfg.provider_tipe.ProviderType;

public class DatosUsuarioSingleton {
    private static String email;
    private static ProviderType proovedor;

    private static DatosUsuarioSingleton datosUsuarioSingleton;

    private DatosUsuarioSingleton() {
    }

    public static void eliminarDatos(){
        email = null;
        proovedor = null;
    }

    public static DatosUsuarioSingleton getInstance(){
        if(datosUsuarioSingleton == null)
            datosUsuarioSingleton = new DatosUsuarioSingleton();

        return datosUsuarioSingleton;
    }

    public static void inicializar(String email1, ProviderType proovedor1){
        email = email1;
        proovedor = proovedor1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        DatosUsuarioSingleton.email = email;
    }

    public ProviderType getProovedor() {
        return proovedor;
    }

    public void setProovedor(ProviderType proovedor) {
        DatosUsuarioSingleton.proovedor = proovedor;
    }

}