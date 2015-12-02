package tries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tools.Tools;
import briandais.ArbreBriandais;

public class MethodesTrieHybride {
	
	public static TrieHybride construireExempleBase() {
		TrieHybride t = new TrieHybride();
		List<String> list = Tools.getListOfString("exemple_base");
		for (String mot : list)
			ajoutMot(mot, t);	
		return t;
	}

	/**** Ajouter mots du texte dans un TrieHybride ****/
	public static TrieHybride construireTrie() {
		TrieHybride t = new TrieHybride();
		File rep = new File("Shakespeare");
		File[] files = rep.listFiles();
		List<String> list;

		for (File file : files) {
			list = Tools.getListOfString(file.getAbsolutePath());
			for (String mot : list)
				ajoutMot(mot, t);
		}

		return t;
	}

	/*** Fonction d'ajout successif dans un TrieHybride ******/
	public static TrieHybride ajoutMot(String mot, TrieHybride t) {

		if (t.estArbreVide()) {
			int cpt = 1;
			t.setVal(mot.charAt(0));
			if (mot.length() == 1) { // Si le mot ne fait qu'une lettre on met
				// le flag arret ici
				t.setArret(true);
				t.setNum();
				return t;
			}
			TrieHybride t2 = t.getEq();
			while (cpt != mot.length()) {
				t2.setVal(mot.charAt(cpt));
				if (cpt == mot.length() - 1) {
					t2.setArret(true);
					t2.setNum();
				} else
					t2 = t2.getEq(); // On continue d'inserer le reste du mot
				// juste en dessous
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
				if (mot.length() == 1) { // On est sur on mot du texte deja
					// insere mais n'etant pas forcement
					// valide (où arret valait false -> on le met a true)
					t.setArret(true);
					t.setNum();
				} else {
					mot = mot.substring(1);
					t.setEq(ajoutMot(mot, t.getEq()));
				}
			}
		}
		return t;
	}

	/***** Fonction de recherche d'un mot dans l'arbre (TrieHybride) ****/
	public static boolean recherche(TrieHybride t, String mot) {

		if (mot.length() == 1) {
			if (t.getVal() == mot.charAt(0)) {
				return (t.getArret() == Tools.EPSILON);
			}
		}

		if (t.getVal() == mot.charAt(0)) {
			mot = mot.substring(1);
			return (recherche(t.getEq(), mot)
					|| recherche(t.getEq().getSup(), mot) || recherche(t
							.getEq().getInf(), mot));
		}

		if (t.getVal() > mot.charAt(0)) {
			if (!t.getInf().estArbreVide())
				return recherche(t.getInf(), mot);
			return false;
		} else {
			if (!t.getSup().estArbreVide())
				return recherche(t.getSup(), mot);
			return false;
		}
	}

	/*****
	 * Fonction permettant de compter le nombre de mots valides dans un
	 * Triehybride
	 ****/
	public static int comptageMots(TrieHybride t) {

		if (t.estArbreVide())
			return 0;

		int cpt = 0;
		if (!t.estArbreVide()) {
			if (t.getArret() == Tools.EPSILON)
				cpt++;

			if (!t.getInf().estArbreVide())
				cpt += comptageMots(t.getInf());

			if (!t.getEq().estArbreVide())
				cpt += comptageMots(t.getEq());

			if (!t.getSup().estArbreVide())
				cpt += comptageMots(t.getSup());
		}
		return cpt;
	}

	/***** Fonction listant les mots du TrieHybride dans l'ordre alphabetique ****/
	public static ArrayList<String> listeMots(TrieHybride t) {
		if (t.estArbreVide())
			return null;
		ArrayList<String> liste = new ArrayList<String>();
		lister(t, liste, "");
		return liste;
	}

