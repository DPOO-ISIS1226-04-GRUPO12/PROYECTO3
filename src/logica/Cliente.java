package logica;

import java.util.ArrayList;
import java.util.Date;

import consola.EmpresaAlquilerVehiculos;

public  class Cliente extends UsuarioGenerico {
 
 public String nombre;
 public String nacionalidad;
 public String telefono;
 public String fechaNac;
 protected LicienciaConducion licienciaConducion;

 protected  MedioDePago tarjeta;

 protected  ArrayList<Reserva>  reservas = new ArrayList<Reserva>(); ;



 
 public Cliente(String nombre, String nacionalidad, String telefono, String fechaNacimiento,String usuario, String contraseña, String tipoUsuario,ArrayList<Reserva> reservas,LicienciaConducion licencia, MedioDePago tarjeta)
	{
	  super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.fechaNac = fechaNacimiento;
		this.reservas = new ArrayList<Reserva>();;
		this.licienciaConducion = licencia;
		this.tarjeta = tarjeta;
	}
 	public Cliente() {
	super("", "", "Cliente");
 	}
 
    public String getNombre() 
    {
    	return nombre;
    }

   
    public MedioDePago getMedioDePago() 
    {
	   return tarjeta;
    }

   
    public Reserva crearReserva(Vehiculo vehiculo,Date fechaInicio,Date FechaFinal,double precio30 ,double precioRestante,String sedeDevolver,String sedeRecoger) 
    {
	  Reserva reserva = new Reserva(EmpresaAlquilerVehiculos.getNumeroReservaInteger(),vehiculo.getCategoria().getNombreCategoria(),
			  fechaInicio,FechaFinal,precio30,precioRestante,
			  precio30+precioRestante,tarjeta.getNumeroTarjeta(),
			  sedeRecoger,sedeDevolver,null,false,vehiculo,nombre);	
	  System.out.println(reserva);
	  reservas.add(reserva);
	  return reserva;
    }

  
    public  ArrayList<Reserva> getReservas() 
    {
	  return reservas;
    }
    

    
    public String getFechaNac()
    {
	  return fechaNac;
    }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public void setMedioDePago(MedioDePago tarjeta) {
		this.tarjeta = tarjeta;
	}
	public void setLicienciaConducion(LicienciaConducion licienciaConducion) {
		this.licienciaConducion = licienciaConducion;
	}
	public int getNumeroTarjeta() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
    
}
