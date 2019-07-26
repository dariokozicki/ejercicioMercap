package Dominio;

import java.time.DayOfWeek;

public class Tasa {
    private FranjaHoraria franja;
    private DayOfWeek dia;
    private double monto;

    public Tasa(DayOfWeek dia, FranjaHoraria franja, double monto){
        this.dia = dia;
        this.franja = franja;
        this.monto = monto;
    }

    public double monto(){
        return monto;
    }
    public FranjaHoraria franja(){
        return franja;
    }
    public DayOfWeek dia(){
        return dia;
    }
}
