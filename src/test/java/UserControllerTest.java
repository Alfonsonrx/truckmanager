import com.diamondogs.trucksapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User section functionality.
 * Tests user data operations and business logic.
 */
public class UserControllerTest {

    @Test
    @DisplayName("La clase de usuario deberia guardar los datos correctamente")
    public void testUserDataSaving() {
        User user = new User(
                1,
                "Carlos Rodríguez",
                "crodriguez",
                "Conductor",
                "+56912345678",
                "securePassword123"
        );

        assertAll("Verificar guardado de datos de usuario",
                () -> assertEquals(1, user.getId()),
                () -> assertEquals("Carlos Rodríguez", user.getName()),
                () -> assertEquals("crodriguez", user.getUsername()),
                () -> assertEquals("Conductor", user.getRole()),
                () -> assertEquals("+56912345678", user.getPhone()),
                () -> assertEquals("securePassword123", user.getPassword())
        );
    }

    @Test
    @DisplayName("Username should not be empty")
    public void testUsernameNotEmpty() {
        User user = new User();
        user.setUsername("validuser");

        assertNotNull(user.getUsername());
        assertFalse(user.getUsername().isEmpty(), "El username no debería estar vacío");
    }

    @Test
    @DisplayName("Numeros de telefono pueden ser null o vacios")
    public void testPhoneNumberCanBeNull() {
        User user = new User();
        user.setPhone(null);

        assertNull(user.getPhone(), "El teléfono debería poder ser null");
    }
}
