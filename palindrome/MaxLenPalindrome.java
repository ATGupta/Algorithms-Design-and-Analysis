package palindrome;

import Main.NamedList;

public class MaxLenPalindrome {
	private String pal=null;
	private Integer l;
	
	public MaxLenPalindrome(String inp) {
		pal=null;
		l=-1;
		
		palinRec(inp);
		//palinDNC(inp, 0, inp.length()-1);
	}
	
	private void palinRec(String inp) {
		for(Integer i=0; i<inp.length(); i++) {
			NamedList<Integer> n = palin(inp,i,i,1);
			if(l < n.getByIndex(0)) {
				l=n.getByIndex(0);
				pal=inp.substring(n.getByIndex(1), n.getByIndex(2)+1);
			}
		}
	}

	private NamedList<Integer> palinDNC(String inp, Integer s, Integer e){
		if(s==e) {
			return palin(inp,s,s,1);
		}
		Integer mid=(s+e)/2;
		palinDNC(inp, s, e);
		
		
		return null;
	}

	private NamedList<Integer> palin(String pal, Integer a, Integer z, Integer l) {
		if(a==z)if(z<pal.length()-1)if(pal.charAt(a) == pal.charAt(z+1))return palin(pal,a,z+1,l+1);
		if(a-1>0 && z+1<pal.length())if(pal.charAt(a-1) == pal.charAt(z+1)) return palin(pal,a-1,z+1,l+2);
		NamedList<Integer> n=new NamedList<Integer>();n.add(l);n.add(a);n.add(z);
		return n;
	}

	public String toString() {
		return "[" + pal + ", " + Integer.toString(l) + "]";
	}
}
