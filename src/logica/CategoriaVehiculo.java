package logica;

public class CategoriaVehiculo {
 private String nombreCategoria ; // Falta en Diseño
 private double tarifaDiaria;
 private Tarifario tarifario;
 
 
 public CategoriaVehiculo(String nombreCategoria,double tarifaDiaria,Tarifario tarifario)
 {
	 this.nombreCategoria = nombreCategoria;
	 this.tarifaDiaria = tarifaDiaria;
	 this.tarifario = tarifario;
	 
 }
 
 
	 public String getNombreCategoria() 
	 {
		return nombreCategoria;
	 }
	
	
	 public Tarifario getTarifario() 
	 {
		return tarifario;
	 }
	
	
	 public double getTarifaDiaria() 
	 {
		return tarifaDiaria;
	 }
	




}
