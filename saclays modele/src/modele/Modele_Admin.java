package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Client.Client;

public class Modele_Admin {

	
	public Vector<Modele_Admin> listerAmis() {
		Vector<Modele_Admin> amis = new Vector<Modele_Admin>();
			
		if (!Client.estValide()) return amis;
			
		Statement st = null;
		ResultSet rs = null;
		try {
			st = Client.connection.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM signalements"
//faire aussi "SELECT utilisateurs.id_utilisateur, utilisateurs.prenom, utilisateurs.nom FROM utilisateurs, signalements WHERE utilisateurs.id_utilisateur" + id_utilisateur
					
				
/*			while(rs.next()) {
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
	*/
}
