package logica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
public class ControladorReserva {
	
	
	//Verificar datos ingresados por el usuario
	public Vehiculo ReservaVehiculo(String categoria , ArrayList<CategoriaVehiculo> categoriaVehiculo , String nombreSede ,Date fechaInicio,Date fechaFinal ,ArrayList<Sede> listaSedes) throws ParseException{
		

		boolean encontrado = false;
		 for (CategoriaVehiculo  categorias :categoriaVehiculo) {
			 if(categoria.equalsIgnoreCase(categorias.getNombreCategoria())) {
				 categoria = categorias.getNombreCategoria();
				 encontrado = true;
				 break;
				 }
			 
		 } if(encontrado) {
			 
			  		Sede sede = buscarSedePorNombre(nombreSede, listaSedes);
			  			if(sede==null)
			  				return null;
		                for	(int i =0; i<sede.getInventarioVehiculos().size();i++)	{
		                	 Vehiculo coche = sede.getInventarioVehiculos().get(i);              
						if((coche.getCategoria().getNombreCategoria().equals(categoria)&& coche.getAlquilado()==false && coche.isFuncional() )) {
							
							
						
							
							
							if( coche.getFechaInicio() == null  ) {
								coche.setFechaInicio(fechaInicio); // Falta añadir que tampoco esten en mantenimiento o lavandose
								coche.setFechaFinal(fechaFinal);
								return coche;
							}
						
							else if( fechaInicio.after(coche.getFechaFinal())||fechaFinal.before(coche.getFechaInicio())){
								
									coche.setFechaInicio(fechaInicio);
									coche.setFechaFinal(fechaFinal);
									return coche;
								
								
							}
						}	
		 				
					}
		 }else {
			return null;
		}
		return null;
		
		
		
		//Calcula el total del precio de la reserva sin seguro
	} 
	
	
	public  Double ValorReservaSinSeguro(Vehiculo vehiculo,ArrayList<Sede> sedes,String sedeDevuelta) {
		Tarifario tarifario = vehiculo.getCategoria().getTarifario();
		Sede sede = buscarSedePorNombre(sedeDevuelta, sedes);
		if(sede==null) 
			return null;
		double precioCategoria = vehiculo.getCategoria().getTarifaDiaria();
		long tiempodiferencia=vehiculo.getFechaInicio().getTime()-vehiculo.getFechaFinal().getTime();
		long diasDiferencia = tiempodiferencia / (24 * 60 * 60 * 1000);
		int numeroDias = (int) (diasDiferencia + 1);
		
		if(sedeDevuelta.equalsIgnoreCase(vehiculo.getSedeActual().getNombre())) {
			tarifario.setValorExtraOtraSede(0);
		}
		
		if(!tarifario.TemporadaAlta(vehiculo.getFechaInicio(), vehiculo.getFechaFinal())) {
			tarifario.setAumentoTemporada(0);
		}
		
		Double tarifaTotalSinSeguro=(precioCategoria*numeroDias)+(tarifario.getAumentoTemporada())+tarifario.getValorExtraOtraSede();
		return tarifaTotalSinSeguro;
	} 
	
	
	public Reserva CrearReservaCliente(Cliente cliente,double valorSinSeguro ,
			AdministradorGeneral administradorGeneral,boolean conSeguro,
			Vehiculo vehiculo,String sedeDevolver,String sederecoger,ArrayList<Seguro> seguros,ArrayList<Integer> posiciones) {
		
		double precioSeguro = administradorGeneral.administrarSeguro(seguros,posiciones,conSeguro,vehiculo);
		double precioTotal = valorSinSeguro + precioSeguro;
		double precio30 = precioTotal*0.3;
		double precioRestante = precioTotal*0.7;
		Reserva reserva = cliente.crearReserva(vehiculo,vehiculo.getFechaInicio(), vehiculo.getFechaFinal(), precio30, precioRestante,sedeDevolver,sederecoger);
		return reserva;
	}

	
	public Reserva CrearReservaACliente(Cliente cliente,double valorSinSeguro ,
			AdministradorGeneral administradorGeneral,boolean conSeguro,
			Vehiculo vehiculo,String sedeDevolver,String sederecoger,ArrayList<Seguro> seguros,ArrayList<Integer> posiciones,Empleado empleado) {
		double precioSeguro = administradorGeneral.administrarSeguro(seguros,posiciones,conSeguro,vehiculo);
		double precioTotal = valorSinSeguro + precioSeguro;
		double precio30 = precioTotal*0.3;
		double precioRestante = precioTotal*0.7;
		Reserva reserva = empleado.crearReserva(vehiculo,vehiculo.getFechaInicio(), vehiculo.getFechaFinal(), precio30, precioRestante,sedeDevolver,sederecoger,cliente.getMedioDePago().getNumeroTarjeta(),cliente.getNombre());
		return reserva;
	}
	

	public Sede buscarSedePorNombre(String nombreSede, ArrayList<Sede> sedes) {
		Sede sedeABuscarSede=null;	
		for (Sede sede : sedes) {
			if(sede.getNombre().equalsIgnoreCase(nombreSede)) {
				sedeABuscarSede = sede;
				return sedeABuscarSede;
			}
		}return sedeABuscarSede;
	}

	}
	/*
	private int numeroDiaAtencionSede(String fecha,int posicion) {
		String[] partes = fecha.split("/");
		if (partes.length == 2) {
            String dias = partes[0];
            String horas = partes[1];
            String[] diasArray = dias.split("-");
            String[] horasArray = horas.split("-");
            if (diasArray.length == 2 && horasArray.length == 2) {
            	 String dia = diasArray[posicion];
            Integer	numero= obtenerNumeroDiaSemana(dia);
            return numero;
            }
		}
		return 0;
	}
	
	private Date horaAtencion(String fecha,int posicion) {
		String[] partes = fecha.split("/");
		if (partes.length == 2) {
            String dias = partes[0];
            String horas = partes[1];
            String[] diasArray = dias.split("-");
            String[] horasArray = horas.split("-");
            if (diasArray.length == 2 && horasArray.length == 2) {
                String Hora = horasArray[posicion];
                Date horas1 = convertirHoraADate(Hora);
                return horas1;
            }
		}return null;
	}
	
	private Integer obtenerNumeroDiaSemana(String nombreDia) {
	    switch (nombreDia.toLowerCase()) {
	        case "domingo":
	            return Calendar.SUNDAY;
	        case "lunes":
	            return Calendar.MONDAY;
	        case "martes":
	            return Calendar.TUESDAY;
	        case "miércoles":
	            return Calendar.WEDNESDAY;
	        case "jueves":
	            return Calendar.THURSDAY;
	        case "viernes":
	            return Calendar.FRIDAY;
	        case "sábado":
	            return Calendar.SATURDAY;
	        default:
	            return null;
	    }
	}
	
	public static Date convertirHoraADate(String hora) {
        try {
            SimpleDateFormat formatoHora = new SimpleDateFormat("hha");
            return formatoHora.parse(hora);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/