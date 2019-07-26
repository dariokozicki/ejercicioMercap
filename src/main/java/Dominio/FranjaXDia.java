package Dominio;

import java.time.DayOfWeek;

public class FranjaXDia {
    private FranjaHoraria franja;
    private DayOfWeek dia;
    private double monto;

    public FranjaXDia(DayOfWeek dia, FranjaHoraria franja, double monto){
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
