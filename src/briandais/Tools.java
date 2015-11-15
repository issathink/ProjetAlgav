package briandais;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tools {
	
	public static void main(String []args) {
		System.out.println(getListOfString("exemple_base"));
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

}