	public static void lister(TrieHybride t, ArrayList<String> liste, String mot) {

		String s = mot;
		s += t.getVal();
		String s2 = s;

		if (!t.getInf().estArbreVide()) {
			if (s.length() != 0)
				s = s.substring(0, s.length() - 1);
			lister(t.getInf(), liste, s);
		}

		if (t.getArret() == Tools.EPSILON) {
			liste.add(s2);
		}

		if (!t.getEq().estArbreVide())
			lister(t.getEq(), liste, s2);

		if (!t.getSup().estArbreVide()) {
			if (s2.length() != 0)
				s2 = s2.substring(0, s2.length() - 1);
			lister(t.getSup(), liste, s2);
		}

	}

	/**** Fonction comptant les pointeurs nuls ****/
	public static int comptageNil(TrieHybride t) {

		int cpt = 0;
		if (t.estArbreVide()) {
			return 1;
		}

		if (!t.estArbreVide()) {
			cpt += comptageNil(t.getInf());
			cpt += comptageNil(t.getEq());
			cpt += comptageNil(t.getSup());
		}
		return cpt;
	}

	/***** Fonction donnant la hauteur de l'arbre *****/
	public static int hauteur(TrieHybride t) {

		int cpt = 0;
		if (t.estArbreVide())
			return 0;

		cpt++;
		int tmp = cpt, tmp2 = cpt, tmp3 = cpt;

		if (!t.getInf().estArbreVide())
			tmp += hauteur(t.getInf());

		if (!t.getEq().estArbreVide())
			tmp2 += hauteur(t.getEq());

		if (!t.getSup().estArbreVide())
			tmp3 += hauteur(t.getSup());

		return Math.max(tmp, Math.max(tmp2, tmp3));
	}

	/**** Fonction calculant la profondeur moyenne du TrieHybride ***/
	private static int cpt = 0, niveau = 0;

	public static double profondeurMoyenne(TrieHybride t) {
		if (t.estArbreVide())
			return 0;

		return calculProfondeur(t, 0);
	}

	public static double calculProfondeur(TrieHybride t, int cur_niv) {

		if (t.getEq().estArbreVide() && t.getInf().estArbreVide()
				&& t.getSup().estArbreVide()) {
			cpt++;
			niveau += cur_niv;
		}

		else {
			if (!t.getInf().estArbreVide())
				calculProfondeur(t.getInf(), cur_niv + 1);

			if (!t.getEq().estArbreVide())
				calculProfondeur(t.getEq(), cur_niv + 1);

			if (!t.getSup().estArbreVide())
				calculProfondeur(t.getSup(), cur_niv + 1);
		}

		return ((double) niveau) / cpt;

	}

	/***** Fonction qui compte le nombre de mots prefix de celui passé en argument ***/
	public static int prefix(TrieHybride t, String mot) {

		if (t.estArbreVide())
			return 0;

		if (t.getVal() == mot.charAt(0)) {
			if (mot.length() == 1)
				return comptageMots(t.getEq());
			if (!t.getEq().estArbreVide()) {
				mot = mot.substring(1);
				return prefix(t.getEq(), mot);
			}
		}

		if ((!t.getInf().estArbreVide()) && (t.getVal() > mot.charAt(0))) {
			return prefix(t.getInf(), mot);
		}

		if ((!t.getSup().estArbreVide()) && (t.getVal() < mot.charAt(0))) {
			return prefix(t.getSup(), mot);
		}

		return 0;

	}

