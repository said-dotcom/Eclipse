package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AjoutLivre extends JFrame {
    private JTextField txtIsbn;
    private JTextField txtTitre;

    /**
     * Create the application.
     */
    public AjoutLivre() {
        initialize();
        setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Ajouter Livre - Admin");
        setBounds(100, 100, 450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblTitre = new JLabel("AJOUTER UN LIVRE");
        lblTitre.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblTitre.setBounds(140, 20, 180, 30);
        getContentPane().add(lblTitre);
        
        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setBounds(80, 80, 60, 20);
        getContentPane().add(lblIsbn);
        
        txtIsbn = new JTextField();
        txtIsbn.setBounds(150, 80, 180, 25);
        getContentPane().add(txtIsbn);
        txtIsbn.setColumns(10);
        
        JLabel lblTitreLivre = new JLabel("Titre:");
        lblTitreLivre.setBounds(80, 120, 60, 20);
        getContentPane().add(lblTitreLivre);
        
        txtTitre = new JTextField();
        txtTitre.setBounds(150, 120, 180, 25);
        getContentPane().add(txtTitre);
        txtTitre.setColumns(10);
        
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterLivreDansBDD();
            }
        });
        btnAjouter.setBounds(170, 180, 100, 30);
        getContentPane().add(btnAjouter);
        
        // Label pour debug
        JLabel lblDebug = new JLabel("État: Prêt");
        lblDebug.setBounds(150, 220, 200, 20);
        getContentPane().add(lblDebug);
    }
    
    private void ajouterLivreDansBDD() {
        String isbn = txtIsbn.getText().trim();
        String titre = txtTitre.getText().trim();
        
        // Validation
        if (isbn.isEmpty() || titre.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez remplir l'ISBN et le titre !", 
                "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Connection conn = null;
        try {
            // 1. Connexion à la BDD
            System.out.println("=== TENTATIVE AJOUT LIVRE ===");
            System.out.println("ISBN: " + isbn + ", Titre: " + titre);
            
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apjava", "root", "");
            System.out.println("✅ Connexion BDD OK");
            
            // 2. Vérifier si ISBN existe déjà
            String checkSQL = "SELECT COUNT(*) FROM livre WHERE ISBN = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
            checkStmt.setString(1, isbn);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int existe = rs.getInt(1);
            
            if (existe > 0) {
                JOptionPane.showMessageDialog(this, 
                    "❌ Cet ISBN existe déjà dans la base !", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 3. Insérer le nouveau livre
            // IMPORTANT: Utilisez les mêmes noms de colonnes que dans votre BDD
            String insertSQL = "INSERT INTO livre (ISBN, TITRE, PRIX, AUTEUR, ADHERENT) VALUES (?, ?, ?, ?, ?)";
            System.out.println("SQL d'insertion: " + insertSQL);
            
            PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
            insertStmt.setString(1, isbn);          // ISBN
            insertStmt.setString(2, titre);         // TITRE
            insertStmt.setFloat(3, 0.0f);           // PRIX (0 par défaut)
            insertStmt.setNull(4, Types.VARCHAR);   // AUTEUR (NULL)
            insertStmt.setString(5, "");            // ADHERENT (chaîne vide, pas NULL)
            
            int rows = insertStmt.executeUpdate();
            System.out.println("✅ Lignes insérées: " + rows);
            
            if (rows > 0) {
                // SUCCÈS
                System.out.println("✅ LIVRE AJOUTÉ AVEC SUCCÈS !");
                
                // Recharger les données dans le modèle MVC
                try {
                    Controller.MainMVC.getM().getall();
                    System.out.println("✅ Données rechargées dans MainMVC");
                } catch (Exception ex) {
                    System.out.println("⚠️ Erreur rechargement: " + ex.getMessage());
                }
                
                // Message à l'utilisateur
                JOptionPane.showMessageDialog(this, 
                    "✅ Livre ajouté avec succès !\n\n" +
                    "ISBN: " + isbn + "\n" +
                    "Titre: " + titre + "\n\n" +
                    "Vérifiez dans le catalogue ou dans phpMyAdmin.",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                
                // Vider les champs
                txtIsbn.setText("");
                txtTitre.setText("");
                txtIsbn.requestFocus(); // Retour au champ ISBN
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Aucune ligne insérée !", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            
            // Fermer les ressources
            insertStmt.close();
            checkStmt.close();
            
        } catch (SQLException ex) {
            // ERREUR DÉTAILLÉE
            System.out.println("❌ ERREUR SQL:");
            System.out.println("  Message: " + ex.getMessage());
            System.out.println("  Code erreur: " + ex.getErrorCode());
            System.out.println("  État SQL: " + ex.getSQLState());
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(this, 
                "❌ Erreur base de données:\n" + ex.getMessage() + 
                "\n\nVérifiez:\n1. La table 'livre' existe\n2. Les colonnes sont correctes\n3. Connexion MySQL active",
                "Erreur SQL", JOptionPane.ERROR_MESSAGE);
                
        } finally {
            // Fermer la connexion
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("=== FIN TENTATIVE AJOUT ===");
    }
}