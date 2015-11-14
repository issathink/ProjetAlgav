package Tries;


public class TrieHybride {

	private char val = '/';
	private boolean arret;
	private TrieHybride inf;
	private TrieHybride eq;
	private TrieHybride sup;


	public TrieHybride(){
		this.arret = false;
	}

	public void setVal(char elem){
		val = elem;
	}

	public char getVal(){
		return val;
	}

	public boolean estArbreVide(){
		if(val == '/'){
			return true;
		}
		return false;
	}
	
	public void setArret(){
		arret = true;
	}
	
	public boolean getArret() {
		return arret;
	}

	public TrieHybride getInf(){
		inf = new TrieHybride();
		return inf;
	}

	public TrieHybride getEq(){
		eq = new TrieHybride();
		return eq;
	}

	public TrieHybride getSup(){
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
