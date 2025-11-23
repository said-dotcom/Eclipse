package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.MainMVC;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Emprunt {

	private JFrame frame;



	/**
	 * Create the application.
	 */
	public View_Emprunt() throws SQLException, ClassNotFoundException {
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
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("n°");
		lblNewLabel.setBounds(98, 49, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(172, 49, 107, 20);
		frame.getContentPane().add(editorPane);
		
		JPanel panel = new JPanel();
		
		panel.setBounds(108, 92, 266, 124);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN");
		panel.add(lblNewLabel_1);
		
		JEditorPane editorPane_1 = new JEditorPane();
		panel.add(editorPane_1);
		
		JButton btnNewButton_1 = new JButton("Emprunter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(324, 47, 57, 19);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Accueil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Accueil d = new View_Accueil();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(177, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
		//
		
	
	}
}
