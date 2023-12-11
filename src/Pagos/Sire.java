package Pagos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Sire extends PagosGenerales{

	
	public String generartexto(String nombre, int cantidad, int numeroTarjeta, int contraseñaTarjeta, String pasaje) {
   	 String linea = "";
   	 System.out.println("Se pudo Sire");
   	// Generar un número aleatorio como identificador
        Random rand = new Random();
        int identificador = rand.nextInt(1000000); // Puedes ajustar el rango según tus necesidades

        // Obtener la fecha y hora actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaHoraActual = dateFormat.format(new Date());
        
        linea += identificador + ";";
        linea += nombre + ";";
        linea += cantidad + ";";
        linea += numeroTarjeta + ";";
        linea += pasaje + ";";
        linea += "APROBADO;";
        linea += fechaHoraActual + ";"; 

        linea += "\n";
   	 	
   	 return linea;
	}
    
    public void GuardarPagoRegistro(String Texto) {
   	 try {
   	     	String nombreArchivo = "./data/Pagos/" + "Sire.json";    
   	         BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
   	         writer.write(Texto);
   	         writer.close();
   	         System.out.println("Se ha guardado el archivo de reservas en: " + nombreArchivo);
   	     } catch (IOException e) {
   	         System.err.println("Error al guardar el archivo.");
   	         e.printStackTrace();
   	     }
	}
}
