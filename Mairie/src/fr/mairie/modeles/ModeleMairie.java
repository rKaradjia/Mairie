package fr.mairie.modeles;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

//import com.mysql.jdbc.PreparedStatement;  annuler vers 8 mysql java connector
import java.sql.PreparedStatement;


import fr.mairie.controleurs.AuthentificationControleur;
import fr.mairie.entites.Activites;
import fr.mairie.entites.Menus;
import fr.mairie.entites.Reservations;
import fr.mairie.entites.SanteAssoc;
import fr.mairie.technique.ConnexionBD;
import fr.mairie.technique.ConnexionException;
import fr.mairie.vues.AjoutActivite;
import fr.mairie.vues.Authentification;
import fr.mairie.vues.Mairie;



public class ModeleMairie {
	//Cette variable sert à stockée le login pour une vérification des droits 
	private static String lg;
	
	
	private static ModeleMairie modele = null ;
	
//	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>() ;
	
	/** Constructeur
	 * 
	 */
	public ModeleMairie(){
		super() ;
		
		
	}
	
	// Implémentation du DP Singleton (méthode)
	
	/*public static ModeleFoody getModele(){
		if( modele == null ){
			modele = new ModeleFoody() ;
		}
		return modele ;
	}*/
	
	//Se Connecter a la base
	public static boolean seConnecter(String TfLogin,String Pfmdp,Authentification vue) throws ConnexionException,SQLException{
		
		Connection connexion = ConnexionBD.getConnexion();
			
		
		String sql="select nom,prenom from utilisateur where login='"+TfLogin+"' && mdp='"+Pfmdp+"'";
		
		PreparedStatement requetePreparee=(PreparedStatement) connexion.prepareStatement(sql);	
		
		ResultSet resultat = requetePreparee.executeQuery();
			
		boolean connexionOk;
			if(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
				
					setLg(TfLogin);
					JOptionPane connexionrs=new JOptionPane();
					connexionrs.showMessageDialog(connexionrs,"Cliquez pour continuer","Connexion Réussie ", 1);
					connexionrs.setVisible(false);
					vue.setVisible(false);
			//		vue2.creerBarreAuthentifie();
					connexionOk=true;
				
			}else{
				JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Identifiant ou mot de passe incorect","Veuillez réesayé",0);
			
				
					
					/*ControleurAuthentification controleur=new ControleurAuthentification(vue);
					controleur.reinitialiser();*/
					fails.setVisible(false);
					vue.initialiser();
					connexionOk=false;
				
			}
			requetePreparee.close();
			return connexionOk;
			
		  }	
		

