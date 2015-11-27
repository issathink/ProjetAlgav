package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import briandais.ArbreBriandais;
import tries.TrieHybride;

public class Tools {

	public final static char EPSILON = '#';
	public final static char FIN = '/';

	static String LireFichier(String file) {

		File f = new File(file);
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas ete trouve");
		}

		int t = (int) f.length();
		String texte = "";
		try {
			while (t > 0) {
				char c = (char) fr.read();
				texte += String.valueOf(c);
				t--;
			}
			fr.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : "
					+ exception.getMessage());
		}
		return texte;

	}

	/*
	 * Cette fonction prend le nom d'un fichier en parametres et retourne une
	 * liste de chaines de caracteres (supprime tous les blancs et les retour
	 * chariots).
	 * 
	 * @param filename - le nom du fichier à parser
	 * 
	 * @return list - la liste contenant
	 */
	public static ArrayList<String> getListOfString(String filename) {
		ArrayList<String> list = new ArrayList<String>();

		File file = new File(filename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				text = text.trim();
				list.add(text);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Verifiez que le fichier : '" + filename
					+ "' existe et reesayer.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				System.err.println("An unexpected error occured.");
			}
		}

		return list;
	}

	/******* Generer fichier .odt pour l'affichage ******/
	public static void fileDot(TrieHybride th, String filename) {
		String texte = getGraphic(th);
		String file = filename + ".dot";

		File f = new File(file);
		if (f.exists())
			f.delete();

		FileWriter fw;
		try {
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

	public static String getGraphic(TrieHybride th) {
		String s = "digraph G {\n";
		s += "node [color=lightblue2, style=filled];\n";
		s += generGraph(th);
		s += "}\n";
		return s;
	}

	public static String generGraph(TrieHybride t) {
		if (t == null) {
			return "";
		}

		String s = "";
		int id = t.getId();
		char val = t.getVal();

		String num = (t.getArret() == Tools.EPSILON) ? " xlabel=\""
				+ t.getNumMot() + "\"" : "";
		s += id + " [label=\"" + val + "\"" + num + "];\n";

		if (!t.getInf().estArbreVide()) {
			s += "edge [arrowsize=2, color=red];\n";
			s += id + " -> " + t.getInf().getId() + ";\n";
		}
		if (!t.getEq().estArbreVide()) {
			s += "edge [arrowsize=2, color=green];\n";
			s += id + " -> " + t.getEq().getId() + ";\n";
		}
		if (!t.getSup().estArbreVide()) {
			s += "edge [arrowsize=2, color=blue];\n";
			s += id + " -> " + t.getSup().getId() + ";\n";
		}

		if (!t.getInf().estArbreVide())
			s += generGraph(t.getInf());
		if (!t.getEq().estArbreVide())
			s += generGraph(t.getEq());
		if (!t.getSup().estArbreVide())
			s += generGraph(t.getSup());

		return s;
	}

	public static String getGraphicBriandais(ArbreBriandais arbre) {
		String s = "digraph G {\n";
		s += "node [color=lightblue2, style=filled];\n";
		s += generGraphBriandais(arbre);
		s += "}\n";
		return s;
	}

	public static String generGraphBriandais(ArbreBriandais arbre) {
		if (arbre == null) {
			return "";
		}

		String num = (arbre.getContent() == Tools.EPSILON) ? " xlabel=\"" + ""
				+ "\"" : "";
		int id = arbre.getId();
		String s = "";
		char val = arbre.getContent();

		s += id + " [label=\"" + val + "\"" + num + "];\n";

		if (arbre.getFils() != null) {
			s += "edge [arrowsize=2, color=green];\n";
			s += id + " -> " + arbre.getFils().getId() + ";\n";
		}
		if (arbre.getSuivant() != null) {
			s += "edge [arrowsize=2, color=blue];\n";
			s += id + " -> " + arbre.getSuivant().getId() + ";\n";
		}

		if (arbre.getFils() != null)
			s += generGraphBriandais(arbre.getFils());
		if (arbre.getSuivant() != null)
			s += generGraphBriandais(arbre.getSuivant());

		return s;
	}

	public static void fileDotBriandais(ArbreBriandais arbre, String filename) {
		String texte = getGraphicBriandais(arbre);
		String file = filename + ".dot";

		File f = new File(file);
		if (f.exists())
			f.delete();

		FileWriter fw;
		try {
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

	public static void commandDot(String filename) {
		Runtime runtime = Runtime.getRuntime();
		File file = new File(filename + ".jpg");

		if (file.exists())
			file.delete();

		try {
			Process p = runtime.exec(new String[] { "dot", "-Tjpg",
					"-o" + filename + ".jpg", filename + ".dot" });
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static TrieHybride copyOf(TrieHybride t) {
		TrieHybride copy = new TrieHybride();
		copy.setArret(t.getArret() == Tools.EPSILON);
		copy.setEq(t.getEq());
		copy.setInf(t.getInf());
		copy.setSup(t.getSup());
		if (t.getArret() == Tools.EPSILON)
			copy.setNum(t.getNumMot());
		copy.setVal(t.getVal());
		return copy;
	}

}
