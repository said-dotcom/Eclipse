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

        // Champ de saisie du numéro
        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(159, 26, 107, 20);
        frame.getContentPane().add(editorPane);

        // Bouton OK
        JButton btnOk = new JButton("OK");
        btnOk.setBounds(320, 22, 89, 23);
        frame.getContentPane().add(btnOk);

        // Labels
        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setBounds(63, 89, 49, 23);
        frame.getContentPane().add(lblPrenom);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(63, 134, 49, 23);
        frame.getContentPane().add(lblNom);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(63, 176, 49, 23);
        frame.getContentPane().add(lblEmail);

        // Panneaux blancs pour afficher les infos
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

        // Labels internes pour afficher les infos utilisateur - declaration de la variables
        JLabel lblPrenomValue = new JLabel("");
        JLabel lblNomValue = new JLabel("");
        JLabel lblEmailValue = new JLabel("");
        
        //Pour ajouter les valeur dans les panels
        panelPrenom.add(lblPrenomValue);
        panelNom.add(lblNomValue);
        panelEmail.add(lblEmailValue);

        // Bouton Accueil
        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    View_Accueil h = new View_Accueil();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAccueil.setBounds(177, 216, 89, 23);
        frame.getContentPane().add(btnAccueil);

        // --- ACTION DU BOUTON OK ---
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numero = editorPane.getText().trim();

                // ⚙️ Exemple temporaire : données simulées (tu remplaceras avec ta BDD) On ajoute manuellement
                if (numero.equals("1")) {
                    lblPrenomValue.setText("Jean");
                    lblNomValue.setText("Dupont");
                    lblEmailValue.setText("jean.dupont@email.com");
                } else if (numero.equals("2")) {
                    lblPrenomValue.setText("Marie");
                    lblNomValue.setText("Durand");
                    lblEmailValue.setText("marie.durand@email.com");
                } else if (numero.equals("3")) {
                    lblPrenomValue.setText("Paul");
                    lblNomValue.setText("Nguyen");
                    lblEmailValue.setText("paul.nguyen@example.com");
                }
                else {
                	lblPrenomValue.setText("Inconnu");
                    lblNomValue.setText("Inconnu");
                    lblEmailValue.setText("Inconnu");
                }
                
                // lier avec la base de donnees

                // 🧩 Si tu veux lier avec ton modèle MVC :
                // Utilisateur u = mainMVC.getM().getByNum(numero);
                // if (u != null) {
                //     lblPrenomValue.setText(u.getPrenom());
                //     lblNomValue.setText(u.getNom());
                //     lblEmailValue.setText(u.getEmail());
                // } else {
                //     lblPrenomValue.setText("Inconnu");
                //     lblNomValue.setText("Inconnu");
                //     lblEmailValue.setText("Inconnu");
                // }
            }
        });
    }
}
