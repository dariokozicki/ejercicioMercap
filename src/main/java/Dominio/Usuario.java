package Dominio;

import java.util.ArrayList;
import java.util.Optional;

public class Usuario {
    private ArrayList<Mes> meses;
    private String nombre;
    public Usuario(String nombre,ArrayList<Mes> meses){
        this.nombre = nombre;
        this.meses = meses;
    }
    //facturacion de un mes entero
    public double facturacion(int mes, int año){
        return conseguirMes(mes,año).get().monto();
    }

    private Optional<Mes> conseguirMes(int mes, int año){
        return meses.stream().filter(m->m.año()==año && m.mes()==mes).findFirst();
    }

    public void realizarLlamado(Llamable llamado){
        meses.get(meses.size()-1).agregarLlamado(llamado);
    }
}
