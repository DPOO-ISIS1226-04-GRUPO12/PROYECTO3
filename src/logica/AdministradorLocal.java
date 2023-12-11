package logica;

public class AdministradorLocal extends UsuarioGenerico {
	
 private String nombre;
 private String sede;
 private String usuario;
 private String contrasenia;
	
 public AdministradorLocal(String usuario, String contraseña, String tipoUsuario, String nombre, String sede) {
		super(usuario, contraseña, tipoUsuario);
		this.usuario = usuario;
		this.contrasenia = contraseña;
		this.nombre = nombre;
		this.sede = sede;
	}
	
   public String getSede()
   {
	 return sede;
   }
   
   
   public void entregaVehiculo() 
   { 
	 
   }

   
   public String getUsuario() 
   {
	return usuario;
   }

   
   public String getContrasenia()
   {
	return contrasenia;
   }

   
   public String getNombre() 
   {
	return nombre;
   }
 
}
