package briandais.test;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class BriandaisTest {
	
	public static void main(String []args) {
		ArbreBriandais arbre = MethodesArbreBriandais.construire("yes");
		MethodesArbreBriandais.afficheFormate(arbre);
		
		System.out.println(MethodesArbreBriandais.recherche(arbre, "yesm"));
	}

}
