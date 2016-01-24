import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class VentanaIdioma extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Container contenedor;
	private JPanel panel;
	private JLabel etiqueta;
	private JComboBox<String> comboIdioma;
	private JButton botonGuardar;

	public VentanaIdioma() {
		super ("Ejemplo de archivo de texto");
		contenedor = getContentPane();

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(120, -1));
		botonGuardar = new JButton ("Guardar");
		botonGuardar.setPreferredSize(new Dimension(100, 30));
		botonGuardar.addActionListener(this);
		panel.add(botonGuardar);
		contenedor.add(panel, BorderLayout.EAST);

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		etiqueta = new JLabel ("Idioma: ");
		panel.add(etiqueta);
		comboIdioma = new JComboBox<String>();
		comboIdioma.setPreferredSize(new Dimension(100, 30));
		comboIdioma.setEditable(true);
		comboIdioma.setMaximumRowCount(3);
		panel.add(comboIdioma);
		contenedor.add(panel, BorderLayout.CENTER);

		setSize(400, 130);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar();
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaIdioma();     
	}

	public void actionPerformed(ActionEvent evento) {  
		if (evento.getSource().equals(botonGuardar)) {
			guardar();
		}
	}
	
	private void inicializar() { //implementación de práctica
		comboIdioma.removeAllItems();
		try(FileReader file= new FileReader("idiomas.txt");
			BufferedReader bfr= new BufferedReader(file)
		){
			String line= null;
			while((line= bfr.readLine()) != null) {
				comboIdioma.addItem(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void guardar() { //implementación de práctica
		String selectedItemCombo= comboIdioma.getSelectedItem().toString();
		if(!existBuscar(selectedItemCombo)){
			guardarDato(selectedItemCombo);
			inicializar(); //logra un rendimiento reducido. 
			//Sería mejor insertar el item en el combo, 
			//esto inmediatamente después de guardar en el archivo
		}
	}
	
	private boolean existBuscar(String object){ //implementación de práctica
		for(int i=0; i<comboIdioma.getItemCount(); i++){
			if(comboIdioma.getItemAt(i).equalsIgnoreCase(object)){
				return true;
			}
		}
		return false;
	}
	
	private void guardarDato(String nuevo){ //implementación de práctica
		try(FileWriter file= new FileWriter("idiomas.txt", true);
			BufferedWriter bfr= new BufferedWriter(file)
		){
			bfr.write(nuevo);
			bfr.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
