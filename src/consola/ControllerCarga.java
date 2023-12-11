package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import logica.CategoriaVehiculo;
import logica.Cliente;
import logica.Empleado;
import logica.LicienciaConducion;
import logica.MedioDePago;
import logica.Sede;
import logica.Tarifario;
import logica.UsuarioGenerico;
import logica.Vehiculo;

public class ControllerCarga {

    //Cargas completas de la informacion de la empresa
	
	public ArrayList<AdministradorLocal> cargarAdministradorLocal(String archivoAdmiLocal) 
    {
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<AdministradorLocal> adminLocal= new java.util.ArrayList<>();
		try
		{
			archivo = new FileReader(archivoAdmiLocal);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String nombre = partes[0];
				String sede = partes[1];
				String usuario = partes[2];
				String contraseña = partes[3];
				String tipoUsuario = partes[4];
				
				AdministradorLocal Admin= new AdministradorLocal(usuario, contraseña, tipoUsuario, nombre, sede);
				adminLocal.add(Admin);
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
		return adminLocal;	
	}
	
	
	
	
	public AdministradorGeneral cargarAdministradorGeneral(String archivoAdministradorGeneral) 
	{
		FileReader archivo;
		BufferedReader lector;
	    AdministradorGeneral admiGeneral = null;
		try
		{
			archivo = new FileReader(archivoAdministradorGeneral);
			lector = new BufferedReader(archivo);
			String cadena;
			
			
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String nombre = partes[0];
				String usuario = partes[1];
				String contraseña = partes[2];
				String tipoUsuario = partes[3];
				
			    admiGeneral = new AdministradorGeneral(nombre, usuario, contraseña, tipoUsuario);
				
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
		
		return admiGeneral;
	}
	
	
	public ArrayList<Cliente> cargarClientes(String archivoClientes) throws ParseException 
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
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

	
	public ArrayList<Empleado> cargarEmpleados(String archivoEmpleados)
    {
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<Empleado> empleados = new java.util.ArrayList<>();
		try
		{
			archivo = new FileReader(archivoEmpleados);
			lector = new BufferedReader(archivo);
			String cadena;
			
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String nombre = partes[0];
				String sede = partes[1];
				String usuario = partes[2];
				String contraseña = partes[3];
				String tipoUsuario = partes[4];
				
				Empleado perCliente = new Empleado(usuario, contraseña, tipoUsuario, nombre, sede);
				empleados.add(perCliente);
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
		return empleados;
	}
	
	
	public ArrayList<Vehiculo> cargarVehiculos(ArrayList<CategoriaVehiculo> categoriaVehiculos,String archivoVehiculos) 
	{
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<Vehiculo> Vehiculos = new java.util.ArrayList<>();
		try
		{
			archivo = new FileReader(archivoVehiculos);
			lector = new BufferedReader(archivo);
			String cadena;
			
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				int idVehiculo = Integer.parseInt(partes[0]);
				String sedeActual = partes[1];
				int capacidad = Integer.parseInt(partes[2]);
				String placa = partes[3];
				String modelo = partes[4];
				String color = partes[5];
				String tipoTransmision = partes[6];
				String categoria = (partes[7]);
				String rutaImagen = partes[8];
				String tipoVehiculo = partes[9];
				int porAdicional = Integer.parseInt(partes[10]);
				CategoriaVehiculo categoriaObjeto=null;
				for (CategoriaVehiculo categoriaVehiculo : categoriaVehiculos) {
					if(categoriaVehiculo.getNombreCategoria().equalsIgnoreCase(categoria)) {
						categoriaObjeto = categoriaVehiculo;
					}
				}
				
				Vehiculo Vehi = new Vehiculo(idVehiculo, false, null,sedeActual, capacidad, placa, modelo, color, tipoTransmision
						,categoriaObjeto,null,null,false,true,rutaImagen,tipoVehiculo,porAdicional);
				Vehiculos.add(Vehi);


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
		return Vehiculos;	
	}
	
	
    public ArrayList<Sede> cargarSedes(ArrayList<Empleado> lEmpleado, ArrayList<Vehiculo> lVehiculos, ArrayList<AdministradorLocal> lAdmiLocal, String ArchivoSede) 
	{
		FileReader archivo;
		BufferedReader lector;
		java.util.ArrayList<Sede> sedes = new java.util.ArrayList<>();
		try
		{
			archivo = new FileReader(ArchivoSede);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String nombre = partes[0];
				String ubicacion = partes[1];
				String diasHorasAtencion = partes[2];
				
				
				ArrayList<Empleado> lEmple = new java.util.ArrayList<>();
				for(int i = lEmpleado.size()-1;i >= 0;i--) 
				{
					if(lEmpleado.get(i).getSede().equals(nombre) )
					{
						lEmple.add(lEmpleado.get(i));
					}
					
				}
				
				
				ArrayList<Vehiculo> lVehi = new java.util.ArrayList<>();
				for(int i = lVehiculos.size()-1;i >= 0;i--) 
				{
					if(lVehiculos.get(i).getSede().equals(nombre))
					{
						lVehi.add(lVehiculos.get(i));
					}
					
				}
				
				
				AdministradorLocal AdminiLocal = null;
				for(int i = lAdmiLocal.size()-1;i >= 0;i--) 
				{
					if(lAdmiLocal.get(i).getSede() .equals(nombre))
					{
						AdminiLocal = lAdmiLocal.get(i);
					}
				}
				
				

			
				Sede sed = new Sede(nombre, ubicacion,diasHorasAtencion, lVehi, lEmple, AdminiLocal);
				for (Vehiculo vehiculo : lVehiculos) {
					 if(vehiculo.getSede().equals(nombre)) {
						 vehiculo.setSedeActual(sed);
					 }
				}
				sedes.add(sed);
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
		return sedes;
		
		
	}
	
    
    public ArrayList<CategoriaVehiculo> cargarCategoria(String categoriaVehiculo) 
    {
		FileReader archivo;
		BufferedReader lector;
		ArrayList<CategoriaVehiculo> vcArrayList = new ArrayList<CategoriaVehiculo>();		
		try
		{
			archivo = new FileReader(categoriaVehiculo);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				String[ ] partes = cadena.split(";");
				String categoria = partes[0] ;
				Double tarifaDia = Double.parseDouble(partes[1]);
				Double aumentoTemporada = Double.parseDouble(partes[2]);
				Double valorExtraSede = Double.parseDouble(partes[3]);
				Double valorExtra2conduc = Double.parseDouble(partes[4]);
				
				Tarifario tarifario = new Tarifario(aumentoTemporada,valorExtraSede,valorExtra2conduc);
				CategoriaVehiculo categoriavehiculo = new CategoriaVehiculo( categoria, tarifaDia,tarifario);
				vcArrayList.add(categoriavehiculo);
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
		return vcArrayList;
	}
    
    
    public ArrayList<UsuarioGenerico> cargaUsuarios(ArrayList<Empleado> LEmpleado,ArrayList<Cliente> listaClientes,ArrayList<AdministradorLocal> lAdmiLocal,AdministradorGeneral LAdmiGene) {

        ArrayList<UsuarioGenerico> listaUsuario = new java.util.ArrayList<>();

        for ( Empleado lEmpleado : LEmpleado) {
            listaUsuario.add(lEmpleado);
        }

        for ( Cliente Cliente : listaClientes) {
            listaUsuario.add(Cliente);
        }

        for ( AdministradorLocal LAdminLocal : lAdmiLocal) {
            listaUsuario.add(LAdminLocal);
        }

        listaUsuario.add(LAdmiGene);


        return listaUsuario;
    }


	public ArrayList<String> cargaMediosDePago(String string) {
		FileReader archivo;
		BufferedReader lector;
		ArrayList<String> PagosArrayList = new ArrayList<String>();		
		try
		{
			archivo = new FileReader(string);
			lector = new BufferedReader(archivo);
			String cadena;
		
			while((cadena = lector.readLine()) != null)
			{
				
				PagosArrayList.add(cadena);
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
		return PagosArrayList;
		
	}
    
}

