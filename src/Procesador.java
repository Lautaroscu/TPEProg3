package src;

import java.util.ArrayList;
import java.util.List;

public class Procesador implements Comparable<Procesador> {
    private String idProcesador, codigoProcesador;
    private Boolean estaRefrigerado;
    private Integer anioFuncionamiento;
    private List<Tarea> tareasAsignadas;
    private final int MAX_CRITICAS = 2;
    private int criticas;
    private int sumaTiempoTareas;

    public Procesador(String idProcesador, String codigoProcesador, Boolean estaRefrigerado,
            Integer anioFuncionamiento) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        tareasAsignadas = new ArrayList<>();
        sumaTiempoTareas = 0;
    }

    public int getSumaTiempoTareas() {
        return sumaTiempoTareas;
    }

    public String getIdProcesador() {
        return this.idProcesador;
    }

    public boolean asignarTarea(Tarea tarea, int tiempo) {
        if (tarea.getEsCritica() && criticas >= MAX_CRITICAS) {
            return false;
        }
        if (!this.getEstaRefrigerado() && !cumple(tiempo, tarea)) {
            return false;
        }

        tareasAsignadas.add(tarea);
        sumaTiempoTareas += tarea.getTiempoEjecucion();
        if (tarea.getEsCritica()) {
            criticas++;
        }
        return true;
    }

    private boolean cumple(int tiempo, Tarea t) {
        return tiempo >= t.getTiempoEjecucion() + this.sumaTiempoTareas;

    }

    public void eliminarTarea(Tarea t) {
        tareasAsignadas.remove(t);
        sumaTiempoTareas -= t.getTiempoEjecucion();
        if (t.getEsCritica()) {
            criticas--;
        }
    }

    public int getCriticas() {
        return criticas;
    }

    public int getMAX_CRITICAS() {
        return MAX_CRITICAS;
    }

    public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }

    public List<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }

    public void setIdProcesador(String idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getCodigoProcesador() {
        return this.codigoProcesador;
    }

    public void setCodigoProcesador(String codigoProcesador) {
        this.codigoProcesador = codigoProcesador;
    }

    public Boolean isEstaRefrigerado() {
        return this.estaRefrigerado;
    }

    public Boolean getEstaRefrigerado() {
        return this.estaRefrigerado;
    }

    public void setEstaRefrigerado(Boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    public Integer getAnioFuncionamiento() {
        return this.anioFuncionamiento;
    }

    public void setAnioFuncionamiento(Integer anioFuncionamiento) {
        this.anioFuncionamiento = anioFuncionamiento;
    }

    @Override
    public String toString() {
        return "Procesador: {\n" +
                "    idProcesador: " + idProcesador + "\n" +
                "    refrigerado: " + estaRefrigerado + "\n" +
                "    tareas asignadas: " + tareasAsignadas + "\n" +

                "}";
    }

    public Procesador getCopia() {
        Procesador copia = new Procesador(this.idProcesador, this.codigoProcesador, this.estaRefrigerado,
                this.anioFuncionamiento);
        copia.tareasAsignadas.addAll(tareasAsignadas);
        return copia;
    }

    @Override
    public int compareTo(Procesador o) {
        return Integer.compare(this.getSumaTiempoTareas(), o.getSumaTiempoTareas());
    }
}