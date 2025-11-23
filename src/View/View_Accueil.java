package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.MainMVC;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;

public class View_Accueil {

	private JFrame frame;
	protected View_Catalogue View_Accueil;



	/**
	 * Create the application.
	 */
	public View_Accueil() throws SQLException, ClassNotFoundException{
		MainMVC.getM().getall();
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
		
		JButton btnNewButton = new JButton("Catalogue");
		btnNewButton.setBounds(112, 74, 184, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Accueil = new View_Catalogue() ;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		//**************************************************************************
		JButton btnCompte = new JButton("Compte");
		
		//pour que le boutton va vers la page du compte 
		
		btnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// la variable qui permet de l'exécuté 
					View_Compte a = new View_Compte();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCompte.setBounds(112, 127, 184, 23);
		frame.getContentPane().add(btnCompte);
		//**************************************************************************
		JButton btnEmprunt = new JButton("Emprunt");
		btnEmprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Emprunt b = new View_Emprunt();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEmprunt.setBounds(24, 177, 184, 23);
		frame.getContentPane().add(btnEmprunt);
		
		//**************************************************************************
		JButton btnRestutiait = new JButton("Restituer");
		btnRestutiait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Restitution c = new View_Restitution();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRestutiait.setBounds(242, 177, 184, 23);
		frame.getContentPane().add(btnRestutiait);
		
		JLabel lblNewLabel = new JLabel("ACCUEIL");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(169, 32, 86, 31);
		frame.getContentPane().add(lblNewLabel);
	}
}
