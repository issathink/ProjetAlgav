package tries;

import tools.Tools;


public class TrieHybride {

	private char val;
	private char arret;
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
		this.arret = '\0';
		this.val = Tools.FIN;
		num_mot = -1;
	}
	
	public TrieHybride(char val, char arret, TrieHybride inf, TrieHybride eq, TrieHybride sup){
		this.id = id_gene;
		id_gene++;
		this.val = val;
		this.arret = arret;
		this.inf = inf;
		this.eq = eq;
		this.sup = sup;
		if(arret == Tools.EPSILON)
		num_mot = getNumMot();
		else
			num_mot = -1;
	}

	public void setSelf(TrieHybride t){  //Recopie d'un arbre sur soit
		id = t.getId();
		val = t.getVal();
		arret = t.getArret();
		num_mot = t.getNumMot();
		inf  = t.getInf();
		sup = t.getSup();
		eq = t.getEq();
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
		if(val == Tools.FIN){
			return true;
		}
		return false;
	}
	
	public void setArret(boolean b){
		if(b)
			arret = Tools.EPSILON;
		else
			arret = '\0';
	}
	
	public char getArret(){
		return arret;
	}
	
	public void setNum(){
		num_mot = cpt_mot;
		cpt_mot++;
	}
	
	public void setNum(int num){
		num_mot = num;
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
