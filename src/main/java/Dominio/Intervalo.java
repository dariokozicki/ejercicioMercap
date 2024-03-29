package Dominio;

import java.time.DayOfWeek;
import  java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Intervalo {
    private LocalDateTime inicio;
    private LocalDateTime finalizacion;

    public Intervalo(LocalDateTime inicio, LocalDateTime finalizacion){
        this.inicio = inicio;
        this.finalizacion = finalizacion;
    }
    public double duracion(){
        return duracion(inicio,finalizacion);
    }
    public double duracion(LocalDateTime inicio, LocalDateTime finalizacion){
        return ChronoUnit.MINUTES.between(inicio, finalizacion);
    }
    public double duracionSegunFranja(FranjaHoraria franja){
            return duracion(franja.adaptar(inicio),franja.adaptar(finalizacion));
    }
    public boolean ocurreEn(DayOfWeek dia){
        return inicio.getDayOfWeek()==dia;
    }
}
