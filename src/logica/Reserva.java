package logica;

import java.util.Date;
import java.util.function.BooleanSupplier;



public class Reserva {
	private Integer identificador;
	private String nombrePersona;
	private String categoriaVehiculo;
	private Date FechaInicio ;
	private Date FechaFinal ;
	private double precio30;
	private double precioRestante;
	private double precioTotal;
	private int numeroTarjeta;
	private String sedeNombreRecoger;
	private String sedeNombreDevolver;
	private ConductorAdicional conductorAdicional;
	private boolean  vehiculoRecogido;
	private Vehiculo vehiculo;
	private int idVehiculo;
	
	public Reserva(Integer identificador, String categoriaVehiculo, Date fechaInicio, Date fechaFinal, double precio30,
			double precioRestante,double precioTotal,int numeroTarjeta,
			String sedenombreRecoger,String sedeNombreDevolver ,
			ConductorAdicional conductorAdicional, boolean vehiculoRecogido,Vehiculo vehiculo,String nomrePersona) {
		this.nombrePersona = nomrePersona;
		this.identificador = identificador;
		this.categoriaVehiculo = categoriaVehiculo;
		FechaInicio = fechaInicio;
		FechaFinal = fechaFinal;
		this.precio30 = precio30;
		this.precioRestante = precioRestante;
		this.precioTotal = precioTotal;
		this.numeroTarjeta = numeroTarjeta;
		this.sedeNombreRecoger = sedenombreRecoger;
		this.sedeNombreDevolver =sedeNombreRecoger;
		this.conductorAdicional=conductorAdicional;
		this.vehiculoRecogido = vehiculoRecogido;
		this.vehiculo = vehiculo;
		
	}

	public Integer getIdentificador() {
		return identificador;
	}

	
	public String getCategoriaVehiculo() {
		return categoriaVehiculo;
	}

	
	public Date getFechaInicio() {
		return FechaInicio;
	}

	
	public Date getFechaFinal() {
		return FechaFinal;
	}

	
	public double getPrecio30() {
		return precio30;
	}

	
	public double getPrecioRestante() {
		return precioRestante;
	}

	
	public double getPrecioTotal() {
		return precioTotal;
	}

	
	public String getSedeNombreRecoger() {
		return sedeNombreRecoger;
	}

	
	public String getSedeNombreDevolver() {
		return sedeNombreDevolver;
	}

	
	public void setConductorAdicional(ConductorAdicional conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}

	
	public void setVehiculoRecogido(boolean vehiculoRecogido) {
		this.vehiculoRecogido = vehiculoRecogido;
	}

	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	
	
	public ConductorAdicional getConductorAdicional(){
		return conductorAdicional;
	}
	
	
	public boolean getVehiculoRecogido() {
		return vehiculoRecogido;
	}
	

	
	public int getIdVehiculo() {
		return idVehiculo;
	}
	
	
	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setVehiculo(Vehiculo vehiculo2) {
		vehiculo = vehiculo2;
	}

	public Boolean isVehiculoRecogido() {
		return vehiculoRecogido;
	}
		
}
	

