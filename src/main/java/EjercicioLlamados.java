import Dominio.*;

import java.time.*;
import java.util.ArrayList;


// Asumo que las llamadas empiezan y terminan el mismo dia
// Arbitrariamente elijo 500 como el bono basico
// Los tests están un poco más claros

public class EjercicioLlamados {
    public static void main(String[] args){
        EjercicioLlamados programa = new EjercicioLlamados();

        ArrayList<Tasa> tasas = programa.cargarTasas();

        ArrayList<Llamable> llamadosDeJuan = programa.cargarLlamados(tasas);

        Mes julio = new Mes(LocalDateTime.now().toLocalDate(),new Abono(500), llamadosDeJuan);

        Usuario juanPerez = new Usuario("Juan Perez",new ArrayList<Mes>(){{add(julio);}});

        programa.imprimirFactura(juanPerez,7,2019);
    }


    public ArrayList<Tasa> cargarTasas(){
        FranjaHoraria ceroAOcho = new FranjaHoraria(0,8);
        FranjaHoraria ochoAVeinte = new FranjaHoraria(8,20);
        FranjaHoraria veinteAVeinticuatro = new FranjaHoraria(20,24);
        FranjaHoraria todoElDia = new FranjaHoraria(0,24); //ejemplo de como se puede customizar
        ArrayList<Tasa> tasas = new ArrayList<>();
        tasas.add(new Tasa(DayOfWeek.MONDAY,ochoAVeinte,0.2));
        tasas.add(new Tasa(DayOfWeek.MONDAY,veinteAVeinticuatro,0.1));
        tasas.add(new Tasa(DayOfWeek.MONDAY,ceroAOcho,0.1));
        tasas.add(new Tasa(DayOfWeek.TUESDAY,ceroAOcho,0.1));
        tasas.add(new Tasa(DayOfWeek.TUESDAY,ochoAVeinte,0.2)); //etc
        tasas.add(new Tasa(DayOfWeek.SUNDAY,todoElDia,0.1));
        return tasas;
    }

    public ArrayList<Llamable> cargarLlamados(ArrayList<Tasa> tasas){
        int cant = 4;
        LocalDateTime[][] momentos = new LocalDateTime[cant][2];
        Long[][] epochs = new Long[cant][2];
        epochs[0][0] = 1563789600L;
        epochs[0][1] = 1563822000L;
        epochs[1][0] = 1563784200L;
        epochs[1][1] = 1563843600L;
        epochs[2][0] = 1564304400L;
        epochs[2][1] = 1564351200L;
        epochs[3][0] = 1564304400L;
        epochs[3][1] = 1564351200L;
        for (int i=0;i<cant;i++) {
            momentos[i][0] = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(epochs[i][0]), ZoneId.of("America/Argentina/Buenos_Aires")));
            momentos[i][1] = LocalDateTime.now(Clock.fixed(Instant.ofEpochSecond(epochs[i][1]), ZoneId.of("America/Argentina/Buenos_Aires")));

        }
        Intervalo[] intervalos = new Intervalo[4];
        ArrayList<Llamable> llamados = new ArrayList<>();
        for (int i=0;i<cant;i++){
            intervalos[i] = new Intervalo(momentos[i][0],momentos[i][1]);
        }
        llamados.add(new LlamadoLocal(intervalos[0],tasas));
        llamados.add(new LlamadoLocal(intervalos[1],tasas));
        llamados.add(new LlamadoLocal(intervalos[2],tasas));

        Pais Australia = new Pais(0.5);
        llamados.add(new LlamadoInternacional(intervalos[3],Australia));

        return llamados;
    }

    public void imprimirFactura(Usuario usuario,int mes, int año){
        System.out.println("Factura del " + mes + "/" + año + " de " + usuario.nombre());
        ArrayList<Llamable>llamados = usuario.llamados(mes,año);
        for (Llamable llamado : llamados){
            System.out.println(llamado.monto() + " +");
        }
        System.out.println(usuario.conseguirMes(mes,año).get().abono()+ "\n=");
        
        System.out.println(usuario.facturacion(mes,año));
    }
}
