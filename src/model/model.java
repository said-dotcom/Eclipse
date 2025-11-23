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
		private Connection conn ;
		
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
			
			//Conexion à la BDD
			String BDD = "apjava";
			String url = "jdbc:mysql://localhost:3306/"+BDD;
			String user = "root";
			String passwd = "";
			System.out.println("connecteé à la BDD");
			
			Class.forName("com.mysql.jdbc.Driver");
			//Vérifie la connection 
			 conn = DriverManager.getConnection(url,user,passwd);
			System.out.println("connexion ok");
			
			//ETAPE 2 : afficher les livres avec un SELECT
			// Déclaration de vaiable 
			ResultSet resultats;
			String requete ;
			// je fais ma requete 
			requete = "SELECT * FROM LIVRE";
			Statement stmt = conn.createStatement();
			resultats = stmt.executeQuery(requete);
			// afficher les livres present dans la BDD
			while(resultats.next()) {
				System.out.println("ISBN :"+resultats.getString(1)+
				" - titre: "+resultats.getString(2)+
				" - prix: "+resultats.getFloat(3));
			}
			
			
			//ETAPE 3 : ins�rer un livre
			//stmt.executeUpdate("INSERT INTO Livre VALUES ('test2','titre',0,null,null);");
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
			
			//*************************************************
			// chargement de la liste des AUTEUR
			//*************************************************
			
			resultat=stmt.executeQuery("SELECT * from AUTEUR");
			while(resultat.next())
			{
				
				AUTEUR a = new AUTEUR(resultat.getString(1), resultat.getString(2), resultat.getString(3),  resultat.getString(4),  resultat.getString(5));
				ListAUTEUR.add(a);
			}	
			
			//*************************************************
			// chargement de la liste des ADHERENT
			//*************************************************
			resultat=stmt.executeQuery("SELECT * from ADHERENT");
			while(resultat.next())
			{
				ADHERENT ad = new ADHERENT(resultat.getString(1), resultat.getString(2), resultat.getString(3),  new ArrayList<LIVRE>());
				ListADHERENT.add(ad);
			}	
			
			//*************************************************
			// chargement de la liste de LIVRE
			//*************************************************

			resultat=stmt.executeQuery("SELECT * from LIVRE");
			while(resultat.next())
			{
				LIVRE l = new LIVRE(resultat.getString(1), resultat.getString(2), resultat.getFloat(3), null, null);
				//si le livre � un auteur. On l'ajoute dans le livre
				if(resultat.getString(5)!=null)
				{
					AUTEUR a = findauteur(resultat.getString(5));
					l.setAuteur(a);
				}	
				//si le livre � un emprunteur
				if(resultat.getString(4)!=null)
				{
					ADHERENT ad = findadherent(resultat.getString(4));
					//on affecte l'emprunteur au livre
					l.setEmprunteur(ad);

					//on ajoute le livre � l'adh�rent
					ad.getListLivre().add(l);
				}
				ListLivre.add(l);
			}

		}
		
		//m�thode qui retourne un AUTEUR en fonction de son num�ro
		public AUTEUR findauteur(String num)
		{
			for (AUTEUR a : ListAUTEUR)
			{
				if(a.getNum().equals(num))
				{
					return a;
				}
			}
		
			return null;
		}
			
		//m�thode qui retourne un ADHERENT en fonction de son num�ro
		public ADHERENT findadherent(String num)
		{
			for (ADHERENT ad : ListADHERENT)
			{
				if(ad.getNum().equals(num))
				{
					return ad;
				}
			}
			return null;
			
		}




				
		
		
}