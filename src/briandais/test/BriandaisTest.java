package briandais.test;

import java.util.ArrayList;
import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;
import briandais.Tools;

public class BriandaisTest {

	public static void main(String[] args) {
		ArrayList<String> list = Tools.getListOfString("exemple_base");

		ArbreBriandais arbre = MethodesArbreBriandais.insertion(null,
				list.get(0));
		for (String mot : list) {
			arbre = MethodesArbreBriandais.insertion(arbre, mot);
		}
		/*
		arbre = MethodesArbreBriandais.insertion(arbre, "hello");
		arbre = MethodesArbreBriandais.insertion(arbre, "hell");
		arbre = MethodesArbreBriandais.insertion(arbre, "dell");
		arbre = MethodesArbreBriandais.insertion(arbre, "ell");
		arbre = MethodesArbreBriandais.insertion(arbre, "el");
		arbre = MethodesArbreBriandais.insertion(arbre, "elle");*/

		MethodesArbreBriandais.afficher(arbre);
		// System.out.println(MethodesArbreBriandais.recherche(arbre, "yesm"));
	}

}
