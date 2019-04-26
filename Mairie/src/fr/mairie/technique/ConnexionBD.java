package fr.mairie.technique;

import java.sql.DriverManager;
import java.util.Calendar;

//import com.mysql.jdbc.Connection; depreciated since java connector 8
import java.sql.Connection; //replace by ...  documentation



public class ConnexionBD {
	 /*
	 1-Dans un premier temps, dans mysql executer les commandes suivantes : 
	    SET time_zone = SYSTEM;
	    SET global time_zone = SYSTEM;	  
	 2-Ensuite choisir le timeZone      
	   Below some server Timezone values:
	 		EST5EDT
	 		CST6CDT
	 		MST7MDT
	 		PST8PDT  			
     3-Enfin le dbUrl : l'ajouter apres le nom de la table : ?serverTimezone=  LE_Time_Zone choisit*/
	private static String dbURl="jdbc:mysql://localhost:3306/mairie?serverTimezone=EST5EDT";
	private static String user = "root";
	private static String password = "azerty";
	
	private static Connection connexion = null;
	
	
	private ConnexionBD() throws ConnexionException{
		
		
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");       //com.mysql.jdbc.Driver   depreciated since java connector 8
			connexion = (Connection) DriverManager.getConnection(dbURl,user,password);
			
			
			
		}
		catch(Exception e){
			
			
			System.out.println("Erreur ! :" + e.getMessage());
			throw new ConnexionException();
			
		}
		
	}
	
	public static Connection getConnexion() throws ConnexionException{
		
		if(connexion == null){
			
			new ConnexionBD();
			
		}
		
		
		return connexion;
		
		
	}
}

