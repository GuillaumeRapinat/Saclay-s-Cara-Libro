package modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Client.Client;

public class Modele_Signalement {

	public static String signaler(int id_com_publi) {
		if (!Client.estValide()) return "ERREUR SERVEUR";
		
		PreparedStatement pst = null;
		try {
			pst = Client.connection.prepareStatement(
					"INSERT INTO signalements (id_com_publi, date_heure) VALUES (?,NOW())");
			pst.setInt(1, id_com_publi);
			//pst.setInt(2, id_utilisateur);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "ERREUR SIGNALER";
		} finally {
			try {
				if(pst != null) pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "SUCCES";
	}

}
