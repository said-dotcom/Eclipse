package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.MainMVC;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class View_Restitution {

	private static final String DefaultComponentFactory = null;
	private JFrame frame;
	private JTextField txtRestituer;

	/**
	 * Create the application.
	 */
	public View_Restitution() throws SQLException, ClassNotFoundException {
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
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("ISBN");
		lblNewJgoodiesLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewJgoodiesLabel.setBounds(59, 42, 74, 31);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(70, 33, 163, 41);
		frame.getContentPane().add(editorPane);
		
		JButton btnNewButton = new JButton("Rendre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(161, 101, 125, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Accueil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Accueil a = new View_Accueil();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setBounds(22, 200, 97, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		txtRestituer = new JTextField();
		txtRestituer.setText("Restituer");
		txtRestituer.setBounds(273, 173, 153, 31);
		frame.getContentPane().add(txtRestituer);
		txtRestituer.setColumns(10);
	}
}
