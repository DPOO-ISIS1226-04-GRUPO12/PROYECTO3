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

public class InterfazRegistrarClienteAdminLocal extends JFrame implements ActionListener{
	
	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelAdminLocal interfazAdminLocal;
	
	public InterfazRegistrarClienteAdminLocal(PanelAdminLocal panelAdminLocal) {
		this.interfazAdminLocal = panelAdminLocal;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazAdminLocal.agregarLabel("Nombre", listaLabels);
		interfazAdminLocal.agregarLabel("Nacionalidad", listaLabels);
		interfazAdminLocal.agregarLabel("Telefono", listaLabels);
		interfazAdminLocal.agregarLabel("Fecha de Nacimiento", listaLabels);
		interfazAdminLocal.agregarLabel("Pais de expedicion", listaLabels);
		interfazAdminLocal.agregarLabel("Usuario", listaLabels);
		interfazAdminLocal.agregarLabel("Contraseña", listaLabels);
		interfazAdminLocal.agregarLabel("Numero Licencica", listaLabels);
		interfazAdminLocal.agregarLabel("FechaVen Licencia", listaLabels);
		interfazAdminLocal.agregarLabel("Numero de Tarjeta", listaLabels);
		interfazAdminLocal.agregarLabel("Contraseña de la tarjeta", listaLabels);
		
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		panelInterfaz.add(new JLabel("Ingrese la informacion del vehiculo a registrar"),c);
		listaJTextFields = panelAdminLocal.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,5,1);
		
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
			String Nacionalidad="";
			String telefono="";
			String FechaNac="";
			String PaisExp="";
			String Usuario="";
			String Contraseña="";
			int NLicencia = 0;
			String FechaVencLicen="";
			int NTarjeta = 0;
			int ContraTarjeta = 0;

			for (int i = 0; i < listaJTextFields.size(); i++) {
	
				switch (i) {
				
				case 0: 
					Nombre = listaJTextFields.get(i).getText();
					
					break;
					
				case 1:
					Nacionalidad = listaJTextFields.get(i).getText();
				
					break;
				case 2:
				    telefono = listaJTextFields.get(i).getText();
		
					break;
				case 3:
					FechaNac = listaJTextFields.get(i).getText();
			
					break;
				case 4:
					PaisExp = listaJTextFields.get(i).getText();
					
					break;
				case 5:
					Usuario =listaJTextFields.get(i).getText();
					
					break;
				case 6:
					Contraseña =listaJTextFields.get(i).getText();
					
					break;
				case 7:
					try {
						String Licencia = listaJTextFields.get(i).getText();
						NLicencia = Integer.parseInt(Licencia);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el cliente, numero de licencia no valido", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				case 8:
					FechaVencLicen =listaJTextFields.get(i).getText();
					
					break;
				case 9:
					try {
						String NTarje = listaJTextFields.get(i).getText();
						NTarjeta = Integer.parseInt(NTarje);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el cliente, numero de Tarjeta no valido", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				case 10:
					try {
						String CTarjeta = listaJTextFields.get(i).getText();
						ContraTarjeta = Integer.parseInt(CTarjeta);
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar el cliente, contraseña no valido, tiene que ser de 4 digitos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				default:
					JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				
				}
			if( (Nombre.equals("")) || (Nacionalidad.equals("")) || (telefono.equals("")) || (FechaNac.equals("")) || (PaisExp.equals("")) || (Usuario.equals("")) || (Contraseña.equals(""))){
				JOptionPane.showMessageDialog(null, "No se pudo registrar el vehiculo, Complete todos los Espacio", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}else{
				try {
					interfazAdminLocal.RegistrarClienteNuevo(Nombre,Nacionalidad,telefono,FechaNac,PaisExp,Usuario,Contraseña,NLicencia,FechaVencLicen,NTarjeta,ContraTarjeta);
					JOptionPane.showMessageDialog(this, "Registro del Cliente Exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
			}
			}
		}
}

