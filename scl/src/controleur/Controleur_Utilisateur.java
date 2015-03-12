package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import vue.ImageFileView;
import vue.ImageFilter;
import vue.ImagePreview;

import modele.Modele_Utilisateur;
import vue.Vue_Accueil;
import vue.Vue_Admin;
import vue.Vue_Ami;
import vue.Vue_Charger_Image;
import vue.Vue_Liste_Amis;
import vue.Vue_Liste_Jaime;
import vue.Vue_Message;
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
	public static final String ACTION_LISTER_MESSAGES = "LISTE DE MESSAGES";
	public static final String ACTION_AJOUTER_IMAGE = "AJOUTER IMAGE";
	public static final String ACTION_ANNULER_AJOUTER_IMAGE = "ANNULER AJOUTER IMAGE";
	
	public static final String ACTION_RECHERCHER_AMIS = "RECHERCHER AMIS";
	public static final String ACTION_DEMANDER_AMI = "DEMANDER EN AMI";
	public static final String ACTION_PAS_POUVOIR_VOIR_PROFIL = "PAS POUVOIR VOIR LE PROFIL, LE DEMANDER";
	public static final String ACTION_PROFIL_D_UN_AMI = "VOIR PROFIL D'UN AMI";
	public static final String ACTION_PROFIL_PERSONNEL = "VOIR SON PROFIL";
	public static final String ACTION_SUPPRIMER_AMI = "SUPPRIMER DE LA LISTE D'AMIS";
	public static final String ACTION_ENVOYER_MESSAGE = "ENVOYER_MESSAGE";
	
	////////////////ADMIN////////////////
//	public static final String ACTION_LISTE_SIGNALEMENTS = "LISTE DES SIGNALEMENTS";

	

	
	Controleur_Mur controleurMur;
	private Modele_Utilisateur modeleUtilisateur;
	private JFrame vue;
	private JFrame vue2;
	private JFrame vue3;
	private JFrame vue5;
	private JFrame vueAmi;
	private JFrame vueM;
	private JFrame vueS;
	private JFileChooser fc;
//////////////////////////////	
	public Controleur_Utilisateur() {
		modeleUtilisateur = new Modele_Utilisateur();
		vue = new Vue_Accueil(this);
	}

	 
	
	public void actionPerformed(ActionEvent e) {
		UIManager.put("OptionPane.background",new ColorUIResource(Vue_Utilisateur.marron));
		UIManager.put("Panel.background",new ColorUIResource(Vue_Utilisateur.marron));
		switch(e.getActionCommand()) {
		case ACTION_CONNECTION:
			connection();
			break;
//juste pour visualiser admin			
		case ACTION_ADMIN:
			coadmin();			//provisoir
			break;
		case ACTION_PROFIL_D_UN_AMI:
			vue.setVisible(false);
			vue.dispose();
			if (vueAmi!= null){
				vueAmi.setVisible(false);
			}
			vueAmi = new Vue_Ami(this, 1);		//provisoir
			vue5.setVisible(false);
			break;
		case ACTION_PROFIL_PERSONNEL:
			if (vueAmi != null){
				vueAmi.setVisible(false);
			}
			if (vue != null){
				vue.setVisible(false);
			}
			vue = new Vue_Utilisateur(this);
		case ACTION_VOIR_MUR:
			voirMur();
			break;
		case ACTION_LISTER_AMIS:
			listeAmis();
			break;
		case ACTION_LISTER_MESSAGES:
			listeMessages();
			break;
		case ACTION_AJOUTER_IMAGE:
			ajouterImage();
			break;
	case ACTION_RECHERCHER_AMIS:
			listeRecherche();
			break;
		case ACTION_DEMANDER_AMI:
			JOptionPane.showMessageDialog(new JDialog(),"                                          Votre demande a bien été reçu!\nSi d'ici un mois vous n'avez toujours pas de réponse, vous pourrez refaire la demande =)", "Demande ajoutée!", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
			break;
		case ACTION_SUPPRIMER_AMI:
			Object[] options2 = {"Oui", "Non"};
			int n2 = JOptionPane.showOptionDialog(new JDialog(), "Êtes-vous sûrs de vouloir supprimer cette personne de votre liste d'amis?", "Supprimer de la liste d'amis",
					JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),
				    options2, options2[0]);
			if (n2 == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(new JDialog(), "Cet utilisateur ne fait plus parti de vos amis.", "Ami supprimé de votre liste", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
			}
			break;
		case ACTION_ENVOYER_MESSAGE:
			JOptionPane.showMessageDialog(new JDialog(),"        Votre message est envoyé.", "Message", JOptionPane.DEFAULT_OPTION);
			break;
		case ACTION_PAS_POUVOIR_VOIR_PROFIL:
			
			//**** changer la police du texte!!
			Object[] options = {"Allez, soyons fou!",
                    "Non, j'ai juste fait une mauvaise manip'"};
			JDialog dialogue = new JDialog();
				int n = JOptionPane.showOptionDialog(dialogue,
				    "Vous ne pouvez pas accéder au profil d'un Utilisateur si vous n'êtes pas ami(e) avec lui.\n                                           Voulez vous le demander en ami?",
				    "Vous n'êtes pas ami(e)s",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")),     //do not use a custom Icon
				    options,  //the titles of buttons
				    options[0]); //default button title
			if (n == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(dialogue, "                            Votre demande a bien été prise en compte.\nNous vous conseillons d'attendre au moins un mois avant de refaire la demande.", "Demande ajoutée!", JOptionPane.NO_OPTION, new ImageIcon(getClass().getClassLoader().getResource("images/notifications.png")));
			}
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
//////ADMIN//////
		
		}
	}

	private void listeAmis() {
		vue2 = new Vue_Liste_Amis();
		vue2.setVisible(true);
	}
	
	private void listeMessages() {
		vueM = new Vue_Message(new Controleur_Message());
		vueM.setVisible(true);
	}
	
	private void ajouterImage() {
		//Set up the file chooser.
        if (fc == null) {
            fc = new JFileChooser();

	    //Add a custom file filter and disable the default
	    //(Accept All) file filter.
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);

	    //Add custom icons for file types.
            fc.setFileView(new ImageFileView());

	    //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }
        
        //Show it.
        int returnVal = fc.showDialog(Vue_Utilisateur.fc, "Ajouter une Image");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
       // 	File file = fc.getSelectedFile();
            
        // *****************je pense qu'à ce moment là il faudra rajouté l'image dans la BD
            
        } else {
        }
 
        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    	
		
	}

	private void listeRecherche() {
		vue5 = new Vue_Recherche_Nouvel_Ami(this);
		vue5.setVisible(true);
	}
	
	private void voirMur() {
		new Vue_Mur(controleurMur);
		
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
