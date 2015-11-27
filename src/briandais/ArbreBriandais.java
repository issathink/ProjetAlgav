package briandais;

/*
 * La structre de l'arbre de la Briandais.
 * Epsilon est represente par : '!'
 */
public class ArbreBriandais {

	private ArbreBriandais suivant;
	private ArbreBriandais fils;
	private char content;
	private int id;
	private static int gene_id = 0;
	
	public ArbreBriandais() {
		this.suivant = null;
		this.fils = null;
		this.content = '\0';
		this.id = gene_id;
		gene_id++;
	}

	public ArbreBriandais(ArbreBriandais suivant, ArbreBriandais fils,
			char content) {
		this.suivant = suivant;
		this.fils = fils;
		this.content = content;
		this.id = gene_id;
		gene_id++;
	}

	public ArbreBriandais(char content) {
		this.content = content;
		this.suivant = null;
		this.fils = null;
		this.id = gene_id;
		gene_id++;
	}

	/*
	 * A partir de là il n'y a que des getters and setters (Rien d'interessant)
	 * 
	 */
	public int getId(){
		return id;
	}
	
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
