package briandais;

public class MethodesArbreBriandais {

	/*
	 * Construit l'arbre de la Briandais contenant uniquement 'mot'. Retourne le
	 * nouvel arbre.
	 */
	public static ArbreBriandais construire(String mot) {
		// System.out.println("Construire : " + mot);
		if (mot.equals(""))
			return new ArbreBriandais(ArbreBriandais.EPSILON);
		return new ArbreBriandais(null, MethodesArbreBriandais.construire(mot
				.substring(1)), mot.charAt(0));
	}

	/*
	 * Insere dans l'arbre le mot 'mot' s'il n'existe pas (ignore s'il existe
	 * deja). Retourne le nouvel arbre contenant 'mot'.
	 */
	public static ArbreBriandais insertion(ArbreBriandais arbre, String mot) {
		if (recherche(arbre, mot)) {
			System.out.println("Le mot : " + mot + " existe");
			return arbre;
		}

		if (arbre == null) {
			System.out.println("L'arbre est vide. construit : " + mot);
			return construire(mot);
		}

		if (mot.equals("")) {
			System.out.println("Le mot est vide.");
			return new ArbreBriandais(arbre, null, ArbreBriandais.EPSILON);
		}

		char c = mot.charAt(0);
		char content = arbre.getContent();

		if (c < content) {
			System.out.println("< J'insere au debut. content: '" + content
					+ "' c: '" + c + "'");
			ArbreBriandais abr = insertion(arbre.getSuivant(), mot);
			return new ArbreBriandais(arbre, abr.getFils(), abr.getContent());
		} else if (c > content) {
			System.out.println("> J'insere apres. content : '" + content
					+ "' c: " + c + " mot: " + mot);
			ArbreBriandais abr = arbre;
			ArbreBriandais abr2;

			while (abr != null) {
				if ((abr2 = abr.getSuivant()) == null) {
					abr.setSuivant(construire(mot));
					// return new ArbreBriandais(construire(mot), abr.getFils(),
					// abr.getContent());
					return arbre;
				}

				if (abr2.getContent() == c) {
					System.out.println("Entre 2 egale. content : "
							+ abr2.getContent() + " c: " + c);

					insertion(abr2.getFils(), mot.substring(1));
					return arbre;
					// return new ArbreBriandais(abr2.getSuivant(), insertion(
					// abr2.getFils(), mot.substring(1)), abr2.getContent());
				}

				if (abr.getContent() < c && abr2.getContent() > c) {
					System.out.println("Entre deux. abr.C: " + abr.getContent()
							+ " abr2.C : " + abr2.getContent());
					ArbreBriandais tmp = construire(mot);
					// ArbreBriandais tmp2 = new ArbreBriandais(abr2,
					// tmp.getFils(), tmp.getContent());
					abr.setSuivant(tmp);
					tmp.setSuivant(abr2);
					return arbre;
					// return new ArbreBriandais(tmp2, abr.getFils(),
					// abr.getContent());
				}

				abr = abr2;
			}
		} else {
			System.out.println("= J'insere au meme. content: '" + content
					+ "' c: '" + c + "'");
			return new ArbreBriandais(arbre.getSuivant(), insertion(
					arbre.getFils(), mot.substring(1)), arbre.getContent());
		}

		System.out.println("PANIQUE!!! Ce message ne doit pas etre affiche.");
		return null;
	}

	/*
	 * Recherche le caractere 'c' dans les noeuds freres de l'arbre. Si un noeud
	 * a pour contenu 'c' on retourne 0, -1 s'il est plus petit que le tous les
	 * noeuds
	 */
	public static int rechercheDansFreres(ArbreBriandais arbre, char c) {
		if (arbre == null)
			return c;

		char content = arbre.getContent();
		while (arbre != null) {
			if (content == c)
				return 0;
		}

		if (content == c)
			return c;
		return -1;
	}

	/*
	 * Recherche 'mot' dans l'arbre. Retourne true si le mot existe false sinon
	 */
	public static boolean recherche(ArbreBriandais arbre, String mot) {
		if (arbre == null)
			return false;

		if (mot.equals("")) {
			if (arbre.getContent() == ArbreBriandais.EPSILON)
				return true;
			return recherche(arbre.getSuivant(), mot);
		}

		if (arbre.getContent() == mot.charAt(0))
			return recherche(arbre.getFils(), mot.substring(1));
		return recherche(arbre.getSuivant(), mot);

	}

	/*
	 * Suprrime 'mot' de l'arbre s'il existe (l'ignore sinon). Retourne le
	 * nouvel arbre sans 'mot'.
	 */
	public static ArbreBriandais suppression(ArbreBriandais arbre, String mot) {
		return null;
	}

	/*
	 * Affiche tous les mots de l'arbre.
	 */
	public static void afficher(ArbreBriandais arbre) {
		afficheRec(arbre, "");
	}

	private static void afficheRec(ArbreBriandais arbre, String pref) {
		if (arbre == null) {
			// System.out.println();
			return;
		}

		
		System.out.print(arbre.getContent() + " ");
		if (arbre.getContent() == ArbreBriandais.EPSILON) {
			//System.out.println(pref + ",");
			pref = "";
			 System.out.println();
		} else
			pref += arbre.getContent();

		afficheRec(arbre.getFils(), pref);
		afficheRec(arbre.getSuivant(), pref);
	}

	/*
	 * Affiche l'arbre de manière formattée. (Comme la representation sur
	 * papier)
	 */
	public static void afficheFormate(ArbreBriandais arbre) {

	}

}
