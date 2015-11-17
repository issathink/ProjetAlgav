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
			return construire(mot);
		}

		if (mot.equals("")) {
			ArbreBriandais e = new ArbreBriandais(arbre.getSuivant(),
					arbre.getFils(), arbre.getContent());
			arbre.setContent(ArbreBriandais.EPSILON);
			arbre.setFils(null);
			arbre.setSuivant(e);
			return arbre;
		}

		char c = mot.charAt(0);
		char content = arbre.getContent();

		if (c < content) {
			ArbreBriandais abr = construire(mot.substring(1));
			ArbreBriandais e = new ArbreBriandais(arbre.getSuivant(),
					arbre.getFils(), content);
			arbre.setContent(c);
			arbre.setFils(abr);
			arbre.setSuivant(e);
			return arbre;
		} else if (c > content) {
			ArbreBriandais abr = arbre;
			ArbreBriandais abr2;

			while (abr != null) {
				if ((abr2 = abr.getSuivant()) == null) {
					abr.setSuivant(construire(mot));
					return arbre;
				}

				if (abr2.getContent() == c) {
					insertion(abr2.getFils(), mot.substring(1));
					return arbre;
				}

				if (abr.getContent() < c && abr2.getContent() > c) {
					ArbreBriandais tmp = construire(mot);
					abr.setSuivant(tmp);
					tmp.setSuivant(abr2);
					return arbre;
				}

				abr = abr2;
			}
		} else {
			ArbreBriandais abr = insertion(arbre.getFils(), mot.substring(1));
			arbre.setFils(abr);
			return arbre;
		}

		System.out.println("Ce message ne doit pas etre affiche.");
		return null;
	}

	/*
	 * Compte le nombre de mots de l'arbre.
	 */
	public static int comptageMots(ArbreBriandais arbre) {
		if (arbre == null)
			return 0;

		if (arbre.getContent() == ArbreBriandais.EPSILON)
			return 1 + comptageMots(arbre.getFils())
					+ comptageMots(arbre.getSuivant());

		return comptageMots(arbre.getFils()) + comptageMots(arbre.getSuivant());
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
		afficheRec(arbre, "", 0);
	}

	private static void afficheRec(ArbreBriandais arbre, String pref, int niveau) {
		if (arbre == null) {
			// System.out.println();
			return;
		}

		System.out.print(arbre.getContent() + " ");
		if (arbre.getContent() == ArbreBriandais.EPSILON) {
			System.out.println();
			/*
			 * System.out.println(pref.substring(pref.length() - niveau,
			 * pref.length()));
			 */
		} else
			pref += arbre.getContent();

		// afficheRec(arbre.getFils(), pref, niveau + 1);
		// pref = "";
		// afficheRec(arbre.getSuivant(), pref, niveau);

		afficheRec (arbre.getFils (), pref.substring(0, niveau+1), niveau+1);
		afficheRec (arbre.getSuivant(), pref.substring(0, niveau), niveau);

	}

	/*
	 * Affiche l'arbre de manière formattée. (Comme la representation sur
	 * papier)
	 */
	public static void afficheFormate(ArbreBriandais arbre) {

	}

}
