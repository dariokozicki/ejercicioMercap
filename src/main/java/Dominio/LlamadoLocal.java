package Dominio;

import java.util.ArrayList;

public class LlamadoLocal implements Llamable {
    private Intervalo intervalo;
    private ArrayList<FranjaXDia> tasas;

    public LlamadoLocal(Intervalo intervalo, ArrayList<FranjaXDia> tasas){
        this.intervalo = intervalo;
        this.tasas = tasas;
    }
    public double monto(){
        return tasas.stream()
                .filter(tasa->intervalo.ocurreEn(tasa.dia()))
                .map(tasa->intervalo.duracionSegunFranja(tasa.franja())*tasa.monto())
                .reduce(0.0,(sub,e)->sub+e);
    }
}
