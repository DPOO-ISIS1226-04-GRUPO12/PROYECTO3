package logica;

import java.util.Date;

import consola.EmpresaAlquilerVehiculos;

public class Empleado  extends UsuarioGenerico{
	
	private String nombre;
	private String sede;
	
	public Empleado(String usuario, String contraseña, String tipoUsuario, String nombre, String sede) {
		super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.sede = sede;
	}
	
	
	public String getNombre()
	{
		return nombre;
	}
	
	
	public String getSede() {
		return sede;
	}
	
	
	public void administarRecogidaCliente(Reserva reserva) 
    {
		reserva.setVehiculoRecogido(true);
	}
	
	
	public double agregarConductor(double valorAdicionalConductor,Reserva reserva,int numero,String pais,Date fechaVencimiento) {
		LicienciaConducion licienciaConducion = new LicienciaConducion(numero,pais,fechaVencimiento);
		ConductorAdicional conductorAdicional =new ConductorAdicional(licienciaConducion);
		reserva.setConductorAdicional(conductorAdicional);
		return valorAdicionalConductor;	
	}
	
	
	public void devolucionCocheCliente(Reserva reserva) 
	{
		Vehiculo vehiculoReserva = reserva.getVehiculo();
		reserva.setVehiculoRecogido(false);
		vehiculoReserva.setFechaInicio(null);	
		vehiculoReserva.setFechaFinal( null);
		vehiculoReserva.setAlquilado(false);
		
    }
	
	
	public Reserva crearReserva(Vehiculo vehiculo,Date fechaInicio,Date FechaFinal,double precio30 ,double precioRestante,
			String sedeDevolver,String sedeRecoger,
			int numerotarjeta,String nombreCliente) 
	{
		
		  Reserva reserva = new Reserva(EmpresaAlquilerVehiculos.getNumeroReservaInteger(),vehiculo.getCategoria().getNombreCategoria(),
				  fechaInicio,FechaFinal,precio30,precioRestante,
				  precio30+precioRestante,numerotarjeta,
				  sedeRecoger,sedeDevolver,null,false,vehiculo,nombreCliente);	  
		  return reserva;
    }

	
	public void revisarEstadoVehiculo(Vehiculo vehiculoReserva,boolean funcional) {
		if(funcional)
		vehiculoReserva.setFuncional(true);
		else {
		vehiculoReserva.setFuncional(false);
		}
	}
}

