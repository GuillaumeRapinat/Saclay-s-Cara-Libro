package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import Client.Client;

public class Modele_Publication {

	private static Vector<Modele_Publication> publications;
	private int id_publi;
	private int id_utilisateur;
	private InputStream photo;
	private String message;
	private Date date;
	private Time heure;
	
	// charge les publications de l'utilisateur donné en paramètre
	public static void chargerPublications(int id_utilisateur) {
		publications = new Vector<Modele_Publication>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_publi,photo,message,date_heure FROM publications WHERE id_utilisateur=" + id_utilisateur);
			
			while(rs.next()) {
				Modele_Publication publication = new Modele_Publication();
				publication.id_publi = rs.getInt("id_publi");
				publication.id_utilisateur = id_utilisateur;
				publication.message = rs.getString("message");
				publication.photo = rs.getBinaryStream("photo");
				publication.date = rs.getDate("date_heure");
				publication.heure = rs.getTime("date_heure");
				publications.add(0, publication);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String publier(String message, String urlPhoto) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		FileInputStream fs = null;
		try {
			if (urlPhoto == null) {
				pst = Client.connection.prepareStatement(
						"INSERT INTO publications (id_utilisateur,message,date_heure) VALUES (?,?,NOW())");
			} else {
				pst = Client.connection.prepareStatement(
						"INSERT INTO publications (id_utilisateur,message,photo,date_heure) VALUES (?,?,?,NOW())");
				
				// vérifie la taille du fichier
				long taille = (new File(urlPhoto)).length();
			    if (taille > 512000) return "ERREUR TAILLE";
			    
			    // lit le fichier
				fs = new FileInputStream(urlPhoto);
				pst.setBinaryStream(3, fs, (int)taille);	
			}
			
			pst.setInt(1, Modele_Utilisateur.getMonId());
			pst.setString(2, message);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR PUBLIER";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "ERREUR FICHIER";
		} finally {
			try {
				if(pst != null) pst.close();
				if (fs != null) fs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	///////////////////////////////////////////////////////
	// GETTERS

	public static Vector<Modele_Publication> getPublications() {
		return publications;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public String getMessage() {
		return message;
	}
	
	public int getIdPubli() {
		return id_publi;
	}
	
	public int getIdUtilisateur() {
		return id_utilisateur;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Time getHeure() {
		return heure;
	}

	public static Modele_Publication getPublication(int i) {
		return publications.get(i);
	}
	
}
