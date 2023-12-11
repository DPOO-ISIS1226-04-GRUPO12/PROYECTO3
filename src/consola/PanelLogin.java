package consola;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLogin extends JPanel implements ActionListener{
	
	private Image imagen;
	private JTextField jTextFieldUsuario;
	private JTextField jTextFieldContrasenia;
	private JButton botonIngresar;
	private EmpresaAlquilerVehiculos empresaAlquilerVehiculos;
	private InterfazPrincipal interfazPrincipal;
	
	public PanelLogin(EmpresaAlquilerVehiculos empresaAlquilerVehiculos,InterfazPrincipal interfazPrincipal) {
		this.interfazPrincipal = interfazPrincipal;
		this.empresaAlquilerVehiculos = empresaAlquilerVehiculos;
		cargarImagen();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		jTextFieldContrasenia = new JTextField();
		jTextFieldUsuario = new JTextField();
		
		botonIngresar = new JButton("Ingresar");
		botonIngresar.setActionCommand("Ingresar");
		botonIngresar.addActionListener(this);
		
		//Componente Imagen
		c.gridx =0;
		c.gridy=0;
		add(new JLabel(new ImageIcon(imagen)),c);
		//Componente Usuario
		JLabel Usuario = new JLabel("<html><b>Usuario</b></html>");
		c = new GridBagConstraints();
		c.anchor =GridBagConstraints.WEST;
		c.gridy=2;
		add(Usuario,c);
		//Componente TextField del Usuario
		c = new GridBagConstraints();
		c.gridy=3;
		jTextFieldUsuario.setPreferredSize(new Dimension(600, 30));
		c.anchor =GridBagConstraints.WEST;
		add(jTextFieldUsuario,c);
		//Componente Contraseña
		JLabel contraseña = new JLabel("<html><b>Contraseña</b></html>");
		c = new GridBagConstraints();
		c.anchor =GridBagConstraints.WEST;
		c.gridy=4;
		add(contraseña,c);
		//Componente TextField del Usuario
		c = new GridBagConstraints();
		c.gridy=5;
		jTextFieldContrasenia.setPreferredSize(new Dimension(600, 30));
		c.anchor =GridBagConstraints.WEST;
		add(jTextFieldContrasenia,c);
		//Componente Olvidar Contraseña
		
		
		c = new GridBagConstraints();
		c.anchor =GridBagConstraints.CENTER;
		c.gridy=6;
		//Componente Boton Ingresar
		c = new GridBagConstraints();
		c.gridy=8;
		c.anchor =GridBagConstraints.CENTER;
		add(botonIngresar,c);
		

		
	}
	
	
	private void cargarImagen() {
		try {
            imagen = ImageIO.read(new File("./data/Imagenes/logo.png")); 	
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se encontro logo");
            
        }
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ingresar")) {
			String usuario = empresaAlquilerVehiculos.ejecutarPrograma(jTextFieldUsuario.getText(),jTextFieldContrasenia.getText());
			if(usuario.equals("No encontrado")&& getComponentCount()==7) {
				JLabel noEncontrado = new JLabel("Usuario O contraseña incorrecta");
				GridBagConstraints c = new GridBagConstraints();
				c.gridy=1;
				c.anchor = GridBagConstraints.WEST;
				add(noEncontrado,c);
				this.revalidate();
				}
			else if (usuario.equalsIgnoreCase("Administrador General")) {
					interfazPrincipal.dispose();
					interfazPrincipal.interfazAdminGeneral();
			}
			else if(usuario.equalsIgnoreCase("Administrador Local")) {
				interfazPrincipal.dispose();
				interfazPrincipal.interfazAdminLocal(jTextFieldUsuario.getText(),jTextFieldContrasenia.getText());
			}
			else if(usuario.equalsIgnoreCase("Cliente")) {
				interfazPrincipal.dispose();
				interfazPrincipal.interfazCliente(jTextFieldUsuario.getText(), jTextFieldContrasenia.getText());
			}else if(usuario.equalsIgnoreCase("Empleado")) {
				interfazPrincipal.dispose();
				interfazPrincipal.interfazEmpleado(jTextFieldUsuario.getText(),jTextFieldContrasenia.getText());
			}
		}
		
	}
	
	
}
