package consola;


import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.JTextField;

import com.itextpdf.text.DocumentException;

import ProgramaClientes.InterfazClientesIndependiente;

import Pagos.PagosGenerales;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import logica.LicienciaConducion;
import logica.MedioDePago;
import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import logica.Cliente;
import logica.Empleado;
import logica.Reserva;
import logica.Sede;
import logica.CategoriaVehiculo;
import logica.ControladorReserva;
import logica.Seguro;

import logica.UsuarioGenerico;
import logica.Vehiculo;

public class EmpresaAlquilerVehiculos {
  private ControladorReserva controllerEmpresa = new ControladorReserva();
  private ArrayList<UsuarioGenerico> listaUsuarioGenericos;
  private ArrayList<Cliente> listaClientes ;
  private ArrayList<Sede> listaSedes;
  private ArrayList<Empleado> listaEmpleados;
  private ArrayList<CategoriaVehiculo> categoriaVehiculo;
  private AdministradorGeneral administradorGeneral;
  private ArrayList<Vehiculo> listaVehiculo ;
  private ArrayList<AdministradorLocal> listaAdministradorLocal;
  private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
  private ArrayList<Seguro> seguros = new ArrayList<Seguro>();
  public static Integer numeroReservaInteger = 0 ;
  public static Map<Date, Integer> calendario;
  private ArrayList<String> listaMediosDePago;
  private PagosGenerales PagoTarjeta;
  private FacturaPdf factura = new FacturaPdf();
  public static Integer numeroFactura = 0 ;
  
  
  
  
  public EmpresaAlquilerVehiculos() throws ParseException {
	  Map<Date, Integer> mapaFechas = generarFechasDeDosAnios(2023, 2024);
		 EmpresaAlquilerVehiculos.calendario = mapaFechas;
		 ControllerCarga control = new ControllerCarga();
		 this.cargaDatos(control);
		 Persistencia persistencia = new Persistencia();
		 this.cargaPersistencia(persistencia, this);	
		 this.cargarReservasEnCalendario(this);
  }
  
   //Guardar Informacion reserva para persistencia
   private void guardarycerra() 
  {
	 guardarReservas();
  }
 
   public String crearTextoReservas()
   {
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 	String linea = "";
 	for(Reserva reserva: reservas)
 	{
 		linea += reserva.getIdentificador()+";";
 		linea += reserva.getCategoriaVehiculo()+";";
 		linea += formato.format(reserva.getFechaInicio()) +";";
 		linea += formato.format(reserva.getFechaFinal())+";"; 
 		linea += reserva.getPrecio30()+";";
 		linea += reserva.getPrecioRestante()+";";
 		linea += reserva.getPrecioTotal()+";";
 		linea += reserva.getNumeroTarjeta()+";";
 		linea += reserva.getSedeNombreRecoger()+";";
 		linea += reserva.getSedeNombreDevolver()+";";
 		if (reserva.getConductorAdicional()!=null) {
 			
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getNumeroLicencia()+";";
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getPaisExpedicion()+";";
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getFechaVencimiento()+";";
 		
 		}else {
 			linea+= ";;;";
 		}
 		linea += reserva.getVehiculoRecogido()+";";
 		linea += reserva.getVehiculo().getIdVehiculo()+";";
 		linea += reserva.getNombrePersona();
 		linea += "\n";
 	}
 	return linea;
   }
 
   
   public void guardarReservas() 
   {
     try {
     	String nombreArchivo = "./data/persistencia/" + "reservas.txt";    
         BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
         writer.write(crearTextoReservas());
         writer.close();
         System.out.println("Se ha guardado el archivo de reservas en: " + nombreArchivo);
     } catch (IOException e) {
         System.err.println("Error al guardar el archivo.");
         e.printStackTrace();
     }
    } 
   
   
   
   

