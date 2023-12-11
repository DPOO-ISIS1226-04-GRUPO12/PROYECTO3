package consola;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarReservaEmpleado extends JFrame implements ActionListener{
	
	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelEmpleado interfazEmpleado;
	private InterfazMedioDePagoTarjeta interfazPagoTarjeta;
	
	public InterfazRegistrarReservaEmpleado(PanelEmpleado panelEmpleado) {
		this.interfazEmpleado = panelEmpleado;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazEmpleado.agregarLabel("Categoría", listaLabels);
		interfazEmpleado.agregarLabel("Sede a Recoger", listaLabels);
		interfazEmpleado.agregarLabel("Fecha Inicial", listaLabels);
		interfazEmpleado.agregarLabel("Fecha Final", listaLabels);
		interfazEmpleado.agregarLabel("Sede a devolver", listaLabels);
		interfazEmpleado.agregarLabel("Nombre Cliente", listaLabels);
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		panelInterfaz.add(new JLabel("Ingrese la informacion del vehiculo a registrar"),c);
		listaJTextFields = panelEmpleado.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,3,1);
		
		panelInterfaz.setBackground(new Color(79,193,223));
		//Boton Registrar
		botonRegistrar  = new JButton("Reservar");
		botonRegistrar.setBackground(new Color(	188,192,193));
		botonRegistrar.setSize(getPreferredSize());
		botonRegistrar.addActionListener(this);
		c.gridx =1;
		c.gridy =7;
		c.gridwidth= 3;
		c.weightx = 2;
		
		panelInterfaz.add(botonRegistrar,c);
		
		add(panelInterfaz);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setSize(760,230);
		setResizable(false);	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== botonRegistrar) {
			
			
	        
			String categoria="";
			String sedeRecoger="";
			String FechaInicial="";
			String FechaFinal="";
			String sedeDevolver="";
			String NombreCliente="";
		    int o = 0;
			for (int i = 0; i < listaJTextFields.size(); i++) {
	
				switch (i) {
				
				case 0: 
					categoria = listaJTextFields.get(i).getText();
					
					break;
					
				case 1:
					sedeRecoger = listaJTextFields.get(i).getText();
				
					break;
				case 2:
				    FechaInicial = listaJTextFields.get(i).getText();
		
					break;
				case 3:
					FechaFinal = listaJTextFields.get(i).getText();
			
					break;
				case 4:
					sedeDevolver = listaJTextFields.get(i).getText();
					
					break;
				case 5:
					NombreCliente =listaJTextFields.get(i).getText();
					
					break;
					
				default:
					JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				
				}
			if( (categoria.equals("")) || (sedeRecoger.equals("")) || (FechaInicial.equals("")) || (FechaFinal.equals("")) || (sedeDevolver.equals("")) || (NombreCliente.equals(""))) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo, Complete todos los Espacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				
			int estaHecha =interfazEmpleado.CrearReservaNueva(categoria, sedeRecoger, FechaInicial, FechaFinal,sedeDevolver,NombreCliente);
			if(estaHecha == 0) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo, Cliente no encontrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				Object[] opciones = { "Efectivo", "Tarjeta"};

		        // Mostrar el cuadro de diálogo con opciones personalizadas
		        int seleccion = JOptionPane.showOptionDialog(null, "¿Como desea realizar el pago? : ", "Opciones Personalizadas",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
		        
		        if (seleccion == 1) {
		        	interfazPagoTarjeta = new InterfazMedioDePagoTarjeta(interfazEmpleado);
		        	interfazPagoTarjeta.setVisible(true);
		        	this.dispose();
		        }else {
			      JOptionPane.showMessageDialog(this, "Vehiculo registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			      this.dispose();
		        }
		
			}
			}
			
		}
}}

