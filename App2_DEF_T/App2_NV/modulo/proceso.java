package modulo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class proceso {
    private List<cliente> clientes;
    private List<plan> planes;
    private List<sede> sedes;
    
    public proceso() {
        clientes = new ArrayList<>();
        planes = new ArrayList<>();
        sedes = new ArrayList<>();
    }

    // Leer archivo
    public void leerArchivo() {
        String archivoCSV = "bigmuscle2.csv";
        String linea = "";
        String separador = ",";
        
        boolean primeraLinea = true;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar la primera línea
                }
                
                linea = linea.replaceAll("\"", ""); // eliminar todas las comillas dobles de la línea
                String[] datosCliente = linea.split(separador);
                String rut = datosCliente[0];
                String nombreCompleto = datosCliente[1];
                int edad = Integer.parseInt(datosCliente[2]);
                String codPlan = datosCliente[3];
                String descripcionPlan = datosCliente[4];
                String desde = datosCliente[5];
                String hasta = datosCliente[6];
                String codSede = datosCliente[7];
                String ubicacionSede = datosCliente[8];
                
                // Buscamos el plan en la lista de planes, y si no existe, lo agregamos
                plan plan = buscarPlan(codPlan, descripcionPlan);
                if (plan == null) {
                    plan = new plan(codPlan, descripcionPlan);
                    planes.add(plan);
                }
                
                // Buscamos la sede en la lista de sedes, y si no existe, la agregamos
                sede sede = buscarSede(codSede, ubicacionSede);
                if (sede == null) {
                    sede = new sede(codSede, ubicacionSede);
                    sedes.add(sede);
                }
                
                cliente cliente = new cliente(rut, nombreCompleto, edad, codPlan, descripcionPlan, desde, hasta, codSede, ubicacionSede);
                clientes.add(cliente);
            }
            System.out.println("Clientes leídos correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * CLIENTES
     */

    // Mostrar clientes
    public void mostrarClientes() {
        for (cliente cliente : clientes) {
            System.out.println(cliente.getRut() + " - " + cliente.getNombreCompleto() + " - " + cliente.getEdad() + " - " + cliente.getCodPlan() + " - " + cliente.getDescripcionPlan() + " - " + cliente.getDesde() + " - " + cliente.getHasta() + " - " + cliente.getCodSede() + " - " + cliente.getUbicacionSede());
        }
    }

    // Agregar cliente 1.-OPCION
    public void agregarCliente(List<plan> planes, List<sede> sedes) {
            Scanner sc = new Scanner(System.in);

            // Validar rut
            boolean rutValido = false;
            String rut = "";
            while (!rutValido) {
                System.out.println("Ingrese el rut del cliente (con guión): ");
                rut = sc.nextLine();

                // Validar formato del rut
                if (!rut.matches("\\d{7,8}-[\\dkK]{1}")) {
                    System.out.println("El rut ingresado no es válido. Por favor, inténtelo de nuevo.");
                } else {
                    rutValido = true;
                }
            }
        
            System.out.println("Ingrese el nombre completo del cliente: ");
            String nombreCompleto = sc.nextLine();
        
            System.out.println("Ingrese la edad del cliente: ");
            int edad = Integer.parseInt(sc.nextLine());
        
            // Mostrar lista de planes disponibles
            System.out.println("-----------------------------");
            System.out.println("Lista de planes disponibles: ");
            for (plan plan : planes) {
                System.out.println(plan.getCodPlan() + " - " + plan.getDescripcionPlan());
            }
            System.out.println("-----------------------------");
            String codPlan = "";
            String descripcionPlan = "";
            boolean planEncontrado = false;
            while (!planEncontrado) {
                System.out.println("Ingrese el código del plan elegido: ");
                codPlan = sc.nextLine();
                for (plan plan : planes) {
                    if (plan.getCodPlan().equals(codPlan)) {
                        descripcionPlan = plan.getDescripcionPlan();
                        planEncontrado = true;
                        break;
                    }
                }
                if (!planEncontrado) {
                    System.out.println("Código de plan no encontrado. Por favor, ingrese un código de plan válido.");
                }
            }
        
            // Validar fechas
            boolean fechasValidas = false;
            String desde = "";
            String hasta = "";

            while (!fechasValidas) {
                System.out.println("Ingrese la primera fecha (formato: DD/MM/YYYY): ");
                desde = sc.nextLine();
            
                System.out.println("Ingrese la segunda fecha (formato: DD/MM/YYYY): ");
                hasta = sc.nextLine();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("DD/MM/YYYY");
            
                try {
                    Date fecha1 = formatoFecha.parse(desde);
                    Date fecha2 = formatoFecha.parse(hasta);
                
                    if (fecha1.compareTo(fecha2) >= 0) {
                        System.out.println("La primera fecha debe ser menor que la segunda fecha. Ingrese las fechas nuevamente.");
                    } else {
                        fechasValidas = true;
                        System.out.println("Se ingresaron bien las fechas.");
                    }
                } catch (ParseException e) {
                    System.out.println("Error al analizar las fechas. Asegúrese de utilizar el formato DD/MM/YYYY. Ingrese las fechas nuevamente.");
                }
            }
        
            // Mostrar lista de sedes disponibles
            System.out.println("-----------------------------");
            System.out.println("Lista de sedes disponibles: ");
            for (sede sede : sedes) {
                System.out.println(sede.getCodSede() + " - " + sede.getUbicacionSede());

            }
            System.out.println("-----------------------------");
            System.out.println("Ingrese el código de la sede elegida: ");
            String codSede = sc.nextLine();
        
            // Verificar que el código de sede ingresado exista en la lista de sedes
            boolean sedeEncontrada = false;
            String ubicacionSede = "";
            for (sede sede : sedes) {
                if (sede.getCodSede().equals(codSede)) {
                    sedeEncontrada = true;
                    ubicacionSede = sede.getUbicacionSede();
                    break;
                }
            }
        
            // Si la sede no fue encontrada, volver a solicitar el código de sede
            while (!sedeEncontrada) {
                System.out.println("Código de sede no encontrado. Por favor, ingrese un código de sede válido: ");
                codSede = sc.nextLine();
                for (sede sede : sedes) {
                    if (sede.getCodSede().equals(codSede)) {
                        sedeEncontrada = true;
                        ubicacionSede = sede.getUbicacionSede();
                        break;
                    }
                }
            }
        
            // Crear nuevo objeto Cliente y agregarlo a la lista
            cliente nuevoCliente = new cliente(rut, nombreCompleto, edad, codPlan, descripcionPlan, desde, hasta, codSede, ubicacionSede);
            clientes.add(nuevoCliente);
        
            System.out.println("Cliente agregado exitosamente");
        }
    
    // Tener un Back Up del primer csv entregado
    public void hacerBackup(String archivoCSV) {
        String backupCSV = "backup_" + archivoCSV;
        String separador = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV));
                FileWriter fw = new FileWriter(backupCSV)) {
            String linea;
            // Leer y escribir cada línea del archivo
            while ((linea = br.readLine()) != null) {
                fw.write(linea);
                fw.write("\n"); // Agregar salto de línea
            }
            System.out.println("Archivo CSV respaldado correctamente como: " + backupCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar clientes en archivo 11.-OPCION SALIR
    public void guardarClientesEnArchivo() {
        String archivoCSV = "bigmuscle2.csv";
        String separador = ",";
        try (FileWriter fw = new FileWriter(archivoCSV)) {
            // Escribimos los titulos de cada columna
            fw.append("rut");
            fw.append(separador);
            fw.append("nombre_completo");
            fw.append(separador);
            fw.append("edad");
            fw.append(separador);
            fw.append("cod_plan");
            fw.append(separador);
            fw.append("descripcion_plan");
            fw.append(separador);
            fw.append("desde");
            fw.append(separador);
            fw.append("hasta");
            fw.append(separador);
            fw.append("cod_sede");
            fw.append(separador);
            fw.append("ubicacion_sede");
            fw.append("\n");
    
            // Ordenar clientes por nombre
            // Usamos la clase Collections.sort para ordenar por nombre de forma ascendente 
            // Aprendimos esta clase en https://www.geeksforgeeks.org/collections-sort-java-examples/
            Collections.sort(clientes, Comparator.comparing(cliente::getNombreCompleto));
    
            // Escribir clientes
            for (cliente cliente : clientes) {
                fw.append(cliente.getRut());
                fw.append(separador);
                fw.append(cliente.getNombreCompleto());
                fw.append(separador);
                fw.append(Integer.toString(cliente.getEdad()));
                fw.append(separador);
                fw.append(cliente.getCodPlan());
                fw.append(separador);
                fw.append(cliente.getDescripcionPlan());
                fw.append(separador);
                fw.append(cliente.getDesde());
                fw.append(separador);
                fw.append(cliente.getHasta());
                fw.append(separador);
                fw.append(cliente.getCodSede());
                fw.append(separador);
                fw.append(cliente.getUbicacionSede());
                fw.append("\n");
            }
            System.out.println("Clientes guardados en archivo correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Quitar un cliente 2.-OPCION
    public void quitarCliente() {
        Scanner sc = new Scanner(System.in);

        // Mostrar lista de clientes
        System.out.println("-----------------------------");
        System.out.println("Lista de clientes:");
        mostrarClientes();
        System.out.println("-----------------------------");

        System.out.println("Ingrese el rut del cliente a quitar: ");
        String rut = sc.nextLine();
        
        // Buscar el cliente en la lista de clientes
        cliente cliente = null;
        for (cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                cliente = c;
                break;
            }
        }
        
        // Si el cliente no fue encontrado, volver a solicitar el rut
        while (cliente == null) {
            System.out.println("Rut no encontrado. Por favor, ingrese un rut válido: ");
            rut = sc.nextLine();
            for (cliente c : clientes) {
                if (c.getRut().equals(rut)) {
                    cliente = c;
                    break;
                }
            }
        }
        
        // Eliminar el cliente de la lista
        clientes.remove(cliente);
        System.out.println("Cliente eliminado exitosamente");
    }
     
    //Editar un cliente 3.-OPCION
    public void editarCliente(){

        // Mostrar solo rut y nombre de clientes
        System.out.println("-----------------------------");
        System.out.println("Lista de clientes:");
        mostrarRutNombreClientes();
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el rut del cliente a buscar: ");
        String rut = sc.nextLine();
        
        // Buscar el cliente en la lista de clientes
        cliente cliente = null;
        for (cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                cliente = c;
                break;
            }
        }
        
        // Si el cliente no fue encontrado, volver a solicitar el rut
        while (cliente == null) {
            System.out.println("Rut no encontrado. Por favor, ingrese un rut válido: ");
            rut = sc.nextLine();
            for (cliente c : clientes) {
                if (c.getRut().equals(rut)) {
                    cliente = c;
                    break;
                }
            }
        }

        System.out.println("Datos del cliente:");
        System.out.println("Rut: " + cliente.getRut());
        System.out.println("Nombre completo: " + cliente.getNombreCompleto());
        System.out.println("Edad: " + cliente.getEdad());
        System.out.println("Código de plan: " + cliente.getCodPlan());
        System.out.println("Descripción de plan: " + cliente.getDescripcionPlan());
        System.out.println("Fecha de inicio: " + cliente.getDesde());
        System.out.println("Fecha de término: " + cliente.getHasta());
        System.out.println("Código de sede: " + cliente.getCodSede());
        System.out.println("Ubicación de sede: " + cliente.getUbicacionSede());

        // Mostrar lista de planes disponibles
        System.out.println("Lista de planes disponibles: ");
        for (plan plan : planes) {
            System.out.println(plan.getCodPlan() + " - " + plan.getDescripcionPlan());
        }
    
        System.out.println("Ingrese el código del plan elegido: ");
        String codPlan = sc.nextLine();
    
        // Obtener la descripción del plan elegido
        String descripcionPlan = "";
        for (plan plan : planes) {
            if (plan.getCodPlan().equals(codPlan)) {
                descripcionPlan = plan.getDescripcionPlan();
                break;
            }
        }
        
        //Verificacion de fechas
        boolean fechasValidas = false;
        String desde = "";
        String hasta = "";

        while (!fechasValidas) {
            System.out.println("Ingrese la primera fecha (formato: DD/MM/YYYY): ");
            desde = sc.nextLine();
        
            System.out.println("Ingrese la segunda fecha (formato: DD/MM/YYYY): ");
            hasta = sc.nextLine();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("DD/MM/YYYY");
            
            try {
                //Cambiamos el formato para usar Date
                Date fecha1 = formatoFecha.parse(desde);
                Date fecha2 = formatoFecha.parse(hasta);
                
                //Gracias al cambio del formato podemos comparar las fechas y podemos determinar si fueron bien ingresadas las fechas
                if (fecha1.compareTo(fecha2) >= 0) {
                    System.out.println("La primera fecha debe ser menor que la segunda fecha. Ingrese las fechas nuevamente.");
                } else {
                    fechasValidas = true;
                    System.out.println("Se ingresaron bien las fechas.");
                }
            } catch (ParseException e) {
                System.out.println("Error al analizar las fechas. Asegúrese de utilizar el formato DD/MM/YYYY. Ingrese las fechas nuevamente.");
            }
        }
    
        // Mostrar lista de sedes disponibles
        System.out.println("Lista de sedes disponibles: ");
        for (sede sede : sedes) {
            System.out.println(sede.getCodSede() + " - " + sede.getUbicacionSede());
        }
    
        System.out.println("Ingrese el código de la sede elegida: ");
        String codSede = sc.nextLine();
    
        // Verificar que el código de sede ingresado exista en la lista de sedes
        boolean sedeEncontrada = false;
        String ubicacionSede = "";
        for (sede sede : sedes) {
            if (sede.getCodSede().equals(codSede)) {
                sedeEncontrada = true;
                ubicacionSede = sede.getUbicacionSede();
                break;
            }
        }
    
        // Si la sede no fue encontrada, volver a solicitar el código de sede
        while (!sedeEncontrada) {
            System.out.println("Código de sede no encontrado. Por favor, ingrese un código de sede válido: ");
            codSede = sc.nextLine();
            for (sede sede : sedes) {
                if (sede.getCodSede().equals(codSede)) {
                    sedeEncontrada = true;
                    ubicacionSede = sede.getUbicacionSede();
                    break;
                }
            }
        }
    
        // Crear nuevo objeto Cliente y agregarlo a la lista
        cliente nuevoCliente = new cliente(rut, cliente.getNombreCompleto(), cliente.getEdad(), codPlan, descripcionPlan, desde, hasta, codSede, ubicacionSede);
        clientes.remove(cliente);
        clientes.add(nuevoCliente);
        System.out.println("Cliente editado exitosamente");
    }

    // Buscar cliente por rut y mostrar datos del cliente 10.-OPCION
    public void buscarCliente() {

        // Mostrar solo rut y nombre de clientes
        System.out.println("-----------------------------");
        System.out.println("Lista de clientes:");
        mostrarRutNombreClientes();
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el rut del cliente a buscar: ");
        String rut = sc.nextLine();
        
        // Buscar el cliente en la lista de clientes
        cliente cliente = null;
        for (cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                cliente = c;
                break;
            }
        }
        
        // Si el cliente no fue encontrado, volver a solicitar el rut
        while (cliente == null) {
            System.out.println("Rut no encontrado. Por favor, ingrese un rut válido: ");
            rut = sc.nextLine();
            for (cliente c : clientes) {
                if (c.getRut().equals(rut)) {
                    cliente = c;
                    break;
                }
            }
        }
        
        // Mostrar datos del cliente
        System.out.println("Datos del cliente:");
        System.out.println("Rut: " + cliente.getRut());
        System.out.println("Nombre completo: " + cliente.getNombreCompleto());
        System.out.println("Edad: " + cliente.getEdad());
        System.out.println("Código de plan: " + cliente.getCodPlan());
        System.out.println("Descripción de plan: " + cliente.getDescripcionPlan());
        System.out.println("Fecha de inicio: " + cliente.getDesde());
        System.out.println("Fecha de término: " + cliente.getHasta());
        System.out.println("Código de sede: " + cliente.getCodSede());
        System.out.println("Ubicación de sede: " + cliente.getUbicacionSede());
    }
   
    // Contar cantidad de clientes
    public int getCantidadClientes() {
        return clientes.size();
    }

    // Mostrar solo rut y nombre de clientes
    public void mostrarRutNombreClientes() {
        for (cliente cliente : clientes) {
            System.out.println(cliente.getRut() + " - " + cliente.getNombreCompleto());
        }
    }
    /*
     * PLANES
     */

    // Se obtienen los planes
    public List<plan> getPlanes() {
        return planes;
    }

    // Buscar plan
    public plan buscarPlan(String codPlan, String descripcionPlan) {
        for (plan plan : planes) {
            if (plan.getCodPlan().equals(codPlan) && plan.getDescripcionPlan().equals(descripcionPlan)) {
                return plan;
            }
        }
        return null;
    }

    // Mostrar planes
    public void mostrarPlanes() {
        for (plan plan : planes) {
            System.out.println(plan.getCodPlan() + " - " + plan.getDescripcionPlan());
        }
    }

    // Agregar plan 4.-OPCION
    public void agregarPlan() {

        //Mostar lista de planes
        System.out.println("-----------------------------");
        System.out.println("Lista de planes: ");
        mostrarPlanes();
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código del plan: ");
        String codPlan = sc.nextLine();

        System.out.println("Ingrese la descripción del plan: ");
        String descripcionPlan = sc.nextLine();

        plan nuevoPlan = new plan(codPlan, descripcionPlan);
        planes.add(nuevoPlan);

        System.out.println("Plan agregado exitosamente");
    }

    // Quitar plan solo si no hay clientes asociados 5.-OPCION
    public void quitarPlan() {

        // Mostrar lista de planes
        System.out.println("-----------------------------");
        System.out.println("Lista de planes: ");
        mostrarPlanes();
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código del plan a quitar: ");
        String codPlan = sc.nextLine();

        // Buscar el plan en la lista de planes
        plan plan = null;
        for (plan p : planes) {
            if (p.getCodPlan().equals(codPlan)) {
                plan = p;
                break;
            }
        }

        // Si el plan no fue encontrado, volver a solicitar el código de plan
        while (plan == null) {
            System.out.println("Código de plan no encontrado. Por favor, ingrese un código de plan válido: ");
            codPlan = sc.nextLine();
            for (plan p : planes) {
                if (p.getCodPlan().equals(codPlan)) {
                    plan = p;
                    break;
                }
            }
        }

        // Verificar que no haya clientes asociados al plan
        boolean clientesAsociados = false;
        for (cliente cliente : clientes) {
            if (cliente.getCodPlan().equals(codPlan)) {
                clientesAsociados = true;
                break;
            }
        }

        // Si hay clientes asociados, no se puede eliminar el plan
        if (clientesAsociados) {
            System.out.println("No se puede eliminar el plan porque hay clientes asociados");
        } else {
            // Eliminar el plan de la lista
            planes.remove(plan);
            System.out.println("Plan eliminado exitosamente");
        }
    }

    // Cambiar plan de un cliente y sus fechas de inicio y término 6.-OPCION
    public void cambiarPlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el rut del cliente a cambiar de plan: ");
        String rut = sc.nextLine();

        // Buscar el cliente en la lista de clientes
        cliente cliente = null;
        for (cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                cliente = c;
                break;
            }
        }

        // Si el cliente no fue encontrado, volver a solicitar el rut
        while (cliente == null) {
            System.out.println("Rut no encontrado. Por favor, ingrese un rut válido: ");
            rut = sc.nextLine();
            for (cliente c : clientes) {
                if (c.getRut().equals(rut)) {
                    cliente = c;
                    break;
                }
            }
        }

        // Mostrar lista de planes disponibles
        System.out.println("Lista de planes disponibles: ");
        for (plan plan : planes) {
            System.out.println(plan.getCodPlan() + " - " + plan.getDescripcionPlan());
        }

        System.out.println("Ingrese el código del plan elegido: ");
        String codPlan = sc.nextLine();

        // Obtener la descripción del plan elegido
        String descripcionPlan = "";
        for (plan plan : planes) {
            if (plan.getCodPlan().equals(codPlan)) {
                descripcionPlan = plan.getDescripcionPlan();
                break;
            }
        }

        // Cambiar el plan del cliente
        cliente.setCodPlan(codPlan);
        cliente.setDescripcionPlan(descripcionPlan);

        System.out.println("Plan cambiado exitosamente");

        // Cambiar las fechas de inicio y término del cliente
        System.out.println("Ingrese la nueva fecha de inicio (DD/MM/AAAA): ");
        String desde = sc.nextLine();

        System.out.println("Ingrese la nueva fecha de término (DD/MM/AAAA): ");
        String hasta = sc.nextLine();

        cliente.setDesde(desde);
        cliente.setHasta(hasta);

        System.out.println("Fechas cambiadas exitosamente");
    }


      /*
     * SEDES
     */
 
    // Se obtienen las sedes
    public List<sede> getSedes() {
        return sedes;
    }

    // Buscar sede
    public sede buscarSede(String codSede, String ubicacionSede) {
        for (sede sede : sedes) {
            if (sede.getCodSede().equals(codSede) && sede.getUbicacionSede().equals(ubicacionSede)) {
                return sede;
            }
        }
        return null;
    }

    // Mostrar sedes
    public void mostrarSedes() {
        for (sede sede : sedes) {
            System.out.println(sede.getCodSede() + " - " + sede.getUbicacionSede());
        }
    }

    // Agregar sede 7.-OPCION
    public void agregarSede() {

        // Mostrar lista de sedes
        System.out.println("-----------------------------");
        System.out.println("Lista de sedes: ");
        mostrarSedes();
        System.out.println("-----------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código de la sede: ");
        String codSede = sc.nextLine();

        System.out.println("Ingrese la ubicación de la sede: ");
        String ubicacionSede = sc.nextLine();

        sede nuevaSede = new sede(codSede, ubicacionSede);
        sedes.add(nuevaSede);

        System.out.println("Sede agregada exitosamente");
    }

    // Quitar sede solo si no hay clientes asociados 8.-OPCION
    public void quitarSede() {

        // Mostrar lista de sedes
        System.out.println("-----------------------------");
        System.out.println("Lista de sedes: ");
        mostrarSedes();
        System.out.println("-----------------------------");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código de la sede a quitar: ");
        String codSede = sc.nextLine();

        // Buscar la sede en la lista de sedes
        sede sede = null;
        for (sede s : sedes) {
            if (s.getCodSede().equals(codSede)) {
                sede = s;
                break;
            }
        }

        // Si la sede no fue encontrada, volver a solicitar el código de sede
        while (sede == null) {
            System.out.println("Código de sede no encontrado. Por favor, ingrese un código de sede válido: ");
            codSede = sc.nextLine();
            for (sede s : sedes) {
                if (s.getCodSede().equals(codSede)) {
                    sede = s;
                    break;
                }
            }
        }

        // Verificar que no haya clientes asociados a la sede
        boolean clientesAsociados = false;
        for (cliente cliente : clientes) {
            if (cliente.getCodSede().equals(codSede)) {
                clientesAsociados = true;
                break;
            }
        }

        // Si hay clientes asociados, no se puede eliminar la sede
        if (clientesAsociados) {
            System.out.println("No se puede eliminar la sede porque hay clientes asociados");
        } else {
            // Eliminar la sede de la lista
            sedes.remove(sede);
            System.out.println("Sede eliminada exitosamente");
        }
    }

    // Cambiar sede de un cliente 9.-OPCION
    public void cambiarSede() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el rut del cliente a cambiar de sede: ");
        String rut = sc.nextLine();

        // Buscar el cliente en la lista de clientes
        cliente cliente = null;
        for (cliente c : clientes) {
            if (c.getRut().equals(rut)) {
                cliente = c;
                break;
            }
        }

        // Si el cliente no fue encontrado, volver a solicitar el rut
        while (cliente == null) {
            System.out.println("Rut no encontrado. Por favor, ingrese un rut válido: ");
            rut = sc.nextLine();
            for (cliente c : clientes) {
                if (c.getRut().equals(rut)) {
                    cliente = c;
                    break;
                }
            }
        }

        // Mostrar lista de sedes disponibles
        System.out.println("Lista de sedes disponibles: ");
        for (sede sede : sedes) {
            System.out.println(sede.getCodSede() + " - " + sede.getUbicacionSede());
        }

        System.out.println("Ingrese el código de la sede elegida: ");
        String codSede = sc.nextLine();

        // Obtener la ubicación de la sede elegida
        String ubicacionSede = "";
        for (sede sede : sedes) {
            if (sede.getCodSede().equals(codSede)) {
                ubicacionSede = sede.getUbicacionSede();
                break;
            }
        }

        // Cambiar la sede del cliente
        cliente.setCodSede(codSede);
        cliente.setUbicacionSede(ubicacionSede);

        System.out.println("Sede cambiada exitosamente");
    }

}