   //Persistencia
   public void reservaV(EmpresaAlquilerVehiculos self, int id, Date fechaInicio, Date fechaFinal) throws ParseException{
		Vehiculo coche = null;

		for(Vehiculo e: self.listaVehiculo)
		{if( e.getIdVehiculo()== id ){coche = e;}
		}
		
		if( coche.getFechaInicio() == null) {
			coche.setFechaInicio(fechaInicio);
						coche.setFechaFinal(fechaFinal);
					}					
		else if( fechaInicio.after(coche.getFechaFinal())||fechaFinal.before(coche.getFechaInicio())){
			
				coche.setFechaInicio(fechaInicio);
				coche.setFechaFinal(fechaFinal);}					
	}
   
   
   
   
   //Administrador General
   public void darDeBajaVehiculoAdmin(int idVehiculo)
   {
 	 
 	 
 	 Vehiculo vehiculoDeBajaVehiculo=null;
 	 
 	 for (Vehiculo vehiculo : listaVehiculo) {
 		if(idVehiculo==(vehiculo.getIdVehiculo())) {
 			vehiculoDeBajaVehiculo = vehiculo;
 		}
 	}
 	administradorGeneral.darDeBajaVehiculo(listaVehiculo, vehiculoDeBajaVehiculo, vehiculoDeBajaVehiculo.getSedeActual());
 	
    }
  

   public void modificarVehiculoAdministradorGeneral(String nombreSedeString
 		 ,String modelo ,int capacidad,
 		 String placa, String color,String tipoTransmision,String categoriaVehiculo ,String rutaarchivo,int idVehiculo) 
   {
 		Sede sede = controllerEmpresa.buscarSedePorNombre(nombreSedeString, listaSedes);
 		CategoriaVehiculo categoria =null;
 		
 		for (CategoriaVehiculo categoriaVehiculo2 : this.categoriaVehiculo) {
 			if(categoriaVehiculo.equals(categoriaVehiculo2.getNombreCategoria())) {
 				categoria = categoriaVehiculo2;
 			}
 		}
 		Vehiculo vehiculo = new Vehiculo(idVehiculo, false, sede,nombreSedeString, capacidad, placa,
 				                         modelo, color, tipoTransmision, categoria, null,
 				                         null, false, false,rutaarchivo,null,0);
 		
 		administradorGeneral.registrarCompraVehiculo(vehiculo, sede,listaVehiculo);
 		
    }
   
   
   public Vehiculo buscarAutoPorId(int ID)
   {
	for (Vehiculo vehiculo : listaVehiculo){
		if(vehiculo.getIdVehiculo()==ID) {
			return vehiculo;
		}
		
	}
	return null;
	}
   
   
   public Map<Date, Integer> sacarFechaSede(String idSede) 
   {
	
	for (Map.Entry<Date, Integer> entry : calendario.entrySet()) {
        System.out.println("Fecha: " + entry.getKey() + ", Número8844: " + entry.getValue());
    }
    return calendario;	
    }
   
   
   
   
   
