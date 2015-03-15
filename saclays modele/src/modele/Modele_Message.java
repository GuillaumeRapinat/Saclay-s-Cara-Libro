package modele;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import Client.Client;

public class Modele_Message {

	int id_message;
	int id_utilisateur_e;
	String date_heure;
	Date date;
	Time heure;
	String objet;
	String texte;
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
					"SELECT id_message, id_utilisateur_e, date_heure, objet, lu, nom, prenom FROM messages, utilisateurs WHERE messages.id_ utilisateur_e = utilisatteurs.id_utilisateur AND id_utilisateur_r=" + id_utilisateur + " AND visibilite_r = TRUE");
			
			while(rs.next()) {
				Modele_Message message = new Modele_Message();
				message.id_message = rs.getInt("id_message");
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
	
	
	public Modele_Message recupMessage(int id){
		Modele_Message contenuMessage = new Modele_Message();

		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_message, id_utilisateur_e, date_heure, objet, message, lu, nom, prenom FROM messages, utilisateurs WHERE messages.id_utilisateur_e = utilisateurs.id_utilisateur AND id_message =" +id_message);			
				Modele_Message m = new Modele_Message();
				m.id_message = rs.getInt("id_message");
				m.id_utilisateur_e = rs.getInt("id_utilisateur_e");
				m.nom = rs.getString("nom");
				m.prenom = rs.getString("prenom");
//				m.date_heure = rs.getDateTime("date_heure");
				m.objet = rs.getString("objet");
				m.texte = rs.getString("message");
				m.lu = rs.getBoolean("lu");
		
		} catch (SQLException e) {
			e.printStackTrace();
			return contenuMessage;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return contenuMessage;
	}
	
	
	public static String envoieMessage(String objet, String texteMessage, int id_utilisateur_e, int id_utilisateur_r){
		if (!Client.estValide()) return "ERREUR SERVEUR";
		if(texteMessage == null) return "MESSAGE VIDE!!";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"INSERT INTO messages (id_utilisateur_e, id_utlisateur_r, date_heure, objet, message, visibilite_e, visibilite_r) VALUES (?,?,?,?,?,?)");
			pst.setInt(1, id_utilisateur_e);
			pst.setInt(2, id_utilisateur_r);
			pst.setTime(3, null);
			pst.setString(4, objet);
			pst.setString(5, texteMessage);
			pst.setBoolean(6, true);
			pst.setBoolean(7, true);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR ENVOI";
		} finally {
			try {
				if(pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return "ENVOI REUSSI";
	}
	///////////GETTER//////////
	
	
	public int getIdM(){
		return id_message;
	}
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
	public String getTexte() {

		return texte;
	}
	
}
