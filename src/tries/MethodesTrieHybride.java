package tries;

import java.util.ArrayList;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class MethodesTrieHybride {



	/*File repertoire=new File("./Text/Shakespeare/");// ici le repertoire qui contient tous les fichiers
    String [] files=repertoire.list();// attention ici recupere le nom du fichier avec le .txt a la fin

    for(int i=1;i<files.length;i++){
        motRecup=CreationFichier.recupMotFichierShakespeare(files[i]);// motRecup Arraylist<String>

        for (int j=0;j<motRecup.size();j++){
            abrB1.ajout(motRecup.get(j));
        }


    }
	 */


	/****Ajouter mots du texte dans un TrieHybride****/
	public static TrieHybride construireTrie(){
		int i = 0;
		TrieHybride t = new TrieHybride();
		ArrayList<String >tab_mots = Tools.getListOfString("exemple_base");

		while(i < tab_mots.size()){
			ajoutMot(tab_mots.get(i), t);
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
				t.setNum();
				return t;
			}
			TrieHybride t2 = t.getEq();
			while(cpt != mot.length()){
				t2.setVal(mot.charAt(cpt));
				if(cpt == mot.length() - 1){
					t2.setArret(true);
					t2.setNum();
				}
				else
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
				if(mot.length() == 1){  //On est sur on mot du texte dej� inser� mais n'�tant pas forc�ment valide (arret = false)
					t.setArret(true);
					t.setNum();
				}
				else{
					mot = mot.substring(1);
					t.setEq(ajoutMot(mot, t.getEq()));
				}
			}
		}
		return t;
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


	/*****Fonction listant les mots du TrieHybride dans l'ordre alphab�tique****/
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

	public static double profondeurMoyenne(TrieHybride t){
		if(t.estArbreVide())
			return 0;

		return calculProfondeur(t, 0);
	}
	public static double calculProfondeur(TrieHybride t, int cur_niv){

		if(t.getEq().estArbreVide() && t.getInf().estArbreVide() && t.getSup().estArbreVide()){
			cpt++;
			niveau += cur_niv;
		}

		else{
			if(!t.getInf().estArbreVide())
				calculProfondeur(t.getInf(), cur_niv+1);

			if(!t.getEq().estArbreVide())
				calculProfondeur(t.getEq(), cur_niv+1);

			if(!t.getSup().estArbreVide())
				calculProfondeur(t.getSup(), cur_niv+1);
		}

		return ((double)niveau)/cpt;

	}


	/*****Fonction qui compte le nombre de mots prefix de celui passé en argument***/
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
		/*****Si le mot est prefixe d'au moins un autre***/
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

		/*******Si n'a pas de mot prefixe****/
		suppr = t;  //noeud a partir duquel on supprime l'arbre
		eq = null;
		pred = null;
		next = null;
		TrieHybride pere_suppr = suppr;
		TrieHybride haut = null;
		TrieHybride save = new TrieHybride();

		while(mot.length()-1 > 0){  //-1 car on ne regarde pas la derniere lettre qui est celle du mot a supprimer
			
			if(!t.getEq().getInf().estArbreVide() || !t.getEq().getSup().estArbreVide()){
				haut = t;
			}
			if(mot.charAt(0) < t.getVal()){
				pred = t.getInf();
				t = pred;
				suppr = t;
			}
			else {
				if(mot.charAt(0) > t.getVal()){
					next = t.getSup();
					t = next;
					suppr = t;
				}
				else{
					if(t.getArret() || !t.getInf().estArbreVide() || !t.getSup().estArbreVide()){
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
	
		
		if(suppr.getEq().estArbreVide() && !suppr.getInf().estArbreVide() || !suppr.getSup().estArbreVide()){   //Si on est sur la racine sans fils en dessous
			if(suppr.getId() != 0){
				
			}
			else{
				
			}
		}

		boolean bool = false;
		if(pere_suppr != null && suppr != null)
			if(pere_suppr.getEq().getId() == suppr.getId() && (!pere_suppr.getInf().estArbreVide() || !pere_suppr.getSup().estArbreVide()))
				bool = true;
		

		while(!suppr.getArret()){
			eq = suppr.getEq();
			suppr.setArret(false);
			suppr.setVal('/');
			suppr = null;
			suppr = eq;
		}
		suppr.setArret(false);
		suppr.setVal('/');
		suppr = null;

		
		if(haut != null && bool){
			
			if(hauteur(save.getInf()) > hauteur(save.getSup())){    //equlibrage
				TrieHybride fd = save.getSup();
				haut.setEq(save.getInf());
				haut = haut.getEq();
				while(!haut.estArbreVide()){ 
					haut = haut.getSup();    //on ne peut pas aller a gauche car les fils sont toujours de cle superieur a la cle de fd
				}

				haut.setSelf(fd);
			}
			else{
				TrieHybride fg = save.getInf();
				haut.setEq(save.getSup());
				haut = haut.getEq();
				while(!haut.estArbreVide()){ 
					haut = haut.getInf();   //on ne peut pas aller a droite car les fils sont toujours de cle superieur a la cle de fg
				}
				haut.setSelf(fg);
			}
		}

		return t;
	}


	/******Fonction de conversion d'un TrieHybride vers un Arbdre de la Briandais*****/
	public static ArbreBriandais trieVersBriandais(TrieHybride t){

		if(t.estArbreVide())
			return null;	

		if(t.getInf().estArbreVide() && t.getEq().estArbreVide() && t.getSup().estArbreVide())
			return new ArbreBriandais(t.getVal());

		ArbreBriandais briandais = null;

		if(!t.getInf().estArbreVide())
			briandais = new ArbreBriandais(trieVersBriandais(t.getInf()), trieVersBriandais(t.getInf().getEq()), t.getInf().getVal());

		if(!t.getEq().estArbreVide())
			briandais = MethodesArbreBriandais.fusion(briandais, new ArbreBriandais(trieVersBriandais(t.getEq()), trieVersBriandais(t.getEq().getEq()), t.getEq().getVal()));
		
		if(!t.getSup().estArbreVide())
			briandais = MethodesArbreBriandais.fusion(briandais, new ArbreBriandais(trieVersBriandais(t.getSup()), trieVersBriandais(t.getSup().getEq()), t.getSup().getVal()));

		return briandais;

	}



	/******Fonction d'insertion suivie d'un potentiel reequilibrage****/
	public static TrieHybride triEquilibre(){
		int i = 0;
		TrieHybride t = new TrieHybride();
		ArrayList<String >tab_mots = Tools.getListOfString("exemple_base");

		while(i < tab_mots.size()){
			ajoutMotReequilibre(tab_mots.get(i), t, null, null);
			i++;
		}
		return t;
	}

	public static TrieHybride ajoutMotReequilibre(String mot, TrieHybride t, TrieHybride pere, TrieHybride gd_pere){

		if(t.getVal() == mot.charAt(0)){
			gd_pere = t;
		}

		if(t.estArbreVide()){
			int cpt = 1;
			t.setVal(mot.charAt(0));
			if(mot.length() == 1){  //Si le mot ne fait qu'une lettre on met le flag arret ici
				t.setArret(true);
				t.setNum();
				return t;
			}
			TrieHybride t2 = t.getEq();
			while(cpt != mot.length()){
				t2.setVal(mot.charAt(cpt));
				if(cpt == mot.length() - 1){
					t2.setArret(true);
					t2.setNum();
				}
				else
					t2 = t2.getEq();   //On continue d'inserer le reste du mot juste en dessous
				cpt++;
			}

			/*****Ici le reequilibrage****/
			if(gd_pere != null && (hauteur(pere.getEq()) < hauteur(pere.getInf()) || hauteur(pere.getEq()) < hauteur(pere.getSup()))){
				TrieHybride copy_pere = Tools.copyOf(pere);
				TrieHybride copy_t = Tools.copyOf(t);
				TrieHybride copy_t_inf = Tools.copyOf(t.getInf());
				TrieHybride copy_t_sup = Tools.copyOf(t.getSup());
				TrieHybride copy_gd_pere = Tools.copyOf(gd_pere);

				if(hauteur(pere.getInf()) < hauteur(pere.getSup())){
					copy_gd_pere.setEq(copy_t);
					copy_gd_pere.getEq().setSup(copy_t_sup);
					copy_gd_pere.getEq().setInf(copy_pere);
					copy_gd_pere.getEq().getInf().setSup(copy_t_inf);
					t.setSelf(copy_gd_pere.getEq());
					pere.setSelf(t);
				}
				else{
					copy_gd_pere.setEq(copy_t);
					copy_gd_pere.getEq().setInf(copy_t_inf);
					copy_gd_pere.getEq().setSup(copy_pere);
					copy_gd_pere.getEq().getSup().setInf(copy_t_sup);
					t.setSelf(copy_gd_pere.getEq());
					pere.setSelf(t);
				}
				return null;
			}
			/*****Fin du reequilibrage****/

			return t;

		}
		if(mot.charAt(0) < t.getVal()){
			t.setInf(ajoutMotReequilibre(mot, t.getInf(), t, gd_pere));
		}
		else{
			if(mot.charAt(0) > t.getVal()){
				t.setSup(ajoutMotReequilibre(mot, t.getSup(), t, gd_pere));
			}
			else{
				if(mot.length() == 1){  //On est sur on mot du texte dej� inser� mais n'�tant pas forc�ment valide (arret = false)
					t.setArret(true);
					t.setNum();
				}
				else{
					mot = mot.substring(1);
					t.setEq(ajoutMotReequilibre(mot, t.getEq(), t, gd_pere));
				}
			}
		}
		return t;
	}

}