   //Empleado
   public int ProgramaEmpleadoDevolucion(Integer idVehiculoSeleccionado, Empleado empleado) 
   {
 	 ArrayList<Reserva> reservasHechas = reservas;
 	 Reserva reservaEncontrada = null;
 	 for (Reserva reserva : reservasHechas) {
 			if((idVehiculoSeleccionado.equals(reserva.getVehiculo().getIdVehiculo()) && (reserva.getVehiculo().getAlquilado() == true)) )
 				reservaEncontrada = reserva; 
 	
     }
 	 if (reservaEncontrada == null) {
 		 return 0;
 	 }
 	 
 	 empleado.devolucionCocheCliente(reservaEncontrada);
 	 return 1;
   }
  
    
   public int ProgramaEmpleadoRecogerCliente(Empleado empleado, Integer idVehiculoSeleccionado) throws IOException, DocumentException 
    {
 	   Reserva reservaClienteInterno = null;
 	   int esta67 = recogerVehiculoCliente(reservaClienteInterno, empleado, idVehiculoSeleccionado);
 	   return esta67;
    }
   
   
   private int recogerVehiculoCliente(Reserva reservaClienteInterno,Empleado empleadoLogin, Integer idVehiculoSeleccionado) throws IOException, DocumentException 
   {

 	 for (Reserva reserva : reservas) {
 			if((idVehiculoSeleccionado.equals(reserva.getVehiculo().getIdVehiculo()) && (reserva.getVehiculo().getAlquilado() == false)) )
 				reservaClienteInterno = reserva; 		
 	
     }
 	 if(reservaClienteInterno == null) {
 	
 		 return 0;
 	 }
 		 double valorAdicional = 0;
 		 String adicional = "1";
 		 if(adicional.equals("1")) {
 			int numero = 123456;
 			String pais = "Colombia";
 			valorAdicional = reservaClienteInterno.getVehiculo().getCategoria().getTarifario().getValorExtra2Conduc();
 			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
 			Date fechaVencimiento;
 			try {
 				fechaVencimiento = formatoFecha.parse("2023-12-03");
 				valorAdicional= empleadoLogin.agregarConductor(valorAdicional, reservaClienteInterno, numero, pais, fechaVencimiento);
 				double tarifaTotal = reservaClienteInterno.getPrecioRestante() + valorAdicional;
 				 empleadoLogin.administarRecogidaCliente(reservaClienteInterno);
 				//Llamar función Factura
 				FacturaPdf.generateInvoice("./facturas/invoice_"+numeroFactura+".pdf", reservaClienteInterno.getNombrePersona(), reservaClienteInterno.getVehiculo().getModelo()+" con placa "+reservaClienteInterno.getVehiculo().getPlaca(), tarifaTotal);
 				numeroFactura += 1;
 				} catch (ParseException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}	
 		 }
 		 return 1;
 		 }	  
   
   
   public ArrayList<Integer> crearListaCarros() 
   {
	
	ArrayList<Integer> listaCar = new ArrayList<Integer>();
	
	for( Vehiculo carLocal: listaVehiculo) {
		listaCar.add(carLocal.getIdVehiculo());
	}
	
	return listaCar;
	
   }
   
   
   public void realizarPago(String nombre, int cantidad, int numeroTarjeta, int contraseñaTarjeta, String pasaje) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		for(String medio : listaMediosDePago) {
			if(medio.equals(pasaje)) {
				try {
			        Class<?> gatewayClass = Class.forName(pasaje);
			        
			        Constructor<?> constructor = gatewayClass.getDeclaredConstructor();
			        PagosGenerales gateway = (PagosGenerales) constructor.newInstance();
			        
					String texto = gateway.generartexto(nombre,cantidad, numeroTarjeta, contraseñaTarjeta, pasaje);
					gateway.GuardarPagoRegistro(texto);
			    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			        e.printStackTrace();
			    }
		     }
		
	}
   }
  
   //Admini Local
   public void crearUsuario(String nombre2, String nacionalidad2, String telefono2, String fechaNac2, String paisExp, String usuario2, String contraseña2, int nLicencia, String fechaVencLicen, int nTarjeta, int contraTarjeta) throws ParseException {
	 

	   
	   String nombre = nombre2;
	   String nacionalidad = nacionalidad2;
	   String telefono = telefono2;
	   String fechaNac = fechaNac2;
	   String usuario = usuario2;
	   String contraseña = contraseña2;
	   
	   int numeroLicencia = nLicencia;
	   String paisExpe = paisExp;
	   String Fecvenci = fechaVencLicen;
	   int numeroTarjeta = nTarjeta;
	   int contraseñaTarjeta = contraTarjeta;
	   


	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   Date fechau =format.parse(fechaVencLicen);
	   

	   MedioDePago tarjeta = new MedioDePago(numeroTarjeta,contraseñaTarjeta);
	   LicienciaConducion Lice = new LicienciaConducion(numeroLicencia, paisExpe, fechau );
	   Cliente cliente = new Cliente(nombre, nacionalidad, telefono, fechaNac,usuario, contraseña, "Cliente", null, Lice, tarjeta);

	   listaClientes.add(cliente);
	   //UsuarioGenerico NuevoUsuario = new UsuarioGenerico(usuario2, contraseña2, "cliente");
   }
   
   
   
   
 
   
   public int agregarEmpleado(String nombre2, String sede2, String usuario2, String contraseña2) {
       
	   System.out.println("\n*******CREAR EMPLEADO***************\n");
	   System.out.println("Por favor llene el formulario: \n");
	   String nombre = nombre2;
	   String sede = sede2;
	   String usuario = usuario2;
	   String contraseña = contraseña2;
	   
	   Empleado worker = new Empleado(nombre, sede, usuario, contraseña, "Empleado");
	   
	   for ( Sede sed : listaSedes) {
           if (sed.getNombre().equals(sede)){
        	   sed.getEmpleados().add(worker);
           }
       }
	   
	   System.out.println("Empleado Agregado exitosamente \n");
	   
	   return 1;
	   
    }
   
   public void crearCliente(ArrayList<JTextField> listaJTextFields) {
	   Cliente cliente = new Cliente();
	   int numeroLicencia=0;
	   String paisExpedicicionLicencia="";
	   Date fechaVencimiento = null;
	   int numeroTarjeta=0;
       int contraseñaTarjeta = 0;
	   String usuario="";
	   String Contrasenia= "";
		for (int i =0; i<listaJTextFields.size();i++) {
			JTextField field;
			switch (i) {
			
			case 0: 
				field=listaJTextFields.get(i);
				cliente.setNombre(field.getText());
				break;
			
			case 1:
				field=listaJTextFields.get(i);
				cliente.setNacionalidad(field.getText());
				break;
			
			case 2:
				field=listaJTextFields.get(i);
				cliente.setTelefono(field.getText());
				break;
			
			case 3:
				field=listaJTextFields.get(i);
				cliente.setFechaNac(field.getText());
				break;
			
			case 4 :
				field=listaJTextFields.get(i);
				numeroLicencia = Integer.parseInt(field.getText());
				break;
			case 5 :
				field=listaJTextFields.get(i);
				paisExpedicicionLicencia = field.getText();
				break;
			case 6 :
				 field=listaJTextFields.get(i);
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				 try {
					fechaVencimiento = dateFormat.parse(field.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 7 : 
				field=listaJTextFields.get(i);
				numeroTarjeta = generateRandomNumber(8);
				
				break;
				
			case 8:
				field=listaJTextFields.get(i);
				contraseñaTarjeta = generateRandomNumber(4);
				break;
			
			case 9:
				field=listaJTextFields.get(i);
				cliente.SetUsuario(field.getText());
				usuario = field.getText();
				break;
			case 11:
				field=listaJTextFields.get(i);
				cliente.setContraseña(field.getText());
				Contrasenia = field.getText();
				
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + i);
			}
		}
		LicienciaConducion licienciaConducion = new LicienciaConducion(numeroLicencia, paisExpedicicionLicencia, fechaVencimiento);
		cliente.setLicienciaConducion(licienciaConducion);
		MedioDePago tarjeta = new MedioDePago(numeroTarjeta, contraseñaTarjeta);
        cliente.setMedioDePago(tarjeta);
		listaClientes.add(cliente);
		UsuarioGenerico usuarioGenerico = new UsuarioGenerico(usuario, Contrasenia, "Cliente");
		listaUsuarioGenericos.add(usuarioGenerico);
		
   }
   
   
   private int generateRandomNumber(int length) {
	    Random random = new Random();
	    StringBuilder builder = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        builder.append(random.nextInt(10));
	    }

	    return Integer.parseInt(builder.toString());
	}
   
   
   public ArrayList<String> crearListaUsuario() {
		 ArrayList<String> listaUsuarios = new ArrayList<String>();
		 for (UsuarioGenerico usuarioGenerico : listaUsuarioGenericos) {
			
			listaUsuarios.add(usuarioGenerico.getUsuario());
			
		}
		 return listaUsuarios;
	 }
   
   
   
   
   
