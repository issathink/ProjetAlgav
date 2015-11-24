package Tries;

public class TestTrieHybride {

	public static void main(String[] args) {

		TrieHybride t = MethodesTrieHybride.construireTrie();

		/*
		 * String texte = Tools.LireFichier("exemple_base"); String []tab_mots =
		 * Tools.tab_word(texte); for(String mot : tab_mots){
		 * System.out.println("mot : " + mot + "  "+
		 * MethodesTrieHybride.recherche(t, mot)); }
		 */

		// System.out.println(MethodesTrieHybride.comptageMots(t));

		/*
		 * ArrayList<String> l = MethodesTrieHybride.listeMots(t); for(String
		 * mot : l) System.out.println(mot);
		 */

		// System.out.println(MethodesTrieHybride.comptageNil(t));

		// System.out.println(MethodesTrieHybride.hauteur(t));

		// System.out.println(MethodesTrieHybride.prefix(t, "d"));

		// System.out.println(MethodesTrieHybride.profondeurMoyenne(t));

		// MethodesTrieHybride.printTrieHybride(t);

		MethodesTrieHybride.suppression(t, "dactylographie");

		Tools.genererFichierDot(t, "testTH");

		// System.out.println(t.getSup().getInf().getInf().getEq().getInf().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getArret());

	}

}
