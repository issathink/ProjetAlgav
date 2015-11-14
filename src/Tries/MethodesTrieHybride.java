package Tries;

public class MethodesTrieHybride {

	/************ Texte a imprimer ************/
	private static String texte = Tools.LireFichier("exemple_base");

	public static TrieHybride construireTrie() {
		int i = 0;
		TrieHybride t = new TrieHybride();
		String[] tab_mots = Tools.tab_word(texte);
		System.out.println(texte);
		for (int j = 0; j < tab_mots.length; j++) {
			System.out.print(tab_mots[i] + " ");
		}
		System.out.println();

		while (i < 2) {// tab_mots.length){
			ajoutMot(tab_mots[i], t);
			i++;
		}
		return t;
	}

	public static TrieHybride ajoutMot(String mot, TrieHybride t) {
		System.out.println("jdzk ajout : " + mot);
		if (t.estArbreVide()) {
			int cpt = 1;
			t.setVal(mot.charAt(0));
			TrieHybride t2 = t.getEq();
			while (cpt != mot.length()) {
				t2.setVal(mot.charAt(cpt));
				if (cpt == mot.length() - 1)
					t2.setArret();
				t2 = t2.getEq(); // On continue d'inserer le reste du mot juste
									// en dessous
				t2 = new TrieHybride();
				System.out.println(t2.getVal());
				cpt++;
			}
			return t;
		}
		if (mot.charAt(0) < t.getVal()) {
			System.out.println("A gauche");
			t.setInf(ajoutMot(mot, t.getInf()));
		} else {
			if (mot.charAt(0) > t.getVal()) {
				System.out.println("A droite");
				t.setSup(ajoutMot(mot, t.getSup()));
			} else {
				System.out.println("On descend");
				if (mot.length() == 1) // On est sur on mot du texte
					t.setArret();
				else {
					mot = mot.substring(1);
					t.setEq(ajoutMot(mot, t.getEq()));
				}
			}
		}
		return t;
	}

	public static void printTrieHybride(TrieHybride t) {

	}

}