	/***** Fonction de suppression d'un mot dans un TrieHybride *******/
	public static TrieHybride suppression(TrieHybride t, String mot) {
		if (t.estArbreVide())
			return t;

		if (!recherche(t, mot))
			return t;

		TrieHybride eq;
		TrieHybride pred;
		TrieHybride next;
		TrieHybride suppr = null;
		/***** Si le mot est prefixe d'au moins un autre ***/
		if (prefix(t, mot) != 0) {

			while (mot.length() > 0) {
				if (mot.charAt(0) < t.getVal()) {
					pred = t.getInf();
					t = pred;
				} else {
					if (mot.charAt(0) > t.getVal()) {
						next = t.getSup();
						t = next;
					} else {
						suppr = t;
						eq = t.getEq();
						t = eq;
						mot = mot.substring(1);
					}
				}
			}
			suppr.setArret(false);
			return t;
		}

		/******* Si n'a pas de mot prefixe ****/
		suppr = t; // noeud a partir duquel on supprime l'arbre
		eq = null;
		pred = null;
		next = null;
		TrieHybride pere_suppr = suppr;
		TrieHybride haut = t;
		TrieHybride from = t;
		TrieHybride save = new TrieHybride();

		int n = 0;
		if (mot.length() > 1)
			n = 1;

		while (mot.length() - n > 0) {

			if (!t.getEq().getInf().estArbreVide()
					|| !t.getEq().getSup().estArbreVide()) {
				haut = t;
			}
			if (mot.charAt(0) < t.getVal()) {
				from = t;
				pred = t.getInf();
				t = pred;
				suppr = t;
			} else {
				if (mot.charAt(0) > t.getVal()) {
					from = t;
					next = t.getSup();
					t = next;
					suppr = t;
				} else {
					if (t.getArret() == Tools.EPSILON
							|| !t.getInf().estArbreVide()
							|| !t.getSup().estArbreVide()) {
						save = t;
						suppr = t.getEq();
						pere_suppr = t;
					}
					eq = t.getEq();
					t = eq;
					mot = mot.substring(1);
				}
			}
		}

		boolean bool = false;
		if (pere_suppr != null && suppr != null)
			if (pere_suppr.getEq().getId() == suppr.getId()
			&& (!pere_suppr.getInf().estArbreVide() || !pere_suppr
					.getSup().estArbreVide()))
				bool = true;

		// Cas ou on retire le mot et n'a pas de pere Eq (on vient d'un Inf ou
		// d'un Sup)
		if ((from.getInf().getId() == pere_suppr.getId()
				|| from.getSup().getId() == pere_suppr.getId() || (n == 0 && pere_suppr
				.getId() == 0))
				&& (pere_suppr.getArret() != Tools.EPSILON || n == 0)) {

			if (pere_suppr.getId() != 0) { // Si on est pas sur la racine

				int id = pere_suppr.getId();
				if (hauteur(pere_suppr.getInf()) < hauteur(pere_suppr.getSup())) {
					if (pere_suppr.getInf().estArbreVide()) {
						if (haut.getSup().getId() == id)
							haut.setSup(haut.getSup().getSup());
						else
							haut.setInf(haut.getSup().getSup());
						return haut;
					}
					TrieHybride fg = pere_suppr.getInf();
					pere_suppr = pere_suppr.getSup();
					while (!pere_suppr.estArbreVide()) {
						pere_suppr = pere_suppr.getInf(); // on ne peut pas
						// aller a droite
						// car
						// les fils sont toujours de cle
						// superieur a la cle de fg
					}
					pere_suppr.setSelf(fg);
					if (haut.getSup().getId() == id)
						haut.setSup(haut.getSup().getSup());
					else
						haut.setInf(haut.getInf().getSup());
					return haut;
				}

				else {
					if (pere_suppr.getSup().estArbreVide()) {
						if (haut.getSup().getId() == id)
							haut.setSup(haut.getSup().getInf());
						else
							haut.setInf(haut.getSup().getInf());
						return haut;
					}
					TrieHybride fd = pere_suppr.getSup();
					pere_suppr = pere_suppr.getInf().getSup();
					while (!pere_suppr.estArbreVide()) {
						pere_suppr = pere_suppr.getSup(); // on ne peut pas
						// aller a droite
						// car
						// les fils sont toujours de cle
						// superieur a la cle de fg
					}
					pere_suppr.setSelf(fd);
					if (haut.getSup().getId() == id)
						haut.setSup(haut.getSup().getSup());
					else
						haut.setInf(haut.getSup().getSup());
					return haut;
				}
			}

			else { // Si on est sur la racine
				if (hauteur(pere_suppr.getInf()) < hauteur(pere_suppr.getSup())) {
					if (pere_suppr.getInf().estArbreVide()) {
						pere_suppr.setSelf(pere_suppr.getSup());
						return pere_suppr;
					}
					TrieHybride fg = pere_suppr.getInf();
					pere_suppr = pere_suppr.getSup();
					while (!pere_suppr.estArbreVide()) {
						pere_suppr = pere_suppr.getInf(); // on ne peut pas
						// aller a droite
						// car
						// les fils sont toujours de cle
						// superieur a la cle de fg
					}
					pere_suppr.setSelf(fg);
					haut.setSelf(haut.getSup());
					return haut;
				}

				else {
					if (haut.getSup().estArbreVide()) {
						haut.setSelf(pere_suppr.getInf());
						return haut;
					}
					TrieHybride fd = pere_suppr.getSup();
					pere_suppr = pere_suppr.getInf();
					while (!pere_suppr.estArbreVide()) {
						pere_suppr = pere_suppr.getSup(); // on ne peut pas
						// aller a droite
						// car
						// les fils sont toujours de cle
						// superieur a la cle de fg
					}
					pere_suppr.setSelf(fd);
					haut.setSelf(haut.getInf());
					return haut;
				}
			}
		}

		while (suppr.getArret() != Tools.EPSILON) {
			eq = suppr.getEq();
			suppr.setArret(false);
			suppr.setVal(Tools.FIN);
			suppr = null;
			suppr = eq;
		}
		suppr.setArret(false);
		suppr.setVal(Tools.FIN);
		suppr = null;

		if (bool && pere_suppr.getArret() != Tools.EPSILON) {

			if (hauteur(save.getInf()) > hauteur(save.getSup())) { // equlibrage
				TrieHybride fd = save.getSup();
				haut.setEq(save.getInf());
				haut = haut.getEq();
				while (!haut.estArbreVide()) {
					haut = haut.getSup(); // on ne peut pas aller a gauche car
					// les fils sont toujours de cle
					// superieur a la cle de fd
				}

				haut.setSelf(fd);
			} else {
				TrieHybride fg = save.getInf();
				haut.setEq(save.getSup());
				haut = haut.getEq();
				while (!haut.estArbreVide()) {
					haut = haut.getInf(); // on ne peut pas aller a droite car
					// les fils sont toujours de cle
					// superieur a la cle de fg
				}
				haut.setSelf(fg);
			}
		}

		return t;
	}

