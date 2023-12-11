package logica;

import java.util.ArrayList;




public class Sede {
	
	private String nombre;
	private String ubicacion;
	private String diasHorasAtencion;
	private ArrayList<Vehiculo> inventarioVehiculos;
	private ArrayList<Empleado> empleados;
	private AdministradorLocal administradorLocal;
	
	
	public Sede(String nombre, String ubicacion, String diasHorasAtencion, ArrayList<Vehiculo> inventarioVehiculos,ArrayList<Empleado> empleados, AdministradorLocal administradorLocal)
	{
		this.nombre =nombre;
		this.ubicacion = ubicacion;
		this.diasHorasAtencion = diasHorasAtencion;
		this.inventarioVehiculos = inventarioVehiculos;
		this.empleados = empleados;
		this.administradorLocal = administradorLocal;
	}
	
	
	//Getters and Setters
	public ArrayList<Vehiculo> getInventarioVehiculos() {
		return inventarioVehiculos;
	}
	
	
	public void setInventarioVehiculos(ArrayList<Vehiculo> inventarioVehiculos) {
		this.inventarioVehiculos = inventarioVehiculos;
	}

	
	public String getNombre() {
		return nombre;
	}
	
	
	public ArrayList<Empleado> getEmpleados(){
		return empleados;
	}
	
	
	public String getDiasHorasAtencion() {
		return diasHorasAtencion;
	}
	
	
	public String getUbicacion() {
		return ubicacion;
	}
	
}