//Ejecucion Ventana Login
   public String ejecutarPrograma(String usuario, String contrasenia)  
   {
	 String tipoUsuario =login(usuario, contrasenia);
	 if(tipoUsuario.equals("")) {
		 return "No encontrado";
	 } else {
		 return tipoUsuario;
	 }
	
   }

  
   private String login(String usuario,String contrasenia) 
   { 
	 for (UsuarioGenerico usuarioGenerico : listaUsuarioGenericos) {
		String userFor = usuarioGenerico.getUsuario();
		String passwordFor = usuarioGenerico.getContraseña();
		if(usuario.equals(userFor) && passwordFor.equals(contrasenia)) {
			String tipoUsario= usuarioGenerico.getTipoUsuario();
			return tipoUsario;
		}
		
	 }return "";
   }
   
   
   
  
   
 //Busqueda Cliente - Empleado - AdministradorLocal
   public Cliente buscarClienteSistema(String nombreCliente) 
   {
	 Cliente clienteEncontrado;
	 for (Cliente cliente2 : listaClientes) {
		if(cliente2.getNombre().equalsIgnoreCase(nombreCliente)) {
			clienteEncontrado = cliente2;
			return clienteEncontrado;
		}
	 } return null;
   }
 
   
   public Empleado buscarEmpleadoPorLogin(String usuario,String contrasenia) 
   {
	 Empleado EmpleadoEncontrado;
	 for (Empleado EmpleadoLocal : listaEmpleados) {
		if(EmpleadoLocal.getUsuario().equals(usuario)&& EmpleadoLocal.getContraseña().equals(contrasenia)) {
			EmpleadoEncontrado = EmpleadoLocal;
			return EmpleadoEncontrado;
		}
	 } return null;
	}
   
   
   public AdministradorLocal buscarAdministradorLocalPorLogin(String usuario,String contrasenia) 
   {
	 AdministradorLocal administradorLocalEncontrado;
	 for (AdministradorLocal administradorLocal2 : listaAdministradorLocal) {
		if(administradorLocal2.getUsuario().equals(usuario)&& administradorLocal2.getContrasenia().equals(contrasenia)) {
			administradorLocalEncontrado = administradorLocal2;
			return administradorLocalEncontrado;
		}
	 } return null;
   }
   
   
   
   

