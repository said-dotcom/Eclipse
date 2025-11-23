package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.MainMVC;
import model.LIVRE;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;

public class View_Catalogue {

	private JFrame frame;
	private View_Accueil View_Catalogue;



	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Catalogue() throws SQLException, ClassNotFoundException {
		MainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
		
		//System.out.println(mainMVC.getM().getListLivre().get(0).getEmprunteur().getNom());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de livre :");
		lblNewLabel.setBounds(10, 48, 133, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(177, 48, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		//On prend la listes des livres pour comter combien on a et afficher le nombre
		lblNewLabel_1.setText(String.valueOf(MainMVC.getM().getListLivre().size()));
		
		//Afficheer les noms des livres 
		List list = new List();
		list.setBounds(36, 68, 356, 155);
		frame.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("Accueil");
		btnNewButton.setBounds(157, 229, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				View_Catalogue = new View_Accueil() ;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Catalogue");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(153, 11, 93, 41);
		frame.getContentPane().add(lblNewLabel_2);
		for(LIVRE l : MainMVC.getM().getListLivre())
			
		{
			list.add(l.getISBN()+" _ "+l.getTitre());
		}
	}
}
