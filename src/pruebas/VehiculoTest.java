package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.CategoriaVehiculo;
import logica.Sede;
import logica.Vehiculo;

import java.util.Date;

public class VehiculoTest {

    private Vehiculo vehiculo;

    @BeforeEach
    public void setUp() {
        CategoriaVehiculo categoria = new CategoriaVehiculo("CategoriaTest", 50.0, null);
        Sede sede = new Sede(null,null,null, null, null, null);
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        vehiculo = new Vehiculo(1, false, sede, "SedeInicial", 5, "ABC123", "Modelo1", "Rojo", "Automático", categoria, fechaInicio, fechaFinal, true, true, "ruta/imagen.jpg", null, 0);
    }

    @Test
    public void testGetCategoria() {
        CategoriaVehiculo categoria = vehiculo.getCategoria();
        assertNotNull(categoria);
    }

    @Test
    public void testGetIdVehiculo() {
        int idVehiculo = vehiculo.getIdVehiculo();
        assertEquals(1, idVehiculo);
    }

    @Test
    public void testGetFechaInicio() {
        Date fechaInicio = vehiculo.getFechaInicio();
        assertNotNull(fechaInicio);
    }

    @Test
    public void testSetFechaInicio() {
        Date nuevaFechaInicio = new Date();
        vehiculo.setFechaInicio(nuevaFechaInicio);
        assertEquals(nuevaFechaInicio, vehiculo.getFechaInicio());
    }

    @Test
    public void testGetFechaFinal() {
        Date fechaFinal = vehiculo.getFechaFinal();
        assertNotNull(fechaFinal);
    }

    @Test
    public void testSetFechaFinal() {
        Date nuevaFechaFinal = new Date();
        vehiculo.setFechaFinal(nuevaFechaFinal);
        assertEquals(nuevaFechaFinal, vehiculo.getFechaFinal());
    }

    @Test
    public void testGetSedeActual() {
        Sede sedeActual = vehiculo.getSedeActual();
        assertNotNull(sedeActual);
    }



    @Test
    public void testSetConSeguro() {
        vehiculo.setConSeguro(false);
        assertFalse(vehiculo.isConSeguro());
    }


}

