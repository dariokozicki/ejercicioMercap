package Dominio;

public class LlamadoInternacional implements Llamable {
    private Intervalo intervalo;
    private Pais pais;

    public LlamadoInternacional(Intervalo intervalo, Pais pais){
        this.intervalo = intervalo;
        this.pais = pais;
    }
    public double monto(){
        return pais.monto()*intervalo.duracion();
    }
}
