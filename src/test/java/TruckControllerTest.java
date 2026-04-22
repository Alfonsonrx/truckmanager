import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.repositories.TruckRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TruckController business logic.
 * Note: Tests focus on logic that doesn't require Swing components.
 * Swing UI tests would require integration testing framework.
 */
public class TruckControllerTest {

    //Probamos si funciona el aviso de 5000 km (Debe ser mayor a 5000)
    @Test
    @DisplayName("Deberoa retornar que no requiere mantenimiento")
    public void testMaintenance() {
        Truck truck = new Truck();
        truck.setKilometers(5000);

        boolean result = truck.requiresMaintenance();

        //resultados esperados
        assertFalse(result, "Un camión con menos de 5000 km NO debería requerir mantenimiento");

    }

    @Test
    @DisplayName("La clase de mantenimiento deberia guardar los datos correctamente")
    public void testTruckDataValidation() {
        Date maintenanceDate = Date.valueOf("2024-01-15");
        Truck truck = new Truck(
                1,
                "AB-C1-23",
                "Volvo",
                "FH16",
                "Rojo",
                2020,
                maintenanceDate,
                25000,
                5
        );

        // Validar que todos los campos requeridos están presentes
        assertAll("Validación de datos del camión",
                () -> assertNotNull(truck.getPlate(), "La patente no debería ser null"),
                () -> assertFalse(truck.getPlate().isEmpty(), "La patente no debería estar vacía"),
                () -> assertNotNull(truck.getBrand(), "La marca no debería ser null"),
                () -> assertNotNull(truck.getModel(), "El modelo no debería ser null"),
                () -> assertTrue(truck.getYear() > 1900, "El año debería ser válido"),
                () -> assertTrue(truck.getKilometers() >= 0, "Los kilómetros no deberían ser negativos")
        );
    }

    @Test
    @DisplayName("Los kilometros no deberian ser negativos")
    public void testTruckKilometersNotNegative() {
        Truck truck = new Truck();
        truck.setKilometers(-1000);

        //  Si se ingresa negativo, lo cambia a positivo
        assertTrue(truck.getKilometers() >= 0, "Los kilómetros deberían ser mayores o iguales a 0");
    }

    @Test
    @DisplayName("Deberia identificar camiones que requieran mantencion en una lista")
    public void testIdentifyTrucksRequiringMaintenance() {
        Truck truck1 = new Truck(); // 0 km
        truck1.setId(1);
        truck1.setPlate("AB-C1-23");
        truck1.setKilometers(3000);

        Truck truck2 = new Truck(); // Needs maintenance
        truck2.setId(2);
        truck2.setPlate("AZ-C1-23");
        truck2.setKilometers(7500);

        Truck truck3 = new Truck(); // At boundary
        truck3.setId(3);
        truck3.setPlate("AB-D1-23");
        truck3.setKilometers(5000);

        Truck truck4 = new Truck(); // Needs maintenance
        truck4.setId(4);
        truck4.setPlate("QE-C1-43");
        truck4.setKilometers(15000);

        List<Truck> trucks = Arrays.asList(truck1, truck2, truck3, truck4);

        // Identificar camiones que necesitan mantenimiento
        List<Truck> trucksNeedingMaintenance = trucks.stream()
                .filter(Truck::requiresMaintenance)
                .toList();

        assertAll("Verificar identificación de camiones para mantenimiento",
                () -> assertEquals(2, trucksNeedingMaintenance.size(), "Deberían ser 2 camiones"),
                () -> assertTrue(trucksNeedingMaintenance.contains(truck2), "Debería incluir HIGH-002"),
                () -> assertTrue(trucksNeedingMaintenance.contains(truck4), "Debería incluir VERYHIGH-004"),
                () -> assertFalse(trucksNeedingMaintenance.contains(truck1), "NO debería incluir LOW-001"),
                () -> assertFalse(trucksNeedingMaintenance.contains(truck3), "NO debería incluir MID-003")
        );
    }

    @Test
    @DisplayName("Deberia contar correctamente los camiones con mantencion necesaria")
    public void testCountTrucksByMaintenanceStatus() {
        Truck truck1 = new Truck();
        truck1.setKilometers(3000);

        Truck truck2 = new Truck();
        truck2.setKilometers(7500);

        Truck truck3 = new Truck();
        truck3.setKilometers(5000);

        Truck truck4 = new Truck();
        truck4.setKilometers(12000);

        List<Truck> trucks = Arrays.asList(truck1, truck2, truck3, truck4);

        long needingMaintenance = trucks.stream()
                .filter(Truck::requiresMaintenance)
                .count();

        long notNeedingMaintenance = trucks.stream()
                .filter(t -> !t.requiresMaintenance())
                .count();

        assertAll("Contar camiones por estado de mantenimiento",
                () -> assertEquals(2, needingMaintenance, "2 camiones necesitan mantenimiento"),
                () -> assertEquals(2, notNeedingMaintenance, "2 camiones no necesitan mantenimiento")
        );
    }

    @Test
    @DisplayName("Camiones con alto kilometraje deberia detectar y generar alerta")
    public void testHighKilometersTriggerMaintenanceWorkflow() {
        Truck truck = new Truck();
        truck.setId(10);
        truck.setPlate("MA-IN-01");
        truck.setKilometers(12000);

        // Verificar que el camión requiere mantenimiento
        boolean requiresMaintenance = truck.requiresMaintenance();

        // Verificar que la lógica de alerta se activaría
        boolean alertShouldTrigger = truck.getKilometers() > 5000;

        assertAll("Flujo de trabajo de mantenimiento",
                () -> assertTrue(requiresMaintenance, "El camión debería requerir mantenimiento"),
                () -> assertTrue(alertShouldTrigger, "La alerta debería activarse"),
                () -> assertEquals(12000, truck.getKilometers(), "Los kilómetros deberían coincidir")
        );
    }
}
