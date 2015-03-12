package vue;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modele.Utilisateur;



import controleur.Controleur_Utilisateur;

public class Vue_Table extends AbstractTableModel {
	
	
	int id;
	String prenom;
	String nom;
	int age;
	boolean sexe;
	ArrayList<Vue_Table> notes;
	
/*	Vue_Table(int id, String prenom, String nom, int age, boolean sexe){
		this.id = id;
		this.prenom =prenom;
		this.nom = nom;
		this.age = age;
		this.sexe = sexe;
	}
	
//	private void chargerDepuisBaseDeDonnees() {

		
/*		if (notes != null) {
			return;
		}

//		notes = new ArrayList<Vue_Table>();

/*		// Filles (nom, prenom, annee, sexe, note)
		donneUt(2, "Durand", "Marie", 19, false);
		donneUt(28, "Alesi", "Julie", 18, false);
		

		// Garcons (nom, prenom, annee, sexe, note)
		notes.add(54, "Michelet", "Jean", 24, true);
		donneUt(12, "Dupond", "Pierre", 18, true);
		donneUt(7, "Timberot", "Martin", 21, true);
		donneUt(9, "Gravatas", "Paul", 22, true);
		

	}

	private void donneUt(int id, String prenom, String nom, int age, boolean sexe) {
//		final Utilisateur eleve = new Utilisateur();
//		notes.add(eleve);
		this.id = id;
		this.prenom =prenom;
		this.nom = nom;
		this.age = age;
		this.sexe = sexe;
		
	}
*/
	private final String[] entetes = { "iD", "Prénom", "Nom", "Age", "Sexe"};
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		//return notes.size();
		return 6;
	}
	
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return int.class;
			case 1:
				return String.class;
	
			case 3:
				return String.class;
	
			case 2:
				return int.class;
	
			case 4:
				return boolean.class;
	
			default:
				return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {

		case 0:
			// Nom
		//	return notes.get(rowIndex).getNom();
			return 1;
		case 1:
			// Prenom
		//	return notes.get(rowIndex).getEleve().getPrenom();
			return "Guillaume";
		case 2:
			// Age
		//	return notes.get(rowIndex).getEleve().getAnnee();
			return "Rapinat";
		case 3:
			// Sexe
		//	return notes.get(rowIndex).getEleve().getSexe();
			return 24;
		case 4:
			return true;
		default:
			throw new IllegalArgumentException();
		}
	}


	
	
	
	
	

}
