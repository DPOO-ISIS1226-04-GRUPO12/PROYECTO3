package consola;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.itextpdf.text.DocumentException;

import logica.AdministradorLocal;
import logica.Empleado;


public class PanelEmpleado extends JPanel implements ActionListener{
	
	private InterfazPrincipal interfazPrincipal;
	private JButton botonRecibirVehiculo;
	private JButton BtonCrearReserva;
	private JButton btonModificarEstado;
	private Image imagen;	
	private JComboBox<Integer> usuariosBox; 
	private InterfazRegistrarReservaEmpleado interfazRegistrarReservaEmpleado;
	private Empleado empleado;

	
	public PanelEmpleado(InterfazPrincipal interfazPrincipal,Empleado empleado ) {
		
		this.interfazPrincipal = interfazPrincipal;
		this.empleado = empleado;
		cargarImagen();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Image
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		imagen = imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel lJLabel = new JLabel(new ImageIcon(imagen)) ;
		lJLabel.setSize(50,50);
		add(lJLabel, c);
		c = new GridBagConstraints();

		// JLabel "Empleado"
		c.gridx = 0;
		c.gridy = 3; 
		JLabel labelEmpleado = new JLabel("Empleado");
		labelEmpleado.setFont(new Font(labelEmpleado.getFont().getName(),Font.ITALIC,25));
		add(labelEmpleado, c);
		
		JPanel PanelInfo = new JPanel();
		PanelInfo.setLayout(new GridLayout(3,2));
		JLabel labelNombre = new JLabel("Nombre");
		Font fontnombre = labelNombre.getFont();
		labelNombre.setFont(new Font(fontnombre.getName(), Font.PLAIN, 25));
		PanelInfo.add(labelNombre);

		// JLabel con el nombre del Empleado
		JLabel labelNombreValor = new JLabel(empleado.getNombre());
		Font fontNombre = labelNombreValor.getFont();
		labelNombreValor.setFont(new Font(fontNombre.getName(), Font.ITALIC, 25));
		PanelInfo.add(labelNombreValor);

		// JLabel "Usuario"
		JLabel labelUsuario = new JLabel("Usuario:");
		Font fontu = labelUsuario.getFont();
		labelUsuario.setFont(new Font(fontu.getName(),Font.PLAIN,25));
		PanelInfo.add(labelUsuario);

		// JLabel con el nombre de usuario del Empleado
		JLabel labelUsuarioValor = new JLabel(empleado.getUsuario());
		Font fontUsuario = labelUsuarioValor.getFont();
		labelUsuarioValor.setFont(new Font(fontUsuario.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelUsuarioValor);

		// JLabel "Sede"
		JLabel labelSede = new JLabel("Sede:");
		labelSede.setFont(new Font(labelSede.getFont().getName(),Font.PLAIN,25));
		PanelInfo.add(labelSede);

		// JLabel con el nombre de la sede del administrador
		JLabel labelSedeValor = new JLabel(empleado.getSede());
		Font fontSede = labelSedeValor.getFont();
		labelSedeValor.setFont(new Font(fontSede.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelSedeValor);
		// JLabel "Nombre"
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(1, 10, 1, 1);
		add(PanelInfo,c);
		
		//Panel buscar por id vehiculo
		
		JPanel panelBuscarCarro = new JPanel();
		panelBuscarCarro.setLayout(new GridLayout(1,2,1,0));
		JLabel labelIngresar = new JLabel("Seleccione ID Vehiculo: ");
		panelBuscarCarro.add(labelIngresar);
	
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=5;
		c.insets= new Insets(5, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		add(panelBuscarCarro,c);
		
		//Modificacion del estado de los vehiculos
		JPanel panelModificarEstado = new JPanel();
		panelModificarEstado.setLayout(new GridLayout(1,2,10,0));
		ArrayList<Integer> listaCarros = interfazPrincipal.listaCarros();
		Integer[] arrayCarros = listaCarros.toArray(new Integer[0]);

		usuariosBox = new JComboBox<Integer>(arrayCarros);
		
		btonModificarEstado = new JButton("Devolucion del carro");
		btonModificarEstado.setBackground(Color.red);
		panelModificarEstado.add(usuariosBox);
		panelModificarEstado.add(btonModificarEstado);
		btonModificarEstado.addActionListener(this);
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=6;
		c.insets= new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		add(panelModificarEstado,c);
		
		//Regsitro de alguiler y entrega de vehiculos
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(1,2,50,0));
		botonRecibirVehiculo = new JButton("Entrega Vehiculo");
		BtonCrearReserva = new JButton("Crear reserva");
		panelInferior.add(botonRecibirVehiculo);
		panelInferior.add(BtonCrearReserva);
		BtonCrearReserva.addActionListener(this);
		botonRecibirVehiculo.addActionListener(this);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy =7;
		c.insets = new Insets(20,1, 1, 1);
		c.gridwidth = 3;
		add(panelInferior,c);
		
	}
	
	
	private void cargarImagen() {
		try {
			imagen  = ImageIO.read(new File("./data/Imagenes/usuario.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

	public void agregarLabel(String nombre,ArrayList<JLabel> listaLabels) {
		JLabel label = new JLabel(nombre);
        listaLabels.add(label);
	}
	
	
	public int CrearReservaNueva(String categoria, String sedeRecoger, String fechaInicial, String fechaFinal, String sedeDevolver, String nombreCliente) {
		int estaHecha = interfazPrincipal.buscarClienteYCrearReservaCliente(categoria, sedeRecoger, fechaInicial, fechaFinal, sedeDevolver, nombreCliente);
		return estaHecha;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==BtonCrearReserva) {
			 interfazRegistrarReservaEmpleado = new InterfazRegistrarReservaEmpleado(this);
			 interfazRegistrarReservaEmpleado.setVisible(true);
			 
		
    	}else if (e.getSource()== btonModificarEstado) {
    		 Integer idVehiculoSeleccionado = (Integer) usuariosBox.getSelectedItem();
    	
    		 int Hecha = interfazPrincipal.DevolverCarro(idVehiculoSeleccionado,empleado);
    		 if(Hecha == 0) {
    			 JOptionPane.showMessageDialog(null, "No ha habido reserva de este Vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    		 }else {
    			 JOptionPane.showMessageDialog(null, "Devolucion Exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    		 }
    		     
    	}else if(e.getSource()== botonRecibirVehiculo){
    		
    		Integer idVehiculoSeleccionado = (Integer) usuariosBox.getSelectedItem();
    		try {
				int hecho3 = interfazPrincipal.RecibirCarroCliente(empleado, idVehiculoSeleccionado);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Entrega Hecha", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            });
    	}
    		
    		
		
	    }
	
	
	public ArrayList<JTextField> crearJtextFieldsParaLabels(ArrayList<JLabel> listaLabels,JPanel panel, int primeraColumna,int inicioDesde0) {
		ArrayList<JTextField> listaJTextFields = new ArrayList<JTextField>();
		for ( int i=0;i<listaLabels.size();i++) {
			GridBagConstraints c;
			Border borde = BorderFactory.createLineBorder(Color.black, 1);

	       
	        
			if(i<primeraColumna) {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
					textField.setBorder(borde);
				 listaJTextFields.add(textField);
				 textField.setPreferredSize(new Dimension(200, 30));
				 textField.setEditable(true);
//				
				 c.gridy=i+inicioDesde0;
				 c.gridx=0;
				 panel.add(listaLabels.get(i),c);
				 c.gridx=1;
				 panel.add(textField,c);
				
				
				
			}	else {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
				textField.setBorder(borde);
				listaJTextFields.add(textField);
				textField.setPreferredSize(new Dimension(230, 30));
				textField.setEditable(true);
				c.gridx=3;
				c.gridy=inicioDesde0+i-primeraColumna;
				panel.add(textField,c);
				c.gridx=2;
				panel.add(listaLabels.get(i),c);
			}
			
		}
		return listaJTextFields;
	}

	
	public void actualizarUsuariosBox() {
	    ArrayList<Integer> listaCarros = interfazPrincipal.listaCarros();
	    Integer[] arrayCarros = listaCarros.toArray(new Integer[0]);
	    usuariosBox.setModel(new JComboBox<>(arrayCarros).getModel());
	}


	public int realizarPagotarjeta(String nombre, int cantidad, int numeroTarjeta, int contraseñaTarjeta, String pasaje) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		int estaHecha = interfazPrincipal.buscarClienteYPagar(nombre, cantidad, numeroTarjeta, contraseñaTarjeta, pasaje);
		return estaHecha;
	}


}


