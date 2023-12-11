package consola;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PanelAuxLista extends JPanel implements ActionListener {
	//private ArrayList<JTextField> listaJTextFields ;
	//private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	
	public PanelAuxLista(int tipo,InterfazRegistrarReservaCliente iRegistroReservaCliente) {
	
	//setUndecorated(true); 
	JPanel panelInterfaz = new JPanel();
	panelInterfaz.setLayout(new GridBagLayout());
	agregarLabel("Categoría", iRegistroReservaCliente.listaLabels);
	agregarLabel("Sede a Recoger", iRegistroReservaCliente.listaLabels);
	if (tipo==0) {
	agregarLabel("Fecha Inicial", iRegistroReservaCliente.listaLabels);}
	agregarLabel("Fecha Final", iRegistroReservaCliente.listaLabels);
	agregarLabel("Sede a devolver", iRegistroReservaCliente.listaLabels);

	//Labels
	if (tipo==0) {
		iRegistroReservaCliente.listaJTextFields = crearJtextFieldsParaLabels(iRegistroReservaCliente.listaLabels, panelInterfaz,3,2);}
	else {
		iRegistroReservaCliente.listaJTextFields = crearJtextFieldsParaLabels(iRegistroReservaCliente.listaLabels, panelInterfaz,2,2);
	}
	
	setBackground(new Color(79,193,223));
	panelInterfaz.setBackground(new Color(79,193,223));
	add(panelInterfaz);
	setSize(700,700);
	//setResizable(false);
	
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
