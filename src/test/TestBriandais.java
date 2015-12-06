package test;

import java.util.List;

import tools.Chrono;
import tools.Tools;
import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class TestBriandais {

	public static void main(String[] args) {

		Chrono chrono = new Chrono();

		/***************** Construction par ajout simple ********************/
		chrono.start();
		System.out.println("Construction Simple: ");
		ArbreBriandais arbre = Tools.constructionAjoutSimple();
		chrono.stop();

		/**************** Construction Parallele ****************************/
		/*
		 * chrono.start(); ArbreBriandais arbre = constuctionParallele();
		 * System.out.println("\nConstruction Parallele: "); chrono.stop();
		 */

		/***************** Recherche ****************************************/
		long startTime = System.nanoTime();
		System.out.println("\nRecherche: ");
		System.out.println(MethodesArbreBriandais.recherche(arbre, "wagtail"));
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time : " + elapsedTime + " ns");

		/**************** Comptage mots *************************************/
		chrono.start();
		System.out.println("\nComptage mots: ");
		System.out.println(MethodesArbreBriandais.comptageMots(arbre));
		chrono.stop();

		/**************** Comptage Nil **************************************/
		chrono.start();
		System.out.println("\nComptage Nil: ");
		System.out.println(MethodesArbreBriandais.comptageNil(arbre));
		chrono.stop();

		/**************** Hauteur *******************************************/
		System.out.println("\nHauteur: ");
		startTime = System.nanoTime();
		System.out.println(MethodesArbreBriandais.hauteur(arbre));
		stopTime = System.nanoTime();
		elapsedTime = stopTime - startTime;
		System.out.println("Time : " + elapsedTime + " ns");

		/**************** Profondeur moyenne ********************************/
		System.out.println("\nProfondeur moyenne: ");
		chrono.start();
		System.out.println(MethodesArbreBriandais.profondeurMoyenne(arbre));
		chrono.stop();

		/**************** Briandais vers Trie Hybride **********************/
		System.out.println("\nBriandais vers Trie: ");
		chrono.start();
		MethodesArbreBriandais.briandaisVersTrie(arbre);
		chrono.stop();

		/**************** Construire exemple de base ************************/
		System.out.println("\nConstruire exemple de base: ");
		List<String> list = Tools.getListOfString("exemple_base");
		startTime = System.nanoTime();
		stopTime = System.nanoTime();
		ArbreBriandais arbreCEB = null;
		for (String mot : list)
			arbreCEB = MethodesArbreBriandais.insertion(arbreCEB, mot);
		elapsedTime = stopTime - startTime;
		System.out.println("Time : " + elapsedTime + " ns");
		/**************** Decommenter ces lignes pour generer un visuel *****/
		/*
		 * Tools.fileDotBriandais(arbreCEB, "Briandais");
		 * Tools.commandDot("Briandais");
		 */
	}

}
