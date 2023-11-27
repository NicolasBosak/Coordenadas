import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Ordenar {
    private LinkedList<Punto> puntos = new LinkedList<>();

    public Ordenar() {
    }
    public Ordenar(LinkedList<Punto> puntos) {
        this.puntos = puntos;
    }
    public void encolar(Punto punto){
        puntos.add(punto);
    }
    public LinkedList<Punto> getPuntos() {
        return puntos;
    }
    public void ordenarPuntos() {
        Collections.sort(puntos, new Comparator<Punto>() {
            @Override
            public int compare(Punto p1, Punto p2) {
                return Double.compare(p1.distanciaOrigen(), p2.distanciaOrigen());
            }
        });
    }
}
