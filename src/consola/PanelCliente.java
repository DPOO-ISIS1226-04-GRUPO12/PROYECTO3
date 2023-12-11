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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class PanelCliente extends JPanel implements ActionListener{
	private Image imagen ;
	private InterfazPrincipal interfazPrincipal;
	private JButton botonReserva;
	private JButton botonAlquilar;
	private InterfazRegistrarReservaCliente interfazRegistrarReservaCliente;
	private InterfazRegistrarVehiculo interfazRegistrarVehiculo;
	private String usuarioA;
	private String contraseniaA;
	
	public PanelCliente(InterfazPrincipal interfazPrincipal, String nombre, String usuario, String fechaNac, String contrasenia) {
		this.interfazPrincipal = interfazPrincipal;
		this.usuarioA = usuario;
		this.contraseniaA = contrasenia;
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
				
		// JLabel "Cliente"
		c.gridx = 0;
		c.gridy = 5; 
		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setFont(new Font(labelCliente.getFont().getName(),Font.ITALIC,25));
		add(labelCliente, c);
		
		
		// Label Nombre y Nombre Cliente
		//Label
		JPanel PanelInfo = new JPanel();
		PanelInfo.setLayout(new GridLayout(3,2));
		JLabel labelNombre = new JLabel("Nombre");
		Font fontnombre = labelNombre.getFont();
		labelNombre.setFont(new Font(fontnombre.getName(), Font.PLAIN, 25));
		PanelInfo.add(labelNombre);
		//NombreCliente
		
		JLabel labelNombreValor = new JLabel(nombre);
		Font fontNombre = labelNombreValor.getFont();
		labelNombreValor.setFont(new Font(fontNombre.getName(), Font.ITALIC, 25));
		PanelInfo.add(labelNombreValor);

		// JLabel "Usuario"
		JLabel labelUsuario = new JLabel("Usuario:");
		Font fontu = labelUsuario.getFont();
		labelUsuario.setFont(new Font(fontu.getName(),Font.PLAIN,25));
		PanelInfo.add(labelUsuario);

		// JLabel con el nombre de usuario del administrador
		JLabel labelUsuarioValor = new JLabel(usuario);
		Font fontUsuario = labelUsuarioValor.getFont();
		labelUsuarioValor.setFont(new Font(fontUsuario.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelUsuarioValor);

		// JLabel "FechaNac"
		JLabel labelFecha = new JLabel("FechaNac: ");
		labelFecha.setFont(new Font(labelFecha.getFont().getName(),Font.PLAIN,25));
		PanelInfo.add(labelFecha);


		
		// JLabel con el nombre de la sede del administrador
		JLabel labelFechaNacValor = new JLabel(fechaNac);
		Font fontSede = labelFechaNacValor.getFont();
		labelFechaNacValor.setFont(new Font(fontSede.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelFechaNacValor);
		// JLabel "Nombre"
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(1, 10, 1, 1);
		add(PanelInfo,c);
		
		//Botones Inferiores
				JPanel panelInferior = new JPanel();
				panelInferior.setLayout(new GridLayout(1,2,50,0));
				botonReserva = new JButton("Reservar Vehículo");
				botonAlquilar = new JButton("Alquilar Vehículo");
			    // Agregar ActionListener a los botones
			    botonReserva.addActionListener(this);
			    botonAlquilar.addActionListener(this);
				panelInferior.add(botonReserva);
				panelInferior.add(botonAlquilar);
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy =6;
				c.insets = new Insets(20,1, 1, 1);
				c.gridwidth = 3;
				add(panelInferior,c);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==botonReserva) {
			 interfazRegistrarReservaCliente = new InterfazRegistrarReservaCliente(0,usuarioA,contraseniaA,interfazPrincipal);
			 interfazRegistrarReservaCliente.setVisible(true);
			
		}else if (e.getSource()==botonAlquilar) {
			interfazRegistrarReservaCliente = new InterfazRegistrarReservaCliente(1,usuarioA,contraseniaA,interfazPrincipal);
			interfazRegistrarReservaCliente.setVisible(true);
		}
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
	
}