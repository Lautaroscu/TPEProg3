package TPEProg3.src;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import src.soluciones.Solucion;
import src.soluciones.SolucionBacktracking;
import src.soluciones.SolucionGreedy;
import TPEProg3.src.utils.CSVReader;

public class Servicios {
    private HashMap<String, Tarea> tareas = new HashMap<>();
    private ArrayList<Tarea> tareasCriticas = new ArrayList<>();
    private ArrayList<Tarea> tareasNoCriticas = new ArrayList<>();
    private int tiempoMenor = Integer.MAX_VALUE;
    private int estados;

    private List<Procesador> procesadores = new ArrayList<>();
    private List<Procesador> solucionFinalProcesadores = new ArrayList<>();
    private ArrayList<Tarea> tareasList;

    /**
     * NO modificar la interfaz de esta clase ni sus métodos públicos.
     * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
     * de implementación.
     */

    /*
     * Expresar la complejidad temporal del constructor.
     */
    // Complejidad Temporal del Constructor :
    // O(n) PD : aca nose si deberia haber sumando readProcesesor + readTask son
    // ambos O(n) ,no
    // recurdo si se suman los O(n) o se puede simplificar en un O(n)

    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();

        reader.readProcessors(pathProcesadores, procesadores);
        reader.readTasks(pathTareas, tareas, tareasCriticas, tareasNoCriticas); // llena las estructuras
        tareasList = new ArrayList<Tarea>(this.tareas.values());

    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    // O(1) o un aproximado dependiendo la implementacion de sus RS en la libreria
    // "HashMap"
    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    // Complejidad Temporal :
    // O(1)
    public List<Tarea> servicio2(boolean esCritica) {

        if (esCritica)
            return tareasCriticas;

        return tareasNoCriticas;

    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    // Complejidad Temporal :
    // O(n)
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        final int PRIORIDAD_MIN = 0, PRIORIDAD_MAX = 100;

        if ((prioridadInferior > PRIORIDAD_MAX || prioridadInferior < PRIORIDAD_MIN)
                || prioridadSuperior < PRIORIDAD_MIN || prioridadSuperior > PRIORIDAD_MAX) {
            throw new IllegalArgumentException(
                    "La prioridad inferior debe ser mayor o igual a " + PRIORIDAD_MIN + " \n" +
                            " y la prioridad superior debe ser menor o igual a" + PRIORIDAD_MAX);
        }

        return tareas.values()
                .stream()
                .filter(tarea -> tarea.getNivelDePrioridad() >= prioridadInferior
                        && tarea.getNivelDePrioridad() <= prioridadSuperior)
                .collect(Collectors.toList());
    }

    /*
     * Por cada procesador recorremos todas las tareas (de forma recursiva) donde a
     * la primer tarea del arreglo siempre se la asignamos al propio proceador.
     * Vamos probando todas las combinaciones posibles, y en donde tenemos un estado
     * (todas las tareas asignadas) evaluamos si es el tiempo mayor de los
     * proceadores
     * en ese estado es menor al tiempoMenor guardado en una variable.
     * En cada asignacion de la tarea, para minimizar la cantidad de estados
     * realizamos una "poda" comparando si asignando la tarea a ese procesador el
     * tiempo es mayor al tiempo menor guardado hasta
     * el momento.
     */

    private int getTiempoMenorBacktracking(int tiempo) {
        ArrayList<Tarea> verTareas = new ArrayList<>();

        for (Procesador p : procesadores) {
            buscarTiempoMenorBackTracking(p, tareasList.get(0), tareasList, 0, verTareas, tiempo);

        }
        return this.tiempoMenor;

    }

    private void buscarTiempoMenorBackTracking(Procesador p, Tarea tarea, ArrayList<Tarea> tareas, int index,
            ArrayList<Tarea> tareasAsignadas, int tiempo) {
        if (!p.asignarTarea(tarea, tiempo)) {
            return;
        }
        estados++;
        tareasAsignadas.add(tarea);

        if (getTiempoMayorProcesador() > this.tiempoMenor) {
            tareasAsignadas.remove(tareasAsignadas.size() - 1);
            p.eliminarTarea(p.getTareasAsignadas().size() - 1);

            return;
        }

        if (index < tareas.size() - 1) {
            Tarea t = tareas.get(index + 1);

            for (Procesador pr : procesadores) {
                buscarTiempoMenorBackTracking(pr, t, tareas, index + 1, tareasAsignadas, tiempo);
            }
        }

        if (tareasAsignadas.size() == tareas.size())

        {
            if (this.tiempoMenor > getTiempoMayorProcesador()) {
                this.tiempoMenor = getTiempoMayorProcesador();
                this.solucionFinalProcesadores.clear();
                guardarSolucion();

            }
        }

        tareasAsignadas.remove(tareasAsignadas.size() - 1);
        p.eliminarTarea(p.getTareasAsignadas().size() - 1);

    }

    private int getTiempoMayorProcesador() {
        int sumaMayor = 0;

        for (int i = 0; i < procesadores.size(); i++) {
            int suma = procesadores.get(i).getSumaTiempos();
            if (suma > sumaMayor) {
                sumaMayor = suma;
            }
        }
        return sumaMayor;
    }

    public Solucion asignarTareasBacktracking(int tiempo) {
        int tiempoMaximo = this.getTiempoMenorBacktracking(tiempo);

        return new SolucionBacktracking(solucionFinalProcesadores, tiempoMaximo, this.estados);
    }

    public Solucion asignarTareasGreedy(int tiempo) {
        Collections.sort(tareasList);

        // candidatos en vez de tiempo
        return new SolucionGreedy(procesadores, getTiempoMenorGreedy(tiempo),
                // por cada tarea, tiene como candidato todos los procesadores, ya que,
                // ordenamos los procesadores por cada asignacion de tarea
                // para poder mejorar la busqueda del "mejor procesador" en ese momento.
                this.procesadores.size() * this.tareasList.size());
    }

    /*
     * Nuestra estrategia de resolucion consiste en primero recorrer las tarea, y
     * por cada una la voy agregando al procesador con menor tiempo de ejecucion
     * (por cada asignacion de una tarea a un procesador , ordenamos el arreglo de
     * procesadores por tiempo ASC),
     * al principio van a asignarse las tareas a los procesadores vacios que a su
     * vez se iran ordenando automaticamente. Si todas las tareas fueron asignadas
     * teniendo en cuenta las resticciones mostramos los procesadores.
     * Y si no, el metodo devuelve -1 , es decir, al menos una tarea no pudo ser
     * asignada a ningun procesador
     * 
     */

    public int getTiempoMenorGreedy(int tiempo) {
        // [30 , 25 , 20 , 12]

        for (Tarea t : tareasList) {
            int i = 0;
            while (i < procesadores.size() && !procesadores.get(i).asignarTarea(t, tiempo)) {
                i++;
            }
            if (i == procesadores.size()) {
                return -1;
            }

            Collections.sort(procesadores);

        }
        return getTiempoMayorProcesador();
    }

    private void guardarSolucion() {
        for (Procesador p : procesadores) {
            solucionFinalProcesadores.add(p.getCopia());
        }
    }

}
