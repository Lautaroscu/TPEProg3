package src.soluciones;

import java.util.List;
import src.Procesador;

public abstract class Solucion {
    private List<Procesador> procesadores;
    private int tiempoMaximoEjecucion;

    public Solucion(List<Procesador> procesadores, int tiempoMaximoEjecucion) {
        this.procesadores = procesadores;
        this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
    }

    public List<Procesador> getProcesadores() {
        return this.procesadores;
    }

    public void setProcesadores(List<Procesador> procesadores) {
        this.procesadores = procesadores;
    }

    public int getTiempoMaximoEjecucion() {
        return this.tiempoMaximoEjecucion;
    }

    public void setTiempoMaximoEjecucion(int tiempoMaximoEjecucion) {
        this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
    }

    @Override
    public String toString() {

        return "Procesadores con sus tareas asignadas : " + procesadores + " \n" +
                " tiempo maximo de ejecucion : " + tiempoMaximoEjecucion + " \n";
    }
}
