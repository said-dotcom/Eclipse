package model;

import java.util.ArrayList;

public class ADHERENT {
	private String num;
	private String nom;
	private String prenom;
	private ArrayList<LIVRE> ListLivre;
	
	public ADHERENT(String num, String nom, String prenom, ArrayList<LIVRE> listeLivre) {
		super();
		this.num = num;
		this.nom = nom;
		this.prenom = prenom;
		this.ListLivre = listeLivre;	
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public ArrayList<LIVRE> getListLivre() {
		return ListLivre;
	}

	public void setListLivre(ArrayList<LIVRE> listLivre) {
		ListLivre = listLivre;
	}
	
	
	
}