package briandais.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;
import briandais.Tools;

public class BriandaisTest {

	public static void main(String[] args) {
		
		ArrayList<String> list = Tools.getListOfString("exemple_base");
		ArbreBriandais arbre = null;
		
		for (String mot : list) {
			arbre = MethodesArbreBriandais.insertion(arbre, mot);
		}

		MethodesArbreBriandais.afficher(arbre);
		
		Set<String> set = new HashSet<String>();
		set.addAll(list);

		System.out.println("Taille : " + list.size() + ", ComptageMots: "
				+ MethodesArbreBriandais.comptageMots(arbre) + ", Set: "
				+ set.size());

		for (String mot : list) {
			System.out.println(mot + " "
					+ MethodesArbreBriandais.recherche(arbre, mot));
		}

		/*
		 * arbre = MethodesArbreBriandais.insertion(arbre, "hello"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "hell"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "dell"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "ell"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "el"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "elle");
		 */

		// MethodesArbreBriandais.afficher(arbre);
		// System.out.println(MethodesArbreBriandais.recherche(arbre, "yesm"));
	}

}
