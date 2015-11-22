package Tries;

import java.util.ArrayList;

public class MethodesTrieHybride {

	/************Texte a imprime************/
	private static String texte = Tools.LireFichier("exemple_base");
	
	
	/****Ajouter mots du texte dans un TrieHybride****/
	public static TrieHybride construireTrie(){
		int i = 0;
		TrieHybride t = new TrieHybride();
		String []tab_mots = Tools.tab_word(texte);

		while(i < tab_mots.length){
			ajoutMot(tab_mots[i], t);
			i++;
		}
		return t;
	}


	/***Fonction d'ajout successif dans un TrieHybride******/
	public static TrieHybride ajoutMot(String mot, TrieHybride t){
		
		if(t.estArbreVide()){
			int cpt = 1;
			t.setVal(mot.charAt(0));
			if(mot.length() == 1){  //Si le mot ne fait qu'une lettre on met le flag arret ici
				t.setArret(true);
			}
			TrieHybride t2 = t.getEq();
			while(cpt != mot.length()){
				t2.setVal(mot.charAt(cpt));
				if(cpt == mot.length() - 1)
					t2.setArret(true);
				t2 = t2.getEq();   //On continue d'inserer le reste du mot juste en dessous
				cpt++;
			}
			return t;
		}
		if(mot.charAt(0) < t.getVal()){
			t.setInf(ajoutMot(mot, t.getInf()));
		}
		else{
			if(mot.charAt(0) > t.getVal()){
				t.setSup(ajoutMot(mot, t.getSup()));
			}
			else{
				if(mot.length() == 1){  //On est sur on mot du texte dejï¿½ inserï¿½ mais n'ï¿½tant pas forcï¿½ment valide (arret = false)
					t.setArret(true);
				}
				else{
					mot = mot.substring(1);
					t.setEq(ajoutMot(mot, t.getEq()));
				}
			}
		}
		return t;
	}



	/*****Fonction d'affichage de l'arbre TrieHybride******/
	public static void printTrieHybride(TrieHybride t){

		if(!t.estArbreVide())
			System.out.println("Je suis "+t.getVal() + " fils gauche "+t.getInf().getVal()+ " fils eq " + t.getEq().getVal() + " fils droit "+t.getSup().getVal());

		if(!t.getInf().estArbreVide())
			printTrieHybride(t.getInf());

		if(!t.getEq().estArbreVide())
			printTrieHybride(t.getEq());

		if(!t.getSup().estArbreVide())
			printTrieHybride(t.getSup());

	}


	/*****Fonction de recherche d'un mot dans l'arbre (TrieHybride)****/
	public static boolean recherche(TrieHybride t, String mot){

		if(mot.length() == 1){
			if(t.getVal() == mot.charAt(0)){
				return (t.getArret());
			}
		}

		if(t.getVal() == mot.charAt(0)){
			mot = mot.substring(1);
			return (recherche(t.getEq(), mot) || recherche(t.getEq().getSup(), mot) || recherche(t.getEq().getInf(), mot));
		}
		if(t.getVal() > mot.charAt(0)){
			if(!t.getInf().estArbreVide())
				return recherche(t.getInf(), mot);
			return false;
		}
		else{
			if(!t.getSup().estArbreVide())
				return recherche(t.getSup(), mot);
			return false;
		}
	}


	/*****Fonction permettant de compter le nombre de mots valides dans un Triehybride****/
	public static int comptageMots(TrieHybride t){

		if(t.estArbreVide())
			return 0;

		int cpt = 0;
		if(!t.estArbreVide()){
			if(t.getArret())
				cpt++;

			if(!t.getInf().estArbreVide())
				cpt += comptageMots(t.getInf());

			if(!t.getEq().estArbreVide())
				cpt += comptageMots(t.getEq());

			if(!t.getSup().estArbreVide())
				cpt += comptageMots(t.getSup());
		}	
		return cpt;
	}


	/*****Fonction listant les mots du TrieHybride dans l'ordre alphabï¿½tique****/
	public static ArrayList<String> listeMots(TrieHybride t){
		if(t.estArbreVide())
			return null;
		ArrayList<String>liste = new ArrayList<String>();
		lister(t, liste, "");
		return liste;
	}
	public static void lister(TrieHybride t, ArrayList<String> liste, String mot){

		String s = mot;
		s += t.getVal();
		String s2 = s;

		if(!t.getInf().estArbreVide()){
			if(s.length() != 0)
				s = s.substring(0, s.length()-1);
			lister(t.getInf(), liste, s);
		}

		if(t.getArret()){
			liste.add(s2);
		}		

		if(!t.getEq().estArbreVide())
			lister(t.getEq(), liste, s2);	

		if(!t.getSup().estArbreVide()){
			if(s2.length() != 0)
				s2 = s2.substring(0, s2.length()-1);
			lister(t.getSup(), liste, s2);
		}

	}



