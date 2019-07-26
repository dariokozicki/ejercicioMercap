package Dominio;

public class LlamadoNacional implements  Llamable {
    private Intervalo intervalo;
    private Localidad localidad;

    public LlamadoNacional(Intervalo intervalo, Localidad localidad){
        this.intervalo = intervalo;
        this.localidad = localidad;
    }
    public double monto(){
        return localidad.monto()*intervalo.duracion();
    }
}
