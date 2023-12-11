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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarVehiculo extends JFrame implements ActionListener {
	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private InterfazPrincipal interfazPrincipal;
	
	public InterfazRegistrarVehiculo(InterfazPrincipal interfazPrincipal) {
		this.interfazPrincipal = interfazPrincipal;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazPrincipal.agregarLabel("ID", listaLabels);
		interfazPrincipal.agregarLabel("Categoria", listaLabels);
		interfazPrincipal.agregarLabel("Placa", listaLabels);
		interfazPrincipal.agregarLabel("Transmision", listaLabels);
		interfazPrincipal.agregarLabel("Url Imagen", listaLabels);
		interfazPrincipal.agregarLabel("Sede", listaLabels);
		interfazPrincipal.agregarLabel("Modelo", listaLabels);
		interfazPrincipal.agregarLabel("Color", listaLabels);
		interfazPrincipal.agregarLabel("Capacidad", listaLabels);
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		panelInterfaz.add(new JLabel("Ingrese la informacion del vehiculo a registrar"),c);
		listaJTextFields = interfazPrincipal.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,5,1);
		
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
		setSize(700,270);
		setResizable(false);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== botonRegistrar) {
			String texto="";
			String categoria="";
			int id=0;
			String placa="";
			String transmision="";
			String rutaImagen="";
			String sede="";
			String modelo="";
			String colorString="";
			int capacidad=0;
			for (int i = 0; i < listaJTextFields.size(); i++) {
				
				switch (i) {
				
				case 0: 
					//Id
					try {
						texto = listaJTextFields.get(i).getText();
						id = Integer.parseInt(texto);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					
				case 1:
					categoria = listaJTextFields.get(i).getText();
					break;
				case 2:
					placa =listaJTextFields.get(i).getText();
					break;
				case 3:
					transmision = listaJTextFields.get(i).getText();
					break;
				case 4:
					rutaImagen = listaJTextFields.get(i).getText();
					break;
				case 5:
					sede =listaJTextFields.get(i).getText();
					break;
				case 6:
					modelo =listaJTextFields.get(i).getText();
					break;
				case 7:
					colorString =listaJTextFields.get(i).getText();
					break;
				case 8:
					try {
						capacidad = Integer.parseInt(listaJTextFields.get(i).getText())  ;
						break;
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						continue;
					}
					
				default:
					JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			interfazPrincipal.RegistrarVehiculoSistema(sede, modelo, capacidad, placa, colorString, transmision, categoria, rutaImagen, id);
			JOptionPane.showMessageDialog(this, "Vehiculo registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		
	}
	
}
