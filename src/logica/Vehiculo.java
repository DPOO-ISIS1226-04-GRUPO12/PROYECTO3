package logica;

import java.util.Date;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class Vehiculo {
	private int idVehiculo;
	private Sede sedeActual;
	private boolean alquilado =false; 
	private String sedeInicial; 
	private int capacidad;
	private String placa;
	private String modelo;
	private String color;
	private String tipoTransmision;
	private CategoriaVehiculo categoria;
	private Date fechaInicio=null;
	private Date fechaFinal=null;
	private  boolean conSeguro;
	private boolean funcional = true;
	private String rutaArchivoImagen;
	private String tipoVehiculo;
	private int porAdicional;
	

	public Vehiculo(int idVehiculo, boolean alquilado,Sede sedeActual,String sedeInicial,int capacidad, String placa, 
			String modelo, String color, 
			String tipoTransmision,CategoriaVehiculo categoria,
			Date fechaInicio,Date fechaFinal,boolean conseguro,boolean funcional ,String rutaArchivoImagen, String tipoVeh, int porAdi)
	{
		this.idVehiculo = idVehiculo;
		this.alquilado = alquilado;
		this.sedeInicial = sedeInicial;
		this.sedeActual = sedeActual;
		this.capacidad = capacidad;
		this.placa = placa;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.categoria = categoria;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.conSeguro = conseguro;
		this.funcional = funcional;
		this.rutaArchivoImagen = rutaArchivoImagen;
		this.tipoVehiculo = tipoVeh;
		this.porAdicional = porAdi;
		
	}

	
	
	//Getters and Setters
	public CategoriaVehiculo getCategoria() 
	{
		return categoria;
	}

	
	public int getIdVehiculo() 
	{
		return idVehiculo;
	}


	public Date getFechaInicio() 
	{
		return fechaInicio;
	}
	
	
	public void setFechaInicio(Date fechaInicio) 
	{
		this.fechaInicio = fechaInicio;
	}
	
	
	public Date getFechaFinal() 
	{
		return fechaFinal;
	}
	
	
	public void setFechaFinal(Date fechaFinal) 
	{
		this.fechaFinal = fechaFinal;
	}
	
	
	public Sede getSedeActual() 
	{
		return sedeActual;
	}
	
	
	public String getRutaImagen() 
	
	{
		return rutaArchivoImagen;
	}
	
	
	public void setConSeguro(boolean conSeguro) {
		this.conSeguro = conSeguro;
	}
	
	
	public String getSede()
    {
    	return sedeInicial;
    }	

	
	public boolean getAlquilado() {
		return alquilado;
	}
	
	
	public String getSedeString() {
		return sedeInicial;
	}
	
	
	public int getCapacidad() {
		return capacidad;
	}
	
	
	public String getPlaca() {
		return placa;
	}
	
	
	public String getModelo() {
		return modelo;
	}
	
	
	public String getColor() {
		return color;
	}
	
	
	public String getTipoTransmision() {
		return tipoTransmision;
	}
	
	
	public void setFuncional(boolean funcional) {
		this.funcional = funcional;
	}
	
	
	public void setSedeActual(Sede sedeActual) {
		this.sedeActual = sedeActual;
	}
	
	
	public void setAlquilado (boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	
	public boolean isFuncional() {
		return funcional;
	}



	public Boolean isConSeguro() {
		// TODO Auto-generated method stub
		return conSeguro;
	}



	public String getTipoVehiculo() {
		return tipoVehiculo;
	}



	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}



	public void setTipoTransmision(String tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}



	public int getPorAdicional() {
		return porAdicional;
	}



	public void setPorAdicional(int porAdicional) {
		this.porAdicional = porAdicional;
	}



		
}
