package consola;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazMedioDePagoTarjeta extends JFrame implements ActionListener{

	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelEmpleado interfazEmpleado;
	String pasaje = "";
	
	JRadioButton cdDifi1;
    JRadioButton cdDifi2;
    JRadioButton cdDifi3;
	
	public InterfazMedioDePagoTarjeta(PanelEmpleado panelEmpleado) {
		this.interfazEmpleado = panelEmpleado;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazEmpleado.agregarLabel("Nombre: ", listaLabels);
		interfazEmpleado.agregarLabel("Cantidad: ", listaLabels);
		interfazEmpleado.agregarLabel("Numero de la Tarjeta: ", listaLabels);
		interfazEmpleado.agregarLabel("Contraseña: ", listaLabels);
	    
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 4, 10, 10);
		panelInterfaz.add(new JLabel("Seleccione el pasaje de Pago: "),c);
		listaJTextFields = panelEmpleado.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,2,2);
		
		ButtonGroup grupoDificultad = new ButtonGroup();
	       
	     
	       
	       cdDifi1 = new JRadioButton("PayPal");
	       cdDifi2 = new JRadioButton("PayU");
	       cdDifi3 = new JRadioButton("Sire");
	       
	       grupoDificultad.add(cdDifi1);
	       grupoDificultad.add(cdDifi2);
	       grupoDificultad.add(cdDifi3);
	       
	       cdDifi1.setBackground(new Color(79,193,223)); // Establecer fondo azul para los JRadioButton
	       cdDifi2.setBackground(new Color(79,193,223));
	       cdDifi3.setBackground(new Color(79,193,223));
		
		panelInterfaz.setBackground(new Color(79,193,223));
		//Boton Registrar
		botonRegistrar  = new JButton("Pagar");
		botonRegistrar.setBackground(new Color(	188,192,193));
		botonRegistrar.setSize(getPreferredSize());
		botonRegistrar.addActionListener(this);
		
		
		
		c.gridx =1;
		c.gridy =7;
		c.gridwidth= 3;
		c.weightx = 2;
		
		
		panelInterfaz.add(cdDifi1);
		panelInterfaz.add(cdDifi2);
		panelInterfaz.add(cdDifi3);
		panelInterfaz.add(botonRegistrar,c);
		
	       cdDifi1.addActionListener( this );
	       cdDifi2.addActionListener( this );
	       cdDifi3.addActionListener( this );
		
		add(panelInterfaz);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setSize(760,230);
		setResizable(false);	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource( )== cdDifi1) {
	    	pasaje = "Pagos.PayPal";
	    }else if(e.getSource( )== cdDifi2) {
	    	pasaje = "Pagos.PayU";
	    }else if(e.getSource( )== cdDifi3) {
	    	pasaje = "Pagos.Sire";
	    }else if(e.getSource()== botonRegistrar) {
			
			String Nombre="";
			int cantidad = 0;
			int NumeroTarjeta= 0 ;
			int ContraseñaTarjeta= 0 ;
			
		    int o = 0;
			for (int i = 0; i < listaJTextFields.size(); i++) {
	
				switch (i) {
				
				case 0: 
					Nombre = listaJTextFields.get(i).getText();
					
					break;
					
				case 1:
					try {
						String Canti = listaJTextFields.get(i).getText();
						cantidad = Integer.parseInt(Canti);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el pago, cantidad invalida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				case 2:
					try {
						String NumTarjeta = listaJTextFields.get(i).getText();
						NumeroTarjeta = Integer.parseInt(NumTarjeta);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el pago, numero de tarjeta invalido", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				case 3:
					try {
						String ContraTarjeta = listaJTextFields.get(i).getText();
						ContraseñaTarjeta = Integer.parseInt(ContraTarjeta);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el pago, contraseña invalida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
			
				default:
					JOptionPane.showMessageDialog(null, "No se pudo realizar el pago", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				
				}
			if( (Nombre.equals(""))) {
				JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo, Complete todos los espacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else if(pasaje.equals("")){
				JOptionPane.showMessageDialog(null, "No se pudo el pago, Seleccione una de las opciones de pago", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else {
				
				int estaHecha;
				try {
					estaHecha = interfazEmpleado.realizarPagotarjeta(Nombre, cantidad, NumeroTarjeta, ContraseñaTarjeta,pasaje);
					if(estaHecha == 0) {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el pado del vehiculo, Cliente no encontrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else if(estaHecha == 1){
						JOptionPane.showMessageDialog(null, "No se pudo realizar el pado del vehiculo, Numero de tarjeta o contraseña Incorrecto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}else{
					JOptionPane.showMessageDialog(this, "Vehiculo registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				
				    this.dispose();
					}
				} catch (NoSuchMethodException | SecurityException | IllegalArgumentException
						| InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}
}
}
