package com.GRH.myapp.GRH_myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.GRH.myapp.GRH_myapp.service.SeguimientosSolicitudesService;
import com.GRH.myapp.GRH_myapp.service.TypeRequestService;
import com.GRH.myapp.GRH_myapp.service.StatusSolicitudesService;

import com.GRH.myapp.GRH_myapp.model.TypeRequest;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.model.SeguimientosSolicitudes;
import com.GRH.myapp.GRH_myapp.model.StatusSolicitudes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class GrhMyappApplication implements CommandLineRunner {

    @Autowired
    private SeguimientosSolicitudesService SeguimientosSolicitudesService;
    @Autowired
    private TypeRequestService TypeRequestService;
    @Autowired
    private StatusSolicitudesService statusSolicitudesService;



    public static void main(String[] args) {
        SpringApplication.run(GrhMyappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nBienvenido al sistema de gestión de solicitudes.");
            System.out.println("1. Crear una solicitud");
            System.out.println("2. Listar todas las solicitudes");
            System.out.println("3. Buscar una solicitud por ID");
            System.out.println("4. Actualizar una solicitud");
            System.out.println("5. Eliminar una solicitud");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            
            switch (option) {
                case 1:
                    createRequest(scanner);
                    break;
                case 2:
                    printAllOrderedRequests();
                    break;
                case 3:
                    findRequestById(scanner);
                    break;
                case 4:
                    updateRequest(scanner);
                    break;
                case 5:
                    deleteRequest(scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
        scanner.close();
    }

    private void createRequest(Scanner scanner) {
        try {
            SeguimientosSolicitudes seguimientosSolicitudes = new SeguimientosSolicitudes();

            System.out.print("Ingrese el ID del tipo de solicitud: ");
            int typeRequestId = scanner.nextInt();
            scanner.nextLine(); 

            Optional<TypeRequest> optionalTypeRequest = TypeRequestService.findById(typeRequestId);
            if (!optionalTypeRequest.isPresent()) {
                System.out.println("Tipo de solicitud no encontrado. Asegúrate de que el ID sea correcto.");
                return; // Salir del método si el tipo de solicitud no se encuentra
            }
            TypeRequest typeRequest = optionalTypeRequest.get();

            // Obtener el StatusSolicitudes
            //System.out.print("Ingrese el código del estado de la solicitud: ");
            int statusCodeId = 1; //scanner.nextInt();
            //scanner.nextLine();

            Optional<StatusSolicitudes> optionalStatus = statusSolicitudesService.findById(statusCodeId);
            if (!optionalStatus.isPresent()) {
                System.out.println("Estado de solicitud no encontrado. Asegúrate de que el ID sea correcto.");
                return; // Salir del método si el estado no se encuentra
            }
            StatusSolicitudes statusSolicitudes = optionalStatus.get();


            // Llenar los datos en SeguimientosSolicitudes
            seguimientosSolicitudes.setIdRequest(typeRequestId); // Asignar ID del tipo de solicitud
            seguimientosSolicitudes.setStatusCode(statusSolicitudes); // Asignar el estado

            seguimientosSolicitudes.setVigente(true); // Establecer como vigente (puedes cambiar la lógica según lo necesites)
            seguimientosSolicitudes.setCreationDate(new Date()); // Asignar la fecha actual como fecha de creación


            // Guardar la solicitud en la base de datos
            SeguimientosSolicitudesService.save(seguimientosSolicitudes);
            System.out.println("¡Solicitud creada con éxito!");

        } catch (Exception e) {
            System.out.println("Error al crear la solicitud: " + e.getMessage());
        }
    }
    public void printAllOrderedRequests() {
        // Definir el campo por el cual se quiere ordenar, por ejemplo, "id" o "fechaSolicitud"
        String orderByField = "idGuid"; 
        boolean asc = true;  // true para ascendente, false para descendente

        // Obtener todas las solicitudes ordenadas
        List<SeguimientosSolicitudes> solicitudes = SeguimientosSolicitudesService.findAllOrdered(orderByField, asc);

        // Imprimir los registros en la consola
        solicitudes.forEach(solicitud -> {
            System.out.println(solicitud.toString());
        });
    }
    private void listRequests() {
        List<SeguimientosSolicitudes> requests = SeguimientosSolicitudesService.findAll();
        if (requests.isEmpty()) {
            System.out.println("No hay solicitudes disponibles.");
        } else {
            System.out.println("Lista de Solicitudes:");
            for (SeguimientosSolicitudes request : requests) {
                System.out.println(request.toString()); // Asegúrate de que `toString` esté bien implementado
            }
        }
    }
    private void findRequestById(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea buscar: ");
        int requestId = scanner.nextInt();
        scanner.nextLine(); 

        Optional<SeguimientosSolicitudes> optionalRequest = SeguimientosSolicitudesService.findById(requestId);
        if (optionalRequest.isPresent()) {
            SeguimientosSolicitudes request = optionalRequest.get();
            System.out.println("Solicitud encontrada: " + request.toString());
        } else {
            System.out.println("Solicitud no encontrada con ID: " + requestId);
        }
    }
    /*
    private void updateRequest(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea actualizar: ");
        int requestId = scanner.nextInt();
        scanner.nextLine(); 

        Optional<SeguimientosSolicitudes> optionalRequest = SeguimientosSolicitudesService.findById(requestId);
        if (optionalRequest.isPresent()) {
            SeguimientosSolicitudes request = optionalRequest.get();

            System.out.print("¿Está vigente? (true/false): ");
            boolean vigente = scanner.nextBoolean();
            request.setVigente(vigente);

            // Guardar cambios
            SeguimientosSolicitudesService.save(request);
            System.out.println("Solicitud actualizada con éxito.");
        } else {
            System.out.println("Solicitud no encontrada con ID: " + requestId);
        }
    }
    */
    private void updateRequest(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea actualizar: ");
        int requestId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Optional<SeguimientosSolicitudes> optionalRequest = SeguimientosSolicitudesService.findById(requestId);
        if (optionalRequest.isPresent()) {
            SeguimientosSolicitudes request = optionalRequest.get();

            // Actualizar el campo 'vigente'
            System.out.print("¿Está vigente? (true/false): ");
            boolean vigente = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea
            request.setVigente(vigente);

            // Actualizar el campo 'id_type_request'
            
            System.out.print("Ingrese el nuevo ID del tipo de solicitud: ");
            int idTypeRequest = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            request.setIdRequest(idTypeRequest);                   

            Optional<TypeRequest> optionalTypeRequest = TypeRequestService.findById(idTypeRequest);
            if (!optionalTypeRequest.isPresent()) {
                System.out.println("Tipo de solicitud no encontrado. Asegúrate de que el ID sea correcto.");
                return; // Salir del método si el tipo de solicitud no se encuentra
            }

            // Usar el método updateRequest del service para aplicar las restricciones
            try {
                SeguimientosSolicitudes updatedRequest = SeguimientosSolicitudesService.updateRequest(requestId, request);
                System.out.println("Solicitud actualizada con éxito: " + updatedRequest.toString());
            } catch (Exception e) {
                System.out.println("Error al actualizar la solicitud: " + e.getMessage());
            }
        } else {
            System.out.println("Solicitud no encontrada con ID: " + requestId);
        }
    }

    
    private void deleteRequest(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea eliminar: ");
        int requestId = scanner.nextInt();
        scanner.nextLine(); 

        Optional<SeguimientosSolicitudes> optionalRequest = SeguimientosSolicitudesService.findById(requestId);
        if (optionalRequest.isPresent()) {
            SeguimientosSolicitudesService.deleteById(requestId);
            System.out.println("Solicitud eliminada con éxito.");
        } else {
            System.out.println("Solicitud no encontrada con ID: " + requestId);
        }
    }


}

/*
    private void listRequests() {
        List<SeguimientosSolicitudes> requests = requestService.findAll();
        if (requests.isEmpty()) {
            System.out.println("No hay solicitudes.");
        } else {
            requests.forEach(System.out::println);
        }
    }

    private void findRequestById(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            System.out.println(request.get());
        } else {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
        }
    }

    private void updateRequest(Scanner scanner) {
        /*System.out.print("Ingrese el ID de la solicitud que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> requestOpt = requestService.findById(id);
        if (!requestOpt.isPresent()) {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
            return;
        }

        Request request = requestOpt.get();

        System.out.print("Ingrese el nuevo comentario: ");
        String comment = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de inicio (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);

        System.out.print("Ingrese la nueva fecha final (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

        request.setComment(comment);
        request.setDateStart(startDate);
        request.setDateFinal(endDate);

        // Actualizar en la base de datos
        requestService.save(request);
        System.out.println("¡Solicitud actualizada con éxito!");*/
 /*   }

    private void deleteRequest(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> requestOpt = requestService.findById(id);
        if (!requestOpt.isPresent()) {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
            return;
        }

        requestService.deleteById(id);
        System.out.println("¡Solicitud eliminada con éxito!");
    }
}

/*package com.GRH.myapp.GRH_myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.GRH.myapp.GRH_myapp.model.Request;
import com.GRH.myapp.GRH_myapp.service.RequestService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class GrhMyappApplication implements CommandLineRunner {

    @Autowired
    private RequestService requestService;

    public static void main(String[] args) {
        SpringApplication.run(GrhMyappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nBienvenido al sistema de gestión de solicitudes.");
            System.out.println("1. Crear una solicitud");
            System.out.println("2. Listar todas las solicitudes");
            System.out.println("3. Buscar una solicitud por ID");
            System.out.println("4. Actualizar una solicitud");
            System.out.println("5. Eliminar una solicitud");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            
            switch (option) {
                case 1:
                    createRequest(scanner);
                    break;
                case 2:
                    listRequests();
                    break;
                case 3:
                    findRequestById(scanner);
                    break;
                case 4:
                    updateRequest(scanner);
                    break;
                case 5:
                    deleteRequest(scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
        scanner.close();
    }

    private void createRequest(Scanner scanner) {
        try {
            Request request = new Request();

            System.out.print("Ingrese el ID del empleado: ");
            int employeeId = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            
            System.out.print("Ingrese el comentario: ");
            String comment = scanner.nextLine();

            System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            
            System.out.print("Ingrese la fecha final (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

            // Puedes agregar lógica para asociar empleados y tipo de solicitud
            // Por simplicidad, omito estas asociaciones aquí, pero deben ser manejadas con lógica adecuada.

            request.setDateRequest(new Date());
            request.setComment(comment);
            request.setDateStart(startDate);
            request.setDateFinal(endDate);
            
            // Guardar la solicitud en la base de datos
            requestService.save(request);
            System.out.println("¡Solicitud creada con éxito!");

        } catch (Exception e) {
            System.out.println("Error al crear la solicitud: " + e.getMessage());
        }
    }

    private void listRequests() {
        List<Request> requests = requestService.findAll();
        if (requests.isEmpty()) {
            System.out.println("No hay solicitudes.");
        } else {
            requests.forEach(System.out::println);
        }
    }

    private void findRequestById(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            System.out.println(request.get());
        } else {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
        }
    }

    private void updateRequest(Scanner scanner) {
        /*System.out.print("Ingrese el ID de la solicitud que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> requestOpt = requestService.findById(id);
        if (!requestOpt.isPresent()) {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
            return;
        }

        Request request = requestOpt.get();

        System.out.print("Ingrese el nuevo comentario: ");
        String comment = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de inicio (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);

        System.out.print("Ingrese la nueva fecha final (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

        request.setComment(comment);
        request.setDateStart(startDate);
        request.setDateFinal(endDate);

        // Actualizar en la base de datos
        requestService.save(request);
        System.out.println("¡Solicitud actualizada con éxito!");*/
    /*}

    private void deleteRequest(Scanner scanner) {
        System.out.print("Ingrese el ID de la solicitud que desea eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Optional<Request> requestOpt = requestService.findById(id);
        if (!requestOpt.isPresent()) {
            System.out.println("No se encontró ninguna solicitud con ese ID.");
            return;
        }

        requestService.deleteById(id);
        System.out.println("¡Solicitud eliminada con éxito!");
    }
}
*/
/*
@SpringBootApplication
public class GrhMyappApplication implements CommandLineRunner {

    @Autowired
    private AreaService areaService;

    public static void main(String[] args) {
        SpringApplication.run(GrhMyappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            System.out.println("Seleccione una operación CRUD:");
            System.out.println("1. Crear un área");
            System.out.println("2. Leer todas las áreas");
            System.out.println("3. Leer un área por ID");
            System.out.println("4. Actualizar un área");
            System.out.println("5. Eliminar un área");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();

            switch (option) {
                case "1": // Crear un área
                    System.out.print("Ingrese el nombre del área: ");
                    String nameArea = scanner.nextLine();
                    Area newArea = new Area();
                    newArea.setNameArea(nameArea);
                    areaService.save(newArea);
                    System.out.println("Área creada exitosamente.");
                    break;

                case "2": // Leer todas las áreas
                    System.out.println("Lista de todas las áreas:");
                    areaService.findAll().forEach(System.out::println);
                    break;

                case "3": // Leer un área por ID
                    System.out.print("Ingrese el ID del área: ");
                    Integer idRead = Integer.parseInt(scanner.nextLine());
                    Optional<Area> area = areaService.findById(idRead);
                    if (area.isPresent()) {
                        System.out.println("Área encontrada: " + area.get());
                    } else {
                        System.out.println("Área no encontrada.");
                    }
                    break;

                case "4": // Actualizar un área
                    System.out.print("Ingrese el ID del área que desea actualizar: ");
                    Integer idUpdate = Integer.parseInt(scanner.nextLine());
                    Optional<Area> areaToUpdate = areaService.findById(idUpdate);
                    if (areaToUpdate.isPresent()) {
                        System.out.print("Ingrese el nuevo nombre del área: ");
                        String updatedName = scanner.nextLine();
                        Area updatedArea = areaToUpdate.get();
                        updatedArea.setNameArea(updatedName);
                        areaService.save(updatedArea);
                        System.out.println("Área actualizada exitosamente.");
                    } else {
                        System.out.println("Área no encontrada.");
                    }
                    break;

                case "5": // Eliminar un área
                    System.out.print("Ingrese el ID del área que desea eliminar: ");
                    Integer idDelete = Integer.parseInt(scanner.nextLine());
                    if (areaService.findById(idDelete).isPresent()) {
                        areaService.deleteById(idDelete);
                        System.out.println("Área eliminada exitosamente.");
                    } else {
                        System.out.println("Área no encontrada.");
                    }
                    break;

                case "6": // Salir
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta.");
                    break;
            }
        } while (!option.equals("6"));

        scanner.close();
    }
}
*/