package Dominio;

import java.util.ArrayList;

public class LlamadoLocal implements Llamable {
    private Intervalo intervalo;
    private ArrayList<Tasa> tasas;

    public LlamadoLocal(Intervalo intervalo, ArrayList<Tasa> tasas){
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
