package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Client.Client;

public class Modele_Utilisateur {
	
	private int id_utilisateur;
	private String mail;
	private String mdp;  // non crypt� !
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private InputStream photo;
	private String date; 
	//private Vector<Modele_Utilisateur> amis;
	
	// Prend une chaine de caract�re et renvoie son cryptage par l'algorithme MD5
	private static String md5(String motDePasse) {
		String mdpMD5 = null;
		
		try {
			byte[] mdpBytes = motDePasse.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(mdpBytes);
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(mdpBytes);
			BigInteger number = new BigInteger(1, messageDigest);
			mdpMD5 = number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return mdpMD5;
	}
	
	public String connexion(String mail, String mdp) {
		// v�rifie si la connection est valide
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		// tente de se connecter
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT * FROM utilisateurs WHERE mail='" + mail + "'");
			rs.next();
					
			if (!rs.getString("mdp").equals(md5(mdp))) return "ERREUR MDP";
			if (!rs.getBoolean("actif")) return "ERREUR ACTIF";
			if (rs.getBoolean("bloque")) return "ERREUR BLOQUE";
			
			// charge le profil
			id_utilisateur = rs.getInt("id_utilisateur");
			this.mail = mail;
			this.mdp = mdp;
			nom = rs.getString("nom");
			prenom = rs.getString("prenom");
			age = rs.getInt("age");
			sexe = rs.getString("sexe");
			photo = rs.getBinaryStream("photo");
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR CONNEXION";
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "UTILISATEUR";
	}

	public static String creerCompte(String mail, String motDePasse) {
		// v�rifie si la connection est valide
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		// v�rifie si l'adresse mail est valide
		try {
			InternetAddress test = new InternetAddress(java.net.IDN.toASCII(mail));
			test.validate();
		} catch (AddressException e) {
			e.printStackTrace();
			return "ERREUR MAIL";
		}
		
		// v�rifie si le mot de passe est valide
		if (motDePasse.length() < 7) return "ERREUR MDP";
		
		// tente de cr�er le compte
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"INSERT INTO utilisateurs (mail, mdp, connecte, actif, bloque) VALUES (?,?,?,?,?)");
			pst.setString(1, mail);
			pst.setString(2, md5(motDePasse));
			pst.setBoolean(3, false);
			pst.setBoolean(4, true);
			pst.setBoolean(5, false);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR CREATION";
		} finally {
			try {
				if(pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}

	public static String reactiverCompte(String mail, String motDePasse) {
		// v�rifie si la connection est valide
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		// on regarde s'il n'y a pas d'erreurs pour r�activer le compte
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT mdp,actif,bloque FROM utilisateurs WHERE mail='" + mail + "'");
			rs.next();
			
			if (!rs.getString("mdp").equals(md5(motDePasse))) return "ERREUR MDP";
			if (rs.getBoolean("actif")) return "ERREUR ACTIF";
			if (rs.getBoolean("bloque")) return "ERREUR BLOQUE";
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR REACTIVATION";
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// on peut r�activer le compte
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE utilisateurs SET actif=true WHERE mail='" + mail + "'");
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR REACTIVATION";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	// met � jour le compte sur la bdd
	public String miseAJour() {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE utilisateurs SET nom=?,prenom=?,age=?,sexe=? WHERE id_utilisateur=" + id_utilisateur);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setInt(3, age);
			pst.setString(4, sexe);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR UPDATE";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	// retourne tous les utilisateurs correspondant � la recherche donn�e en param�tre
	public static Vector<Modele_Utilisateur> rechercher(String recherche) {
		Vector<Modele_Utilisateur> resultatRecherche = new Vector<Modele_Utilisateur>();
		recherche = recherche.replaceAll(" ", "");
		
		if (!Client.estValide()) return resultatRecherche;
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM utilisateurs WHERE nom='" + recherche + "' OR prenom='" + recherche +
					"' OR CONCAT(nom,prenom)='" + recherche + "' OR CONCAT(prenom,nom)='" + recherche +"'");
			
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.nom = rs.getString("nom");
				utilisateur.prenom = rs.getString("prenom");
				utilisateur.photo = rs.getBinaryStream("photo");
				resultatRecherche.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return resultatRecherche;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultatRecherche;
	}
	
	// retourne tous les utilisateurs avec qui on est amis
	public Vector<Modele_Utilisateur> listerAmis() {
		Vector<Modele_Utilisateur> amis = new Vector<Modele_Utilisateur>();
			
		if (!Client.estValide()) return amis;
			
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM utilisateurs WHERE id_utilisateur="
					+ "(SELECT id_utilisateur_d FROM amities WHERE ACCEPTED=true AND id_utilisateur_a =" + id_utilisateur + ") "
					+ "OR (SELECT id_utilisateur_a FROM amities WHERE ACCEPTED=true AND id_utilisateur_d =" + id_utilisateur + ")");
				
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.nom = rs.getString("nom");
				utilisateur.prenom = rs.getString("prenom");
				utilisateur.age = rs.getInt("age");
				utilisateur.sexe = rs.getString("sexe");
				utilisateur.photo = rs.getBinaryStream("photo");
				amis.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return amis;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return amis;
	}


	public String changerPhoto(String fichier) {
		// v�rifie la taille du fichier
    	long taille = (new File(fichier)).length();
    	if (taille > 512000)  // 500 kb
    		return "ERREUR TAILLE";
		
    	FileInputStream fs = null;
    	try {
			fs = new FileInputStream(fichier); // on lit le fichier
			
			// envoie du fichier au serveur
	    	if (!Client.estValide()) return "ERREUR SERVEUR";
	    	
			PreparedStatement pst = null;
			try {
				pst = Client.connection.prepareStatement(
						"UPDATE utilisateurs SET photo=? WHERE id_utilisateur=" + id_utilisateur);
				pst.setBinaryStream(1, fs, (int)taille);
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return "ERREUR ENVOI";
			} finally {
				try {
					if (pst != null) pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// on met � jour la photo du modele
			photo = new FileInputStream(fichier);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "ERREUR FICHIER";
		} finally {
			try {
				if (fs != null) fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
		return "SUCCES";
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// GETTERS


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
