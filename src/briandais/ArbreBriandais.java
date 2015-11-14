package briandais;

/*
 * La structre de l'arbre de la Briandais.
 */
public class ArbreBriandais {

	private ArbreBriandais suivant;
	private ArbreBriandais fils;
	private char content;
	
	public ArbreBriandais() {
		this.suivant = null;
		this.fils = null;
		this.content = '\u0000';
	}

	public ArbreBriandais(ArbreBriandais suivant, ArbreBriandais fils,
			char content) {
		this.suivant = suivant;
		this.fils = fils;
		this.content = content;
	}

	public ArbreBriandais(char content) {
		this.content = content;
		this.suivant = null;
		this.fils = null;
	}

	/*
	 * A partir de là il n'y a que des getters and setters (Rien d'interessant)
	 */
	public ArbreBriandais getSuivant() {
		return suivant;
	}

	public void setSuivant(ArbreBriandais suivant) {
		this.suivant = suivant;
	}

	public ArbreBriandais getFils() {
		return fils;
	}

	public void setFils(ArbreBriandais fils) {
		this.fils = fils;
	}

	public char getContent() {
		return content;
	}

	public void setContent(char content) {
		this.content = content;
	}

}
