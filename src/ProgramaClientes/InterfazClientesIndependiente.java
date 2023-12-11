package ProgramaClientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import consola.EmpresaAlquilerVehiculos;
import consola.InterfazPrincipal;
import consola.InterfazRegistrarReservaCliente;
import consola.InterfazRegistrarVehiculo;
import logica.Cliente;

public class InterfazClientesIndependiente extends JFrame implements ActionListener {
	
	private JPanel panelPrincipal;
	private	JButton botonRegistrarNuevoUsuario;
	private	JButton botonConsultarDisponibilidad;
	private	JButton botonReservarVehiculo;
	private	ArrayList<JLabel> labelsUsuario = new ArrayList<JLabel>();
	private	JButton botonAgregarUsuario ;
	private	Boolean presionado = false;
	private EmpresaAlquilerVehiculos empresaAlquilerVehiculos;
	private	ArrayList<JTextField> listaJTextFields = new ArrayList<JTextField>();
	private JButton botonBuscarDisponibilidad;
	private JPanel panelDisponibilidad;
	private JTextField fechaInicialField;
	private JTextField fechaFinalField;
	private Frame frameDisponibilidad;
	private String usuarioA = "2";
	private String contraseniaA = "2";
	private InterfazRegistrarVehiculo interfazRegistrarVehiculo;
	
	public InterfazClientesIndependiente(EmpresaAlquilerVehiculos empresaAlquilerVehiculos)
	{
		this.empresaAlquilerVehiculos = empresaAlquilerVehiculos;
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(3,1));
		botonRegistrarNuevoUsuario = new JButton("Registrar nuevo Usuario");
		botonRegistrarNuevoUsuario.addActionListener(this);
		botonConsultarDisponibilidad = new JButton("Consultar Disponibilidad");
		botonConsultarDisponibilidad.addActionListener(this);
		botonReservarVehiculo = new JButton("Reservar Vehiculo");
		botonReservarVehiculo.addActionListener(this);
		panelPrincipal.add(botonRegistrarNuevoUsuario);
		panelPrincipal.add(botonConsultarDisponibilidad);
		panelPrincipal.add(botonReservarVehiculo);
		
