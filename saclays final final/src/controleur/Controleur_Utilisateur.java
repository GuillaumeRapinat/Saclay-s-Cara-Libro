package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.Modele_Admin;
import modele.Modele_Message;
import modele.Modele_Publication;
import modele.Modele_Utilisateur;
import vue.FriendButton;
import vue.IdButton;
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
	public static final String ACTION_AJOUTER_AMI = "AJOUTER AMI";
	public static final String ACTION_ACCEPTER_AMI = "ACCEPTER AMI";
	public static final String ACTION_REFUSER_AMI = "REFUSER AMI";
	public static final String ACTION_SUPPRIMER_AMI = "SUPPRIMER AMI";
	public static final String ACTION_VOIR_PROFIL = "VOIR PROFIL";
	public static final String ACTION_VOIR_MON_PROFIL = "VOIR MON PROFIL";
	public static final String ACTION_DESACTIVER_COMPTE = "DESACTIVER COMPTE";
	public static final String ACTION_VOIR_AMIS_COMMUNS = "AMI COMMUN";
	public static final String ACTION_VOIR_AMI_COMMUN = "AMI COMMUN";
	public static final String ACTION_ENVOYER_MESSAGE = "ENVOYER UN MESSAGE";
	public static final String ACTION_LISTER_MESSAGES = "LISTE DE MESSAGES";
	private Modele_Utilisateur modeleUtilisateur;

	//Admin
	public static final String ACTION_ADMIN = "ADMIN";
	public static final String ACTION_VOIR_PUBLICATION = "VOIR UNE PUBLICATION";
	public static final String ACTION_BLOQUER_UTILISATEUR = "BLOQUER UTILISATEUR";
	public static final String ACTION_LISTE_COMMENTAIRES_ADMIN = "LISTE COMMENTAIRES D'UNE VUE ADMIN";
	public static final String ACTION_SUPPRIMER_PUBLI = "SUPPRIMER PUBLICATION";

	private JFrame vue;

	private int id_publi;

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
		case ACTION_RECHERCHER:
			rechercher();
			break;
		case ACTION_AJOUTER_AMI:
			demandeAjout((IdButton) e.getSource());  // on envoie la demande
			break;
		case ACTION_ACCEPTER_AMI:
			accepterAjout((FriendButton) e.getSource());
			break;
		case ACTION_REFUSER_AMI:
			refuserAjout((FriendButton) e.getSource());
			break;
		case ACTION_SUPPRIMER_AMI:
			supprimerAmi((FriendButton) e.getSource());
			break;
		case ACTION_CHANGER_PHOTO_PROFIL:
			parcourirImage();
			break;
		case ACTION_LISTER_AMIS:
			listerAmis();
			break;
		case ACTION_VOIR_AMIS_COMMUNS:
			amisCommuns((IdButton) e.getSource());
			break;
		case ACTION_VOIR_PROFIL:
			voirProfil((IdButton) e.getSource());
			break;
		case ACTION_VOIR_MON_PROFIL:
			monProfil();
			break;
		case ACTION_DESACTIVER_COMPTE:
			desactiverCompte();
			break;
