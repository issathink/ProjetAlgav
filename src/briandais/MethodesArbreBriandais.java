package briandais;

public class MethodesArbreBriandais {

	/*
	 * Construit l'arbre de la Briandais contenant uniquement 'mot'. Retourne le
	 * nouvel arbre.
	 */
	public static ArbreBriandais construire(String mot) {
		if (mot.equals(""))
			return new ArbreBriandais('@');
		return new ArbreBriandais(null, MethodesArbreBriandais.construire(mot
				.substring(1)), mot.charAt(0));
	}

	/*
	 * Insere dans l'arbre le mot 'mot' s'il n'existe pas (ignore s'il existe
	 * deja). Retourne le nouvel arbre contenant 'mot'.
	 */
	public static ArbreBriandais insertion(ArbreBriandais arbre, String mot) {
		if(recherche(arbre, mot))
			return arbre;
		
		if(mot.equals(""))
			return new ArbreBriandais(arbre, null, '@');
		
		if(arbre == null)
			return construire(mot);
		
		char c = mot.charAt(0);
		char content = arbre.getContent();
		
		if(content < c) {
			ArbreBriandais abr = insertion(arbre.getSuivant(), mot);
			return new ArbreBriandais(arbre, abr.getFils(), abr.getContent());
		} else if(content > c) {
			new 
		} else {
			return new ArbreBriandais(arbre.getSuivant(), insertion(arbre, mot.substring(1)), arbre.getContent());
		}
		
		return null;
	}

	/*
	 * Recherche 'mot' dans l'arbre. Retourne true si le mot existe false sinon
	 */
	public static boolean recherche(ArbreBriandais arbre, String mot) {
		if (arbre == null)
			return false;

		if (mot.equals("")) {
			if (arbre.getContent() == '@')
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

	}

	/*
	 * Affiche l'arbre de manière formattée. (Comme la representation sur
	 * papier)
	 */
	public static void afficheFormate(ArbreBriandais arbre) {
		if (arbre == null) {
			System.out.println();
			return;
		}

		System.out.print(arbre.getContent() + " ");
		if (arbre.getContent() == '@')
			System.out.println();

		afficheFormate(arbre.getFils());
		afficheFormate(arbre.getSuivant());

	}

}
