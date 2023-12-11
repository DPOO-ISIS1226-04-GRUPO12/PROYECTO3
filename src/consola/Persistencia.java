package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.Cliente;
import logica.ConductorAdicional;
import logica.Reserva;
import logica.Vehiculo;
import logica.LicienciaConducion;
import logica.MedioDePago;

public class Persistencia {

	public ArrayList<Cliente> cargarClientesAgregadosNuevos(String archivoClientes) throws ParseException 
    {
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<Cliente> clientes = new java.util.ArrayList<>();
		try
		{
			archivo = new FileReader(archivoClientes);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String nombre = partes[0];
				String nacionalidad = partes[1];
				String telefono = (partes[2]);
				String fechaNacimiento = partes[3];
				String usuario = partes[4];
				String contraseña = partes[5];
				String tipoUsuario = partes[6];
				int numeroLicencia = Integer.parseInt(partes[7]);
				String paisExpedicion = partes[8];
				String fechaVencimientoLicencia = partes[9];
				int numeroTarjeta = Integer.parseInt(partes[10]);
				int contraseñaTarjeta = Integer.parseInt(partes[11]);
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		        Date fechau =format.parse(fechaVencimientoLicencia);
		        
		        MedioDePago tarjeta = new MedioDePago(numeroTarjeta, contraseñaTarjeta);

				LicienciaConducion licencia = new LicienciaConducion(numeroLicencia,paisExpedicion, fechau);
				Cliente perCliente = new Cliente(nombre, nacionalidad, telefono, fechaNacimiento, usuario, contraseña, tipoUsuario,null,licencia,tarjeta);
				clientes.add(perCliente);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
		return clientes;
	}
	
	
	public ArrayList<Reserva> cargarReservas(String archivoReservas, EmpresaAlquilerVehiculos self) throws ParseException 
    {
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<Reserva> reservas = new java.util.ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date fechaInicio = new Date();
		Date fechaFinal = new Date();
		int idVehiculo = 0;
		
		try
		{
			archivo = new FileReader(archivoReservas);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				int identificador = Integer.parseInt(partes[0]);
				String categoriaVehiculo = partes[1];
				String fechaI = (partes[2]);
				fechaInicio = format.parse(fechaI);
				String fechaF = partes[3];
				fechaFinal =format.parse(fechaF);
				double precio30 = Double.parseDouble(partes[4]);
				double precioRestante = Double.parseDouble(partes[5]);
				double precioTotal = Double.parseDouble(partes[6]);
				int numeroTarjeta = Integer.parseInt(partes[7]);
				String sedeNombreRecoger = partes[8];
				String sedeNombreDevolver = partes[9];
				int numeroLicencia=0;
				if(partes[10].equals("")) {
					 numeroLicencia = 0;
					
				}else {
					 numeroLicencia = Integer.parseInt(partes[10]);	
				}
				String paisExpedicion = partes[11];
				String fechaV = partes[12];
				Date fechaVencimiento;
				if(fechaV.equals("")) {
					fechaVencimiento = null;
				}else {
					 fechaVencimiento =format.parse(fechaV);
				}
				// Se inicializa licencia y conductor adicional
				ConductorAdicional conductor = null;
				if(numeroLicencia != 0) {
					LicienciaConducion licencia = new LicienciaConducion(numeroLicencia,paisExpedicion,fechaVencimiento);
					conductor = new ConductorAdicional(licencia);
				}
				boolean vehiculoRecogido =  Boolean.parseBoolean(partes[13]);
				
				//Se inicialia el vehiculo asociado
				Vehiculo vehiculo = null;
				idVehiculo = Integer.parseInt(partes[14]);
				for(Vehiculo i:self.darListaVehiculo())
				{
					if(idVehiculo == i.getIdVehiculo())
					{vehiculo = i;}
				}
				//System.out.println(vehiculo.getColor());
				String nombrePersona = partes[15];
				Reserva perReserva = new Reserva(identificador, categoriaVehiculo, fechaInicio,
						fechaFinal, precio30, precioRestante, precioTotal,numeroTarjeta,
						sedeNombreRecoger,sedeNombreDevolver,conductor,vehiculoRecogido,vehiculo, nombrePersona);
				reservas.add(perReserva);
				self.reservaV(self,idVehiculo,fechaInicio,fechaFinal);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
		
		return reservas;
	}
	
}
