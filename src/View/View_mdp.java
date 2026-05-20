package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class View_mdp {
    private JFrame frame;
    private JPasswordField txtMdp;

    public View_mdp() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("Accès Administrateur");
        frame.setBounds(100, 100, 350, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitre = new JLabel("ACCÈS ADMINISTRATEUR");
        lblTitre.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblTitre.setBounds(70, 20, 220, 30);
        frame.getContentPane().add(lblTitre);

        JLabel lblMdp = new JLabel("Mot de passe:");
        lblMdp.setBounds(50, 70, 100, 25);
        frame.getContentPane().add(lblMdp);

        txtMdp = new JPasswordField();
        txtMdp.setBounds(150, 70, 130, 25);
        frame.getContentPane().add(txtMdp);

        JButton btnConnexion = new JButton("Valider");
        btnConnexion.setBounds(120, 120, 100, 30);
        frame.getContentPane().add(btnConnexion);

        // Action MODIFIÉE
        btnConnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mdp = new String(txtMdp.getPassword()).trim();

                if (verifierDansBDD(mdp)) {
                    JOptionPane.showMessageDialog(frame, "Accès autorisé !");
                    frame.dispose();
                    new AjoutLivre();  // Ouvre AjoutLivre directement
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "Mot de passe incorrect !", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                    txtMdp.setText("");
                    txtMdp.requestFocus();
                }
            }
        });

        txtMdp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnConnexion.doClick();
            }
        });
    }

    // NOUVELLE MÉTHODE
    private boolean verifierDansBDD(String mdp) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apjava", "root", "");
            
            String sql = "SELECT COUNT(*) FROM adminmdp WHERE mot_de_passe = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mdp);
            
            ResultSet rs = stmt.executeQuery();
            rs.next();
            boolean valide = rs.getInt(1) > 0;
            
            rs.close();
            stmt.close();
            conn.close();
            
            return valide;
            
        } catch (SQLException e) {
            // Fallback si erreur BDD
            return mdp.equals("admin123");
        }
    }
}