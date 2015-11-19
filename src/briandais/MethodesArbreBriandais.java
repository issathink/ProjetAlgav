package briandais;

import java.util.ArrayList;
import java.util.List;

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
			return false; // recherche(arbre.getSuivant(), mot);
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
		if(arbre == null)
			return null;
		
		if (mot.equals("") && arbre.getContent() == ArbreBriandais.EPSILON)
			if (arbre.getSuivant() == null)
				return null;
			else
				return arbre.getSuivant();
		else if(mot.equals("")) // Le mot a supprimer n'existe pas
			return arbre;
		
		ArbreBriandais abr;
		char c = mot.charAt(0);
		char content = arbre.getContent();

		if (content < c) {
			abr = suppression(arbre.getSuivant(), mot);
			arbre.setSuivant(abr);
			return arbre;
		} else {
			abr = suppression(arbre.getFils(), mot.substring(1));
			arbre.setFils(abr);
			return arbre;
		}

	}

	/*
	 * Affiche tous les mots de l'arbre.
	 */
	public static void afficher(ArbreBriandais arbre) {
		afficheRec(arbre, "", 0);
	}

	private static void afficheRec(ArbreBriandais arbre, String pref, int niveau) {
		if (arbre == null)
			return;

		pref += arbre.getContent();
		if (arbre.getContent() == ArbreBriandais.EPSILON)
			System.out.println(pref.substring(0, pref.length() - 1));

		afficheRec(arbre.getFils(), pref.substring(0, niveau + 1), niveau + 1);
		afficheRec(arbre.getSuivant(), pref.substring(0, niveau), niveau);
	}

	/*
	 * Liste les mots du dictionnaire dans l'ordre alphabetique.
	 */
	public static List<String> listeMots(ArbreBriandais arbre) {
		List<String> mots = new ArrayList<String>();
		ajoutDansListe(arbre, mots, "", 0);

		return mots;
	}

	private static void ajoutDansListe(ArbreBriandais arbre, List<String> mots,
			String pref, int niveau) {
		if (arbre == null)
			return;

		pref += arbre.getContent();
		if (arbre.getContent() == ArbreBriandais.EPSILON) {
			System.out.println(pref);
			mots.add(pref.substring(0, pref.length() - 1));
		}

		ajoutDansListe(arbre.getFils(), mots, pref.substring(0, niveau + 1),
				niveau + 1);
		ajoutDansListe(arbre.getSuivant(), mots, pref.substring(0, niveau),
				niveau);
	}

	/*
	 * Compte le nombre de pointeurs vers null.
	 */
	public static int comptageNil(ArbreBriandais arbre) {
		if (arbre == null)
			return 1;
		return comptageNil(arbre.getFils()) + comptageNil(arbre.getFils());
	}

	/*
	 * La hauteur de l'arbre.
	 */
	public static int hauteur(ArbreBriandais arbre) {
		int h, hMax;

		if (arbre == null)
			return 0;

		ArbreBriandais abr = arbre.getSuivant();

		if (arbre.getContent() == ArbreBriandais.EPSILON)
			hMax = hauteur(arbre.getFils());
		else
			hMax = 1 + hauteur(arbre.getFils());

		while (abr != null) {
			if (abr.getContent() == ArbreBriandais.EPSILON)
				h = hauteur(abr.getFils());
			else
				h = 1 + hauteur(abr.getFils());
			if (h > hMax)
				hMax = h;
			abr = abr.getSuivant();
		}

		return hMax;
	}

	/*
	 * Le nombre de mots du dictionnaire dont 'mot' est prefixe.
	 */
	public static int prefixe(ArbreBriandais arbre, String mot) {
		/*
		 * if (arbre == null) return 0; if(mot.equals("")) return
		 */
		return -1;
	}

	/*
	 * Profondeur moyenne des feuilles de l'arbre.
	 */
	public static int profondeurMoyenne(ArbreBriandais arbre) {
		String result, tab[];
		int soe, nb;

		result = nbFeuilleSomme(arbre, 0, 0);

		tab = result.split(";");
		soe = Integer.parseInt(tab[0]);
		nb = Integer.parseInt(tab[1]);

		if (nb > 0)
			return soe / nb;
		return 0;
	}

	private static String nbFeuilleSomme(ArbreBriandais arbre, int somme, int nb) {

		if (arbre == null)
			return "0;0";

		// if(arbre.getContent() == ArbreBriandais.EPSILON && arbre.getSuivant()
		// == null)
		// return (somme+1) + ";" + ()
		return "0;0";
	}

	/*
	 * Affiche l'arbre de manière formattée. (Comme la representation sur
	 * papier)
	 */
	public static void afficheFormate(ArbreBriandais arbre) {

	}

}
