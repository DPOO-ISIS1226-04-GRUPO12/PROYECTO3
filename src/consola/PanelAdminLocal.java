package consola;

import java.awt.Color;
import java.awt.Component;
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
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logica.AdministradorLocal;

public class PanelAdminLocal  extends JPanel implements ActionListener{
	private Image imagen ;
	private InterfazPrincipal interfazPrincipal;
	private JButton botonRegistrarEmpleado;
	private JButton BtonRegistrarCliente;
	private JButton btonEliminar;
	private JComboBox<String> usuariosBox ;
	private InterfazRegistrarClienteAdminLocal InterfazRegistrarClienteAdminLocal;
	private InterfazRegistrarEmpleoAdminLocal InterfazRegistrarEmpleoAdminLocal;
	
	
	public PanelAdminLocal(InterfazPrincipal interfazPrincipal,AdministradorLocal administradorLocal) {
		
		this.interfazPrincipal = interfazPrincipal;
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

		// JLabel "Administrador Local"
		c.gridx = 0;
		c.gridy = 5; 
		JLabel labelAdminLocal = new JLabel("Administrador Local");
		labelAdminLocal.setFont(new Font(labelAdminLocal.getFont().getName(),Font.ITALIC,25));
		add(labelAdminLocal, c);
		
		JPanel PanelInfo = new JPanel();
		PanelInfo.setLayout(new GridLayout(3,2));
		JLabel labelNombre = new JLabel("Nombre:");
		Font fontnombre = labelNombre.getFont();
		labelNombre.setFont(new Font(fontnombre.getName(), Font.PLAIN, 25));
		PanelInfo.add(labelNombre);

		// JLabel con el nombre del administrador
		JLabel labelNombreValor = new JLabel(administradorLocal.getNombre());
		Font fontNombre = labelNombreValor.getFont();
		labelNombreValor.setFont(new Font(fontNombre.getName(), Font.ITALIC, 25));
		PanelInfo.add(labelNombreValor);

		// JLabel "Usuario"
		JLabel labelUsuario = new JLabel("Usuario:");
		Font fontu = labelUsuario.getFont();
		labelUsuario.setFont(new Font(fontu.getName(),Font.PLAIN,25));
		PanelInfo.add(labelUsuario);

		// JLabel con el nombre de usuario del administrador
		JLabel labelUsuarioValor = new JLabel(administradorLocal.getUsuario());
		Font fontUsuario = labelUsuarioValor.getFont();
		labelUsuarioValor.setFont(new Font(fontUsuario.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelUsuarioValor);

		// JLabel "Sede"
		JLabel labelSede = new JLabel("Sede:");
		labelSede.setFont(new Font(labelSede.getFont().getName(),Font.PLAIN,25));
		PanelInfo.add(labelSede);

		// JLabel con el nombre de la sede del administrador
		JLabel labelSedeValor = new JLabel(administradorLocal.getSede());
		Font fontSede = labelSedeValor.getFont();
		labelSedeValor.setFont(new Font(fontSede.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelSedeValor);
		// JLabel "Nombre"
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(1, 10, 1, 1);
		add(PanelInfo,c);
		
		//Botones Inferiores
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(1,2,50,0));
		botonRegistrarEmpleado = new JButton("Registrar Empleado");
		BtonRegistrarCliente = new JButton("Registrar Cliente");
		panelInferior.add(botonRegistrarEmpleado);
		panelInferior.add(BtonRegistrarCliente);
		BtonRegistrarCliente.addActionListener(this);
		botonRegistrarEmpleado.addActionListener(this);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy =6;
		c.insets = new Insets(20,1, 1, 1);
		c.gridwidth = 3;
		add(panelInferior,c);
		//Panel eliminar Usuario
		JPanel panelEliminarU = new JPanel();
		panelEliminarU.setLayout(new GridLayout(1,2,10,0));
		 String[] listaUsuarios = interfazPrincipal.listaUsuariosSistema().toArray(new String[0]);
		usuariosBox = new JComboBox(listaUsuarios);
		
		btonEliminar = new JButton("Eliminar");
		btonEliminar.setBackground(Color.red);
		panelEliminarU.add(usuariosBox);
		panelEliminarU.add(btonEliminar);
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=7;
		c.insets= new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		add(panelEliminarU,c);
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
				 textField.setPreferredSize(new Dimension(250, 30));
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
				textField.setPreferredSize(new Dimension(250, 30));
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
	
	
	public void RegistrarClienteNuevo(String nombre, String nacionalidad, String telefono, String fechaNac, String paisExp, String usuario, String contraseña, int nLicencia, String fechaVencLicen, int nTarjeta, int contraTarjeta) throws ParseException {
		
		interfazPrincipal.registrarClienteNeuvo(nombre,nacionalidad,telefono,fechaNac,paisExp,usuario, contraseña, nLicencia,fechaVencLicen,nTarjeta,contraTarjeta);
		
	}
	
	
	public int RegistrarEmpleadoNuevo(String nombre, String sede, String usuario, String contraseña) {
		// TODO Auto-generated method stub
		int comple = interfazPrincipal.registraEmpleadoNuevo(nombre,sede,usuario,contraseña);
		return comple;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BtonRegistrarCliente) {
			InterfazRegistrarClienteAdminLocal = new InterfazRegistrarClienteAdminLocal(this);
			InterfazRegistrarClienteAdminLocal.setVisible(true);
		}else if(e.getSource()== botonRegistrarEmpleado) {
			InterfazRegistrarEmpleoAdminLocal = new InterfazRegistrarEmpleoAdminLocal(this);
			InterfazRegistrarEmpleoAdminLocal.setVisible(true);
		}
		
	}

	

	
	
}
