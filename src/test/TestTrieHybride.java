package test;

import tools.Chrono;
import tries.MethodesTrieHybride;
import tries.TrieHybride;


public class TestTrieHybride {

	public static void main(String []args){

		Chrono chrono = new Chrono();


		/******* Construction simple *******/
		chrono.start();
		System.out.println("\nConstruction Simple: ");
		TrieHybride t = MethodesTrieHybride.construireTrie();
		chrono.stop();

		/******* Construction equilibree *******/
		/*chrono.start();
		 System.out.println("\nConstruction Equilibree: ");
		TrieHybride t = MethodesTrieHybride.trieEquilibre();
		chrono.stop();*/

		/******* Recherche Globale ExempleDeBase ********/
		/*long startTime = System.nanoTime();
		ArrayList<String> tab_mots = Tools.getListOfString("exemple_base");
		for(String mot : tab_mots){
			System.out.println("mot : " + mot + "  "+ MethodesTrieHybride.recherche(t, mot));
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("time : "+elapsedTime);
		}*/

		/******* Recherche Shakespeare **************/
		/*long startTime = System.nanoTime();
		System.out.println("\nRecherche: ");
		System.out.println(MethodesTrieHybride.recherche(t, "acquaintance"));
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("time : "+elapsedTime);*/

		/******* CountWords **********************/
		chrono.start();
		System.out.println("\nComptageMots: ");
		System.out.println("Nombre de mots : "+MethodesTrieHybride.comptageMots(t)); 
		chrono.stop();

		/******* ListWords *************************/
		/*chrono.start();
		System.out.println("\nListerMots: ");
		ArrayList<String> l = MethodesTrieHybride.listeMots(t);
		chrono.stop();
		for(String mot : l)
			System.out.println("Le mot : " + mot + " est présent");*/


		/******* CountNil *************************/
		chrono.start();
		System.out.println("\nComptageNbNil: ");
	  	System.out.println("Il y a " + MethodesTrieHybride.comptageNil(t) + " Nils");
		chrono.stop();

		/******* Height *************************/
		chrono.start();
		System.out.println("\nHauteur: ");
		System.out.println("La hauteur est : " + MethodesTrieHybride.hauteur(t));
		chrono.stop();

		/******* DepthAverage *************************/
		chrono.start();
		System.out.println("\nProfondeurMoyenne: ");
		System.out.println("La profondeur moyenne est : " + MethodesTrieHybride.profondeurMoyenne(t));
		chrono.stop();

		/******* Prefixe *************************/
		/*long startTime = System.nanoTime();
		System.out.println("\nComptePrefixe: ");
		System.out.println("Il y a " + MethodesTrieHybride.prefix(t, "d") + " mot(s) le contenant");
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("time : "+elapsedTime);*/

		/******* Suppression *************************/
		/*chrono.start();
		System.out.println("\nSuppression: ");
		MethodesTrieHybride.suppression(t, "acquaintance");
		chrono.stop();*/

		/******* Transformation TrieVersBriandais *************************/
		/*chrono.start();
		System.out.println("\nTransformation: ");
		ArbreBriandais abr = MethodesTrieHybride.trieVersBriandais(t);
		chrono.stop();*/

		//MethodesArbreBriandais.affichageFormate(abr);
		//MethodesArbreBriandais.afficher(abr);
		//System.out.println("Nb mots : " + MethodesArbreBriandais.comptageMots(abr));
		
		/******* Construction ExempleDeBase *******/
		/*chrono.start();
		System.out.println("\nConstruction exempleDeBase: ");
		TrieHybride t = MethodesTrieHybride.construireExempleBase();
		chrono.stop();*/

		/*********** Generation du visuel **************/
		/*Tools.fileDot(t, "TrieHybride");
		Tools.commandDot("TrieHybride");
		System.out.println("END dot");*/

	}

}
