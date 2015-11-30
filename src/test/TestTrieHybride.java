package test;

import java.util.ArrayList;

import tools.Chrono;
import tools.Tools;
import tries.MethodesTrieHybride;
import tries.TrieHybride;
import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class TestTrieHybride {

	public static void main(String []args){

		Chrono chrono = new Chrono();

		chrono.start();
		TrieHybride t = MethodesTrieHybride.construireTrie(); // MethodesTrieHybride.triEquilibre();
		chrono.stop();

		/*ArrayList<String> tab_mots = Tools.getListOfString("exemple_base");
		for(String mot : tab_mots){
			System.out.println("mot : " + mot + "  "+ MethodesTrieHybride.recherche(t, mot));
		}*/

		long startTime = System.nanoTime();

		System.out.println(MethodesTrieHybride.recherche(t, "acquaintance"));
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("time : "+elapsedTime);


		System.out.println("Mots : "+MethodesTrieHybride.comptageMots(t));

		/*ArrayList<String> l = MethodesTrieHybride.listeMots(t);
		for(String mot : l)
			System.out.println(mot);*/

		MethodesTrieHybride.listeMots(t);

		System.out.println(MethodesTrieHybride.hauteur(t));

		System.out.println(MethodesTrieHybride.comptageNil(t));

		System.out.println("Taille : "+MethodesTrieHybride.hauteur(t));
		System.out.println(MethodesTrieHybride.prefix(t, "d"));

		System.out.println(MethodesTrieHybride.profondeurMoyenne(t));

		/*chrono.start();
		MethodesTrieHybride.suppression(t, "acquaintance");
		chrono.stop();*/


		/*ArbreBriandais abr = MethodesTrieHybride.trieVersBriandais(t);
		ArrayList<String> list = Tools.getListOfString("exemple_base");
		ArbreBriandais arbre = null;*/

		/*for (String mot : list) {
			arbre = MethodesArbreBriandais.insertion(arbre, mot);
		}*/

		//MethodesArbreBriandais.affichageFormate(abr);
		//MethodesArbreBriandais.afficher(abr);
		//System.out.println("Nb mots : " + MethodesArbreBriandais.comptageMots(abr));

		Tools.fileDot(t, "TrieHybride");
		//Tools.commandDot("TrieHybride");

		System.out.println("END dot");

		/*Tools.fileDotBriandais(abr, "Briandais");
		Tools.commandDot("Briandais");
		 */

		chrono.start();
		ArbreBriandais briandais = MethodesTrieHybride.trieVersBriandais(t);
		chrono.stop();

		//System.out.println(t.getSup().getInf().getInf().getEq().getInf().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getArret());

	}

}