//Ejecucion de reserva Empleado - Cliente
   void CrearReserva(String categoria, String sedeR, String fechaI, String fechaF, String sedeD, String usuario,
	   String contrasenia, int reservaOAlquiler, String quienRealiza) throws ParseException {
	
	   Cliente clienteAReservar = buscarClientePorLogin(usuario, contrasenia);

	    if(quienRealiza.equals("Cliente")) {
	      programaCliente(categoria,sedeR,fechaI,fechaF,sedeD,clienteAReservar,reservaOAlquiler);
	      guardarycerra();
	    }else if(quienRealiza.equals("Empleado")) {
		  programaEmpleado(categoria,sedeR,fechaI,fechaF,sedeD,clienteAReservar,reservaOAlquiler);
		  guardarycerra();
	  }
   }

   
   public Cliente buscarClientePorLogin(String usuario,String contrasenia) 
   {
	 Cliente clienteEncontrado;
	 for (Cliente cliente2 : listaClientes) {
		if(cliente2.getUsuario().equals(usuario)&& cliente2.getContraseña().equals(contrasenia)) {
			clienteEncontrado = cliente2;
			return clienteEncontrado;
		}
	 } return null;
   }

   
   private void programaCliente (String categoria, String sedeR,String fechaI,String fechaF,
     String sedeD, Cliente clienteAReservar,int reservaOAlquiler) throws ParseException 
   {
	 
	 if(reservaOAlquiler == 0) {
		 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date fechaInicio =format.parse(fechaI);
		 Date fechaFinal = format.parse(fechaF);
		 try {
		   Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
		   int seguro = 0;
		   boolean conSeguro = false;
		   if(seguro!=0) {
			  segurosPosiciones.add(seguro);
			  String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			  while(masSeguro.equals("1")) {
				  seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
				  segurosPosiciones.add(seguro);
				  masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			   }
			  conSeguro = true;
		   }
		
		    double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
		    reservas.add(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
		    numeroReservaInteger+=1;
		    System.out.println("Creando la reserva... ");
		    Thread.sleep(100);
		    System.out.println("Su valor a pagar es"+reservas.get(reservas.size()-1).getPrecio30());
		    System.out.println("Se hizo el cobro a la tarjeta del 30% del valor de la reserva");
		    System.out.println("Cuando recoga el vehiculo se hara el cobro restante");
		
		  } catch (ParseException e) {
			 e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		 
	 } 
	 else if(reservaOAlquiler == 1) {
		 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
		 Empleado empleadoSede = null;
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date fechaInicio = new Date();
		 Date fechaFinal = format.parse(fechaF);
		 Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
		
		 int seguro = 0;
		 boolean conSeguro = false;
		 if(seguro!=0) {
				segurosPosiciones.add(seguro);
				String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				while(masSeguro.equals("1")) {
					seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
					segurosPosiciones.add(seguro);
					masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				}
				conSeguro = true;
			}
		 
		 double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
		 Reserva alquiler;
		 alquiler =(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
		 numeroReservaInteger+=1;
		 reservas.add(alquiler);
		 
		 for (Sede sedess: listaSedes) {
			 if(sedess.getNombre().equalsIgnoreCase(sedeR)) {
				empleadoSede= sedess.getEmpleados().get(0);
				break;
			 }	 
			
	     } 
	
		 
		 String conductorAdicional = "0";
		 boolean aditional = conductorAdicional.equals("1");
		 double valorConductorExtra=0;
		 
		 if(aditional) 
		 {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 int numero  = Integer.parseInt(input("Ingrese el numero de su liciencia "));
			 String paisExpedicion = input("Ingrese el pais de expedicion de la licencia");
			 Date  fechaVencimiento =  dateFormat.parse(input("Ingrese la fecha de vencimiento de su licencia en formato: dd/MM/yyyy")); 
			 Double valorTotalDouble = alquiler.getVehiculo().getCategoria().getTarifario().getValorExtra2Conduc();
			 valorConductorExtra = empleadoSede.agregarConductor(valorTotalDouble,alquiler,numero,paisExpedicion,fechaVencimiento);
		  }
		 
		 empleadoSede.administarRecogidaCliente(alquiler);
		 
		 System.out.println("Su valor a pagar es"+(reservas.get(reservas.size()-1).getPrecioTotal()+valorConductorExtra));
		 //System.err.println("Sus datos fueron validados ");
		 //System.err.println("Pago exitoso ");
		 //System.out.println("Las llaves estan encima del vidrio");
	 }
	 
	 guardarycerra();
	 }
   
   
   public void programaEmpleado(String categoria, String sedeR,String fechaI,String fechaF,
	     String sedeD, Cliente clienteAReservar,int option) throws ParseException {
		 
		 Reserva reservaClienteInterno=null;
		 
		
		 if(option == 1) {
			 ArrayList<Reserva> reservaClienteLogin=clienteAReservar.getReservas();
			 
		//Devolucion de Vehiculo
		 }else if(option==2) {
			 Reserva reservaClienteLogin=null;
			 Integer numeroReserva = Integer.parseInt(input("Ingrese el identificador de reserva del cliente"));
			 for (Reserva reservasCliente : reservas) {
					if(numeroReserva.equals(reservasCliente.getIdentificador())) 
						reservaClienteLogin = reservasCliente; 
		 }   
		
	    
		 boolean funcional = false;
		 
		 
		  
		 } else if (option==3) {
			 
			 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
			 
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 Date fechaInicio =format.parse(fechaI);
			 Date fechaFinal = format.parse(fechaF);
			 try {
			    Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
			
			    int seguro = 0;
			    boolean conSeguro = false;
			    if(seguro!=0) {
				    segurosPosiciones.add(seguro);
				    String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				while(masSeguro.equals("1")) {
					seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
					segurosPosiciones.add(seguro);
					masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				}
				conSeguro = true;
			}
			
			
			double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
			reservas.add(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
			numeroReservaInteger+=1;
			System.out.println("Creando la reserva... ");
			Thread.sleep(100);
			System.out.println("Su valor a pagar es"+reservas.get(reservas.size()-1).getPrecio30());
			System.out.println("Se hizo el cobro a la tarjeta del 30% del valor de la reserva");
			System.out.println("Cuando recoga el vehiculo se hara el cobro restante");
			
			 } catch (ParseException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		}
			guardarycerra();
		
	 } 
   	
   
   //Carga de informacion calendario
   public static Map<Date, Integer> generarFechasDeDosAnios(int yearInicio, int yearFin) 
   {
    Map<Date, Integer> mapaFechas = new HashMap<>();
   
    for (int year = yearInicio; year <= yearFin; year++) {
        for (int i = 1; i <= LocalDate.ofYearDay(year, 1).lengthOfYear(); i++) {
            LocalDate fecha = LocalDate.ofYearDay(year, i);
            Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            mapaFechas.put(date, 0);
        }
    }
    return mapaFechas;
   }
   
   
   
   

   //Carga de Informacion empleados
   public void cargarReservasEnCalendario(EmpresaAlquilerVehiculos programa)
   {
	for(Vehiculo i:programa.listaVehiculo) {
		
		for(Map.Entry<Date, Integer> entry : EmpresaAlquilerVehiculos.calendario.entrySet())
			{
			 Date fecha = entry.getKey();
			 
			if (i.getFechaInicio()!=null &&(fecha.compareTo(i.getFechaInicio()) >= 0  && fecha.compareTo(i.getFechaFinal()) <= 0)){
				
				
				EmpresaAlquilerVehiculos.calendario.put(fecha, entry.getValue() + 1);
				}
			}
	  }
    }
   
   public DefaultListModel<String> disponibilidadFechas(String FechaInicial ,String fechaFinal) throws ParseException {
	   SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
	   formato.setTimeZone(TimeZone.getTimeZone("COT"));
	   Date fecha1 = formato.parse(FechaInicial);
	
	   Date fecha2 = formato.parse(fechaFinal);
	   
	   DefaultListModel<String> listaDeFechas = new DefaultListModel<>();
	   
	   
	   for(Map.Entry<Date,Integer> entry : EmpresaAlquilerVehiculos.calendario.entrySet() ) {
		  
		   
		    if(verificarFechaEnRango(entry.getKey(),fecha1,fecha2 )) {
		    	
			   String fecha = formato.format(entry.getKey());
			   fecha = fecha + " Numero de Coches disponibles: "+String.valueOf(entry.getValue());
			   listaDeFechas.addElement(fecha);   
		   }
		   
		   
	   }
	   return listaDeFechas;
   }
   
   private static boolean verificarFechaEnRango(Date fechaAVerificar, Date fechaInicio, Date fechaFin) {
       
       Calendar calFechaAVerificar = Calendar.getInstance();
       calFechaAVerificar.setTime(fechaAVerificar);

       Calendar calFechaInicio = Calendar.getInstance();
       calFechaInicio.setTime(fechaInicio);

       Calendar calFechaFin = Calendar.getInstance();
       calFechaFin.setTime(fechaFin);

       // Verifica si la fecha está en el rango
       return (calFechaAVerificar.after(calFechaInicio) || calFechaAVerificar.equals(calFechaInicio))
               && (calFechaAVerificar.before(calFechaFin) || calFechaAVerificar.equals(calFechaFin));
   }
   

   
   //Carga de la persistencia
   private void cargaPersistencia(Persistencia persistencia, EmpresaAlquilerVehiculos self) throws ParseException 
   {
		ArrayList<Reserva> reservasAux;
		reservasAux = persistencia.cargarReservas("./data/persistencia/reservas.txt\\", self);
		reservas.addAll(reservasAux);
		numeroReservaInteger+=reservas.size();
		
   }
    
   
   //carga de datos de la Empresa
   private void cargaDatos(ControllerCarga control) throws ParseException 
   {  
	 listaClientes = control.cargarClientes("./data/clientes.txt\\");
	 listaEmpleados = control.cargarEmpleados("./data/empleados.txt\\");
	 categoriaVehiculo = control.cargarCategoria("./data/tarifas.txt\\\\");
	 listaVehiculo = control.cargarVehiculos(categoriaVehiculo,"./data/vehiculos.txt\\");
	 listaAdministradorLocal = control.cargarAdministradorLocal("./data/administradorLocal.txt\\");
	 administradorGeneral = control.cargarAdministradorGeneral("./data/administradorGeneral.txt\\");
	 listaSedes = control.cargarSedes(listaEmpleados, listaVehiculo, listaAdministradorLocal, "./data/sedes.txt\\");
	 listaUsuarioGenericos = control.cargaUsuarios(listaEmpleados, listaClientes, listaAdministradorLocal, administradorGeneral);
	 listaMediosDePago =  control.cargaMediosDePago("./data/Pasarelas.txt\\");
	 
    }
   
   
   
   

   //Errores en lectura
   public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
   
   
   
   

   //set y get
   public ArrayList<Vehiculo> darListaVehiculo()
   {
	 return listaVehiculo;
   }
 
 
   public static Integer getNumeroReservaInteger() {
	return numeroReservaInteger;
   }
   
   
   
   
   //Main
   public static void main(String[] args) throws ParseException 
   {
	 
	 EmpresaAlquilerVehiculos programa = new EmpresaAlquilerVehiculos();
	
	 InterfazPrincipal ventana = new InterfazPrincipal(programa);
	 ventana.setLocationRelativeTo( null );
     ventana.setVisible( true ); 
     
	
     //System.out.println("Fechas para 2023 y 2024:");
     
     //for (Map.Entry<Date, Integer> entry : EmpresaAlquilerVehiculos.calendario.entrySet()) {
       //  System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());}
 
   }


}
