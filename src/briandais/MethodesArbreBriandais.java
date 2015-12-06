package briandais;

import java.util.ArrayList;
import java.util.List;

import tools.Tools;
import tries.MethodesTrieHybride;
import tries.TrieHybride;

public class MethodesArbreBriandais {

	/*
	 * Construit l'arbre de la Briandais contenant uniquement 'mot'. Retourne le
	 * nouvel arbre.
	 */
	public static ArbreBriandais construire(String mot) {
		if (mot.equals(""))
			return new ArbreBriandais(Tools.EPSILON);
		return new ArbreBriandais(null, MethodesArbreBriandais.construire(mot
				.substring(1)), mot.charAt(0));
	}

	/*
	 * Insere dans l'arbre le mot 'mot' s'il n'existe pas (ignore s'il existe
	 * deja). Retourne le nouvel arbre contenant 'mot'.
	 */
	public static ArbreBriandais insertion(ArbreBriandais arbre, String mot) {

		if (arbre == null) {
			return construire(mot);
		}

		/* Si le mot existe deja dans l'arbre. */
		if (mot.equals("") && arbre.getContent() == Tools.EPSILON)
			return arbre;

		if (mot.equals("")) {
			ArbreBriandais e = new ArbreBriandais(arbre.getSuivant(),
					arbre.getFils(), arbre.getContent());
			arbre.setContent(Tools.EPSILON);
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

		return null;
	}

	/*
	 * Compte le nombre de mots de l'arbre.
	 */
	public static int comptageMots(ArbreBriandais arbre) {
		if (arbre == null)
			return 0;

		if (arbre.getContent() == Tools.EPSILON)
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
			if (arbre.getContent() == Tools.EPSILON)
				return true;
			return false;
		}

		if (arbre.getContent() == mot.charAt(0))
			return recherche(arbre.getFils(), mot.substring(1));
		return recherche(arbre.getSuivant(), mot);

	}

	/*
	 * Supprime le caractere de fin de mot si le mot se termine par EPSILON
	 * l'ignore sinon.
	 */
	private static ArbreBriandais suppressionEpsilon(ArbreBriandais arbre,
			String mot) {
		if (arbre == null)
			return null;

		if (mot.equals("") && arbre.getContent() == Tools.EPSILON)
			if (arbre.getSuivant() == null)
				return null;
			else
				return arbre.getSuivant();
		else if (mot.equals("")) // Le mot a supprimer n'existe pas
			return arbre;

		ArbreBriandais abr;
		char c = mot.charAt(0);
		char content = arbre.getContent();

		if (content < c) {
			abr = suppressionEpsilon(arbre.getSuivant(), mot);
			arbre.setSuivant(abr);
			return arbre;
		} else {
			abr = suppressionEpsilon(arbre.getFils(), mot.substring(1));
			arbre.setFils(abr);
			return arbre;
		}
	}

