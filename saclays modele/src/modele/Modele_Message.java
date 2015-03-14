package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import Client.Client;

public class Modele_Message {

	
	int id_utilisateur_e;
	String date_heure;
	Date date;
	Time heure;
	String objet;
	String prenom;
	String nom;
	boolean lu;
	
	
	public static Vector<Modele_Message> chercherMessages(int id_utilisateur) {
		Vector<Modele_Message> resultatMessages = new Vector<Modele_Message>();
				
		if (!Client.estValide()) return resultatMessages;
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur_e, date_heure, objet, lu, nom, prenom FROM messages, utilisateurs WHERE messages.id_ utilisateur_e = utilisatteurs.id_utilisateur AND id_utilisateur_r=" + id_utilisateur + " AND visibilite_r = TRUE");
			
			while(rs.next()) {
				Modele_Message message = new Modele_Message();
				message.id_utilisateur_e = rs.getInt("id_utilisateur_e");
				message.prenom = rs.getString("prenom");
				message.nom = rs.getString("nom");
				message.date_heure = rs.getString("date_heure");
				message.objet = rs.getString("objet");
				message.lu = rs.getBoolean("lu");
				resultatMessages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return resultatMessages;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultatMessages;
	}
	
	
	
	///////////GETTER//////////
	
	public String getNom(){
		return nom;
	}
	public String getPrenom(){
		return prenom;
	}
	public int getIdE(){
		return id_utilisateur_e;
	}
	public String getObjet(){
		return objet;
	}
	public Date getDate(){
		return date;
	}
	public Time getHeure(){
		return heure;
	}
	public boolean getLu(){
		return lu;
	}
	
}