	/****** Fonction de conversion d'un TrieHybride vers un Arbdre de la Briandais *****/
	public static ArbreBriandais trieVersBriandais(TrieHybride t) {
		if (t.estArbreVide())
			return null;

		if (t.getInf().estArbreVide() && t.getEq().estArbreVide()
				&& t.getSup().estArbreVide()) {
			return new ArbreBriandais(null, new ArbreBriandais(Tools.EPSILON),
					t.getVal());
		}

		ArbreBriandais briandais = null;

		if (t.getArret() == Tools.EPSILON && !t.getInf().estArbreVide()) {
			briandais = trieVersBriandais(t.getInf());

			ArbreBriandais noeud = new ArbreBriandais(t.getVal());
			ArbreBriandais eps = new ArbreBriandais(
					trieVersBriandais(t.getEq()), null, Tools.EPSILON);

			noeud.setFils(eps);
			noeud.setSuivant(trieVersBriandais(t.getSup()));

			ArbreBriandais cp = briandais;
			while (cp.getSuivant() != null)
				cp = cp.getSuivant();

			cp.setSuivant(noeud);

			return briandais;
		} else if (t.getArret() == Tools.EPSILON && t.getInf().estArbreVide()) {
			briandais = new ArbreBriandais(t.getVal());
			ArbreBriandais eps = new ArbreBriandais(
					trieVersBriandais(t.getEq()), null, Tools.EPSILON);
			briandais.setFils(eps);
			briandais.setSuivant(trieVersBriandais(t.getSup()));

			return briandais;
		}

		if (!t.getInf().estArbreVide()) {
			briandais = trieVersBriandais(t.getInf());
			ArbreBriandais abrEq = trieVersBriandais(t.getEq());
			ArbreBriandais abrSup = trieVersBriandais(t.getSup());

			ArbreBriandais abr = new ArbreBriandais(abrSup, abrEq, t.getVal());
			ArbreBriandais cp = briandais;
			while (cp != null) {
				if (cp.getSuivant() == null) {
					cp.setSuivant(abr);
					break;
				}
				cp = cp.getSuivant();
			}
			// briandais.setSuivant(abr);
			return briandais;
		} else if (t.getInf().estArbreVide()) {
			briandais = new ArbreBriandais(t.getVal());
			ArbreBriandais abr = trieVersBriandais(t.getEq());
			ArbreBriandais abrSup = trieVersBriandais(t.getSup());
			briandais.setFils(abr);
			briandais.setSuivant(abrSup);
			return briandais;
			// return new ArbreBriandais(abrSup, abr, t.getVal());
		}

		System.out.println("Ce message ne doit pas etre affiche.");
		return null;
	}

