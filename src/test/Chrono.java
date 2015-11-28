/**
 * 
 */
package test;

import java.util.Date;

/**
 * @author sigaud Cette classe permet de chronometrer le temps de calcul d'un
 *         logiciel Le temps renvoye est le temps ecoule entre l'appel du
 *         constructeur et l'appel de la methode stop()
 */
public class Chrono {
	private long time;

	public Chrono() {
		Date d = new Date();
		time = d.getTime();
	}

	public void stop() {
		Date d = new Date();
		long timeFin = d.getTime();
		long interv = timeFin - time;
		affiche(interv);
	}

	public void affiche(long millis) {
		long ms, secondes, s, minutes, m, heures;
		if (millis >= 1000) {
			secondes = millis / 1000;
			ms = millis - secondes * 1000;
		} else {
			System.out.println("temps ecoule : " + millis + " millisecondes");
			return;
		}
		if (secondes >= 60) {
			minutes = secondes / 60;
			s = secondes - 60 * minutes;
		} else {
			System.out.println("temps ecoule : " + secondes + " s " + ms
					+ " millisecondes");
			return;
		}
		if (minutes >= 60) {
			heures = minutes / 60;
			m = minutes - 60 * heures;
			System.out.println("temps ecoule : " + heures + " h " + m + " mn "
					+ s + " s " + ms + " millisecondes");
			return;
		} else {
			System.out.println("temps ecoule : " + minutes + " mn " + s + " s "
					+ ms + " millisecondes");
			return;
		}
	}
}