	/*
	 * Suprrime 'mot' de l'arbre s'il existe (l'ignore sinon). Retourne le
	 * nouvel arbre sans 'mot'.
	 */
	public static ArbreBriandais suppression(ArbreBriandais arbre, String mot) {
		int pref = prefixe(arbre, mot);
		if (pref == 0) { // Le mot a supprimer n'existe pas
			return arbre;
		}
		if (pref > 1) { // Le mot est prefixe d'autres mots.
			return suppressionEpsilon(arbre, mot);
		}

		ArbreBriandais abr = arbre;
		int prefChar = prefixe(arbre, mot.charAt(0) + "");

		// Le mot n'est pas prefixe d'autres mots on peut supprimer la branche
		if (prefChar == 1) {
			ArbreBriandais tmp = abr;
			if (arbre.getContent() == mot.charAt(0)) {
				return arbre.getSuivant();
			}

			while (abr.getContent() != mot.charAt(0)) {
				tmp = abr;
				abr = abr.getSuivant();
			}
			tmp.setSuivant(abr.getSuivant());
			return arbre;
		} else {
			while (abr.getContent() != mot.charAt(0))
				abr = abr.getSuivant();

			abr.setFils(suppression(abr.getFils(), mot.substring(1)));
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
		if (arbre.getContent() == Tools.EPSILON)
			System.out.println(pref.substring(0, pref.length() - 1));

		afficheRec(arbre.getFils(), pref.substring(0, niveau + 1), niveau + 1);
		afficheRec(arbre.getSuivant(), pref.substring(0, niveau), niveau);
	}

	/*
	 * Affiche l'arbre de manière formattée. (Comme la representation sur
	 * papier)
	 */
	public static void affichageFormate(ArbreBriandais arbre) {
		afficheFormateRec(arbre, 10);
	}

	private static void afficheFormateRec(ArbreBriandais arbre, int decalage) {
		if (arbre == null) {
			System.out.println("nil");
			return;
		}

		System.out.print(arbre.getContent() + " ");
		for (int i = 0; i < decalage; i++)
			System.out.print("-");

		System.out.print("> ");
		afficheFormateRec(arbre.getFils(), decalage - 2);
		afficheFormateRec(arbre.getSuivant(), decalage);
	}

	/*
	 * Liste les mots du dictionnaire dans l'ordre alphabetique.
	 */
	public static List<String> listeMots(ArbreBriandais arbre) {
		List<String> mots = new ArrayList<String>();
		ajoutDansListe(arbre, mots, "", 0);

		return mots;
	}

	/*
	 * Parcours l'arbre et rajoute les mots de l'arbre dans la liste.
	 */
	private static void ajoutDansListe(ArbreBriandais arbre, List<String> mots,
			String pref, int niveau) {
		if (arbre == null)
			return;

		pref += arbre.getContent();
		if (arbre.getContent() == Tools.EPSILON) {
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
		return comptageNil(arbre.getSuivant()) + comptageNil(arbre.getFils());
	}

	/*
	 * La hauteur du plus long mot de l'arbre.
	 */
	public static int plusLongMot(ArbreBriandais arbre) {
		int h, hMax;

		if (arbre == null)
			return 0;

		ArbreBriandais abr = arbre.getSuivant();

		if (arbre.getContent() == Tools.EPSILON)
			hMax = plusLongMot(arbre.getFils());
		else
			hMax = 1 + plusLongMot(arbre.getFils());

		while (abr != null) {
			if (abr.getContent() == Tools.EPSILON)
				h = plusLongMot(abr.getFils());
			else
				h = 1 + plusLongMot(abr.getFils());
			if (h > hMax)
				hMax = h;
			abr = abr.getSuivant();
		}

		return hMax;
	}

	/*
	 * Hauteur de l'arbre.
	 */
	public static int hauteur(ArbreBriandais arbre) {
		if (arbre == null)
			return 0;

		return 1 + Math.max(hauteur(arbre.getFils()),
				hauteur(arbre.getSuivant()));
	}

	/*
	 * Profondeur moyenne des feuilles de l'arbre.
	 */
	public static int profondeurMoyenne(ArbreBriandais arbre) {
		int soe = 0;
		List<Integer> list = new ArrayList<Integer>();

		feuilleEtNiveau(arbre, list, 0);

		if (list.size() > 0) {
			for (Integer prof : list)
				soe += prof;
			return soe / list.size();
		}
		return 0;
	}

	/*
	 * On considere les feuilles comme etant les mot du dictionnaire donc se
	 * terminant par EPSILON.
	 */
	private static void feuilleEtNiveau(ArbreBriandais arbre,
			List<Integer> list, int niveau) {
		if (arbre == null)
			return;

		char content = arbre.getContent();

		if (content == Tools.EPSILON) {
			list.add(niveau);
			feuilleEtNiveau(arbre.getSuivant(), list, niveau + 1);
			return;
		}

		feuilleEtNiveau(arbre.getFils(), list, niveau + 1);
		feuilleEtNiveau(arbre.getSuivant(), list, niveau + 1);
	}

	/*
	 * Le nombre de mots du dictionnaire dont 'mot' est prefixe.
	 */
	public static int prefixe(ArbreBriandais arbre, String mot) {

		ArbreBriandais abr = getSousArbreDuMot(arbre, mot);
		int nbMots = nombreMots(abr);

		return nbMots;
	}

	/*
	 * Etant donne un arbre et un mot, retourne le sous arbre pointe par mot ou
	 * null si le mot n'existe pas.
	 */
	private static ArbreBriandais getSousArbreDuMot(ArbreBriandais arbre,
			String mot) {
		if (arbre == null)
			return null;

		if (mot.equals(""))
			return arbre;

		char c = mot.charAt(0);
		char content = arbre.getContent();

		if (c > content)
			return getSousArbreDuMot(arbre.getSuivant(), mot);
		else if (c < content)
			return null;
		else
			return getSousArbreDuMot(arbre.getFils(), mot.substring(1));
	}

	/*
	 * Compte le nombre de mot de l'arbre.
	 */
	private static int nombreMots(ArbreBriandais arbre) {
		if (arbre == null)
			return 0;

		if (arbre.getContent() == Tools.EPSILON)
			return 1 + nombreMots(arbre.getSuivant());
		return nombreMots(arbre.getFils()) + nombreMots(arbre.getSuivant());
	}

	/*
	 * Fusionne deux arbres de Briandais.
	 */
	public static ArbreBriandais fusion(ArbreBriandais arbre1,
			ArbreBriandais arbre2) {
		if (arbre1 == null)
			return arbre2;
		if (arbre2 == null)
			return arbre1;

		int profMoy1 = profondeurMoyenne(arbre1);
		int profMoy2 = profondeurMoyenne(arbre2);
		char c1 = arbre1.getContent();
		char c2 = arbre2.getContent();

		if (profMoy1 > profMoy2) {
			if (c1 == c2) {
				arbre1.setFils(fusion(arbre1.getFils(), arbre2.getFils()));
				arbre1.setSuivant(fusion(arbre1.getSuivant(),
						arbre2.getSuivant()));
				return arbre1;
			} else if (c1 < c2) {
				arbre1.setSuivant(fusion(arbre1.getSuivant(), arbre2));
				return arbre1;
			} else {
				ArbreBriandais abr = new ArbreBriandais(arbre1.getSuivant(),
						arbre1.getFils(), arbre1.getContent());
				arbre1.setContent(c2);
				arbre1.setFils(arbre2.getFils());
				arbre1.setSuivant(fusion(arbre2.getSuivant(), abr));
				return arbre1;
			}
		} else {
			if (c2 == c1) {
				arbre2.setFils(fusion(arbre1.getFils(), arbre2.getFils()));
				arbre2.setSuivant(fusion(arbre1.getSuivant(),
						arbre2.getSuivant()));
				return arbre2;
			} else if (c2 < c1) {
				arbre2.setSuivant(fusion(arbre2.getSuivant(), arbre1));
				return arbre2;
			} else {
				ArbreBriandais abr = new ArbreBriandais(arbre2.getSuivant(),
						arbre2.getFils(), arbre2.getContent());
				arbre2.setContent(c1);
				arbre2.setFils(arbre1.getFils());
				arbre2.setSuivant(fusion(arbre1.getSuivant(), abr));
				return arbre2;
			}
		}
	}

	/*
	 * Etant donne un arbre de Briandais, retoure le trie correspondant. Ce trie
	 * est un peu plus equilibre que celui d'une transformation simple.
	 */
	public static TrieHybride briandaisVersHybrideOld(ArbreBriandais arbre) {
		List<String> list = MethodesArbreBriandais.listeMots(arbre);
		TrieHybride trie = new TrieHybride();
		int taille = list.size();

		for (int i = 0; i < taille; i++) {
			int mil = list.size() / 2;
			trie = MethodesTrieHybride.ajoutMot(list.get(mil), trie);
			list.remove(mil);
		}

		return trie;
	}

	/*
	 * Etant donne un arbre de Briandais, retoure le trie correspondant.
	 */
	public static TrieHybride briandaisVersTrie(ArbreBriandais arbre) {

		TrieHybride trie = new TrieHybride();
		TrieHybride tSup = new TrieHybride();
		TrieHybride tInf = new TrieHybride();
		TrieHybride tEq = new TrieHybride();

		if (arbre == null) {
			tEq.setVal(Tools.FIN);
			tSup.setVal(Tools.FIN);
			tInf.setVal(Tools.FIN);
			trie.setVal(Tools.FIN);
			trie.setEq(tEq);
			trie.setInf(tInf);
			trie.setSup(tSup);
			return trie;
		}

		ArbreBriandais fils = arbre.getFils();
		tInf.setVal(Tools.FIN);
		trie.setVal(arbre.getContent());
		trie.setInf(tInf);

		if (fils != null && fils.getContent() == Tools.EPSILON) {
			trie.setNum();
			trie.setArret(true);
			trie.setEq(briandaisVersTrie(fils.getSuivant()));
		} else {
			trie.setEq(briandaisVersTrie(fils));
		}

		trie.setSup(briandaisVersTrie(arbre.getSuivant()));

		return trie;
	}
}
