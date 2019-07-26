package Dominio;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    public LocalDateTime adaptarFin(LocalDateTime momento){
        if (momento.getHour()<minimo)
            return momento.minusDays(1).plusHours(momento.getHour());
        return momento;
    }
    public LocalDateTime adaptarInicio(LocalDateTime momento){
        if (momento.getHour()>maximo)
            return momento.plusDays(1).minus(momento.getHour(), ChronoUnit.HOURS);
        return momento;
    }
}
