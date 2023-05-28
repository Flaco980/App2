import java.util.List;

import modulo.plan;
import modulo.sede;
import modulo.proceso;
import modulo.menu;

/*Proyecto realizado por Arturo Figueroa, Agustín Vásque, Diego Espinoza, Matías Céspedes*/


public class App2 {
    public static void main(String[] args) {
        proceso proceso = new proceso();
        // Cargar las listas de planes, sedes y clientes
        proceso.leerArchivo();
        
        List<plan> planes = proceso.getPlanes();
        List<sede> sedes = proceso.getSedes();
        menu menu = new menu(proceso, planes, sedes);
        menu.mostrar();
    }
}