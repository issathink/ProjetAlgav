package briandais.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;
import briandais.Tools;

public class BriandaisTest {

	public static void main(String[] args) {

		ArrayList<String> list = Tools.getListOfString("exemple_base");
		ArbreBriandais arbre = null;

		/*
		 * for (String mot : list) { arbre =
		 * MethodesArbreBriandais.insertion(arbre, mot); }
		 * 
		 * System.out.println("Mots de l'arbre : " +
		 * MethodesArbreBriandais.comptageMots(arbre));
		 * 
		 * MethodesArbreBriandais.afficher(arbre);
		 */

		/*
		 * Set<String> set = new HashSet<String>(); set.addAll(list);
		 * 
		 * System.out.println("Taille : " + list.size() + ", ComptageMots: " +
		 * MethodesArbreBriandais.comptageMots(arbre) + ", Set: " + set.size());
		 * 
		 * for (String mot : list) { System.out.println(mot + " " +
		 * MethodesArbreBriandais.recherche(arbre, mot)); }
		 */

		// List<String> mots = MethodesArbreBriandais.listeMots(arbre);
		// System.out.println(mots + "\n" + mots.size());

		// System.out.println("hauteur: " +
		// MethodesArbreBriandais.hauteur(arbre));

		// arbre = MethodesArbreBriandais.insertion(arbre, "dac");
		// arbre = MethodesArbreBriandais.insertion(arbre, "a");
		int i = 0;

		for (i = 0; i < list.size() - 33; i++) {
			arbre = MethodesArbreBriandais.insertion(arbre, list.get(i));
		}

		ArbreBriandais arbre2 = null;
		// arbre2 = MethodesArbreBriandais.insertion(arbre2, "da");
		for (int j = i; j < list.size(); j++) {
			arbre2 = MethodesArbreBriandais.insertion(arbre2, list.get(j));
		}

		// System.out.println("comptageNil : " +
		// MethodesArbreBriandais.comptageNil(arbre));

		arbre = MethodesArbreBriandais.fusion(arbre, arbre2);

		// MethodesArbreBriandais.afficher(arbre);
		
		List<String> mots = MethodesArbreBriandais.listeMots(arbre);
		System.out.println(mots + "\n" + mots.size());

		// arbre = MethodesArbreBriandais.suppression(arbre, "da");
		// arbre = MethodesArbreBriandais.suppression(arbre, "dac");

		// System.out.println("###########");
		// System.out.println("da est prefixe de " +
		// MethodesArbreBriandais.prefixe(arbre, "dac") + " mot(s)");

		// System.out.println("Profondeur moyenne : " +
		// MethodesArbreBriandais.profondeurMoyenne(arbre));

	}

}
