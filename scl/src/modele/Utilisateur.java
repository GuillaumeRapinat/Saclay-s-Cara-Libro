package modele;

public class Utilisateur {
///// modele provisoir, le temps de comprendre comment fonctionne une table
	private int id;
	private String prenom;
	private String nom;
	private int age;
	private boolean sexe;
	
	
	
	public static int getId(int id){
		return id;
	}
	public static String getPrenom(String prenom){
		return prenom;
	}
	public static String getNom(String nom){
		return nom;
	}
	public static int getAge(int age){
		return age;
	}
	public static boolean getSexe(boolean sexe){
		return sexe;
	}
	
}
