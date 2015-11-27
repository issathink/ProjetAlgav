package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import briandais.ArbreBriandais;
import briandais.MethodesArbreBriandais;

public class ConstruireArbreFichier extends Thread {

	private ArbreBriandais arbre;
	private String filename;

	public ConstruireArbreFichier(String filename) {
		this.filename = filename;
		this.arbre = null;
		start();
	}

	@Override
	public void run() {

		System.out.println("Creating Briandais from file: " + filename);
		File file = new File(filename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				text = text.trim();
				arbre = MethodesArbreBriandais.insertion(arbre, text);
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

		System.out.println(Thread.currentThread() + "_END.");
	}

	public ArbreBriandais getArbre() {
		return arbre;
	}
}
