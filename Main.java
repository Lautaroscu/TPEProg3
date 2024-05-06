package TPEProg3;

public class Main {
    public static void main(String[] args) {
        Servicios s1 = new Servicios("TPE/datasets/Procesadores.csv", "TPE/datasets/Tareas.csv");

        System.out.println(s1.servicio3(70, 80));
    }
}
