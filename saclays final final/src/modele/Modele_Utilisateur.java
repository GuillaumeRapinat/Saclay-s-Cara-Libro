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

import Client.Client;

public class Modele_Utilisateur {
	
	private static int monId;
	
	private int id_utilisateur;
	private String mail;
	private String mdp;  // non crypt� !
	private String nom;
	private String prenom;
	private int age;
	private String sexe;
	private InputStream photo;
	
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
	
	public static boolean bloque() {		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT bloque FROM utilisateurs WHERE id_utilisateur=" + monId);
			rs.next();		
			if(rs.getBoolean("bloque")) return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
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
			monId = id_utilisateur = rs.getInt("id_utilisateur");
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
	
	public static String desactiverCompte() {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE utilisateurs SET actif=false WHERE id_utilisateur=" + monId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR REACTIVER";
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
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur,nom,prenom,photo FROM utilisateurs WHERE nom='" + recherche + "' OR prenom='" + recherche +
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
	
	// retourne tous les utilisateurs qui nous demandent en ami
	public static Vector<Modele_Utilisateur> listerDemandesAjout() {
		Vector<Modele_Utilisateur> demandeurs = new Vector<Modele_Utilisateur>();
				
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur,nom,prenom,photo FROM utilisateurs WHERE id_utilisateur IN "
				+ "(SELECT id_utilisateur_d FROM amities WHERE accepted=false AND id_utilisateur_a="+ monId + ")");
				
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.nom = rs.getString("nom");
				utilisateur.prenom = rs.getString("prenom");
				utilisateur.photo = rs.getBinaryStream("photo");
				demandeurs.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return demandeurs;
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return demandeurs;
	}
	
	// retourne tous les utilisateurs avec qui on est amis
	public static Vector<Modele_Utilisateur> listerAmis() {
		Vector<Modele_Utilisateur> amis = new Vector<Modele_Utilisateur>();
			
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur,nom,prenom,photo FROM utilisateurs WHERE id_utilisateur in "
				+ "(SELECT id_utilisateur_a FROM amities WHERE accepted=true AND id_utilisateur_d="+ monId + ") "
				+ "OR id_utilisateur IN (SELECT id_utilisateur_d FROM amities WHERE accepted=true AND id_utilisateur_a=" + monId + ")");
			
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.nom = rs.getString("nom");
				utilisateur.prenom = rs.getString("prenom");
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
	
	public static Vector<Modele_Utilisateur> amisCommuns(int id_utilisateur) {
		Vector<Modele_Utilisateur> amis = new Vector<Modele_Utilisateur>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT id_utilisateur,nom,prenom,photo FROM utilisateurs WHERE id_utilisateur IN "
				+ "(SELECT id_utilisateur FROM (SELECT id_utilisateur_d AS id_utilisateur FROM amities WHERE accepted=true AND id_utilisateur_a=" + monId + " UNION "
				+ "SELECT id_utilisateur_a AS id_utilisateur FROM amities WHERE accepted=true AND id_utilisateur_d=" + monId + ") AS a)"
				+ "AND id_utilisateur IN "
				+ "(SELECT id_utilisateur FROM (SELECT id_utilisateur_d AS id_utilisateur FROM amities WHERE accepted=true AND id_utilisateur_a=" + id_utilisateur + " UNION "
				+ "SELECT id_utilisateur_a AS id_utilisateur FROM amities WHERE accepted=true AND id_utilisateur_d=" + id_utilisateur + ") AS b)");
			
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.nom = rs.getString("nom");
				utilisateur.prenom = rs.getString("prenom");
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
						"UPDATE utilisateurs SET photo=? WHERE id_utilisateur=" + monId);
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
	
	public static String demandeAjout(int id_utilisateur_a) {
		if(id_utilisateur_a == monId) return "ERREUR SOIMEME";
		
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"INSERT INTO amities (id_utilisateur_d, id_utilisateur_a, accepted) VALUES (?,?,?)");
			pst.setInt(1, monId);
			pst.setInt(2, id_utilisateur_a);
			pst.setBoolean(3, false);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR AJOUT";
		} finally {
			try {
				if(pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return "SUCCES";
	}
	
	public static String accepterAjout(int id_utilisateur_d) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"UPDATE amities SET accepted=true WHERE id_utilisateur_d=? AND id_utilisateur_a=?");
			pst.setInt(1, id_utilisateur_d);
			pst.setInt(2, monId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR AJOUT";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	public static String refuserAjout(int id_utilisateur_d) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"DELETE FROM amities WHERE id_utilisateur_d=? AND id_utilisateur_a=?");
			pst.setInt(1, id_utilisateur_d);
			pst.setInt(2, monId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR REFUS";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}
	
	public static String supprimerAmi(int id_utilisateur) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"DELETE FROM amities WHERE id_amitie in "
					+ "(SELECT * FROM (SELECT id_amitie FROM amities WHERE id_utilisateur_d=? AND id_utilisateur_a=?) AS tmp) OR id_amitie in"
					+ "(SELECT * FROM (SELECT id_amitie FROM amities WHERE id_utilisateur_d=? AND id_utilisateur_a=?) AS tmp)");
			pst.setInt(1, id_utilisateur);
			pst.setInt(2, monId);
			pst.setInt(3, monId);
			pst.setInt(4, id_utilisateur);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR SUPPRIMER";
		} finally {
			try {
				if (pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "SUCCES";
	}
	
	public String chargerProfil(int id_utilisateur) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
				
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT * FROM utilisateurs WHERE id_utilisateur='" + id_utilisateur + "'");
			rs.next();
					
			// charge le profil
			this.id_utilisateur = id_utilisateur;
			mail = rs.getString("mail");
			nom = rs.getString("nom");
			prenom = rs.getString("prenom");
			age = rs.getInt("age");
			sexe = rs.getString("sexe");
			photo = rs.getBinaryStream("photo");
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR CHARGER";
		} finally {
			try {
				if (st != null) st.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "SUCCES";
	}
	
	public Vector<Modele_Utilisateur> listerUtilisateurs() {
		Vector<Modele_Utilisateur> resultatUtilisateurs = new Vector<Modele_Utilisateur>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT id_utilisateur, nom, prenom, age, sexe FROM utilisateurs");
			
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
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

	public Vector<Modele_Utilisateur> listerSignalements() {
		
		Vector<Modele_Utilisateur> resultatSignalements = new Vector<Modele_Utilisateur>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery("SELECT utilisateurs.id_utilisateur, nom, prenom, date FROM utilisateurs, signalements WHERE utilisateurs.id_utilisateur = signalements.id_utilisateur");
			
			while(rs.next()) {
				Modele_Utilisateur utilisateur = new Modele_Utilisateur();
				utilisateur.id_utilisateur = rs.getInt("id_utilisateur");
				utilisateur.prenom = rs.getString("prenom");
				utilisateur.nom = rs.getString("nom");
				//utilisateur.date = rs.getString("date");
				resultatSignalements.add(utilisateur);
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
	
	public int getId() {
		return id_utilisateur;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public static int getMonId() {
		return monId;
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
