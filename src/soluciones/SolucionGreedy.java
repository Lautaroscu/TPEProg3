package src.soluciones;

import java.util.List;

import TPEProg3.src.Procesador;

public final class SolucionGreedy extends Solucion {
    private int candidatos;

    public SolucionGreedy(List<Procesador> procesadores, int tiempoMaximoEjecucion, int candidatos) {
        super(procesadores, tiempoMaximoEjecucion);
        this.candidatos = candidatos;
    }

    @Override
    public String toString() {
        return super.toString() + " \n" +
                " candidatos considerados " + this.candidatos;
    }

}
