package TPEProg3;

import java.util.List;

public class Solucion {
    private List<Procesador> procesadores;
    private int tiempoMaximoEjecucion;
    private int estadosGenerados;

    public Solucion(List<Procesador> procesadores, int tiempoMaximoEjecucion, int estadosGenerados) {
        this.procesadores = procesadores;
        this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
        this.estadosGenerados = estadosGenerados;
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

    public int getEstadosGenerados() {
        return this.estadosGenerados;
    }

    public void setEstadosGenerados(int estadosGenerados) {
        this.estadosGenerados = estadosGenerados;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        return "Procesadores con sus tareas asignadas : " + procesadores + " \n" +
                " tiempo maximo de ejecucion : " + tiempoMaximoEjecucion + " \n" +
                " cantidad de estados generados " + estadosGenerados;
    }
}
