package Tries;

public class MethodesTrieHybride {

	/************ Texte a imprime ************/
	private static String texte = Tools.LireFichier("exemple_base");

	public static TrieHybride construireTrie() {
		int i = 0;
		TrieHybride t = new TrieHybride();
		String[] tab_mots = Tools.tab_word(texte);

		while (i < tab_mots.length) {
			t = ajoutMot(tab_mots[i], t);
			i++;
		}
		return t;
	}

	public static TrieHybride ajoutMot(String mot, TrieHybride t) {
		System.out.println("jdzk");
		if (t.estArbreVide()) {
			int cpt = 0;
			while (cpt != mot.length()) {
				t.setVal(mot.charAt(cpt));
				if (cpt == mot.length() - 1)
					t.setArret();
				t = t.getEq(); // On continue d'inserer le reste du mot juste en
								// dessous
				t = new TrieHybride();
				System.out.println(t.getVal());
				cpt++;
			}
			return t;
		}
		if (mot.charAt(0) < t.getVal()) {
			t.setInf(ajoutMot(mot, t.getInf()));
		} else {
			if (mot.charAt(0) > t.getVal()) {
				t.setSup(ajoutMot(mot, t.getSup()));
			} else {
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