	/****** Fonction d'insertion suivie d'un potentiel reequilibrage ****/
	public static TrieHybride triEquilibre() {
		TrieHybride t = new TrieHybride();
		File rep = new File("Shakespeare");
		File []files = rep.listFiles();
		List<String> list;
		for (File file : files) {
			list = Tools.getListOfString(file.getAbsolutePath());
			for (String mot : list) {
				t = ajoutMot(mot, t);
				t = equilibrage(t);
			}
		}

		return t;
	}

	/* Equilibre le TrieHybride si besoin */
	private static TrieHybride equilibrage(TrieHybride t) {

		int h_inf = hauteur(t.getInf());
		int h_sup = hauteur(t.getSup());

		if (Math.abs(h_inf - h_sup) <= 1)
			return t;

		int fils_g_g = 0;
		int fils_g_d = 0;
		int fils_d_g = 0;
		int fils_d_d = 0;

		if (!t.getInf().estArbreVide()) {
			fils_g_g = hauteur(t.getInf().getInf());
			fils_g_d = hauteur(t.getInf().getSup());
		}
		if (!t.getSup().estArbreVide()) {
			fils_d_g = hauteur(t.getSup().getInf());
			fils_d_d = hauteur(t.getSup().getSup());
		}

		if ((h_inf - h_sup) >= 2) {
			if (fils_g_g > fils_g_d)
				return rotationDroite(t);
			else {
				TrieHybride rota_inf = rotationGauche(t.getInf());
				TrieHybride rotation_droite = t;
				rotation_droite.setInf(rota_inf);
				return rotationDroite(rotation_droite);
			}
		}

		else {
			if (h_inf - h_sup <= -2) {
				if (fils_d_d > fils_d_g)
					return rotationGauche(t);
				else {
					TrieHybride rota_sup = rotationDroite(t.getSup());
					TrieHybride rotation_gauche = t;
					rotation_gauche.setSup(rota_sup);
					return rotationGauche(rotation_gauche);
				}
			} else {
				return t;
			}
		}
	}

	private static TrieHybride rotationDroite(TrieHybride t) {
		if (t.estArbreVide())
			return t;
		if (t.getInf().estArbreVide())
			return t;
		TrieHybride inf = t.getInf();
		TrieHybride inf_sup = inf.getSup();
		return new TrieHybride(inf.getVal(), inf.getArret(), inf.getInf(),
				inf.getEq(), new TrieHybride(t.getVal(), t.getArret(), inf_sup,
						t.getEq(), t.getSup()));
	}

	private static TrieHybride rotationGauche(TrieHybride t) {
		if (t.estArbreVide())
			return t;
		if (t.getSup().estArbreVide())
			return t;
		TrieHybride sup = t.getSup();
		TrieHybride sup_inf = sup.getInf();
		return new TrieHybride(sup.getVal(), sup.getArret(), new TrieHybride(
				t.getVal(), t.getArret(), t.getInf(), t.getEq(), sup_inf),
				sup.getEq(), sup.getSup());
	}

}
