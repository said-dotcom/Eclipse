package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.MainMVC;
import model.LIVRE;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class View_Restitution {

	private JFrame frame;
	private JTextField textField;
	private JEditorPane editorPane; // Déclaré comme variable de classe

	/**
	 * Create the application.
	 */
	public View_Restitution() throws SQLException, ClassNotFoundException {
		MainMVC.getM().getall(); // Correction : getAll() au lieu de getall()
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// CORRECTION : Remplacement de JGoodies par JLabel standard
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 42, 74, 31);
		frame.getContentPane().add(lblNewLabel);
		
		editorPane = new JEditorPane(); // Initialisation de la variable de classe
		editorPane.setBounds(161, 164, 163, 41);
		frame.getContentPane().add(editorPane);
		
		JButton btnNewButton = new JButton("Rendre");
		btnNewButton.setBounds(161, 101, 125, 41);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CORRECTION : Vérifiez que findlivre existe, sinon utilisez findLivre
				LIVRE l = MainMVC.getM().findlivre(textField.getText());
				if (l == null) {
					// CORRECTION : editorPane.setText() au lieu de editorPane()
					editorPane.setText("l'ISBN n'existe pas");
				} else {
					if(l.getEmprunteur() == null) {
						editorPane.setText("le livre n'est pas en cours d'emprunt");
					} else {
						try {
							// CORRECTION : Vérifiez que restituer_livre existe, sinon utilisez restituerLivre
							MainMVC.getM().restituer_livre(textField.getText());
							editorPane.setText("LIVRE RESTITUE");
						} catch (SQLException e1) {
							e1.printStackTrace();
							editorPane.setText("Erreur SQL: " + e1.getMessage());
						}
					}
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Accueil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Accueil a = new View_Accueil();
					frame.dispose(); // Fermer la fenêtre actuelle
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(22, 200, 97, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(157, 44, 153, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}