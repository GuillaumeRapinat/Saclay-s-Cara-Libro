package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import modele.Modele_Message;
import modele.Modele_Utilisateur;
import vue.ImagePreview;
import vue.Vue_Accueil;
import vue.Vue_Admin;
import vue.Vue_Liste_Amis;
import vue.Vue_Message;
import vue.Vue_Recherche;
import vue.Vue_Utilisateur;

public class Controleur_Utilisateur implements ActionListener {
	
	// constantes pour les actionCommand
	public static final String ACTION_CONNEXION = "CONNEXION";
	public static final String ACTION_CREER_COMPTE = "CREER COMPTE";
	public static final String ACTION_DECONNEXION = "DECONNEXION";
	public static final String ACTION_MODIFIER_PROFIL = "MODIFIER PROFIL";
	public static final String ACTION_REACTIVER_COMPTE = "REACTIVER COMPTE";
	public static final String ACTION_TERMINER = "TERMINER";
	public static final String ACTION_RECHERCHER = "RECHERCHER";
	public static final String ACTION_CHANGER_PHOTO_PROFIL = "CHANGER PHOTO PROFIL";
	public static final String ACTION_LISTER_AMIS = "LISTE D'AMIS";
	public static final String ACTION_DESACTIVER_COMPTE = "DESACTIVER COMPTE";
	public static final String ACTION_LISTER_MESSAGES = "LISTE DE MESSAGES";
	
	
	private Modele_Utilisateur modeleUtilisateur;
	private JFrame vue;
	
