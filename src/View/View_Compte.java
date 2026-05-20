package View;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.MainMVC;
import model.ADHERENT;

public class View_Compte {

    private JFrame frame;

    public View_Compte() throws SQLException, ClassNotFoundException {
        MainMVC.getM().getall();
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNum = new JLabel("n°");
        lblNum.setBounds(74, 26, 49, 14);
        frame.getContentPane().add(lblNum);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(159, 26, 107, 20);
        frame.getContentPane().add(editorPane);

        JButton btnOk = new JButton("OK");
        btnOk.setBounds(320, 22, 89, 23);
        frame.getContentPane().add(btnOk);

        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(63, 89, 49, 23);
        frame.getContentPane().add(lblPrenom);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(63, 134, 49, 23);
        frame.getContentPane().add(lblNom);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(63, 176, 49, 23);
        frame.getContentPane().add(lblEmail);

        Panel panelPrenom = new Panel();
        panelPrenom.setBackground(Color.WHITE);
        panelPrenom.setBounds(140, 89, 147, 23);
        frame.getContentPane().add(panelPrenom);

        Panel panelNom = new Panel();
        panelNom.setBackground(Color.WHITE);
        panelNom.setBounds(140, 134, 147, 23);
        frame.getContentPane().add(panelNom);

        Panel panelEmail = new Panel();
        panelEmail.setBackground(Color.WHITE);
        panelEmail.setBounds(140, 176, 226, 23);
        frame.getContentPane().add(panelEmail);

        JLabel lblPrenomValue = new JLabel("");
        JLabel lblNomValue = new JLabel("");
        JLabel lblEmailValue = new JLabel("");
        
        panelPrenom.add(lblPrenomValue);
        panelNom.add(lblNomValue);
        panelEmail.add(lblEmailValue);

        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    View_Accueil h = new View_Accueil();
                    frame.dispose();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAccueil.setBounds(177, 216, 89, 23);
        frame.getContentPane().add(btnAccueil);

        // ACTION DU BOUTON OK (CORRIGÉ)
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numero = editorPane.getText().trim();
                
                // UTILISATION DE VOTRE MODÈLE
                ADHERENT adherent = MainMVC.getM().findadherentbynum(numero);
                
                if (adherent != null) {
                    lblPrenomValue.setText(adherent.getPrenom());
                    lblNomValue.setText(adherent.getNom());
                    lblEmailValue.setText(adherent.getEmail());
                } else {
                    lblPrenomValue.setText("Inconnu");
                    lblNomValue.setText("Inconnu");
                    lblEmailValue.setText("Inconnu");
                }
            }
        });
    }
}