	/****Fonction comptant les pointeurs nuls****/
	public static int comptageNil(TrieHybride t){

		int cpt = 0;
		if(t.estArbreVide()){
			return 1;
		}

		if(!t.estArbreVide()){
			cpt += comptageNil(t.getInf());
			cpt += comptageNil(t.getEq());
			cpt += comptageNil(t.getSup());
		}	
		return cpt;
	}


	/*****Fonction donnant la hauteur de l'arbre*****/
	public static int hauteur(TrieHybride t){
		int cpt = 0;
		if(t.estArbreVide())
			return 0;

		cpt++;
		int tmp = cpt, tmp2 = cpt, tmp3 = cpt;
		if(! t.getInf().estArbreVide())
			tmp += hauteur(t.getInf());

		if(! t.getEq().estArbreVide())
			tmp2 += hauteur(t.getEq());

		if(! t.getSup().estArbreVide())
			tmp3 += hauteur(t.getSup());

		return Math.max(tmp, Math.max(tmp2, tmp3));
	}



	/****Fonction calculant la profondeur moyenne du TrieHybride***/
	private static int cpt = 0, niveau = 0;

	public static int profondeurMoyenne(TrieHybride t){
		if(t.estArbreVide())
			return 0;

		return calculProfondeur(t, 0);
	}
	public static int calculProfondeur(TrieHybride t, int cur_niv){
		cpt++;
		niveau += cur_niv;

		if(!t.getInf().estArbreVide())
			calculProfondeur(t.getInf(), cur_niv+1);

		if(!t.getEq().estArbreVide())
			calculProfondeur(t.getEq(), cur_niv+1);

		if(!t.getSup().estArbreVide())
			calculProfondeur(t.getSup(), cur_niv+1);

		return niveau/cpt;

	}


	/*****Fonction qui compte le nombre de mots prefix de celui passÃ© en argument***/
	public static int prefix(TrieHybride t, String mot){

		if(t.estArbreVide())
			return 0;

		if(t.getVal() == mot.charAt(0)){
			if(mot.length() == 1)
				return comptageMots(t.getEq());
			if(!t.getEq().estArbreVide()){
				mot = mot.substring(1);
				return prefix(t.getEq(), mot);
			}
		}

		if((!t.getInf().estArbreVide()) && (t.getVal() > mot.charAt(0))){
			return prefix(t.getInf(), mot);
		}

		if((!t.getSup().estArbreVide()) && (t.getVal() < mot.charAt(0))){
			return prefix(t.getSup(), mot);
		}

		return 0;

	}



	/*****Fonction de suppression d'un mot dans un TrieHybride*******/
	public static TrieHybride suppression(TrieHybride t, String mot){
		if(t.estArbreVide())
			return t;

		if(!recherche(t, mot))
			return t;

		TrieHybride eq;
		TrieHybride pred;
		TrieHybride next;
		TrieHybride suppr = null;
		if(prefix(t, mot) != 0){

			while(mot.length() > 0){
				if(mot.charAt(0) < t.getVal()){
					pred = t.getInf();
					t = pred;
				}
				else {
					if(mot.charAt(0) > t.getVal()){
						next = t.getSup();
						t = next;
					}
					else{
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

		suppr = null;  //noeud a partir duquel on supprime l'arbre
		eq = null;
		pred = null;
		next = null;
		int cpt = 0;

		while(cpt < mot.length()-1){  //-1 car on ne regarde pas la derniere lettre qui est celle du mot à supprimer
			if(mot.charAt(0) < t.getVal()){
				pred = t.getInf();
				t = pred;
			}
			else {
				if(mot.charAt(0) > t.getVal()){
					next = t.getSup();
					t = next;
				}
				else{
					if(t.getArret()){
						suppr = t.getEq();
					}
					eq = t.getEq();
					t = eq;
					mot = mot.substring(1);
					cpt++;
				}
			}
		}

		while(!suppr.getArret()){
			eq = suppr.getEq();
			suppr.setArret(false);
			suppr.setVal('/');
			suppr = null;
			suppr = eq;
		}
		suppr.setArret(false);
		suppr = null;
		return t;
	}


}