/*		case ACTION_ENVOYER_MESSAGE:
			envoyerMessage();
			JOptionPane.showMessageDialog(new JDialog(),"        Votre message est envoyé.", "Message", JOptionPane.DEFAULT_OPTION);
			break;*/
		case ACTION_LISTER_MESSAGES:
			listeMessages();
			break;
		case ACTION_ADMIN:
			coadmin();			//provisoir
			break;
		case ACTION_VOIR_PUBLICATION:
			voirPublication((IdButton) e.getSource());
			break;
		case ACTION_LISTE_COMMENTAIRES_ADMIN:
			listeCommentaireAdmin((IdButton) e.getSource());
			break;
		case ACTION_SUPPRIMER_PUBLI:
			supprimerPublication((FriendButton) e.getSource());
			break;

		}
	}

	public void deconnexion() {
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
/*		case "ADMIN":
			vue.setVisible(false);
			vue.dispose();
			vue = new Vue_Admin(this);
			break;*/
		case "ERREUR CONNEXION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la tentative de connexion. Le compte est peut-�tre inexistant.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR ACTIF":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte est desactive. Veuillez le reactiver avant de vous connecter.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR BLOQUE":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte a ete bloque par l'administrateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien ete cree. Vous pouvez des a present vous connecter avec ce compte.", "Creation de compte reussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR CREATION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la creation de compte.\nL'adresse electronique est peut-etre deja utilisee.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MAIL":
			JOptionPane.showMessageDialog(new JDialog(),"Adresse electronique non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Mot de passe non valide. Le mot de passe doit contenir au moins 7 caracteres.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a bien ete reactive. Vous pouvez des a present vous connecter avec ce compte.", "Reactivation de compte reussie", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR REACTIVATION":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la reactivation du compte.\nLe compte est peut-etre inexistant.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR MDP":
			JOptionPane.showMessageDialog(new JDialog(),"Le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR ACTIF":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte est toujours actif.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR BLOQUE":
			JOptionPane.showMessageDialog(new JDialog(),"Ce compte a ete bloque par l'administrateur et ne peut etre reactive.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	private void desactiverCompte() {
		switch(Modele_Utilisateur.desactiverCompte()) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le compte a ete desactive. Retour � l'accueil.", "Compte desactive", JOptionPane.INFORMATION_MESSAGE);
			deconnexion();
			break;
		case "ERREUR REACTIVER":
			JOptionPane.showMessageDialog(new JDialog(),"Erreur lors de la desactivation du compte.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
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
			JOptionPane.showMessageDialog(new JDialog(),"Le profil a ete modifie avec succes.", "Modification du profil", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR UPDATE":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de mettre a jour le profil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;	
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        		JOptionPane.showMessageDialog(new JDialog(),"La taille du fichier ne doit pas exceder 500kb.", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR FICHIER":
        		JOptionPane.showMessageDialog(new JDialog(),"Impossible de charger le fichier " + fc.getSelectedFile().getName() + ".", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR ENVOI":
        		JOptionPane.showMessageDialog(new JDialog(),"L'image n'a pas pu etre envoyee.", "Erreur", JOptionPane.ERROR_MESSAGE);
        		break;
        	case "ERREUR SERVEUR":
        		JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
    			deconnexion();
    			break;
        	}
        }
	}
	
	private void demandeAjout(IdButton button) {
		int id_utilisateur_a = button.getId();  // on recupere l'id de l'utilisateur que l'on souhaite ajouter
		
		switch(Modele_Utilisateur.demandeAjout(id_utilisateur_a)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"La demande d'ajout a bien ete prise en compte.", "Demande envoyee", JOptionPane.INFORMATION_MESSAGE);
			button.setEnabled(false);
			break;
		case "ERREUR AJOUT":
			JOptionPane.showMessageDialog(new JDialog(),"La demande d'ajout n'a pas pu �tre envoyee.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		case "ERREUR SOIMEME":
			JOptionPane.showMessageDialog(new JDialog(),"Vous ne pouvez pas vous ajouter vous meme.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		}
	}
	
	private void accepterAjout(FriendButton button) {
		int id_utilisateur_d = button.getId();  // on recupere l'id de la personne qui nous a demande en ami
		
		switch(Modele_Utilisateur.accepterAjout(id_utilisateur_d)) {
		case "SUCCES":
			button.getParent().setEnabled(true);
			button.setEnabled(false);
			break;
		case "ERREUR AJOUT":
			JOptionPane.showMessageDialog(new JDialog(),"Echec de l'acceptation en ami.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}
	
	
	
	
	
	private void amisCommuns(IdButton button) {
		int id_utilisateur = button.getId();
		Vector<Modele_Utilisateur> amis = Modele_Utilisateur.amisCommuns(id_utilisateur);
		if (amis.size() > 0) {
			Vue_Recherche vue = new Vue_Recherche(this, amis);
			vue.setTitle("Amis en communs");
		}
		else
			JOptionPane.showMessageDialog(new JDialog(),"Vous n'avez aucun amis en commun avec cette personne.", "Liste d'amis vide", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	

	private void listeCommentaireAdmin(IdButton button) {
		int id_publi = button.getId();
	/*	vueCommentaire = new Vue_Commentaire(this);
		commentaires = Modele_Commentaire.chargerCommentaires(id_publi);
		vueCommentaire.redessiner(commentaires);
		*/
		
	}

	private void supprimerPublication(FriendButton button) {
		int id_publi = button.getId();
		switch(Modele_Admin.supprimerPubli(id_publi)) {
		case "SUCCES":
			button.getParent().setVisible(false);
			break;
		case "ERREUR SUPPRIMER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de supprimer la publication.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}


	
	private void refuserAjout(FriendButton button) {
		int id_utilisateur_d = button.getId();  // recupere l'id de la personne qui nous a demande en ami
		
		switch(Modele_Utilisateur.refuserAjout(id_utilisateur_d)) {
		case "SUCCES":
			Vue_Liste_Amis vueListeAmis = (Vue_Liste_Amis) SwingUtilities.getRoot(button);
			button.getParent().setVisible(false);
			vueListeAmis.pack();
			break;
		case "ERREUR REFUS":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de refuser la demande.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}
	
	private void supprimerAmi(FriendButton button) {
		int id_utilisateur = button.getId();  // recupere l'id de l'ami que l'on veut supprimer
		
		switch(Modele_Utilisateur.supprimerAmi(id_utilisateur)) {
		case "SUCCES":
			Vue_Liste_Amis vueListeAmis = (Vue_Liste_Amis) SwingUtilities.getRoot(button);
			button.getParent().setVisible(false);
			vueListeAmis.pack();
			break;
		case "ERREUR SUPPRIMER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de supprimer la personne.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}
	
	private void rechercher() {
		String recherche = ((Vue_Utilisateur) vue).getRecherche();  // on recup�re la recherche
		Vector<Modele_Utilisateur> resultatRecherche = Modele_Utilisateur.rechercher(recherche);  // on ex�cute la recherche
		if (resultatRecherche.size() > 0) new Vue_Recherche(this, resultatRecherche);  // on affiche le r�sultat de la recherche
		else							  JOptionPane.showMessageDialog(new JDialog(),"Aucun resultat trouve pour '" + recherche + "'.", "Recherche", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void listerAmis() {
		Vector<Modele_Utilisateur> amis = Modele_Utilisateur.listerAmis();
		Vector<Modele_Utilisateur> demandes = Modele_Utilisateur.listerDemandesAjout();
		if (amis.size() > 0 || demandes.size() > 0) new Vue_Liste_Amis(this, demandes, amis);
		else				 						JOptionPane.showMessageDialog(new JDialog(),"Vous n'avez aucun amis et personne ne vous veut... C'est bien triste :(", "Liste d'amis vide", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void voirProfil(IdButton button) {
		int id_utilisateur = button.getId();  // recupere l'id de l'ami dont on veut voir le profil
		
		switch(modeleUtilisateur.chargerProfil(id_utilisateur)) {
		case "SUCCES":
			((Vue_Utilisateur) vue).redessinerTout();
			((Vue_Utilisateur) vue).autreProfil();
			break;
		case "ERREUR CHARGER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de charger le profil.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
	}

	
	private void monProfil() {
		int monId = Modele_Utilisateur.getMonId();
		switch(modeleUtilisateur.chargerProfil(monId)) {
		case "SUCCES":
			((Vue_Utilisateur) vue).redessinerTout();
			((Vue_Utilisateur) vue).revenirProfil();
			break;
		case "ERREUR CHARGER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de charger le profil.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		}
		
	}
	
/*	private void envoyerMessage() {
		int id_utilisateur_e = Modele_Utilisateur.getMonId();  // on recupere l'id de l'utilisateur que l'on souhaite ajouter
		int id_utilisateur_r = ((Modele_Utilisateur) modeleUtilisateur).getId();
		String objet = ((Vue_Utilisateur) vue).getObjet();
		String texteMessage = ((Vue_Utilisateur) vue).getMessage();
		switch(Modele_Message.envoieMessage(objet, texteMessage, id_utilisateur_e, id_utilisateur_r)) {
		case "ENVOI REUSSI":
			JOptionPane.showMessageDialog(new JDialog(),"Le message a bien ete envoye.", "Message envoyee", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR ENVOIE":
			JOptionPane.showMessageDialog(new JDialog(),"Le message n'a pas pu �tre envoyee.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro.", "Erreur", JOptionPane.ERROR_MESSAGE);
			deconnexion();
			break;
		case "MESSAGE VIDE!!":
			JOptionPane.showMessageDialog(new JDialog(),"Vous ne pouvez pas envoyer un message vide.\nCette application ne permet pas de poker!", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		}
		
	}*/
	
	private void listeMessages() {
		Vector<Modele_Message> resultatMessages = Modele_Message.chercherMessages(Modele_Utilisateur.getMonId());
		new Vue_Message(new Controleur_Message(), resultatMessages);
		
	}	
	
	private void coadmin(){
		
		vue.setVisible(false);
		vue.dispose();
		vue = new Vue_Admin(this, Modele_Admin.listerSignalements(), Modele_Admin.listerUtilisateurs());
		
	}
	
	private void voirPublication(IdButton bouton) {
		int id_publi = bouton.getId();
		Vue_Admin.vuePublication(Modele_Admin.chargerPublication(id_publi));
		
		//JFrame fPubli = new JFrame();
		//Modele_Mur publi = new Modele_Mur();
		//publi = chercherPubli(bouton.getId());
		//Vue_Publication.publier(publi);
	}
	

}
