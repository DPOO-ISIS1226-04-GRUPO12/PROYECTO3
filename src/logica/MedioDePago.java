package logica;

public class MedioDePago {
 private int numeroTarjeta;
 private int contraseñaTarjeta;

 
 	public MedioDePago(int numeroTarjeta,int contraseñaTarjeta) {
 	this.numeroTarjeta = numeroTarjeta;
 	this.contraseñaTarjeta = contraseñaTarjeta;
 	
     }
 	
 	
 	public int getNumeroTarjeta() 
 	{
 		return numeroTarjeta;
 	}
 	
 	public int getContraseñaTarjeta() 
 	{
 		return contraseñaTarjeta;
 	}
 	
 }
