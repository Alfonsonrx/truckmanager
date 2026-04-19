
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.TrucksPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccionTrucksTest {
    //Probamos si funciona el aviso de 5000 km
    @Test
    public void testMaintenance() {
        Truck truck = new Truck();
        truck.setKilometers(5000);

        boolean result = truck.requiresMaintenance();

        //resultados esperados
        assertFalse(result, "Un camión con menos de 5000 km NO debería requerir mantenimiento");

    }
    @Test
    public void testMockeandoUnaVentana() {
        //Creamos los mocks de las vistas
        VentanaCamion mockVentana = mock(VentanaCamion.class);
        TrucksPanel mockPanel = mock(TrucksPanel.class);

        when(mockVentana.getPatente()).thenReturn("FALSA-123");
        when(mockVentana.getMarca()).thenReturn("Volvo");
        when(mockVentana.getAnio()).thenReturn("2020");
        when(mockVentana.getKilometraje()).thenReturn("1000");

        assertEquals("FALSA-123", mockVentana.getPatente());
    }
}
