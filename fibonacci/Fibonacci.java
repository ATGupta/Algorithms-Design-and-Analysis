package fibonacci;

import Main.NamedList;
import numberMultiplication.KaratsubaString;

public class Fibonacci {
	
	private String output;
	private KaratsubaString k=new KaratsubaString();
	private NamedList<String> memo=new NamedList<String>();
	
	public Fibonacci(String st) {
		//output = fiboTopDown(st);
		output = fiboTopDownMemoized(st);
	}

	private String fiboTopDownMemoized(String st) {
		if(memo.size()==0) {memo.add("0");memo.add("1");}
		if(st.equals("0")) {
			return st;
		}
		if(st.equals("1")) {
			return st;
		}
		
		if(k.checkGreater(st,memo.sizeString())) {
			memo.add(
					k.add(fiboTopDownMemoized(k.add(st, "-2")),
				fiboTopDownMemoized(k.add(st, "-1")))
			);
		}
		
		//System.out.println(st);
		return memo.getByIndex(st);
	}

	public String fiboTopDown(String st) {
		if(st.equals("0")) {
			return st;
		}
		if(st.equals("1")) {
			return st;
		}
		
		return k.add(fiboTopDown(k.add(st, k.negative("2"))),
				fiboTopDown(k.add(st, k.negative("1"))));
	}
	
	public String toString() {
		return output;
	}
}
