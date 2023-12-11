package logica;

import java.util.ArrayList;

public class AdministradorGeneral extends UsuarioGenerico {
	private Seguro seguro;
	private String nombre;
	
   public AdministradorGeneral(String nombre, String usuario, String contraseña, String tipoUsuario) 
   {
	super(usuario, contraseña, tipoUsuario);
	this.nombre = nombre;
	}
	
   
   public void darDeBajaVehiculo(ArrayList<Vehiculo> vehiculos,Vehiculo vehiculo,Sede sede) 
   {
	  vehiculos.remove(vehiculo);
	  sede.getInventarioVehiculos().remove(vehiculo);
   }
   
   
   public double administrarSeguro (ArrayList<Seguro> seguros,ArrayList<Integer> posiciones,boolean conSeguro,Vehiculo vehiculo) {

	  if(conSeguro) {
		 double precioseguros=0;
		 vehiculo.setConSeguro(conSeguro);
		 for (Integer integer : posiciones) {
			 precioseguros +=seguros.get(integer-1).getPrecio();
	     }
		 return precioseguros;
	  }
	  else {
		return 0;
	  }
	 
   }
   
   
   public Seguro crearSeguro(String nombreString , int precio) 
   {
	 seguro = new Seguro(nombre, 0);
	 return seguro;
   }
 
 
   public void registrarCompraVehiculo(Vehiculo vehiculo,Sede sede,ArrayList<Vehiculo> vehiculos) {
	 vehiculos.add(vehiculo);
	 sede.getInventarioVehiculos().add(vehiculo);
   }
}
