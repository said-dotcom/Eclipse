package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class model {
    private ArrayList<LIVRE>ListLivre;
    private ArrayList<ADHERENT>ListADHERENT;
    private ArrayList<AUTEUR>ListAUTEUR;
    private Connection conn;
    
    public ArrayList<LIVRE> getListLivre() {
        return ListLivre;
    }
    public void setListLivre(ArrayList<LIVRE> listLivre) {
        ListLivre = listLivre;
    }
    public ArrayList<ADHERENT> getListADHERENT() {
        return ListADHERENT;
    }
    public void setListADHERENT(ArrayList<ADHERENT> listADHERENT) {
        ListADHERENT = listADHERENT;
    }
    public ArrayList<AUTEUR> getListAUTEUR() {
        return ListAUTEUR;
    }
    public void setListAUTEUR(ArrayList<AUTEUR> listAUTEUR) {
        ListAUTEUR = listAUTEUR;
    }

    public model() throws SQLException, ClassNotFoundException {
        //Connexion à la BDD
        String BDD = "apjava";
        String url = "jdbc:mysql://localhost:3306/"+BDD;
        String user = "root";
        String passwd = "";
        System.out.println("connecteé à la BDD");
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,passwd);
        System.out.println("connexion ok");
        
        //Afficher les livres
        ResultSet resultats;
        String requete = "SELECT * FROM LIVRE";
        Statement stmt = conn.createStatement();
        resultats = stmt.executeQuery(requete);
        while(resultats.next()) {
            System.out.println("ISBN :"+resultats.getString(1)+
            " - titre: "+resultats.getString(2)+
            " - prix: "+resultats.getFloat(3));
        }
    }
    
    public void getall () throws SQLException {
        ListLivre = new ArrayList<LIVRE>();
        ListADHERENT = new ArrayList<ADHERENT>();
        ListAUTEUR = new ArrayList<AUTEUR>();
        
        ListADHERENT.clear();
        ListLivre.clear();
        ListAUTEUR.clear();
        
        ResultSet resultat;
        Statement stmt;
        stmt=conn.createStatement();
        
        // Chargement des AUTEUR
        resultat=stmt.executeQuery("SELECT * from AUTEUR");
        while(resultat.next()) {
            AUTEUR a = new AUTEUR(resultat.getString(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));
            ListAUTEUR.add(a);
        }	
        
        // Chargement des ADHERENT (CORRIGÉ)
        resultat=stmt.executeQuery("SELECT * from ADHERENT");
        while(resultat.next()) {
            ADHERENT ad = new ADHERENT(resultat.getString(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), new ArrayList<LIVRE>());
            ListADHERENT.add(ad);
        }	
        
        // Chargement des LIVRE (CORRIGÉ)
        resultat=stmt.executeQuery("SELECT * from LIVRE");
        while(resultat.next()) {
            LIVRE l = new LIVRE(resultat.getString(1), resultat.getString(2), resultat.getFloat(3), null, null);
            
            // CORRECTION : AUTEUR est en colonne 5
            if(resultat.getString(5)!=null) {
                AUTEUR a = findauteur(resultat.getString(5));
                l.setAuteur(a);
            }	
            
            // CORRECTION : ADHERENT est en colonne 4
            if(resultat.getString(4)!=null) {
                ADHERENT ad = findadherent(resultat.getString(4));
                l.setEmprunteur(ad);
                ad.getListLivre().add(l);
            }
            ListLivre.add(l);
        }

        // DEBUG
        System.out.println("=== DEBUG ===");
        System.out.println("Nombre d'adhérents chargés: " + ListADHERENT.size());
        for (ADHERENT ad : ListADHERENT) {
            System.out.println("Adhérent: " + ad.getNum() + " - " + ad.getNom() + " " + ad.getPrenom());
        }
        System.out.println("=============");
    }
    
    public AUTEUR findauteur(String num) {
        for (AUTEUR a : ListAUTEUR) {
            if(a.getNum().equals(num)) {
                return a;
            }
        }
        return null;
    }
        
    public ADHERENT findadherent(String num) {
        for (ADHERENT ad : ListADHERENT) {
            if(ad.getNum().equals(num)) {
                return ad;
            }
        }
        return null;
    }
    
    // MÉTHODE AJOUTÉE
    public ADHERENT findadherentbynum(String num) {
        for (ADHERENT adherent : ListADHERENT) {
            if (adherent.getNum().equals(num)) {
                return adherent;
            }
        }
        return null;
    }
    
    public LIVRE findlivre(String isbn) {
        for (LIVRE livre : ListLivre) {
            if (livre.getISBN().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }

    public void restituer_livre(String isbn) throws SQLException {
        LIVRE livre = findlivre(isbn);
        if (livre != null && livre.getEmprunteur() != null) {
            Statement stmt = conn.createStatement();
            // CORRECTION : ADHERENT au lieu de NumAdherent
            String requete = "UPDATE LIVRE SET ADHERENT = NULL WHERE ISBN = '" + isbn + "'";
            stmt.executeUpdate(requete);
            
            ADHERENT emprunteur = livre.getEmprunteur();
            emprunteur.getListLivre().remove(livre);
            livre.setEmprunteur(null);
        }
    }
    
 // Dans model.java, ajoutez cette méthode :
    public void emprunter_livre(String isbn, String numAdherent) throws SQLException {
        LIVRE livre = findlivre(isbn);
        ADHERENT adherent = findadherentbynum(numAdherent);
        
        if (livre != null && adherent != null && livre.getEmprunteur() == null) {
            // Mettre à jour la base de données
            Statement stmt = conn.createStatement();
            String requete = "UPDATE LIVRE SET ADHERENT = '" + numAdherent + "' WHERE ISBN = '" + isbn + "'";
            stmt.executeUpdate(requete);
            
            // Mettre à jour les objets en mémoire
            livre.setEmprunteur(adherent);
            adherent.getListLivre().add(livre);
        }
    }
}