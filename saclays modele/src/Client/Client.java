package Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controleur.Controleur_Utilisateur;

public class Client {
	
	public static final String IP = "127.0.0.1";
	public static final int PORT = 3306;
	public static final String LOGIN_SERVEUR = "root";
	public static final String MOTDEPASSE_SERVEUR = "admin";
	
	public static Connection connection;
	
	public static boolean estValide() {
		if (connection  == null) return false;
		ResultSet ping = null;
		try {
			if (connection.isClosed()) return false;
			ping = connection.createStatement().executeQuery("SELECT 1");
			return ping.next();
		} catch (SQLException e) {
			return false;
		} finally {
			if (ping != null) {try{ping.close();} catch(Exception e){}}
		}
	}
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/caralibro", LOGIN_SERVEUR, MOTDEPASSE_SERVEUR);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de se connecter au serveur !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		new Controleur_Utilisateur();
		
		/*try {
			connection.close();  // seulement pour tester les erreurs liés à la perte de connection au serveur
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

}