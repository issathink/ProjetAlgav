package test;

import tools.Tools;
import tries.MethodesTrieHybride;
import tries.TrieHybride;
import briandais.MethodesArbreBriandais;

public class TestTrieHybride {
	
	public static void main(String []args){
		
		TrieHybride t = MethodesTrieHybride.triEquilibre();
		
		/*ArrayList<String> tab_mots = Tools.getListOfString("exemple_base");
		for(String mot : tab_mots){
			System.out.println("mot : " + mot + "  "+ MethodesTrieHybride.recherche(t, mot));
		}*/
		
		//System.out.println(MethodesTrieHybride.comptageMots(t));
		
		/*ArrayList<String> l = MethodesTrieHybride.listeMots(t);
		for(String mot : l)
			System.out.println(mot);*/
		
		System.out.println(MethodesTrieHybride.comptageNil(t));
		
		System.out.println(MethodesTrieHybride.hauteur(t));
		
		//System.out.println(MethodesTrieHybride.prefix(t, "d"));
		
		System.out.println(MethodesTrieHybride.profondeurMoyenne(t));
		
		//MethodesTrieHybride.suppression(t, "A");
		
		
		
		MethodesArbreBriandais.afficher(MethodesTrieHybride.trieVersBriandais(t));
		
		Tools.fileDot(t, "TrieHybride");
		Tools.commandDot("TrieHybride");
		
		//ArbreBriandais briandais = MethodesTrieHybride.trieVersBriandais(t);
		
		//System.out.println(t.getSup().getInf().getInf().getEq().getInf().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getEq().getArret());

	}

}
