//ceci n'est pas une vrai classe mais juste une liste de requètes pour le projet cara libro


//connection database
String myDriver = "org......";
String myUrl = "....";
Class.forName(myDriver);
Connection conn = DriverManager.getConnection(myUrl, "root", "");

//pour avoir date
Calendar calendar = Calendar.getInstance();
java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

//*** faire toutes les requètes sql en l'insérant dans un query


//*** toujours créer le java statement pour lire le résultat de la requète dans java 
Statement st = conn.createStatement();

//*** récupérer le résultat de la requète
ResultSet rs = st.executeQuery(query);

//*** créer un itérateur qui récup un a un les valeurs

//*** fermer le statement (peut être fait juste après resultset)
st.close();




 ////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////




//récupérer un mdp d'un utilisateur

String query = "SELECT mdp FROM utilisateur WHERE id_utilisateur =" +id_utilisateur; //faire le ?

	String mdp = rs.getgString("mdp");


//sélectionner toutes les informations du profil d'un utilisateur

String query = "SELECT* FROM utilisateurs WHERE mail='" +mail"'";

// Utilisateur u = new Utilisateur();  ??
int id_utilisateur = rs.getInt("id_utilisateur");
String mail = rs.getString("mail");
String mdp = rs.getString("mdp");
String nom = rs.getString("nom");
String prenom = rs.getString("prenom");
int age = rs.getInt("age);
char sexe = rs.getChar("sexe");
/**/ photo = 		// regarder sur internet le type pour les images... en blob aussi?
boolean connecte = rs.getBoolean("connecte");


//sélectionner les 20 dernières publications (reviens à sélectionner les 20 dernières dates de publication de la nouvelle table donnée:
   //-> sélection de toutes les dates et heure

String query = "SELECT date_heure FROM publication WHERE id_utilisateur=" +id_utilisateur +" and visible=TRUE";

list<Publication> inbox = new LinkedList<Publication>
while (rs.next()) {
	Publication p = new Publication ();
	p.set_heure = rs.getTime("date_heure");
	inbox.add(p);
}

   //récupération de seulement les 20 dernières lignes "visibles" du tableau

String query = "SELECT* FROM publication WHERE date_heure=" +inbox.size()-20/**/ +" and visible=TRUE";

//refaire des objets publi ou repartir de ceux déjà créés?
while (rs.next()){
	int id_publi = rs.getInt("id_publi");
	int id_utilisateur = rs.getInt("id_utilisateur");
	Time heure = rs.getTime("heure");
	Date date = rs.getDate("date");
	/**/ photo = rs.get/**/("photo");
	String message = rs.getString("message");
	int nbr_like = rs.getInt("nbr_like");	
	int nbr_com = rs.getInt("nbr_com");
}

//récupérer la liste d'amis d'un utilisateur 

String query = "SELECT id_utilisateur_a AS id_amis FROM amities WHERE id_utilisateur_d = " +id_utilisateur +" UNION SELECT id_utilisateur_d AS id_amis FROM amities WHERE id_utilisateur_a =" +id_utilisateur;

list<Amis> inbox = new linkedlist<Amis>;
while (rs.next()) {
	Amis a= new Amis();
	a.id_amis = rs.getInt("id_amis");
	inbox.add(a);
}
//ne pas oublier de récup nom et prénom de chaque utilisateur ensuite

//rechercher l'ami d'un utilisateur

String query = "SELECT* from utilisateurs WHERE id_utilisateur = (SELECT id_utilisateur_e FROM amities WHERE id_utilisateur_e = "+id_utilisateur_e+" and id_utilisateur_a = "recherche") OR (SELECT id_utilisateur_a FROM amities WHERE id_utilisateur_a = "recherche" and id_utilisateur_e = "+id_utilisateur+")";

						//***recréer une liste d'ami?
	int id_utilisateur = rs.getInt("id_utilisateur");
	String mail = rs.getString("mail");
	String mdp = rs.getString("mdp");
	String nom = rs.getString("nom");
	String prenom = rs.getString("prenom");
	int age = rs.getInt("age");
	char sexe = rs.getChar("sexe");
	??? photo = 		// regarder sur internet le type pour les images... en blob aussi?
	boolean connecte = rs.getBoolean("connecte");


//récupérer les commentaires d'une publication

String query = "SELECT id_com, id_utilisateur, date_heure, message, nbr_like FROM commentaires WHERE id_publi =" +id_publi +" and visibilite = true;");

