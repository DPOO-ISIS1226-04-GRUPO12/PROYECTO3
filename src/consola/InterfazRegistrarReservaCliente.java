package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//
import java.awt.Component;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarReservaCliente extends JFrame implements ActionListener{
	private JButton botonRegistrar ;
	private PanelAuxLista panelLista;
	public static LocalDate fechaActual;
	public ArrayList<JTextField> listaJTextFields ;
	public ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private String usuarioA;
	private String contraseniaA;
	private InterfazPrincipal interfazPrincipal;
	private int tipo;
	
	public InterfazRegistrarReservaCliente(int tipo,String usuarioA, String contraseniaA,InterfazPrincipal interfazPrincipal) {
		//setSize( 750, 600 );
		this.usuarioA = usuarioA;
		this.contraseniaA = contraseniaA;
		this.interfazPrincipal = interfazPrincipal;
		this.tipo = tipo;
		fechaActual = LocalDate.now();
		setLayout(new BorderLayout());
		setBackground(new Color(79,193,223));
		
		//Titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.add(new JLabel("Ingrese la informacion de la reserva:"));
		panelTitulo.setBackground(new Color(79,193,223));
		panelTitulo.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		//Panel Centro
		panelLista = new PanelAuxLista(tipo,this);
		LineBorder bordeCenter = new LineBorder(new Color(188, 192, 193));
        panelLista.setBorder(bordeCenter);
		add(panelLista, BorderLayout.CENTER);
		
	    //Boton
        botonRegistrar = new JButton("Reservar");
        botonRegistrar.setBackground(new Color(188, 192, 193));
        Dimension botonDimension = new Dimension(100, 40);
        botonRegistrar.setPreferredSize(botonDimension);
        botonRegistrar.addActionListener(this);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(79, 193, 223));
        panelBoton.add(botonRegistrar);
        panelBoton.setBorder(new EmptyBorder(0, 0, 0, 0));
        add(panelBoton, BorderLayout.SOUTH);
				
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        setSize(710, 270);
        setResizable(false);
	}
		 
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== botonRegistrar) {
			String texto="";
			String categoria="";
			//int id=0;
			String sedeR="";
			String fechaI="";
			String fechaF="";
			String sedeD="";
			for (int i = 0; i < listaJTextFields.size(); i++) {
				
				switch (i) {
				
				case 0: 
					//Id
					try {
						categoria = listaJTextFields.get(i).getText();
						break;
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(this, "No se pudo registrar la reserva", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					
					
				case 1:
					sedeR = listaJTextFields.get(i).getText();
					break;
				case 2:
					fechaI =listaJTextFields.get(i).getText();
					break;
				case 3:
					fechaF = listaJTextFields.get(i).getText();
					break;
				case 4:
					sedeD = listaJTextFields.get(i).getText();
					break;
				
				default:
					JOptionPane.showMessageDialog(null, "No se pudo registrar la reserva", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			interfazPrincipal.realizarReservaCliente(categoria, sedeR, fechaI, fechaF, sedeD,usuarioA,contraseniaA,tipo,"Cliente");
			JOptionPane.showMessageDialog(this, "Vehiculo registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	
	}

}