		add(panelPrincipal);
		pack();
		
		
	}
	
	public void interfazRegistrarUsuario() {
		
			
		
		
		JFrame frameRegistrarUsuario = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		agregarAListaLabels("Nombre");
		agregarAListaLabels("Nacionalidad");
		agregarAListaLabels("Telefono");
		agregarAListaLabels("Fechanac");
		agregarAListaLabels("Numero Licencia");
		agregarAListaLabels("Pais Expedicion");
		agregarAListaLabels("Fecha vencimiento Licencia");
		agregarAListaLabels("Numero de tarjeta");
		agregarAListaLabels("Tipo de tarjeta");
		agregarAListaLabels("Fecha vencimiento Tarjeta");
		agregarAListaLabels("Usuario");
		agregarAListaLabels("Contraseña");;
		
		for ( int i=0;i<labelsUsuario.size();i++) {
			GridBagConstraints c;
			Border borde = BorderFactory.createLineBorder(Color.black, 1);

	       
	        
			if(i<6) {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
					textField.setBorder(borde);
				 listaJTextFields.add(textField);
				 textField.setPreferredSize(new Dimension(250, 30));
				 textField.setEditable(true);
//				
				 c.gridy=i+0;
				 c.gridx=0;
				 panel.add(labelsUsuario.get(i),c);
				 c.gridx=1;
				 panel.add(textField,c);
				
				
				
			}	else {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
				textField.setBorder(borde);
				listaJTextFields.add(textField);
				textField.setPreferredSize(new Dimension(250, 30));
				textField.setEditable(true);
				c.gridx=3;
				c.gridy=0+i-6;
				panel.add(textField,c);
				c.gridx=2;
				panel.add(labelsUsuario.get(i),c);
			}
			
		}
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy =7;
		c.gridwidth =4;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		botonAgregarUsuario = new JButton("Agregar Usuario");
		botonAgregarUsuario.addActionListener(this);
		botonAgregarUsuario.setBackground(Color.CYAN);
		panel.add(botonAgregarUsuario,c);
		
	
			
		frameRegistrarUsuario.add(panel);
		frameRegistrarUsuario.setVisible(true);
	
		frameRegistrarUsuario.pack();
		frameRegistrarUsuario.setSize(800, 400);
		
		frameRegistrarUsuario.setResizable(false);
		//frameRegistrarUsuario.set
		
		
	
	}
	public void interfazDisponibilidad() {
		 frameDisponibilidad = new JFrame();	
		 panelDisponibilidad = new JPanel();
		panelDisponibilidad.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel labelText = new JLabel("Consultar disponibilidad de vehiculos");
		c.gridwidth = 2;
		c.gridx =0;
		c.gridy =0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets.bottom = 15;
		panelDisponibilidad.add(labelText,c);
		c = new GridBagConstraints();
		JLabel labelFecha1 = new JLabel("Fecha Inicio");
		c.gridx=0;
		c.gridy=1;
		c.insets.right =10;
		panelDisponibilidad.add(labelFecha1,c);
		
		c.gridx=1;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		fechaInicialField = new JTextField();
		TextPrompt prompts= new TextPrompt("EEE MMM dd HH:mm:ss zzz yyyy", fechaInicialField);
		panelDisponibilidad.add(fechaInicialField,c);
		
		JLabel labelFecha2 = new JLabel("Fecha Final");
		c.gridx=0;
		c.gridy=2;
		c.insets.right =10;
		panelDisponibilidad.add(labelFecha2,c);
		fechaFinalField = new JTextField();
		TextPrompt prompt= new TextPrompt("EEE MMM dd HH:mm:ss zzz yyyy", fechaFinalField);
		c.gridx=1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelDisponibilidad.add(fechaFinalField,c);
		
		botonBuscarDisponibilidad = new JButton("Buscar");
		botonBuscarDisponibilidad.addActionListener(this);
		c.gridx=0;
		c.gridy=3;
		c.insets.top=10;
		c.gridwidth=2;
		
		
		panelDisponibilidad.add(botonBuscarDisponibilidad,c);
		
		
		
		frameDisponibilidad.add(panelDisponibilidad);
		frameDisponibilidad.setVisible(true);
		frameDisponibilidad.pack();	
;	}
	
	public void mostrarListaDisponibilidad() {
		try {
			DefaultListModel<String> listaDefaultListModel =	empresaAlquilerVehiculos.disponibilidadFechas(fechaInicialField.getText(), fechaFinalField.getText());
			JList<String> lista = new JList<>(listaDefaultListModel);
			
			JScrollPane scrollPane = new JScrollPane(lista);
			GridBagConstraints c = new GridBagConstraints();
			c.gridx=0;
			c.gridy =4;
			c.gridwidth=2;
			
			
			panelDisponibilidad.add(scrollPane,c);
			panelDisponibilidad.revalidate();
			frameDisponibilidad.pack();
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonRegistrarNuevoUsuario) {
			interfazRegistrarUsuario();
		}
		else if (e.getSource()==botonAgregarUsuario) {
			empresaAlquilerVehiculos.crearCliente(listaJTextFields);
			
		}
		else if (e.getSource()==botonConsultarDisponibilidad) {
			interfazDisponibilidad();
		}
		else if (e.getSource()==botonBuscarDisponibilidad) {
			mostrarListaDisponibilidad();
		}
		
		else if (e.getSource()==botonReservarVehiculo)
		{
			InterfazPrincipal interfazPrincipal = new InterfazPrincipal(empresaAlquilerVehiculos);
			InterfazRegistrarReservaCliente interfazRegistrarReservaCliente = new InterfazRegistrarReservaCliente(0,usuarioA,contraseniaA,interfazPrincipal);
			interfazRegistrarReservaCliente.setVisible(true);
		}
		
	}
	
	public void agregarAListaLabels(String textoLabel) {
		JLabel label = new JLabel(textoLabel);
		labelsUsuario.add(label);
	}
	
	public static void main(String[] args) throws ParseException {
		EmpresaAlquilerVehiculos logica = new EmpresaAlquilerVehiculos();
		InterfazClientesIndependiente programa2 = new InterfazClientesIndependiente(logica);
		programa2.setLocationRelativeTo(null);
	    programa2.setVisible(true);
	}
	
	
	
	
}