list<Commentaire> inbox = new LinkedList<Commentaire>

while (rs.next()) {
	Commentaire com = new Commentaire();
	com.set_id_com() = rs.getInt("id_com");
	com.set_id_utilisateur() = rs.getInt("id_utilisateur");
	com.set_heure() = rs.getTime("date_heure");
	com.set_message() = rs.getString("message");
	com.set_nbr_like() = rs.getInt("nbr_like");
	inbox.add(com);
}	
	

//récupérer les likes d'une publication, d'un commentaire
	//récup les identifiants
String query = "SELECT id_utilisateur FROM likes WHERE type= " +type +" and id_com_publi = "+id_com_publi;

list<Like> inbox = new LinkedList<Like>

while (rs.next()) {
	Like l = new Like();
	l.set_id_utilisateur() = rs.getInt("id_utilisateur");
	inbox.add(l);
}	
	//********récup les nom et prénom de chaque identifiant********//
while (...){
	String query = "SELECT nom, prenom FROM utilisateurs WHERE id_utilisateur ="+id_utilisateur;

	list<Like> inbox = new LinkedList<Like> //ou <Amis> ou <Commentaire> ...

	while (rs.next()) {
		Like l = new like();
		l.set_nom() = rs.getString("nom");
		l.set_prenom() = rs.getString("prenom");
		inbox.add(l);
	}	
}


//compter les likes 

String query = "SELECT count (id_utilisateur) from likes where type = "+type+" and id_com_publi = " +id_com_publi; 

//***dans objet Publication p??

	p.set_nbr_like() = rs.getInt("nbr_like");


//insérer une publication
	
	//choisir les colonnes à remplir
String query = "INSERT INTO publications (id_utlisateur, date_heure, photo, message, visibilite) VALUES (?,?,?,?,?)";

	//faire l'insertion
PreparedStatement preparedStm = conn.preparedStatement(query);
preparedStm.setString (1, id_utilisateur);
preparedStm.setString (2, startDate je "crois");
preparedStm.setString (3, photo);
preparedStm.setString (4, message);
preparedStm.setString (5, true);


preparedStmt.execute();

conn.close(); 


//insérer un nouvel utilisateur

String query = "INSERT INTO utilisateurs (mail, mdp) VALUES (?,?)";
PreparedStatement preparedStm = con.preparedStatement(query);

preparedStm.setString (1, mail);
preparedStm.setString (2, mdp);
preparedStm.execute();


//insérer un commentaire

	//choisir les colonnes à remplir
String query = "INSERT INTO commentaires (id_publi, id_utlisateur, date_heure, message, visibilite) VALUES (?,?,?,?,?)";

	//faire l'insertion
PreparedStatement preparedStm = conn.preparedStatement(query);
preparedStm.setString (1, id_publi);
preparedStm.setString (2, id_utilisateur);
preparedStm.setString (3, startDate je "crois");
preparedStm.setString (4, message);
preparedStm.setString (5, true);

preparedStmt.execute();

//insérer un like 

	//choisir les colonnes à remplir
String query = "INSERT INTO likes (id_com_publi, id_utlisateur, type) VALUES (?,?,?)";

	//faire l'insertion
PreparedStatement preparedStm = conn.preparedStatement(query);
preparedStm.setString (1, id_com_publi);
preparedStm.setString (2, id_utilisateur);
preparedStm.setString (3, true);
preparedStmt.execute();


//récupérer la liste des messages reçus de l'utilisateur

String query = "SELECT id_message, id_utilisateur_e, date_heure, objet, lu FROM messages WHERE id_utilisateur_r =" +id_utilisateur +" AND visibilite_r = true";

List<Message> inbox = new LinkedList<Message>;

while (rs.next()) {
	Message m = new Message();
	m.set_id_message() = rs.getInt("id_message");
	m.set_id_utilisateur_e() = rs.getInt("id_utilisateur_e");
	m.set_date_heure() = rs.getTime("date_heure");
	m.set_objet() = rs.getString("objet");
	m.set_lu() = rs.getBoolean("lu");
	inbox.add(m);
}

//récupérer la liste des messages envoyés de l'utilisateur

