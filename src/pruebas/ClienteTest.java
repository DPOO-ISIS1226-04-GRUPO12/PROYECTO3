package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Cliente;
import logica.LicienciaConducion;
import logica.Reserva;
import logica.Vehiculo;

import java.util.ArrayList;
import java.util.Date;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        LicienciaConducion licencia = new LicienciaConducion(0, null, null);
        cliente = new Cliente("NombreCliente", "NacionalidadTest", "123456789", "01/01/2000", "usuario", "contraseña", "Cliente", reservas, licencia, null);
    }

    @Test
    public void testGetNombre() {
        String nombre = cliente.getNombre();
        assertEquals("NombreCliente", nombre);
    }

    @Test
    public void testGetMedioDePago() {
        assertNull(cliente.getMedioDePago()); 
    }

    @Test
    public void testCrearReserva() {
        Vehiculo vehiculo = new Vehiculo(0, false, null, null, 0, null, null, null, null, null, null, null, false, false, null, null, 0);
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        Reserva reserva = cliente.crearReserva(vehiculo, fechaInicio, fechaFinal, 50.0, 100.0, "SedeDevolver", "SedeRecoger");

        assertNotNull(reserva);
        assertEquals(1, cliente.getReservas().size());
        assertTrue(cliente.getReservas().contains(reserva));
    }

    @Test
    public void testGetReservas() {
        ArrayList<Reserva> reservas = cliente.getReservas();
        assertNotNull(reservas);
    }

    @Test
    public void testGetNumeroTarjeta() {
        int numeroTarjeta = cliente.getNumeroTarjeta();
        assertEquals(0, numeroTarjeta); 
    }

    @Test
    public void testGetFechaNac() {
        String fechaNac = cliente.getFechaNac();
        assertEquals("01/01/2000", fechaNac);
    }


}

