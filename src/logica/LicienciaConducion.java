package logica;

import java.util.Date;

public class LicienciaConducion {
 private int number;
 private String paisExpedicion;
 private Date fechaVencimiento;

	 public LicienciaConducion(int numeroLicencia, String paisExpedicion, Date fechaVencimientoLicencia) {
		
		this.number = numeroLicencia;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimientoLicencia;
	 }
	 
	 
	 public int getNumeroLicencia() {
		 return number;
	 }
	 
	 
	 public String getPaisExpedicion() {
		 return paisExpedicion;
	 }
	 
	 
	 public Date getFechaVencimiento()
	 {
		 return fechaVencimiento;
	 }
 
}