	public static List<Activites> getUneActivites(String activite) {
		System.out.println("ModeleMairie::getUneActiviteS(String activite) " + activite);
		List<Activites> typeactivite = new ArrayList<Activites>();
		ResultSet resultat = null;
		Connection connection = null;
		PreparedStatement requetePreparee = null;
		
		try {
			
			try {
				connection = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select nomAct,date,lieu,responsable,nbParticipants from "+ activite +" order by date DESC";
			
			System.out.println("::getUneActiviteS(String activite)  request --> " + sql );
			
			requetePreparee = connection.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					Activites uneAct = new Activites();
					uneAct.setNom(resultat.getString("nomAct"));
					uneAct.setDate(resultat.getDate("date"));
					uneAct.setLieu(resultat.getString("lieu"));
					uneAct.setResponsable(resultat.getString("responsable"));
					uneAct.setNbParticipants(resultat.getInt("nbParticipants"));
				    
				    typeactivite.add(uneAct);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return typeactivite;
		
	}

	public static List<SanteAssoc> getTypeSanteAssoc(String type) {
		System.out.println("ModeleMairie::getTypeSanteAssoc(String type) " + type);
		List<SanteAssoc> typeactivite = new ArrayList<SanteAssoc>();
		ResultSet resultat = null;
		Connection connection = null;
		PreparedStatement requetePreparee = null;
		
		try {
			
			try {
				connection = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select identiteResp,adresse,commentaire from santeAssoc where type='"+type+"'";
			
			System.out.println("::getTypeSanteAssoc(String type)   request --> " + sql );
			
			requetePreparee = connection.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					SanteAssoc uneligne = new SanteAssoc();
					uneligne.setIdentiteResp(resultat.getString("identiteResp"));
					uneligne.setAdresse(resultat.getString("adresse"));
					uneligne.setCommentaire(resultat.getString("commentaire"));
				    
				    typeactivite.add(uneligne);
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return typeactivite;
	}

	
	public static boolean AjouterActivite(String date,String typeAct, String lieuString, String responsableString,String nbParticipantsString,String nomString) throws ConnexionException,SQLException {
		
		
		boolean droit = verifierDroitsActivite(getLg(),"droitAjoutActivites");//verifit les droits
		boolean insertion;//cette variable retournera le résultat
		
		if (droit != false) {
		
			System.out.println("Droit de l'ajout pour cet utilisateur -->"+ droit  );
		
		
			Integer nbParticipantsInt;
			System.out.println("Nombre de participants " + nbParticipantsString);
			if (nbParticipantsString.length()!=0) {
		     
					nbParticipantsInt=Integer.parseInt(nbParticipantsString);
			}else {
			  
				nbParticipantsInt = null;

			  
			}
		
			System.out.println("ModeleMairie::AjouterActivite ==> Verfication des parametres: " 
					+" Date ->"+date+ " type actvitite -> "+typeAct+" lieu activite -> "+ lieuString 
					+" Responsable activite -> " + responsableString + " nombre participants -> " + nbParticipantsInt 
					+ " Nom activite -> " + nomString);
		
		
			Statement stmt = null;
			Connection connexion = ConnexionBD.getConnexion();		
			stmt = connexion.createStatement();	
			String sql;
		
		
			//Les differents types d'enregistrement selon le cas
			//Exemple de requete inserée manuellement dans la base de données : 
		
			//Insert into jeux (date,lieu,responsable,type,nomAct) values ('2019-04-20', 
			//'Creche des tulipes','Mr Higono','jeux','Jeux de societe : 09h30');
		
			if (nbParticipantsInt==null ) {
			
				//test a effectue insertion manuel dans la base de données
				sql="INSERT INTO "+typeAct+" (date,lieu,responsable,type,nomAct) VALUES ('"+date+"','"+lieuString+"','"
				+responsableString+"','"+typeAct+"','"+nomString+"')";
			}
			else {
			
				sql="INSERT INTO "+typeAct+" (date,lieu,responsable,nbParticipants,type,nomAct) VALUES ('"+date+"','"+lieuString+"','"
						+responsableString+"','"+nbParticipantsInt+"','"+typeAct+"','"+nomString+"')";
			
			}
		
			//A CORRIGER : You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version 
			int resultat = stmt.executeUpdate(sql);
		
			
		
			if(resultat>0){//tant que il y a des rsats a afficher BOOLEAN
			
				
					JOptionPane inserer=new JOptionPane();
					inserer.showMessageDialog(inserer,"Cliquez pour continuer"," Element ajoute ", 1);
					inserer.setVisible(false);
					//vueAjoutActivite.setVisible(false);
					//			vue2.creerBarreAuthentifie();
					insertion=true;
			
			}else{
			
				JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Identifiant ou mot de passe incorect","Echec de l'ajout : remplissez "
						+ "tous les champs si c'est déjà le cas : Erreur Echec de connection de la BDD, contactez l'administrateur"
						+ "admin@admin.fr .",0);
		
			
				
					/*ControleurAuthentification controleur=new ControleurAuthentification(vue);
					controleur.reinitialiser();*/
					fails.setVisible(false);
					//vueAjoutActivite.initialiser();
					insertion=false;
			
			}
			connexion.close();
			
		
		  }	else {
			  
			  JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Vous n'avez pas les autorisations","Echec "
						+ "--> vous n'avez pas les droits pour cette action"
						+ "admin@admin.fr .",0);
				
				insertion=false;
			  
			  
		  }
		
		return insertion;
	}

	public static Integer getIdAct(String dateString, String lieu, String responsable, Integer nbPart, String activite,
			String nom) throws ConnexionException,SQLException {
		
		System.out.println("ModeleMairie::getIdAct()");
		Statement stmt = null;
		Connection connexion = ConnexionBD.getConnexion();		
		stmt = connexion.createStatement();	
		//Exemple de requete SQL : 
		// select service.droitAjoutActivites from service 
		//inner join utilisateur on service.libelle = utilisateur.service 
		//where utilisateur.login='aKaradjia';
		
		
		//Si le nombre de participants est égale à 0 
		//cela veut dire que dans la base de données
		//la valeur est à null
		//d'ou la modification ci-dessous
		String sql;
		
		if (nbPart==0) {
			 //on ne prend pas en compte de participants
			sql="select id from "+activite+" where date='"+dateString+"' and lieu='"+lieu+"' and responsable='"+responsable+"'"
					+" and type='"+activite+"' and nomAct='"+nom+"'";
		}else {
		
		
		    sql="select id from "+activite+" where date='"+dateString+"' and lieu='"+lieu+"' and responsable='"+responsable+"' and"
				+ " nbParticipants="+nbPart+" and type='"+activite+"' and nomAct='"+nom+"'";
		}
		System.out.println("getIdAct()::String SQL --> " + sql);
		
		
		PreparedStatement requetePreparee=(PreparedStatement) connexion.prepareStatement(sql);	
		
		ResultSet resultat = requetePreparee.executeQuery();
			//System.out.println("ModeleMairie::verifierDroits() ResultSet--> " + resultat);
			 int resultInt = 0;
		//on ne souhaite recuperer qu'un seul résultat	
			if (resultat.next()) {
			    resultInt  = resultat.getInt("id");
			    System.out.println("ModeleMairie::getIdAct() resultat --> "+resultInt);
			   
			    
			}	
		
			return resultInt;
	
	}
	
	public static Boolean ModifActivite(Integer id, String dateAInserer, String typeAct, String lieuString, String responsableString, 
			String nbParticipantsString, String nomString) throws ConnexionException,SQLException{
		// TODO Auto-generated method stub
		System.out.println("ModeleMairie::ModifActivite(...)");
		Statement stmt = null;
		Connection connexion = ConnexionBD.getConnexion();		
		stmt = connexion.createStatement();	
		String sql;
				
		Integer nbParticipantsInt;
		System.out.println("Nombre de participants " + nbParticipantsString);
		if (nbParticipantsString.length()!=0) {	     
				nbParticipantsInt=Integer.parseInt(nbParticipantsString);
		}else {		  
			nbParticipantsInt = null;
		}
//UPDATE client SET rue = '49 Rue Ameline', ville = 'Saint-Eustache-la-Forêt', code_postal = '76210'
//WHERE id = 2
	
		if (nbParticipantsInt==null ) {
			
			//test a effectue insertion manuel dans la base de données
			sql="UPDATE "+typeAct+" SET date='" + dateAInserer +"', lieu='"+lieuString+"',responsable='"+responsableString+"',"
					+"',type='"+typeAct+"',nomAct='"+nomString+"' WHERE id="+id;
		}
		else {
		
			sql="UPDATE "+typeAct+" SET date='" + dateAInserer +"', lieu='"+lieuString+"',responsable='"+responsableString+"',"
					+" nbParticipants='"+nbParticipantsInt+"',type='"+typeAct+"',nomAct='"+nomString+"' WHERE id="+id;
		
		}
		int resultat = stmt.executeUpdate(sql);
		System.out.println("ModifActivite Resultat---->" + resultat);
		Boolean modifier;
		
		if(resultat>0){//tant que il y a des rsats a afficher BOOLEAN
			
				modifier=true;
	
		}else{
	
			    modifier=false;
	
		}
		
		return modifier;
	
     
  }
		
	public static List<Reservations> getReservations() {
		System.out.println("ModeleMairie::getReservations()");
		List<Reservations> lesReserv = new ArrayList<Reservations>();
		ResultSet resultat = null;
		Connection connection = null;
		PreparedStatement requetePreparee = null;
		
		try {
			
			try {
				connection = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select nomEleve,Parents,Adresse,solde from listeReserv";
			
			System.out.println("::getReservations()  request --> " + sql );
			
			requetePreparee = connection.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					Reservations uneReserv = new Reservations();
					uneReserv.setNomEleve(resultat.getString("nomEleve"));
					uneReserv.setParents(resultat.getString("Parents"));
					uneReserv.setAdresse(resultat.getString("Adresse"));
					uneReserv.setSolde(resultat.getInt("solde"));					
				    
				    lesReserv.add(uneReserv);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return lesReserv;
	}
	
	

	public static List<Menus> getMenus() {
		System.out.println("ModeleMairie::getMenus()");
		List<Menus> lesMenus = new ArrayList<Menus>();
		ResultSet resultat = null;
		Connection connection = null;
		PreparedStatement requetePreparee = null;
		/*date        | date         | NO   | PRI | NULL    |       |
| menuSemaine | varchar(255) | YES  |     | NULL    |     */
		try {
			
			try {
				connection = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select date,menuSemaine from menus order by date DESC";
			
			System.out.println("::getUneActiviteS(String activite)  request --> " + sql );
			
			requetePreparee = connection.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					Menus unMenu = new Menus();
					unMenu.setDate(resultat.getDate("date"));
					unMenu.setMenuSemaine(resultat.getString("menuSemaine"));
									
				    
				    lesMenus.add(unMenu);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return lesMenus;
	}

		
	

	public static boolean verifierDroitsActivite(String lg, String droit) throws ConnexionException,SQLException{
		
		boolean result = false;
		
		
		Statement stmt = null;
		Connection connexion = ConnexionBD.getConnexion();		
		stmt = connexion.createStatement();	
		//Exemple de requete SQL : 
		// select service.droitAjoutActivites from service 
		//inner join utilisateur on service.libelle = utilisateur.service 
		//where utilisateur.login='aKaradjia';
		String sql="select service." + droit + " from service inner join utilisateur on service.libelle=utilisateur.service where"
				+ " utilisateur.login='"+lg+"'";
		
		PreparedStatement requetePreparee=(PreparedStatement) connexion.prepareStatement(sql);	
		
		ResultSet resultat = requetePreparee.executeQuery();
			System.out.println("ModeleMairie::verifierDroits() ResultSet--> " + resultat);
			
		//on ne souhaite recuperer qu'un seul résultat	
			if (resultat.next()) {
			    int resultInt  = resultat.getInt("service."+droit);
			    System.out.println("ModeleMairie::verifierDroits() resultat --> "+resultInt);
			    if (resultInt==0) {
			    	result=false;
			    }else {
			    	result=true;
			    }
			    
			}	
		
			return result;
	
		
	}
	
	public static boolean getVerifierDroitsScolaire() throws ConnexionException,SQLException {
		System.out.println("ModeleMairie::getVerifierDroitsScolaire()");
		boolean resultat = false ;
		
		Statement stmt = null;
		Connection connexion = ConnexionBD.getConnexion();		
		stmt = connexion.createStatement();	
		String sql="select service from utilisateur where login='"+getLg()+"'";
		System.out.println("getVerifierDroitsScolaire()  SQL Request " + sql );
		PreparedStatement requetePreparee=(PreparedStatement) connexion.prepareStatement(sql);	
		
		ResultSet executerReq = requetePreparee.executeQuery();
			System.out.println("ModeleMairie::verifierDroits() ResultSet--> " + executerReq);
			
		//on ne souhaite recuperer qu'un seul résultat	
			if (executerReq.next()) {
			    String resReq  = executerReq.getString("service");
			    System.out.println("ModeleMairie::verifierDroits() resultat --> "+resReq);
			    if (resReq.equals("Scolaire")) {
			    	System.out.println("ok :)");
			    			resultat=true;
			    }
			    
			}	
		System.out.println("verifierDroits() ----> "+ resultat);
			return resultat;
	}

	
	
	
	//la derniere accolade active se trouve à la fin du fichier

	
/* jusqu'a la fin -> mis en commentaire
	public static List<Visiteur> getVisiteur() {
		List<Visiteur> visiteurs = new ArrayList<Visiteur>() ;
		ResultSet resultat = null;
		Connection connexion = null;
		PreparedStatement requetePreparee = null;
		
		try {
		
			try {
				connexion = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select VIS_MATRICULE,VIS_NOM,VIS_PRENOM,VIS_DATEEMBAUCHE,VIS_VILLE from VISITEUR order by VIS_NOM";
			
			requetePreparee = (PreparedStatement) connexion.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					Visiteur visiteur = new Visiteur();
					visiteur.setMatricule(resultat.getString("VIS_MATRICULE"));
				    visiteur.setNom(resultat.getString("VIS_NOM"));
				    visiteur.setPrenom(resultat.getString("VIS_PRENOM"));
			       visiteur.setDateEmbauche(resultat.getDate("VIS_DATEEMBAUCHE"));
				    visiteur.setVille(resultat.getString("VIS_VILLE"));
				    
				    visiteurs.add(visiteur);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 return visiteurs;
	}
	
	public static List<Praticien> getPraticienConf() {
		List<Praticien> lesPraticiens = new ArrayList<Praticien>() ;
		ResultSet resultat = null ;
		Connection connexion = null;
		PreparedStatement requetePreparee = null;
		
		try {
			try {
				connexion = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				e.printStackTrace();
			}
			String sql ="select VISITEUR.VIS_NOM, PRA_NOM, PRA_PRENOM, RAPPORT_VISITE.RAP_DATE, RAPPORT_VISITE.RAP_CONF from PRATICIEN inner join RAPPORT_VISITE"
					+ " on PRATICIEN.PRA_NUM = RAPPORT_VISITE.PRA_NUM"
					+ " inner join VISITEUR on RAPPORT_VISITE.VIS_MATRICULE = VISITEUR.VIS_MATRICULE";
			
			requetePreparee = (PreparedStatement) connexion.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
				
			while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
				
				String nomVisiteur = resultat.getString("VIS_NOM");
			    String nomPraticien = resultat.getString("PRA_NOM");
			    String prenomPraticien = resultat.getString("PRA_PRENOM");
		       // visiteur.setDateEmbauche(resultat.getDateFr("VIS_DATEEMBAUCHE"));
			    Date rapportDate = resultat.getDate("RAP_DATE");
			    int rapportConf = resultat.getInt("RAP_CONF");
			    
			     lesPraticiens.add(new Praticien(nomVisiteur,nomPraticien,prenomPraticien,rapportDate,rapportConf));
				
				//	return visiteurs;
			}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			return lesPraticiens;		
			
		}

	
	
	
	

	public static List<Praticien> getPraticienNoto() {
		List<Praticien> lesPraticiens = new ArrayList<Praticien>() ;
		ResultSet resultat = null;
		Connection connexion = null;
		PreparedStatement requetePreparee = null;
		
		try {
		
			try {
				connexion = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql="select PRA_NOM,PRA_PRENOM,PRA_COEFNOTORIETE from PRATICIEN order by PRA_COEFNOTORIETE DESC";
			
			requetePreparee = (PreparedStatement) connexion.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
				
			
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
					Praticien praticien = new Praticien();
				    praticien.setNom(resultat.getString("PRA_NOM"));
				    praticien.setPrenom(resultat.getString("PRA_PRENOM"));
			       // visiteur.setDateEmbauche(resultat.getDateFr("VIS_DATEEMBAUCHE"));
				    praticien.setCoefnoto(resultat.getFloat("PRA_COEFNOTORIETE"));
				    
				     lesPraticiens.add(praticien);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 return lesPraticiens;
	}

	public static List<Praticien> getPraticienVisite() {
		List<Praticien> lesPraticiens = new ArrayList<Praticien>() ;
		ResultSet resultat = null;
		Connection connexion = null;
		PreparedStatement requetePreparee = null;
		
		try {
		
			try {
				connexion = ConnexionBD.getConnexion();
			} catch (ConnexionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String sql = "select PRA_NOM,PRA_PRENOM, RAP_DATE from RAPPORT_VISITE "
					+ "as R1 INNER JOIN PRATICIEN ON R1.PRA_NUM=PRATICIEN.PRA_NUM"
					+ " where RAP_DATE = (SELECT MAX(RAP_DATE) "
					+ "FROM RAPPORT_VISITE as R2 WHERE R1.PRA_NUM=R2.PRA_NUM group by R2.PRA_NUM)"; 
			requetePreparee = (PreparedStatement) connexion.prepareStatement(sql);
			
			resultat = requetePreparee.executeQuery();
			
			
				
			System.out.println(resultat);
			
			
				while(resultat.next()){//tant que il y a des rsats a afficher BOOLEAN
		
					
					Praticien praticien = new Praticien();
				    praticien.setNom(resultat.getString("PRA_NOM"));
				    praticien.setPrenom(resultat.getString("PRA_PRENOM"));
			       // visiteur.setDateEmbauche(resultat.getDateFr("VIS_DATEEMBAUCHE"));
				    praticien.setDateVisite(resultat.getDate("RAP_DATE"));
				    
				    
				     lesPraticiens.add(praticien);
					
					//	return visiteurs;
					
				}
				
		    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 return lesPraticiens;
	}

	public static List<RapportVisite> getRapportsVisite(String matricule, String jtDateMois,String jtDateAnnee){
		System.out.println(matricule +"Dans modele");
		System.out.println(jtDateMois +"Dans modele");
		System.out.println(jtDateAnnee +"Dans modele");
        List<RapportVisite> lesRapportVisites = new ArrayList<RapportVisite>() ;
        ResultSet resultat = null;
        Connection connexion = null;
        //Statement st = connexion.createStatement();
        PreparedStatement requetePreparee = null;
       
        try {
            try {
                connexion = ConnexionBD.getConnexion();
            } catch (ConnexionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       
            String sql = "Select RAP_NUM, PRA_NOM, PRA_PRENOM,RAP_DATE,RAP_DATEREDAC from RAPPORT_VISITE "
            		+ "inner join PRATICIEN ON RAPPORT_VISITE.PRA_NUM=PRATICIEN.PRA_NUM where VIS_MATRICULE = ? and RAP_DATE like ?";
            PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);
       
            pst.setString(1,matricule);
            Integer dateMois = Integer.parseInt(jtDateMois);
            Integer dateAnnee = Integer.parseInt(jtDateAnnee);
            
            if(dateMois<10){
            	 
                String dateEntiere = dateAnnee +"-0"+ dateMois + "%";
                System.out.println(dateEntiere);
                
                pst.setString(2, dateEntiere);
            }
            else{
            
            	String dateEntiere = dateAnnee +"-"+ dateMois + "%";
            	System.out.println(dateEntiere);
            	pst.setString(2, dateEntiere);
       
            }
            resultat= pst.executeQuery();
           
            while(resultat.next()){
            	
            	int numRapport = resultat.getInt("RAP_NUM");
				String nomPraticien = resultat.getString("PRA_NOM");
				String prenomPraticien = resultat.getString("PRA_PRENOM");
				
				Date dateVisite = resultat.getDate("RAP_DATE");
				Date dateRedac = resultat.getDate("RAP_DATEREDAC");
				System.out.println("boucle while");
            	//tant que il y a des rsats a afficher BOOLEAN
        		
				
			    
			     lesRapportVisites.add(new RapportVisite(numRapport,nomPraticien, prenomPraticien,dateVisite,dateRedac));
				
				//	return visiteurs;
				
			}
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
            
        
        
           }
		return lesRapportVisites;  
        }
	
	
	
    public static List<RapportVisite> getLeRapport(int numRapport) {
 		System.out.println("recuperation du modele numero"+ numRapport);
         List<RapportVisite> unRapport = new ArrayList<RapportVisite>() ;
        // ResultSet resultat = null;
       //  ResultSet resultat2 = null;RAPPORT_VISITE RAP_VUE
         Connection connexion = null;
         //Statement st = connexion.createStatement();
    //     PreparedStatement requetePreparee = null;
    //     PreparedStatement requetePreparee2 = null;//RAPPORT_VISITE RAP_VUE   BOOLEAN
         try {
             try {
                 connexion = ConnexionBD.getConnexion();
             } catch (ConnexionException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
        
             Statement st =connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,       
						ResultSet.CONCUR_READ_ONLY);
				String req = "SELECT RAP_BILAN FROM RAPPORT_VISITE where RAP_NUM = '"+numRapport+"' ";
				ResultSet rs = st.executeQuery(req);
	    
				while(rs.next()){
	    	   	
					String bilan =rs.getString("RAP_BILAN");

					unRapport.add(new RapportVisite(bilan));
			
				}
 				
				 String req2 = "UPDATE RAPPORT_VISITE SET RAP_VUE = 1 where RAP_NUM = '"+numRapport+"' ";
				 Statement st2 =connexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,       
							ResultSet.CONCUR_READ_ONLY);;
				 
				 
				 
							int rs2 = st.executeUpdate(req2);
					System.out.println(rs2);
            
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();     
            }
 		return unRapport;  
         }
    
    
    */
	
	
	public static String getLg() {
		return lg;
	}

	public static void setLg(String lg) {
		ModeleMairie.lg = lg;
	}

	

	



	
	
	
	
}



