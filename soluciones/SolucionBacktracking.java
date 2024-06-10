package TPEProg3.soluciones;

import java.util.List;

import TPEProg3.Procesador;

public final class SolucionBacktracking extends Solucion {
    private int cantEstados;

    public SolucionBacktracking(List<Procesador> procesadores, int tiempoMaximoEjecucion, int estadosGenerados) {
        super(procesadores, tiempoMaximoEjecucion);
        cantEstados = estadosGenerados;
    }

    @Override
    public String toString() {
        return super.toString() + " \n" +
                " estado generados :" + this.cantEstados;
    }

}
