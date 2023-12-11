package logica;

public class Seguro {
	private String nombre;
	private double precio  ; 
	
	public Seguro(String nombre,double precio) {
		
		this.nombre = nombre;
		this.precio = precio;
	}

	
	public double getPrecio() {
		return precio;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	public String getnombre() {
		return nombre;
	}
}
