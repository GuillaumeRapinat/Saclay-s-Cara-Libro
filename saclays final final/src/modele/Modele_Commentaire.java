package modele;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import Client.Client;

public class Modele_Commentaire {

	private int id_com;
	private int id_publi;
	private String nom;
	private String prenom;
	private InputStream photo;
	private Date date;
	private Time heure;
	private String message;
	
	public static Vector<Modele_Commentaire> chargerCommentaires(int id_publi) {
		Vector<Modele_Commentaire> commentaires = new Vector<Modele_Commentaire>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_com,message,date_heure,nom,prenom,photo FROM commentaires,utilisateurs WHERE utilisateurs.id_utilisateur=commentaires.id_utilisateur AND id_publi=" + id_publi);
			
			while(rs.next()) {
				Modele_Commentaire com = new Modele_Commentaire();
				com.id_com = rs.getInt("id_com");
				com.id_publi = id_publi;
				com.nom = rs.getString("nom");
				com.prenom = rs.getString("prenom");
				com.photo = rs.getBinaryStream("photo");
				com.message = rs.getString("message");
				com.date = rs.getDate("date_heure");
				com.heure = rs.getTime("date_heure");
				commentaires.add(0, com);
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
		
		return commentaires;
	}
	
	public static String commenter(String message, int id_publi) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"INSERT INTO commentaires (id_publi,id_utilisateur,date_heure,message) VALUES (?,?,NOW(),?)");
			pst.setInt(1, id_publi);
			pst.setInt(2, Modele_Utilisateur.getMonId());
			pst.setString(3, message);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR COMMENTER";
		} finally {
			try {
				if(pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	//////////////////////////////////////////////
	//GETTERS

	public String getMessage() {
		return message;
	}
	
	public int getIdPubli() {
		return id_publi;
	}
	
	public int getIdCom() {
		return id_com;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Time getHeure() {
		return heure;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public InputStream getPhoto() {
		return photo;
	}
	
}
