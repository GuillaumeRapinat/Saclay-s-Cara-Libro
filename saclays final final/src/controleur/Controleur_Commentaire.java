package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import modele.Modele_Commentaire;
import vue.Vue_Commentaire;

public class Controleur_Commentaire implements ActionListener {

	public static final String ACTION_COMMENTER = "COMMENTER";
	public static final String ACTION_VOIR_COMS = "VOIR COMS";
	
	private Vector<Modele_Commentaire> commentaires;
	private Vue_Commentaire vueCommentaire;
	private int id_publi;
	
	public Controleur_Commentaire(int id_publi) {
		this.id_publi = id_publi;
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_COMMENTER:
			Modele_Commentaire.commenter(vueCommentaire.getMessage(), id_publi);
			rafraichirComs();
			break;
		case ACTION_VOIR_COMS:
			vueCommentaire = new Vue_Commentaire(this);
			rafraichirComs();
			break;
		}
	}

	private void rafraichirComs() {
		commentaires = Modele_Commentaire.chargerCommentaires(id_publi);
		vueCommentaire.redessiner(commentaires);
	}

}