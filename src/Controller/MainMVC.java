package Controller;

import java.sql.SQLException;
import model.model;
import View.View_Accueil;


public class MainMVC {
	private static model m;
	
	public static model getM()
	{
		return m;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println ("lancement de mon programme");
		m =new model();
		
		View_Accueil window = new View_Accueil();
		
	}

		
		
		

	}
   







