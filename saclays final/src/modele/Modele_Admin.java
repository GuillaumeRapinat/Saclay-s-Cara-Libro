package modele;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.Icon;

import Client.Client;

public class Modele_Admin {

	private int id_signalement;
	private int id_utilisateur;
	private String mail;
	private String mdp;  // non crypt� !
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private InputStream photo;
	private String date; 
	
	
	public static Vector<Modele_Admin> listerUtilisateurs() {
		Vector<Modele_Admin> resultatUtilisateurs = new Vector<Modele_Admin>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT id_utilisateur, nom, prenom, age, sexe FROM utilisateurs");
			
			while(rs.next()) {
				Modele_Admin utilisateur = new Modele_Admin();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.prenom = rs.getString("prenom");
				utilisateur.nom = rs.getString("nom");
				utilisateur.age = rs.getInt("age");
				utilisateur.sexe = rs.getString("sexe");
				resultatUtilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return resultatUtilisateurs;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultatUtilisateurs;
	}

	public static Vector<Modele_Admin> listerSignalements() {
		
		Vector<Modele_Admin> resultatSignalements = new Vector<Modele_Admin>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT id_signalement, utilisateurs.id_utilisateur, nom, prenom, date FROM utilisateurs, signalements WHERE utilisateurs.id_utilisateur = signalements.id_utilisateur");
			
			while(rs.next()) {
				Modele_Admin sign = new Modele_Admin();
				sign.id_signalement = rs.getInt("id_signalement");
				sign.id_utilisateur = rs.getInt("id_utilisateur");
				sign.prenom = rs.getString("prenom");
				sign.nom = rs.getString("nom");
				sign.date = rs.getString("date");
				resultatSignalements.add(sign);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return resultatSignalements;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultatSignalements;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	// GETTERS

	public int getIdS() {
		return id_signalement;
	}
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getNom() {
		return nom;
	}
	public String getAge() {
		return String.valueOf(age);
	}
	public String getSexe() {
		return sexe;
	}
	public InputStream getPhoto() {
		return photo;
	}

	////////////////////////////////////////////////////////////////////////////////
	// SETTERS
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;		
	}
	
	public void setNom(String nom) {
		this.nom = nom;		
	}
	
	public void setAge(int age) {
		this.age = age;		
	}
	
	public void setSexe(String sexe) {
		this.sexe = sexe;		
	}

	
	
	
	
	
}
