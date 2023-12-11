package consola;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;


public class InterfazRegistrarEmpleoAdminLocal extends JFrame implements ActionListener{
	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelAdminLocal interfazAdminLocal;
	
	public InterfazRegistrarEmpleoAdminLocal(PanelAdminLocal panelAdminLocal) {
		this.interfazAdminLocal = panelAdminLocal;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazAdminLocal.agregarLabel("Nombre", listaLabels);
		interfazAdminLocal.agregarLabel("Sede", listaLabels);
		interfazAdminLocal.agregarLabel("Usuario", listaLabels);
		interfazAdminLocal.agregarLabel("Contraseña", listaLabels);
		
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		panelInterfaz.add(new JLabel("Ingrese la informacion del vehiculo a registrar"),c);
		listaJTextFields = panelAdminLocal.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,2,1);
		
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
		setSize(810,300);
		setResizable(false);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== botonRegistrar) {
			
			
			String Nombre="";
			String Sede="";
			String Usuario="";
			String Contraseña="";
			

			for (int i = 0; i < listaJTextFields.size(); i++) {
	
				switch (i) {
				
				case 0: 
					Nombre = listaJTextFields.get(i).getText();
					
					break;
					
				case 1:
					Sede = listaJTextFields.get(i).getText();
				
					break;
				
				case 2:
					Usuario =listaJTextFields.get(i).getText();
					
					break;
				case 3:
					Contraseña =listaJTextFields.get(i).getText();
					
					break;
				
					
				default:
					JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				
				}
			if( (Nombre.equals("")) || (Sede.equals("")) ||(Usuario.equals("")) || (Contraseña.equals(""))){
				JOptionPane.showMessageDialog(null, "No se pudo registrar el Empleado, Complete todos los Espacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else{
				
				int completo = interfazAdminLocal.RegistrarEmpleadoNuevo(Nombre,Sede,Usuario,Contraseña);
				
				if(completo == 1) {
				JOptionPane.showMessageDialog(this, "Registro del Empleado Exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}
				}
			}
		}
}