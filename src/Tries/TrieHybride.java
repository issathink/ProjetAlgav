package Tries;


public class TrieHybride {

	private char val;
	private boolean arret;
	private int num_mot;
	private int id;
	private TrieHybride inf;
	private TrieHybride eq;
	private TrieHybride sup;
	
	private static int cpt_mot = 0;
	private static int id_gene = 0;   //Permet de generer un id unique pour chaque noeud


	public TrieHybride(){
		this.id = id_gene;
		id_gene++;
		this.arret = false;
		this.val = '/';
		num_mot = -1;
	}

	public void setVal(char elem){
		val = elem;
	}

	public char getVal(){
		return val;
	}
	
	public int getNumMot(){
		return num_mot;
	}
	
	public int getId(){
		return id;
	}

	public boolean estArbreVide(){
		if(val == '/'){
			return true;
		}
		return false;
	}
	
	public void setArret(boolean b){
		arret = b;
		if(b){
			num_mot = cpt_mot;
			cpt_mot++;
		}
	}
	
	public boolean getArret(){
		return arret;
	}
	

	public TrieHybride getInf(){
		if(inf == null)
			inf = new TrieHybride();
		return inf;
	}

	public TrieHybride getEq(){
		if(eq == null)
			eq = new TrieHybride();
		return eq;
	}

	public TrieHybride getSup(){
		if(sup == null)
			sup = new TrieHybride();
		return sup;
	}
	
	
	public void setInf(TrieHybride t){
		inf = t;
	}

	public void setEq(TrieHybride t){
		eq = t;
	}

	public void setSup(TrieHybride t){
		sup = t;
	}


}
