package Tries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tools {

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

	static String[] tab_word(String texte) {
		String s = "";
		int i = 0;
		int k = 0;
		int taille_total = 0;

		// On va mettre dans taille_total le nombre de mots du texte
		while (k < texte.length()) {
			if (texte.charAt(k) == ' ')
				taille_total++;
			k++;
		}

		k = 0;

		// Tableau permettant de recuperer tout les mots du texte
		String tab_mots[] = new String[taille_total + 1];

		// Recuperation des mots dans "tab_mots"(le tableau) caractere par
		// caractere.
		while (k < texte.length()) {
			if (!(texte.charAt(k) == ' ')) {
				s = s.concat(texte.charAt(k) + "");
			} else {
				tab_mots[i] = s;
				i++;
				s = "";
			}
			k++;
		}
		tab_mots[i] = s;
		return tab_mots;
	}

}
