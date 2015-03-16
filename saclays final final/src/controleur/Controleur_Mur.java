package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import vue.IdButton;
import vue.ImagePreview;
import vue.Vue_Mur;
import vue.Vue_Utilisateur;
import modele.Modele_Publication;
import modele.Modele_Signalement;
import modele.Modele_Utilisateur;

public class Controleur_Mur implements ActionListener {

	public static final String ACTION_PUBLIER = "PUBLIER";
	public static final String ACTION_AJOUTER_PHOTO = "AJOUTER PHOTO";
	public static final String ACTION_ENLEVER_PHOTO = "ENLEVER PHOTO";
	public static final String ACTION_SIGNALER_PUBLI = "SIGNALER PUBLI";
	//public static final String ACTION_APERCU = "APERCU";
	
	private Vue_Mur vueMur;
	private Vue_Utilisateur vueUtilisateur;
	private Controleur_Utilisateur controleurUtilisateur;
	private String urlPhoto;

	public Controleur_Mur(Vue_Utilisateur vueUtilisateur, Controleur_Utilisateur controleurUtilisateur) {
		this.vueUtilisateur = vueUtilisateur;
		this.controleurUtilisateur = controleurUtilisateur;
	}
	
	public void setVueMur(Vue_Mur vueMur) {
		this.vueMur = vueMur;
		Modele_Publication.chargerPublications(Modele_Utilisateur.getMonId());
		vueMur.redessiner();
	}
	
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_PUBLIER:
			publier();
			break;
		case ACTION_AJOUTER_PHOTO:
			parcourirImage();
			break;
		case ACTION_ENLEVER_PHOTO:
			urlPhoto = null;
			vueUtilisateur.setTitreImage("image telechargee");
			break;
		case ACTION_SIGNALER_PUBLI:
			signaler((IdButton) e.getSource());
			break;
		}
	}

	private void signaler(IdButton button) {
		int id_com_publi = button.getId();
		
		switch(Modele_Signalement.signaler(id_com_publi)) {
		case "SUCCES":
			JOptionPane.showMessageDialog(new JDialog(),"Le signalement a bien ete envoye a l'administration.", "Signalement envoye", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "ERREUR SIGNALER":
			JOptionPane.showMessageDialog(new JDialog(),"Deja signale.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			controleurUtilisateur.deconnexion();
			break;
		}
	}
	
	private void parcourirImage() {
		JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Images", "jpeg", "png", "jpg", "gif", "bmp");
        fc.setFileFilter(filtre);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setAccessory(new ImagePreview(fc));
        
        int returnVal = fc.showDialog(fc, "Insérer une image");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	urlPhoto = fc.getSelectedFile().getAbsolutePath();
        	vueUtilisateur.setTitreImage(fc.getSelectedFile().getName());
        }
	}

	private void publier() {
		String message = vueUtilisateur.getTextePublier();
		
		switch(Modele_Publication.publier(message, urlPhoto)) {
		case "SUCCES":
			Modele_Publication.chargerPublications(Modele_Utilisateur.getMonId());
			vueMur.redessiner();
			break;
		case "ERREUR FICHIER":
    		JOptionPane.showMessageDialog(new JDialog(),"Impossible de charger l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR TAILLE":
    		JOptionPane.showMessageDialog(new JDialog(),"La taille du fichier ne doit pas exceder 500kb.", "Erreur", JOptionPane.ERROR_MESSAGE);
    		break;
		case "ERREUR PUBLIER":
			JOptionPane.showMessageDialog(new JDialog(),"Impossible de publier.", "Erreur", JOptionPane.ERROR_MESSAGE);
			break;
		case "ERREUR SERVEUR":
			JOptionPane.showMessageDialog(new JDialog(),"Perte de connection au serveur Cara Libro. Retour a l'accueil.", "Erreur", JOptionPane.ERROR_MESSAGE);
			controleurUtilisateur.deconnexion();
			break;
		}
	}
	
}
