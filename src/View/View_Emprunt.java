package View;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import Controller.MainMVC;
import model.ADHERENT;
import model.LIVRE;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Emprunt {

    private JFrame frame;
    private JEditorPane editorPane; // Pour le numéro adhérent
    private JEditorPane editorPane_1; // Pour l'ISBN

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
        
        JLabel lblNewLabel = new JLabel("n° Adhérent");
        lblNewLabel.setBounds(98, 49, 64, 14);
        frame.getContentPane().add(lblNewLabel);
        
        editorPane = new JEditorPane();
        editorPane.setBounds(172, 49, 107, 20);
        frame.getContentPane().add(editorPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(108, 92, 266, 124);
        frame.getContentPane().add(panel);
        
        JLabel lblNewLabel_1 = new JLabel("ISBN");
        panel.add(lblNewLabel_1);
        
        editorPane_1 = new JEditorPane();
        panel.add(editorPane_1);
        
        JButton btnNewButton_1 = new JButton("Emprunter");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numAdherent = editorPane.getText().trim(); // numéro adhérent
                String isbn = editorPane_1.getText().trim(); // ISBN du livre
                
                // Logique pour emprunter le livre
                // 1. Vérifier si l'adhérent existe
                ADHERENT adherent = MainMVC.getM().findadherentbynum(numAdherent);
                if (adherent == null) {
                    System.out.println("Adhérent non trouvé: " + numAdherent);
                    return;
                }
                
                // 2. Vérifier si le livre existe
                LIVRE livre = MainMVC.getM().findlivre(isbn);
                if (livre == null) {
                    System.out.println("Livre non trouvé: " + isbn);
                    return;
                }
                
                // 3. Vérifier si le livre n'est pas déjà emprunté
                if (livre.getEmprunteur() != null) {
                    System.out.println("Livre déjà emprunté par: " + livre.getEmprunteur().getNom());
                    return;
                }
                
                // 4. Mettre à jour la BDD
                try {
                    // TODO: Ajouter la méthode emprunter_livre dans model.java
                    // MainMVC.getM().emprunter_livre(isbn, numAdherent);
                    System.out.println("Emprunt réussi! " + adherent.getNom() + " a emprunté " + livre.getTitre());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(btnNewButton_1);
        
        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Vous pouvez garder cette logique ou la supprimer
            }
        });
        btnNewButton.setBounds(324, 47, 57, 19);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_2 = new JButton("Accueil");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    View_Accueil d = new View_Accueil();
                    frame.dispose();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_2.setBounds(177, 227, 89, 23);
        frame.getContentPane().add(btnNewButton_2);
    }
}