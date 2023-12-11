package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Empleado;
import logica.Reserva;
import logica.Vehiculo;

import java.util.Date;

public class EmpleadoTest {

    private Empleado empleado;

    @BeforeEach
    public void setUp() {
        // Configuración inicial antes de cada prueba
        empleado = new Empleado("usuario", "contraseña", "Empleado", "NombreEmpleado", "SedeEmpleado");
    }

    @Test
    public void testGetNombre() {
        String nombre = empleado.getNombre();
        assertEquals("NombreEmpleado", nombre);
    }

    @Test
    public void testGetSede() {
        String sede = empleado.getSede();
        assertEquals("SedeEmpleado", sede);
    }

    @Test
    public void testAdministrarRecogidaCliente() {
        Reserva reserva = new Reserva(null, null, null, null, 0, 0, 0, 0, null, null, null, false, null, null/* Parámetros del constructor de Reserva */);
        empleado.administarRecogidaCliente(reserva);
        assertTrue(reserva.isVehiculoRecogido());
    }

    @Test
    public void testAgregarConductor() {
        Reserva reserva = new Reserva(null, null, null, null, 0, 0, 0, 0, null, null, null, false, null, null);
        double valorAdicionalConductor = empleado.agregarConductor(50.0, reserva, 123, "PaisConductor", new Date());
        assertEquals(50.0, valorAdicionalConductor, 0.001);
        assertNotNull(reserva.getConductorAdicional());
    } 
    

    @Test
    public void testDevolucionCocheCliente() {
        Reserva reserva = new Reserva(null, null, null, null, 0, 0, 0, 0, null, null, null, false, null, null);
        Vehiculo vehiculo = new Vehiculo(0, false, null, null, 0, null, null, null, null, null, null, null, false, false, null, null, 0);
        vehiculo.setFechaInicio(new Date());
        vehiculo.setFechaFinal(new Date());
        vehiculo.setAlquilado(true);
        reserva.setVehiculo(vehiculo);
        empleado.devolucionCocheCliente(reserva);

        assertFalse(reserva.isVehiculoRecogido());
        assertNull(vehiculo.getFechaInicio());
        assertNull(vehiculo.getFechaFinal());
    
      } 
    } 