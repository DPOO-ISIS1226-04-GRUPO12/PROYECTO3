package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.CategoriaVehiculo;
import logica.Tarifario;

public class CategoriaVehiculoTest {

    private CategoriaVehiculo categoriaVehiculo;

    @BeforeEach
    public void setUp() {
        // Configuración inicial antes de cada prueba
        categoriaVehiculo = new CategoriaVehiculo("CategoriaTest", 50.0, null);
    }

    @Test
    public void testGetNombreCategoria() {
        String nombreCategoria = categoriaVehiculo.getNombreCategoria();
        assertEquals("CategoriaTest", nombreCategoria);
    }

    @Test
    public void testGetTarifario() {
        Tarifario tarifario = categoriaVehiculo.getTarifario();
        assertNotNull(tarifario);
    }

    @Test
    public void testGetTarifaDiaria() {
        double tarifaDiaria = categoriaVehiculo.getTarifaDiaria();
        assertEquals(50.0, tarifaDiaria, 0.001); // Ajusta el valor esperado según tus necesidades
    }
}

