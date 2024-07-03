package src.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.Tarea;
import src.Procesador;

public class CSVReader {

    public CSVReader() {

    }

    public void readTasks(String taskPath, HashMap<String, Tarea> tareas, ArrayList<Tarea> tareasCriticas,
            ArrayList<Tarea> tareasNoCriticas, ArrayList<Tarea> tareasList) { // O(n)

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así

        ArrayList<String[]> lines = this.readContent(taskPath); // O(n)

        for (String[] line : lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String nombre = line[1].trim();
            Integer tiempo = Integer.parseInt(line[2].trim());
            Boolean critica = Boolean.parseBoolean(line[3].trim());
            Integer prioridad = Integer.parseInt(line[4].trim());
            // Aca instanciar lo que necesiten en base a los datos leidos

            Tarea nuevaTarea = new Tarea(id, nombre, tiempo, prioridad, critica);

            if (!tareas.containsKey(id)) {
                tareas.put(id, nuevaTarea);
            }
            if (nuevaTarea.getEsCritica())
                tareasCriticas.add(nuevaTarea);
            else
                tareasNoCriticas.add(nuevaTarea);

            tareasList.add(nuevaTarea);

        }

    }

    public void readProcessors(String processorPath, List<Procesador> procesadors) {// O(n)

        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(processorPath); // O(n)

        for (String[] line : lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String id = line[0].trim();
            String codigo = line[1].trim();
            Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
            Integer anio = Integer.parseInt(line[3].trim());
            // Aca instanciar lo que necesiten en base a los datos leidos
            Procesador p = new Procesador(id, codigo, refrigerado, anio);

            procesadors.add(p);
        }

    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        System.out.println(file.getName());
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }

}
