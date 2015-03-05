package modele;

public class Modele_Utilisateur {
	
	private int id;
	private String login;
	private String motDePasse;
	private String nom;
	private String prenom;
	
	public String connection(String login, String motDePasse) {
		return "UTILISATEUR";
	}
//juste pour visualiser la page admin
//	public String admin(String login, String motDePasse) {
	//	return "ADMIN";
	//}
	
	
	public String creerCompte(String login, String motDePasse) {
		return "SUCCES";
	}

	public String reactiverCompte(String login, String motDePasse) {
		return "SUCCES";
	}

}
