package Tries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tools {

	static String LireFichier(String file){	

		File f = new File(file);
		FileReader fr = null;
		try
		{
			fr = new FileReader(f);
		}
		catch(FileNotFoundException exception)
		{
			System.out.println("Le fichier n'a pas �t� trouv�");
		}

		int t = (int) f.length();
		String texte="";
		try
		{
			while(t>0){
				char c = (char)fr.read();
				texte += String.valueOf(c);
				t--;
			}
			fr.close();
		}
		catch(IOException exception)
		{
			System.out.println("Erreur lors de la lecture : " + exception.getMessage());
		}
		return texte;

	}


	static String[] tab_word(String texte){
		String s = "";
		int i = 0;
		int k = 0;
		int taille_total = 0;

		//On va mettre dans taille_total le nombre de mots du texte
		while(k < texte.length()){
			if(texte.charAt(k) == ' ')
				taille_total++;
			k++;
		}

		k=0;

		//Tableau permettant de r�cuperer tout les mots du texte
		String tab_mots[] = new String[taille_total+1];

		//R�cup�ration des mots dans "tab_mots"(le tableau) caract�re par caract�re.
		while(k < texte.length()){
			if( !(texte.charAt(k) == ' ')){
				if( !(texte.charAt(k) == ',') && !(texte.charAt(k) == '.') && !(texte.charAt(k) == '!') && !(texte.charAt(k) == '?'))  //On ne met pas les virgules, les points(., !, ?,...)
					s = s.concat((texte.charAt(k)+""));
			}
			else{
				tab_mots[i] = s;
				i++;
				s = "";
			}
			k++;
		}
		tab_mots[i] = s;
		return tab_mots;
	}



	/*******Generer fichier .odt pour l'affichage******/
	public static void genererFichierDot(TrieHybride th, String filename) {
		String texte = getGraphviz(th);
		// String dir = "/home/maroc/workspace/AlgAv/graph/";
		String file = filename + ".dot";

		/* Suppresion du dot si existant */
		File f = new File(file);
		if (f.exists())
			f.delete();

		FileWriter fw;
		try {
			System.out.println("Generation du fichier : "+ file);
			// System.out.println(texte);
			fw = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(texte);
			output.flush();
			output.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String getGraphviz(TrieHybride th) {
		String s = "digraph G {\n";
		s += "node [color=lightblue2, style=filled];\n";
		s += generGraphviz(th);
		s += "}\n";
		return s;
	}



	public static String generGraphviz(TrieHybride th) {
		if (th == null) {
			return "";
		}

		String s = "";
		int id = th.getId();
		char cle = th.getVal();

		String sCle = th.getArret() ? " xlabel=\"" + th.getNumMot() + "\"" : "";
		s += id + " [label=\"" + cle + "\"" + sCle + "];\n";

		if (!th.getInf().estArbreVide()) {
			s += "edge [arrowsize=2, color=red];\n";
			s += id + " -> " + th.getInf().getId() + ";\n";
		} /*else {
			s += nullGraphViz(id, "red");
		}*/
		if (!th.getEq().estArbreVide()) {
			s += "edge [arrowsize=2, color=green];\n";
			s += id + " -> " + th.getEq().getId() + ";\n";
		} /*else {
			s += nullGraphViz(id, "green");
		}*/
		if (!th.getSup().estArbreVide()) {
			s += "edge [arrowsize=2, color=blue];\n";
			s += id + " -> " + th.getSup().getId() + ";\n";
		} /*else {
			s += nullGraphViz(id, "blue");
		}*/

		if (!th.getInf().estArbreVide())
			s += generGraphviz(th.getInf());
		if (!th.getEq().estArbreVide())
			s += generGraphviz(th.getEq());
		if (!th.getSup().estArbreVide())
			s += generGraphviz(th.getSup());

		return s;
	}


	/*private static String nullGraphViz(int id, String color) {
		String s;
		int tmp = TrieHybride.get;
		// s = "node [shape = \"point\" color =\"white\"];\n";
		s = tmp + " [label=\"\" color=\"white\"];\n";
		// s += "node [shape = \"box\" color =\"red\"];\n";
		// s += "edge [arrowsize=2, color="+color+"];\n";
		s += id + " -> " + tmp + ";\n";

		return s;
	}*/

}
