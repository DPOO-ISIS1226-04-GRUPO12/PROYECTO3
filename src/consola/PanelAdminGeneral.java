package consola;

import java.awt.BorderLayout;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.Vehiculo;


public class PanelAdminGeneral extends JPanel implements ActionListener {
	private Image imagen;
	private ArrayList<JLabel> listaLabels = new ArrayList<>();
	private ArrayList<JTextField> listaJTextFields = new ArrayList<JTextField>();
	private JButton noAlquiladoButton ;
	private JTextField BuscarIdAuto ;
	private JButton consultarIdAuto;
	private JButton registrarVehiculo;
	private JButton eliminarVehiculo;
	private JTextField textfieldsedes ;
	private JButton consultarIDSedes;
	private InterfazPrincipal interfazPrincipal;
	private JLabel jlabelImagen ;
	private JTextField idAutoEliminar;
	private JFrame frameVentanaEliminar;
	private Calendario calendario;
	
	public PanelAdminGeneral(InterfazPrincipal interfazPrincipal) {
		this.interfazPrincipal = interfazPrincipal;
		
		
		agregarLabel("Id");
        agregarLabel("Categoria");
        agregarLabel("Placa");
        agregarLabel("Transmision");
        agregarLabel("Sede");
        agregarLabel("Modelo");
        agregarLabel("Color");
        agregarLabel("Capacidad");
        agregarLabel("Tipo de Vehiculo");
        agregarLabel("Porcentaje de Seguro");
        
		cargarImagen();
		
		
		setLayout(new GridBagLayout());
		//Componente Imagen
		GridBagConstraints	c = new GridBagConstraints();
		
	
		c.gridwidth=5;
		c.gridheight=1;
		c.gridx=2;
		c.gridy =0;
		c.weightx=1;
		c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
		jlabelImagen = new JLabel();
		jlabelImagen.setIcon(new ImageIcon(imagen));
		
		add(jlabelImagen,c);
		// Componentes de Jlabel y jtextfields
				for ( int i=0;i<listaLabels.size();i++) {
					if(i<4) {
						c = new GridBagConstraints();
						JTextField textField = new JTextField();
						 listaJTextFields.add(textField);
						 textField.setPreferredSize(new Dimension(250, 30));
						 textField.setEditable(false);
						 c.anchor = GridBagConstraints.WEST;
						c.gridy=i+5;
						c.gridx=1;
						c.weightx=1;
						c.weighty=1;
						c.fill = GridBagConstraints.BOTH;
						add(listaLabels.get(i),c);
						c.gridx=2;
						add(textField,c);
						
						
						
					}else {
						c = new GridBagConstraints();
						JTextField textField = new JTextField();
						listaJTextFields.add(textField);
						textField.setPreferredSize(new Dimension(250, 30));
						textField.setEditable(false);
						c.anchor = GridBagConstraints.WEST;
						c.gridx=4;
						c.gridy=i+1;
						c.weightx=1;
						c.weighty=1;
						c.fill = GridBagConstraints.BOTH;
						add(textField,c);
						c.gridx=3;
						listaLabels.get(i).setSize(1,1);
						add(listaLabels.get(i),c);
					}
					
				}
				
				// Jbutton no alquilado
						 c = new GridBagConstraints();
						noAlquiladoButton = new JButton("No alquilado");
						c = new GridBagConstraints();
						noAlquiladoButton.setEnabled(false);
						c.anchor = GridBagConstraints.NORTH;
						noAlquiladoButton.addActionListener(this);
						add(noAlquiladoButton,c);
				
				// Jseparator
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					 
					c = new GridBagConstraints();
					
					c.fill = GridBagConstraints.VERTICAL;
					c.gridx=20;
			
					c.gridheight=5;
					c.gridwidth=1;
					c.weightx=1;
					c.weighty=4;
					c.fill = GridBagConstraints.VERTICAL;
					add(separator,c);
					
				//Jpanel ladoizquierdo
					JPanel ladoIzquierdo = new JPanel();
					GridLayout gridLayout=new GridLayout(9,1);
					 gridLayout.setVgap(10); 
					ladoIzquierdo.setLayout( gridLayout);
					JLabel IngreseId = new JLabel("Ingrese ID Automovil");
					BuscarIdAuto = new JTextField();
					consultarIdAuto = new JButton("Consultar");
					consultarIdAuto.addActionListener(this);
					consultarIDSedes = new JButton("Generar Calendario");
					consultarIDSedes.addActionListener(this);
					registrarVehiculo = new JButton("Registrar vehiculo al inventario");
					registrarVehiculo.addActionListener(this);
					eliminarVehiculo = new JButton("Eliminar vehiculo del inventario"); 
					eliminarVehiculo.addActionListener(this);
					textfieldsedes = new JTextField();					
					ladoIzquierdo.add(IngreseId);
					
					ladoIzquierdo.add(BuscarIdAuto);
					ladoIzquierdo.add(consultarIdAuto);

					ladoIzquierdo.add(registrarVehiculo); ladoIzquierdo.add(eliminarVehiculo);
					ladoIzquierdo.add(consultarIDSedes);
					add(ladoIzquierdo);
					
			
	}
	
	
	private void cargarImagen() {
		try {
            imagen = ImageIO.read(new File("./data/Imagenes/Carro.png")); 	
           
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se encontro logo");
            
        }
	}
	
	
	private void agregarLabel(String nombre) {
		JLabel label = new JLabel(nombre);
        listaLabels.add(label);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==consultarIdAuto) {
			String ID = BuscarIdAuto.getText();
			interfazPrincipal.buscarAutoporId(Integer.parseInt(ID));
			
		}else if (e.getSource()==registrarVehiculo) {
			interfazPrincipal.interfazRegistrarVehiculoAdmin();
		}
		else if (e.getSource()==eliminarVehiculo) {
			frameVentanaEliminar= new JFrame("Ventana con Componentes");
			frameVentanaEliminar.setLayout(new GridLayout(3, 1));
			JLabel label = new JLabel("Ingrese el Id del Vehiculo:");
            label.setHorizontalAlignment(JLabel.CENTER);
            frameVentanaEliminar.add(label);
            idAutoEliminar = new JTextField();
            frameVentanaEliminar.add(idAutoEliminar);
            JButton boton = new JButton("Eliminar");
            frameVentanaEliminar.add(boton);
            boton.addActionListener(this);
            boton.setActionCommand("BotonEliminar");
            frameVentanaEliminar.setVisible(true);
            frameVentanaEliminar.setLocationRelativeTo(this);
            frameVentanaEliminar.setSize(300,150);
		}else if (e.getActionCommand().equals("BotonEliminar")) {
			int ID = Integer.parseInt(idAutoEliminar.getText());
			interfazPrincipal.eliminarVehiculoInventario(ID);
			JOptionPane.showMessageDialog(this, "Vehiculo ELIMINADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			frameVentanaEliminar.dispose();
			
			
		}
		else if (e.getSource()==consultarIDSedes) {
			String idSede = textfieldsedes.getText();
			Map<Date, Integer> map = interfazPrincipal.SacarFechasDeSede(idSede);
            calendario = new Calendario(map);
            calendario.setVisible(true);
		}
		
	}
	
	
	public void actualizarAuto(Vehiculo vehiculo) {
		
		if(vehiculo.getAlquilado()) {
			noAlquiladoButton.setText("Vehiculo Alquilado");

		}else {
			noAlquiladoButton.setText("Vehiculo no alquilado");

		}
		
		try {
			 URL urlImagen= new URL(vehiculo.getRutaImagen());
			 Image imagenUrl = ImageIO.read(urlImagen).getScaledInstance(700, 400, Image.SCALE_SMOOTH);
			 jlabelImagen.setIcon(new ImageIcon(imagenUrl));
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		for (int i = 0; i < listaJTextFields.size(); i++) {
			JTextField field;
			switch (i) {
			
			case 0: 
				field=listaJTextFields.get(i);
				field.setText(String.valueOf(vehiculo.getIdVehiculo()));
				break;
			
			case 1:
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getCategoria().getNombreCategoria());
				break;
			
			case 2:
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getPlaca());
				break;
			
			case 3:
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getTipoTransmision());
				break;
			
			case 4 :
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getSede());
				break;
			case 5 :
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getModelo());
				break;
			case 6 :
				field=listaJTextFields.get(i);
				field.setText(vehiculo.getColor());
				break;
			case 7 : 
				field=listaJTextFields.get(i);
				field.setText(String.valueOf(vehiculo.getCapacidad()));
				break;
			case 8 : 
				field=listaJTextFields.get(i);
				field.setText(String.valueOf(vehiculo.getTipoVehiculo()));
				break;
			case 9 : 
				field=listaJTextFields.get(i);
				field.setText(String.valueOf(vehiculo.getPorAdicional()));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + i);
			}
		}
		
	}
	
}