	public Controleur_Utilisateur() {
		modeleUtilisateur = new Modele_Utilisateur();
		vue = new Vue_Accueil(this);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CONNEXION:
			connexion();
			break;
		case ACTION_CREER_COMPTE:
			creerCompte();
			break;
		case ACTION_DESACTIVER_COMPTE:
			desactiverCompte();
			break;
		case ACTION_DECONNEXION:
			deconnexion();
			break;
		case ACTION_REACTIVER_COMPTE:
			reactiverCompte();
			break;
		case ACTION_MODIFIER_PROFIL:
			((Vue_Utilisateur) vue).modifierProfil();
			break;
		case ACTION_TERMINER:
			miseAJour();
			break;
		case ACTION_LISTER_MESSAGES:
			listeMessages();
			break;
		case ACTION_RECHERCHER:
			String recherche = ((Vue_Utilisateur) vue).getRecherche();  // on recup�re la recherche
			Vector<Modele_Utilisateur> resultatRecherche = Modele_Utilisateur.rechercher(recherche);  // on ex�cute la recherche
			if (resultatRecherche.size() > 0) new Vue_Recherche(this, resultatRecherche);  // on affiche le r�sultat de la recherche
			else							  JOptionPane.showMessageDialog(new JDialog(),"Aucun r�sultat trouv� pour '" + recherche + "'.", "Recherche", JOptionPane.INFORMATION_MESSAGE);
			break;
		case ACTION_CHANGER_PHOTO_PROFIL:
			parcourirImage();
			break;
		case ACTION_LISTER_AMIS:
			Vector<Modele_Utilisateur> amis = modeleUtilisateur.listerAmis();
			if (amis.size() > 0) new Vue_Liste_Amis(this, amis);
			else				 JOptionPane.showMessageDialog(new JDialog(),"Vous n'avez aucun amis... C'est bien triste :(", "Liste d'amis vide", JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}

	private void listeMessages() {
		Vector<Modele_Message> resultatMessages = chercherMessages(Modele_Utilisateur.getMonId());
		new Vue_Message(new Controleur_Message(), resultatMessages);
		
	}	
	
	private void desactiverCompte() {
		Object[] options2 = {"Oui! Bye bye Saclay's Cara Libro!", "Non non, je reste!"};
		int n2 = JOptionPane.showOptionDialog(new JDialog(), "Êtes-vous vraiment sûr(e) de vouloir nous quitter??", "Désactiver son compte",
				JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),
			    options2, options2[0]);
		if (n2 == JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(new JDialog(), "Votre compte est désactiver. Vous ne pourrez plus vous connecter et vos amis ne verrons plus votre profil.\nNous espérons bientôt vous revoir réactiver votre compte!", "Compte désactivé", JOptionPane.DEFAULT_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/logo_SCL_petit.png")));
		}
	}

	private void deconnexion() {
		vue.setVisible(false);
		vue.dispose();
		vue = new Vue_Accueil(this);
	}

	private void connexion() {
		String mail = ((Vue_Accueil) vue).getMail();
		String motDePasse = ((Vue_Accueil) vue).getMotDePasse();
		
		switch(modeleUtilisateur.connexion(mail, motDePasse)) {
		case "UTILISATEUR":
			vue.setVisible(false);
			vue.dispose();
			vue = new Vue_Utilisateur(this, modeleUtilisateur);
			break;
		case "ADMIN":
			vue.setVisible(false);
			vue.dispose();
			vue = new Vue_Admin(this);
			break;
		case "ERREUR CONNEXION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la tentative de connexion. Le compte est peut-�tre inexistant.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR ACTIF":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte est d�sactiv�. Veuillez le r�activer avant de vous connecter.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR BLOQUE":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte a �t� bloqu� par l'administrateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

	private void creerCompte() {
		String mail = ((Vue_Accueil) vue).getMail();
		String motDePasse = ((Vue_Accueil) vue).getMotDePasse();
		
		switch(Modele_Utilisateur.creerCompte(mail, motDePasse)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien �t� cr��. Vous pouvez d�s � pr�sent vous connecter avec ce compte.", "Cr�ation de compte r�ussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR CREATION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la cr�ation de compte.\nL'adresse �lectronique est peut-�tre d�j� utilis�e.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MAIL":
			JOptionPane.showMessageDialog(new JDialog(),"Adresse �lectronique non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe non valide. Le mot de passe doit contenir au moins 7 caract�res.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}
	
	private void reactiverCompte() {
		String login = ((Vue_Accueil) vue).getMail();
		String motDePasse =  ((Vue_Accueil) vue).getMotDePasse();
		
		switch(Modele_Utilisateur.reactiverCompte(login, motDePasse)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien �t� r�activ�. Vous pouvez d�s � pr�sent vous connecter avec ce compte.", "R�activation de compte r�ussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR REACTIVATION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la r�activation du compte.\nLe compte est peut-�tre inexistant.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR ACTIF":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte est toujours actif.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR BLOQUE":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte a �t� bloqu� par l'administrateur et ne peut �tre r�activ�.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	private void miseAJour() {
		// r�cup�re les valeurs de la vue et met � jour le modele
		modeleUtilisateur.setNom(((Vue_Utilisateur) vue).getNom());
		modeleUtilisateur.setPrenom(((Vue_Utilisateur) vue).getPrenom());
		modeleUtilisateur.setAge(((Vue_Utilisateur) vue).getAge());
		modeleUtilisateur.setSexe(((Vue_Utilisateur) vue).getSexe());
					
		((Vue_Utilisateur) vue).terminer();  // remet la vue par d�fault
		
		switch(modeleUtilisateur.miseAJour()) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le profil a �t� modifi� avec succ�s.", "Modification du profil", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR UPDATE":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de mettre � jour le profil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;	
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}
	
	private void parcourirImage() {
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images", "jpeg", "png", "jpg", "gif", "bmp");
        fc.setFileFilter(filtre);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setAccessory(new ImagePreview(fc));
        
        int returnVal = fc.showDialog(fc, "Modifier la photo de profil");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	String fichier = fc.getSelectedFile().getAbsolutePath();
        	
        	switch(modeleUtilisateur.changerPhoto(fichier)) {
        	case "SUCCES":
        		((Vue_Utilisateur) vue).redessinerPhoto();
        		break;
        	case "ERREUR TAILLE":
        		JOptionPane.showMessageDialog(new JDialog(),"La taille du fichier ne doit pas exc�der 500kb.", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR FICHIER":
        		JOptionPane.showMessageDialog(new JDialog(),"Impossible de charger le fichier " + fc.getSelectedFile().getName() + ".", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR ENVOI":
        		JOptionPane.showMessageDialog(new JDialog(),"L'image n'a pas pu �tre envoy�e.", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR SERVEUR":
    			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
    			deconnexion();
    			break;
        	}
        }
	}
	
}
