package briandais.test;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class BriandaisTest {

	public static void main(String[] args) {
		// ArbreBriandais arbre = MethodesArbreBriandais.construire("yes");
		ArbreBriandais arbre = MethodesArbreBriandais.insertion(null, "zello");
		arbre = MethodesArbreBriandais.insertion(arbre, "hello");
		arbre = MethodesArbreBriandais.insertion(arbre, "hell");
		arbre = MethodesArbreBriandais.insertion(arbre, "dell");
		arbre = MethodesArbreBriandais.insertion(arbre, "ell");
		// arbre = MethodesArbreBriandais.insertion(arbre, "el");
		MethodesArbreBriandais.afficher(arbre);
		// System.out.println("A la fin : " + arbre.getContent());
		// System.out.println(MethodesArbreBriandais.recherche(arbre, "yesm"));
	}

}
