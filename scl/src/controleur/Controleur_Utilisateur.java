package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.Modele_Utilisateur;
import vue.Vue_Accueil;
import vue.Vue_Admin;
import vue.Vue_Charger_Image;
import vue.Vue_Liste_Amis;
import vue.Vue_Liste_Jaime;
import vue.Vue_Mur;
import vue.Vue_Recherche_Nouvel_Ami;
import vue.Vue_Utilisateur;

public class Controleur_Utilisateur implements ActionListener {

	// constantes pour les actionCommand
	public static final String ACTION_CONNECTION = "CONNECTION";
	public static final String ACTION_CREER_COMPTE = "CREER COMPTE";
	public static final String ACTION_DECONNECTION = "DECONNECTION";
	public static final String ACTION_MODIFIER_PROFIL = "MODIFIER PROFIL";
	public static final String ACTION_REACTIVER_COMPTE = "REACTIVER COMPTE";
//juste pour visualiser admin	
	public static final String ACTION_ADMIN = "ADMIN";
	public static final String ACTION_VOIR_MUR = "VOIR MUR";
	public static final String ACTION_LISTER_AMIS = "LISTE D'AMIS";
	public static final String ACTION_AJOUTER_IMAGE = "AJOUTER IMAGE";
	public static final String ACTION_ANNULER_AJOUTER_IMAGE = "ANNULER AJOUTER IMAGE";
	
	public static final String ACTION_RECHERCHER_AMIS = "RECHERCHER AMIS";
	public static final String ACTION_DEMANDER_AMI = "DEMANDER EN AMI";
	
	Controleur_Mur controleurMur;
	private Modele_Utilisateur modeleUtilisateur;
	private JFrame vue;
	private JFrame vue3;
//////////////////////////////	
	public Controleur_Utilisateur() {
		modeleUtilisateur = new Modele_Utilisateur();
		vue = new Vue_Accueil(this);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CONNECTION:
			connection();
			break;
//juste pour visualiser admin			
		case ACTION_ADMIN:
			coadmin();
			break;
		case ACTION_VOIR_MUR:
			voirMur();
			break;
		case ACTION_LISTER_AMIS:
			listeAmis();
			break;
		case ACTION_AJOUTER_IMAGE:
			ajouterImage();
			break;
		case ACTION_ANNULER_AJOUTER_IMAGE:
			annulerAjouterImage();
			break;
		
		case ACTION_RECHERCHER_AMIS:
			listeRecherche();
			break;
		case ACTION_DEMANDER_AMI:
			JOptionPane.showMessageDialog(new JDialog(),"                                          Votre demande a bien été reçu!\nSi d'ici un mois vous n'avez toujours pas de réponse, vous pourrez refaire la demande =)", "Demande ajoutée!", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
			break;
//////////////////////////
		case ACTION_CREER_COMPTE:
			creerCompte();
			break;
		case ACTION_DECONNECTION:
			deconnection();
			break;
		case ACTION_MODIFIER_PROFIL:
			modifierProfil();
			break;
		case ACTION_REACTIVER_COMPTE:
			reactiverCompte();
			break;
		}
	}

	private void listeAmis() {
		Vue_Liste_Amis vue2 = new Vue_Liste_Amis();
		vue2.setVisible(true);
		
	}
	
	
	
	private void ajouterImage() {
		vue3 = new Vue_Charger_Image();
		vue3.setVisible(true);
		
	}
	private void annulerAjouterImage() {
		vue3.setVisible(false);
		
	}
	
	private void listeRecherche() {
		JFrame vue5 = new Vue_Recherche_Nouvel_Ami(this);
		vue5.setVisible(true);
	}
	
	private void voirMur() {
		Vue_Mur vuemur = new Vue_Mur(controleurMur);
		//vue = new Vue_Mur(new Controleur_Mur());
		
	}

	private void connection() {
		String login = ((Vue_Accueil) vue).getLogin();
		String motDePasse =  ((Vue_Accueil) vue).getMotDePasse();
		
		switch(modeleUtilisateur.connection(login, motDePasse)) {
		case "UTILISATEUR":
			vue.setVisible(false);
			vue.dispose();
			vue = new Vue_Utilisateur(this);
			break;
		case "ADMIN":
			vue.setVisible(false);
			vue.dispose();
			vue = new Vue_Admin(this);
			break;
		case "ERREUR_LOGIN":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte n'existe pas !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR_MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

//juste pour visualiser admin
	private void coadmin(){
	
		vue.setVisible(false);
		vue.dispose();
		vue = new Vue_Admin(this);
		
	}
	
	
	
	
	
	private void creerCompte() {
		String login = ((Vue_Accueil) vue).getLogin();
		String motDePasse =  ((Vue_Accueil) vue).getMotDePasse();
		
		switch(modeleUtilisateur.creerCompte(login, motDePasse)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien été créé. Vous pouvez dès à présent vous connecter avec ce compte.)", "Création de compte réussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR_LOGIN_EXISTE":
			JOptionPane.showMessageDialog(new JDialog(),"Ce login existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR_LOGIN":
			JOptionPane.showMessageDialog(new JDialog(),"Login incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR_MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	private void deconnection() {
		vue.setVisible(false);
		vue.dispose();
		vue = new Vue_Accueil(this);
	}
	
	private void modifierProfil() {
		
	}
	
	private void reactiverCompte() {
		String login = ((Vue_Accueil) vue).getLogin();
		String motDePasse =  ((Vue_Accueil) vue).getMotDePasse();
		
		switch(modeleUtilisateur.reactiverCompte(login, motDePasse)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien été réactivé. Vous pouvez dès à présent vous connecter avec ce compte.", "Réactivation de compte réussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR_LOGIN":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte n'existe pas !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR_MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
}
