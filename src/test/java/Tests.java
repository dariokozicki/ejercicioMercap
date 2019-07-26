import Dominio.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.*;
import java.util.ArrayList;

public class Tests {
    static ArrayList<Tasa> tasas;
    static FranjaHoraria ceroAOcho;
    static  FranjaHoraria ochoAVeinte;
    static  FranjaHoraria veinteAVeinticuatro;
    static FranjaHoraria todoElDia;
    static  Mes julio;
    static   Abono abonoJulio;
    static   Usuario juanPerez;
    static   LocalDateTime inicio,fin;


    @BeforeAll
    public static void fixture(){
        ceroAOcho = new FranjaHoraria(0,8);
        ochoAVeinte = new FranjaHoraria(8,20);
        veinteAVeinticuatro = new FranjaHoraria(20,24);
        todoElDia = new FranjaHoraria(0,24); //ejemplo de como se puede customizar
        tasas = new ArrayList<>();
        tasas.add(new Tasa(DayOfWeek.MONDAY,ochoAVeinte,0.2));
        tasas.add(new Tasa(DayOfWeek.MONDAY,veinteAVeinticuatro,0.1));
        tasas.add(new Tasa(DayOfWeek.MONDAY,ceroAOcho,0.1));
        tasas.add(new Tasa(DayOfWeek.TUESDAY,ceroAOcho,0.1));
        tasas.add(new Tasa(DayOfWeek.TUESDAY,ochoAVeinte,0.2)); //etc
        tasas.add(new Tasa(DayOfWeek.SUNDAY,todoElDia,0.1));
    }

    @Test
    public void llamadoLunesDeSieteADieciseis(){
        inicio = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1563789600), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock de este lunes 22/7 a las 7:00hs
        fin = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1563822000), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del mismo dia a las 16:00hs
        Intervalo intervaloAbuela = new Intervalo(inicio,fin);
        Llamable llamadoAbuela = new LlamadoLocal(intervaloAbuela,tasas);
        julio = new Mes(inicio.toLocalDate(),new Abono(500),new ArrayList<>());
        ArrayList<Mes> mesesDeJuan = new ArrayList();
        mesesDeJuan.add(julio);
        juanPerez = new Usuario("Juan Lopez",mesesDeJuan);

        juanPerez.realizarLlamado(llamadoAbuela); //60*0.1 + 8*60*0.2 + abono = 602
        Assertions.assertEquals(602,juanPerez.facturacion(7,2019));
    }
    @Test
    public void llamadoLunesDeCincYMediaAVeintiDos(){
        inicio = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1563784200), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock de este lunes 22/7 a las 5:00hs
        fin = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1563843600), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del mismo dia a las 22:00hs
        Intervalo intervaloVicio = new Intervalo(inicio,fin);
        Llamable llamadoVicio = new LlamadoLocal(intervaloVicio,tasas);
        julio = new Mes(inicio.toLocalDate(),new Abono(500),new ArrayList<>());
        ArrayList<Mes> mesesDeJuan = new ArrayList();
        mesesDeJuan.add(julio);
        juanPerez = new Usuario("Juan Lopez",mesesDeJuan);

        juanPerez.realizarLlamado(llamadoVicio); //3*60*0.1 + 12*60*0.2 + 2*60*0.1 + abono = 674
        Assertions.assertEquals(674,juanPerez.facturacion(7,2019));
    }

    @Test
    public void llamadoDomingo(){
        inicio = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1564304400), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del domingo 28/7 a las 6:00hs
        fin = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1564351200), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del mismo dia a las 19:00hs
        Intervalo intervaloEclesiastico= new Intervalo(inicio,fin);
        Llamable llamadoEclesiastico = new LlamadoLocal(intervaloEclesiastico,tasas);
        julio = new Mes(inicio.toLocalDate(),new Abono(500),new ArrayList<>());
        ArrayList<Mes> mesesDeJuan = new ArrayList();
        mesesDeJuan.add(julio);
        juanPerez = new Usuario("Juan Lopez",mesesDeJuan);

        juanPerez.realizarLlamado(llamadoEclesiastico); //13*60*0.1 + abono = 578
        Assertions.assertEquals(578,juanPerez.facturacion(7,2019));
    }
    @Test
    public void llamadoInternacional() {
        inicio = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1564304400), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del domingo 28/7 a las 6:00hs
        fin = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(1564351200), ZoneId.of("America/Argentina/Buenos_Aires")));
        // mock del mismo dia a las 19:00hs
        Intervalo intervaloVIP = new Intervalo(inicio, fin);
        Llamable llamadoVIP = new LlamadoInternacional(intervaloVIP, new Pais(0.5));
        julio = new Mes(inicio.toLocalDate(), new Abono(500), new ArrayList<>());
        ArrayList<Mes> mesesDeJuan = new ArrayList();
        mesesDeJuan.add(julio);
        juanPerez = new Usuario("Juan Lopez", mesesDeJuan);

        juanPerez.realizarLlamado(llamadoVIP); //13*60*0.5 + abono = 890
        Assertions.assertEquals(890, juanPerez.facturacion(7, 2019));
    }
}
