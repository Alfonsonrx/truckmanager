import com.diamondogs.trucksapp.model.Maintenance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Maintenance section functionality.
 * Tests maintenance data operations and business logic.
 */
public class MaintenanceControllerTest {

    @Test
    @DisplayName("La clase de mantenimiento deberia guardar los datos correctamente")
    public void testMaintenanceDataSaving() {
        Date maintenanceDate = Date.valueOf("2024-06-15");
        Maintenance maintenance = new Maintenance(
                1,
                5,  // Truck ID
                maintenanceDate,
                "Cambio de aceite",
                "Se realizó cambio de aceite y filtros de aire"
        );

        assertAll("Verificar guardado de datos de mantenimiento",
                () -> assertEquals(1, maintenance.getId()),
                () -> assertEquals(5, maintenance.getTruck(), "El ID del camión debería coincidir"),
                () -> assertEquals(maintenanceDate, maintenance.getDate()),
                () -> assertEquals("Cambio de aceite", maintenance.getMaintenanceType()),
                () -> assertEquals("Se realizó cambio de aceite y filtros de aire", maintenance.getDescription())
        );
    }

    @Test
    @DisplayName("La fecha debería poder ser null para mantenimientos pendientes")
    public void testMaintenanceNullDate() {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(1);
        maintenance.setTruck(3);
        maintenance.setDate(null);
        maintenance.setMaintenanceType("Pendiente");
        maintenance.setDescription("Mantenimiento programado");

        assertNull(maintenance.getDate(), "La fecha debería poder ser null para mantenimientos pendientes");
    }

    @Test
    @DisplayName("La clase mantenimiento deberia permitir descripciones vacias")
    public void testMaintenanceEmptyDescription() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDescription("");

        assertEquals("", maintenance.getDescription(), "La descripción debería poder estar vacía");
    }


}
