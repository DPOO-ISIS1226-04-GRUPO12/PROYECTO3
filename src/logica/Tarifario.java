package logica;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Flow.Publisher;

public  class Tarifario {
	// private  double tarifaDia ; La tarifa del dia depende de la categoria
	private  double aumentoTemporada;
	private  double valorExtraOtraSede;
	private  double valorExtra2Conduc;
	private final int mestemporadaAltaInicio =5;
	private final int mestemporadaAltaFinal =7;
	
	
	//Toca poner una tarifa 
	public Tarifario(double aumentoTemporada,double valorExtraOtraSede,double valorExtraOtrasede) {
		this.aumentoTemporada = aumentoTemporada;
		this.valorExtra2Conduc = valorExtra2Conduc;
		this.valorExtraOtraSede = valorExtraOtraSede;
	}

	
	public double getAumentoTemporada() 
	{
		return aumentoTemporada;
	}
	
	
	public  void setAumentoTemporada(double aumentoTemporada) 
	{
		this.aumentoTemporada = aumentoTemporada;
	}
	
	
	public  double getValorExtraOtraSede() 
	{
		return valorExtraOtraSede;
	}
	
	
	public void setValorExtraOtraSede(double valorExtraOtraSede) 
	{
		this.valorExtraOtraSede = valorExtraOtraSede;
	}
	
	
	public  double getValorExtra2Conduc() 
	{
		return valorExtra2Conduc;
	}
	
	
	public  void setValorExtra2Conduc(double valorExtra2Conduc) 
	{
		this.valorExtra2Conduc = valorExtra2Conduc;
	}
	
	
	public  boolean TemporadaAlta (Date fecha1 , Date fecha2) 
	
	{
		 
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha1);
		int mesFecha1=calendario.get(calendario.MONTH);
		calendario.setTime(fecha2);
		int mesFecha2=calendario.get(calendario.MONTH);
		
		return mesFecha1>= mestemporadaAltaInicio && mesFecha2 <= mestemporadaAltaFinal;
	}
	

}
