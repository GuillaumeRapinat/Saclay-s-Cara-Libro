import controleur.Controleur_Utilisateur;

public class Client {

	static private final String IP = "127.0.0.1";
	static private final int PORT = 3306;
	
	public static void main(String[] args) {
		new Controleur_Utilisateur();
	}

}