package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Mes {
    private ArrayList<Llamable> llamables;
    private Abono abonoBasico;
    private LocalDate momento;

    public Mes(LocalDate momento,Abono abonoBasico, ArrayList<Llamable> llamables){
        this.momento = momento;
        this.abonoBasico = abonoBasico;
        this.llamables = llamables;
    }
    public int año(){
        return momento.getYear();
    }
    public int mes(){
        return momento.getMonthValue();
    }
    public double monto(){
        return abonoBasico.monto() + llamables.stream()
                .map(llamado->llamado.monto())
                .reduce(0.0,(sub,e)->sub + e);
    }
    public void agregarLlamado(Llamable llamado){
        llamables.add(llamado);
    }
}
