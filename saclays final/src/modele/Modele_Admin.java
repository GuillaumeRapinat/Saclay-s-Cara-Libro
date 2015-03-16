package modele;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;



import Client.Client;

public class Modele_Admin {

	private int id_signalement;
	private int id_utilisateur;
	private int age;
	private int id_publi;
	private int nbrCom;
	private String mail;
	private String mdp;  // non crypt� !
	private String nom;
	private String prenom;
	private String sexe;
	private InputStream photo;
	private String date; 
	private String bloquer;
	private String message;
	private InputStream photopro;


	static Modele_Admin publication;
	
	@SuppressWarnings("unchecked")
	public static Vector<Modele_Admin> listerUtilisateurs() {
		//Vector<Modele_Admin> resultatUtilisateurs = new Vector<Modele_Admin>();
		Statement st = null;
		ResultSet rs = null;
		Vector tableData = new Vector();
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT id_utilisateur, nom, prenom, age, sexe, bloque FROM utilisateurs");
					
			while(rs.next()) {
				Vector<String> utilisateur = new Vector<String>();
				utilisateur.add(String.valueOf(rs.getInt("id_utilisateur")));
				utilisateur.add(rs.getString("prenom"));
				utilisateur.add(rs.getString("nom"));
				utilisateur.add(String.valueOf(rs.getInt("age")));
				utilisateur.add(rs.getString("sexe"));
				 if (rs.getBoolean("bloque")) utilisateur.add("TRUE");
				 else	 utilisateur.add("FALSE");
				tableData.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return tableData;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return tableData;
	}

	public static Vector<Modele_Admin> listerSignalements() {
		
		Vector<Modele_Admin> resultatSignalements = new Vector<Modele_Admin>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT id_signalement, utilisateurs.id_utilisateur, nom, prenom, date_heure FROM utilisateurs, signalements WHERE utilisateurs.id_utilisateur = signalements.id_utilisateur");
			
			while(rs.next()) {
				Modele_Admin sign = new Modele_Admin();
				sign.id_signalement = rs.getInt("id_signalement");
				sign.id_utilisateur = rs.getInt("id_utilisateur");
				sign.prenom = rs.getString("prenom");
				sign.nom = rs.getString("nom");
				//sign.date = rs.getString("date_heure");
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
	
	public static String bloquerU(int id_utilisateur){
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE utilisateurs SET bloque = true WHERE id_utilisateur = " + id_utilisateur);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR BLOQUAGE";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	public static String debloquerU(int id_utilisateur){
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE utilisateurs SET bloque = false WHERE id_utilisateur = " + id_utilisateur);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR BLOQUAGE";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	public static Modele_Admin chargerPublication(int id_publi) {
		publication = new Modele_Admin();
		
		Statement st = null;
		ResultSet rs = null;
		//récup publi
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur, photo, message, date FROM publications WHERE id_publi=" + id_publi);
			
			while(rs.next()) {
				publication.setId_publi(id_publi);
				publication.id_utilisateur = rs.getInt("id_utilisateur");
				publication.setDate("date");
				publication.setMessage(rs.getString("message"));
				publication.photo = rs.getBinaryStream("photo");
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
		//récup utilisateur
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT prenom, nom, photo FROM utilisateurs WHERE id_utilisateurs =" + publication.id_utilisateur);
			
			while(rs.next()) {
				
				publication.prenom = rs.getString("prenom");
				publication.nom = rs.getString("nom");
				publication.setPhotopro(rs.getBinaryStream("photo"));
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
		//récup utilisateur
				try {
					st = Client.connection.createStatement();
					rs = st.executeQuery(
							"SELECT count(id_com) FROM commentaires WHERE id_publi =" + id_publi);
					
					while(rs.next()) {
						
						publication.nbrCom = rs.getInt("count(id_com)");
						
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
				
		
		return publication;
		
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

	public int getId_publi() {
		return id_publi;
	}

	public void setId_publi(int id_publi) {
		this.id_publi = id_publi;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBloquer() {
		return bloquer;
	}

	public void setBloquer(String bloquer) {
		this.bloquer = bloquer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InputStream getPhotopro() {
		return photopro;
	}

	public void setPhotopro(InputStream photopro) {
		this.photopro = photopro;
	}

	

	
	
	
	
	
}
