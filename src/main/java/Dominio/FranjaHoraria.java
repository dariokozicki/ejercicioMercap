package Dominio;


import java.time.LocalDateTime;

public class FranjaHoraria {
    private int minimo;
    private int maximo;

    public FranjaHoraria(int minimo,int maximo){
        this.minimo = minimo;
        this.maximo = maximo;
    }
    public int minimo(){
        return minimo;
    }
    public int maximo(){
        return maximo;
    }
    private int truncar(LocalDateTime momento){
        return Math.min(Math.max(momento.getHour(),minimo), maximo);
    }
    public LocalDateTime adaptar(LocalDateTime momento){
        return momento.toLocalDate().atTime(truncar(momento),0);
    }

}
