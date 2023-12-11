package pruebas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import logica.AdministradorLocal;


public class AdmistradorLocalTest {
	
	private AdministradorLocal admin;
	
	@BeforeEach
	public void setup() {
		 admin = new AdministradorLocal("1", "2", "AdminLocal", "Juan","Bogota");
	}
	
	@Test
	public void getSede() {
		assertEquals("Bogota",admin.getSede());
	}
	
	@Test
	public void getUsuario() {
		assertEquals("1",admin.getUsuario());
	}
	
	@Test
	public void getContrasenia() {
		assertEquals("2",admin.getContrasenia());
	}
	
	@Test
	public void getNombre() 
	   {
		assertEquals("Juan",admin.getNombre());
	   }
}
