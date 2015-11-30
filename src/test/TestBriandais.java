package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tools.Chrono;
import tools.ConstruireArbreFichier;
import tools.Tools;
import tries.MethodesTrieHybride;
import tries.TrieHybride;
import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class TestBriandais {

	public static void main(String[] args) {

		Chrono chrono = new Chrono();
		
		chrono.start();
		ArbreBriandais arbre = constructionAjoutSimple();
		/*ArrayList<String> list = Tools.getListOfString("exemple_base");
		ArbreBriandais arbre = null;

		for (String mot : list) {
			arbre = MethodesArbreBriandais.insertion(arbre, mot);
		}*/
		chrono.stop();
		System.out.println(MethodesArbreBriandais.comptageMots(arbre));
		System.out.println(MethodesArbreBriandais.comptageNil(arbre));
		chrono.start();
		System.out.println(MethodesArbreBriandais.hauteur(arbre));
		chrono.stop();
		System.out.println(MethodesArbreBriandais.profondeurMoyenne(arbre));

		/*
		 * System.out.println("Mots de l'arbre : " +
		 * MethodesArbreBriandais.comptageMots(arbre));
		 */

		// MethodesArbreBriandais.afficher(arbre);

		/*
		 * Set<String> set = new HashSet<String>(); set.addAll(list);
		 * 
		 * System.out.println("Taille : " + list.size() + ", ComptageMots: " +
		 * MethodesArbreBriandais.comptageMots(arbre) + ", Set: " + set.size());
		 * 
		 * for (String mot : list) { System.out.println(mot + " " +
		 * MethodesArbreBriandais.recherche(arbre, mot)); }
		 */

		// List<String> mots = MethodesArbreBriandais.listeMots(arbre);
		// System.out.println(mots + "\n" + mots.size());

		// System.out.println("hauteur: " +
		// MethodesArbreBriandais.hauteur(arbre));

		// arbre = MethodesArbreBriandais.insertion(arbre, "dac");
		// arbre = MethodesArbreBriandais.insertion(arbre, "a");
		/*
		 * int i = 0;
		 * 
		 * for (i = 0; i < list.size() - 33; i++) { arbre =
		 * MethodesArbreBriandais.insertion(arbre, list.get(i)); }
		 * 
		 * ArbreBriandais arbre2 = null;
		 */
		// arbre2 = MethodesArbreBriandais.insertion(arbre2, "da");
		/*
		 * for (int j = i; j < list.size(); j++) { arbre2 =
		 * MethodesArbreBriandais.insertion(arbre2, list.get(j)); }
		 */

		// System.out.println("comptageNil : " +
		// MethodesArbreBriandais.comptageNil(arbre));

		// arbre = MethodesArbreBriandais.fusion(arbre, arbre2);

		// MethodesArbreBriandais.afficher(arbre);

		// List<String> mots = MethodesArbreBriandais.listeMots(arbre);
		// System.out.println(mots + "\n" + mots.size());

		// TrieHybride trie =
		// MethodesArbreBriandais.briandaisVersHybride(arbre);
		// List<String> motsTrie = MethodesTrieHybride.listeMots(trie);
		// System.out.println(motsTrie + "\n" + motsTrie.size());

		/*
		 * List<String> c = Tools.getListOfString("exemple_base"); for (String
		 * mot : c) { System.out.println("mot : " + mot + "  " +
		 * MethodesTrieHybride.recherche(trie, mot)); }
		 */

		// arbre = MethodesArbreBriandais.suppression(arbre, "da");
		// arbre = MethodesArbreBriandais.suppression(arbre, "dac");

		// System.out.println("###########");
		// System.out.println("da est prefixe de " +
		// MethodesArbreBriandais.prefixe(arbre, "dac") + " mot(s)");

		// System.out.println("Profondeur moyenne : " +
		// MethodesArbreBriandais.profondeurMoyenne(arbre));

		// constuireOeuvreShakespeare();

		/*
		 * arbre = MethodesArbreBriandais.insertion(arbre, "dac"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "da"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "allo"); arbre =
		 * MethodesArbreBriandais.insertion(arbre, "el");
		 */

		// MethodesArbreBriandais.affichageFormate(arbre);
		// arbre = MethodesArbreBriandais.suppression(arbre, "da");
		// MethodesArbreBriandais.affichageFormate(arbre);
		// MethodesArbreBriandais.afficher(arbre);

		/*
		 * for(int i=0; i<10; i++) arbre =
		 * MethodesArbreBriandais.suppression(arbre, list.get(i));
		 * System.out.println(MethodesArbreBriandais.comptageMots(arbre));
		 * MethodesArbreBriandais.afficher(arbre); System.out.println(list);
		 */

		/*
		 * TrieHybride trie = MethodesArbreBriandais.briandaisVersTrie(arbre);
		 * System.out.println(MethodesTrieHybride.listeMots(trie));
		 * System.out.println(MethodesTrieHybride.comptageMots(trie));
		 */
		/*ArbreBriandais dict = constuctionParallele();
		System.out.println(MethodesArbreBriandais.comptageMots(dict));
		// MethodesTrieHybride.listeMots(dict);
		MethodesArbreBriandais.afficher(dict);*/
	}

	/*
	 * Construit le dictionnaire contenant l'ensemble des mots de Shakespeare
	 * par ajout simple.
	 */
	public static ArbreBriandais constructionAjoutSimple() {
		ArbreBriandais arbre = null;
		File rep = new File("Shakespeare");
		File[] files = rep.listFiles();
		List<String> list;

		for (File file : files) {
			list = Tools.getListOfString(file.getAbsolutePath());
			for (String mot : list)
				arbre = MethodesArbreBriandais.insertion(arbre, mot);
		}

		System.out.println("END of construction.");
		return arbre;
	}

	/*
	 * Construit le dictionnaire contenant l'ensemble des mots de Shakespeare de
	 * maniere parallele.
	 */
	public static ArbreBriandais constuctionParallele() {
		ArbreBriandais arbre = null;
		File rep = new File("Shakespeare");
		File[] files = rep.listFiles();
		ConstruireArbreFichier[] tabThreads = new ConstruireArbreFichier[files.length];
		int i = 0;

		for (File file : files)
			tabThreads[i++] = new ConstruireArbreFichier(file.getAbsolutePath());

		/* On attends que les threads se terminent. */
		try {
			for (int j = 0; j < tabThreads.length; j++)
				tabThreads[j].join();
		} catch (InterruptedException e) {
			System.out.println("Je devais pas etre interrompu.");
			e.printStackTrace();
		}

		/* On fusionne les arbres. */
		for (int j = 0; j < tabThreads.length; j++)
			arbre = MethodesArbreBriandais.fusion(arbre,
					tabThreads[j].getArbre());

		System.out.println("END of construction.");
		return arbre;
	}

}