String query = "SELECT id_message, id_utilisateur_r, date_heure, objet FROM messages WHERE id_utilisateur_e =" +id_utilisateur +" AND visibilite_e = true";

List<Message> inbox = new LinkedList<Message>;

while (rs.next()) {
	Message m = new Message();
	m.set_id_message() = rs.getInt("id_message");
	m.set_id_utilisateur_r() = rs.getInt("id_utilisateur_r");
	m.set_date_heure() = rs.getTime("date_heure");
	m.set_objet() = rs.getString("objet");
	m.set_lu() = rs.getBoolean("lu");
	inbox.add(m);
}


//lire un message reçu

String query = "SELECT id_message, id_utilisateur_e, date_heure, objet, message, lu FROM messages WHERE id_message =" +id_message;

while (rs.next()) {
	Message m = new Message();
	m.set_id_message() = rs.getInt("id_message");
	m.set_id_utilisateur_e() = rs.getInt("id_utilisateur_e");
	m.set_date_heure() = rs.getTime("date_heure");
	m.set_objet() = rs.getString("objet");
	m.set_message() = rs.getString("message");
	m.set_lu() = rs.getBoolean("lu");
	inbox.add(m);
}


//=> modifier lu (s'il est lu pour la première fois)

if (m.lu != 1){
	m.lu = 1;
	PreparedStatement ps = conn.prepareStatement("UPDATE messages SET lu = TRUE WHERE id_message = " +id_message);
}
	ps.executeUpdate();
	ps.close();


//écrire un message

	//en amont, mettre le tout dans des variables
nouveau_Message(id_utilisateur_e, id_utilisateur_r, message){
	Message m = new Message();
	m.set_id_utilisateur_e() = id_utilisateur_e;
	m.set_id_utilisateur_r() = id_utilisateur_r;
	m.set_message() = message;

	//choisir les colonnes à remplir
	String query = "INSERT INTO messages (id_utilisateur_e, id_utlisateur_r, date_heure, objet, message, visibilite_e, visibilite_r) VALUES (?,?,?,?,?,?)";

	//faire l'insertion
	PreparedStatement preparedStm = conn.preparedStatement(query);
	preparedStm.setString (1, id_utilisateur_e);
	preparedStm.setString (2, id_utlisateur_r);
	preparedStm.setString (3, startDate je "crois");
	preparedStm.setString (4, objet);
	preparedStm.setString (5, message);
	preparedStm.setString (6, true);
	preparedStmt.execute();
	
	//ajouter dans le inbox
	inbox.add(m);
}

//lire un message envoyé

String query = "SELECT id_message, id_utilisateur_r, date_heure, objet, message, lu FROM messages WHERE id_message =" +id_message;

while (rs.next()) {
	Message m = new Message();
	m.set_id_message() = rs.getInt("id_message");
	m.set_id_utilisateur_r() = rs.getInt("id_utilisateur_r");
	m.set_date_heure() = rs.getTime("date_heure");
	m.set_objet() = rs.getString("objet");
	m.set_message() = rs.getString("message");
	m.set_lu() = rs.getBoolean("lu");
	inbox.add(m);
}


//supprimer message (visibilite_e ou _r = false)

	PreparedStatement ps = conn.prepareStatement("UPDATE messages SET visibilite_e = FALSE WHERE id_message = " +id_message);

	ps.executeUpdate();
	ps.close();

	PreparedStatement ps = conn.prepareStatement("UPDATE messages SET visibilite_r = FALSE WHERE id_message = " +id_message);

	ps.executeUpdate();
	ps.close();



//lister les signalements

String query = "SELECT* FROM signalements";

List<Message> inbox = new LinkedList<Message>;

while (rs.next()) {
	Message m = new Message();
	s.set_id_signalement() = rs.getInt("id_signalement");
	s.set_id_com_publi() = rs.getInt("id_com_publi");
	s.set_id_utilisateur() = rs.getInt("id_utilisateur");
	s.set_date_heure() = rs.getTime("date_heure");
	inbox.add(s);
}



//compter le nombre de signalements

String query = "SELECT count(distinct id_signalement), id_com_publi, type FROM signalements GROUP BY id_com_publi";

compter_signalement = rs.getInt("id_signalement");

//compter le nombre de publications par jour

String query = "SELECT count(distinct id_publi), date FROM publication GROUP BY date"; //à revoir pour la date!!

compter_publi = rs.getInt("id_publi");
