package TPE;

public class Tarea {
    private String idTarea, nombreTarea;
    private Integer tiempoEjecucion, nivelDePrioridad;
    private Boolean tareaCritica;

    public Tarea(String idTarea, String nombreTarea, Integer tiempoEjecucion, Integer nivelDePrioridad,
            Boolean esCritica) {
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.tiempoEjecucion = tiempoEjecucion;
        this.nivelDePrioridad = nivelDePrioridad;
        this.tareaCritica = esCritica;
    }

    public String getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return this.nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Integer getTiempoEjecucion() {
        return this.tiempoEjecucion;
    }

    public void setTiempoEjecucion(Integer tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public Integer getNivelDePrioridad() {
        return this.nivelDePrioridad;
    }

    public void setNivelDePrioridad(Integer nivelDePrioridad) {
        this.nivelDePrioridad = nivelDePrioridad;
    }

    public Boolean isEsCritica() {
        return this.tareaCritica;
    }

    public Boolean getEsCritica() {
        return this.tareaCritica;
    }

    public void setEsCritica(Boolean esCritica) {
        this.tareaCritica = esCritica;
    }

    @Override
    public String toString() {
        String critica = tareaCritica ? "SI" : "NO";
        return "Tarea{" + "Nombre: " + this.getNombreTarea() + ", Tiempo de Ejecución: " + this.getTiempoEjecucion()
                + ", Es critica :" + critica + " nivel de prioridad " + nivelDePrioridad;
    }
}
