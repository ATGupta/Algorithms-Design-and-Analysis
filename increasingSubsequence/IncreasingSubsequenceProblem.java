package increasingSubsequence;

import Main.NamedList;

public class IncreasingSubsequenceProblem {
	public IncreasingSubsequenceProblem(Integer[] A) {
		//findIncreasingSibsequenceIterative(A);
		//NamedList<Object> ob=professorsMethod(A,A.length,maxim(A)+1);
		NamedList<NamedList<Object>> memo=new NamedList<NamedList<Object>>();
		NamedList<Object> ob=professorsMethodBottomUp(A,A.length,maxim(A)+1,memo);
		Integer[] arr = (Integer[])ob.getByName("array");
		
		System.out.println(ob);
		for(Integer i=0;i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	private NamedList<Object> professorsMethodBottomUp(Integer[] a, int n, int INF, NamedList<NamedList<Object>> memo) {
		NamedList<Object> ob=new NamedList<Object>();
		//check if memo contains a particular n and i
		for(Integer i=0; i<memo.size(); i++) {
			NamedList<Object> o=memo.getByIndex(i);
			if((Integer)o.getByName("size")==n)
				if((Integer)o.getByName("INF")==INF)
					return o;
		}
		
		if(n<=0) {
			ob.add("length", 0);
			ob.add("array", new Integer[0]);
			return ob;
		}
		NamedList<Object> ob1=professorsMethod(a, n-1, INF);
		NamedList<Object> ob2 = null;
		if(a[n-1]<INF)
			ob2=professorsMethod(a, n-1, a[n-1]);
		Integer l1=(Integer)ob1.getByName("length"), l2=0;
		if(ob2!=null) l2=(Integer)ob2.getByName("length") + 1;
		
		if(l1>=l2) {
			ob.add("length",l1);
			//ob.add("array", joinArrays((Integer[])ob1.getByName("array"), new Integer[] {INF}));
			ob.add("array", ob1.getByName("array"));
		}
		else {
			ob.add("length",l2);
			ob.add("array", joinArrays((Integer[])ob2.getByName("array"), new Integer[] {a[n-1]}));
		}
		
		ob.add("size", n);ob.add("INF", INF);
		memo.add(ob);
		return ob;
	}
	
	private NamedList<Object> professorsMethod(Integer[] a, int n, int INF) {
		NamedList<Object> ob=new NamedList<Object>();
		if(n<=0) {
			ob.add("length", 0);
			ob.add("array", new Integer[0]);
			return ob;
		}
		NamedList<Object> ob1=professorsMethod(a, n-1, INF);
		NamedList<Object> ob2 = null;
		if(a[n-1]<INF)
			ob2=professorsMethod(a, n-1, a[n-1]);
		Integer l1=(Integer)ob1.getByName("length"), l2=0;
		if(ob2!=null) l2=(Integer)ob2.getByName("length") + 1;
		
		if(l1>=l2) {
			ob.add("length",l1);
			//ob.add("array", joinArrays((Integer[])ob1.getByName("array"), new Integer[] {INF}));
			ob.add("array", ob1.getByName("array"));
		}
		else {
			ob.add("length",l2);
			ob.add("array", joinArrays((Integer[])ob2.getByName("array"), new Integer[] {a[n-1]}));
		}
		return ob;
	}

	private Integer maxim(Integer[] A) {
		Integer max = A[0];
		for(Integer i=1; i<A.length; i++) {
			if(max<A[i])
				max = A[i];
		}
		
		return max;
	}

	private void findIncreasingSibsequenceIterative(Integer[] a) {
		NamedList<Integer[]> nl=new NamedList<Integer[]>();
		for(Integer i=a.length-1; i>=0; i--) {
			Integer[] arr=new Integer[] {a[i]};
			
			Boolean  filled=false;
			for(Integer j=0; j<nl.size(); j++) {
				Integer[] indexed=nl.getByIndex(j);
				
				if(arr[0]<indexed[0]) {
					Integer[] newArr = joinArrays(arr, indexed);
					nl.add(newArr);
					filled=true;
				}
			}
			
			nl.add(arr);
		}
		
		Integer[] largest=null;Integer maxL=-1;
		for(Integer j=0; j<nl.size(); j++) {
			if(nl.getByIndex(j).length>maxL) {
				maxL = nl.getByIndex(j).length;
				largest = nl.getByIndex(j);
			}
		}
		
		System.out.println(maxL);
		for(Integer j=0; j<largest.length; j++) {
			System.out.print(largest[j]+" ");
		}
		System.out.println();
	}

	private Integer[] joinArrays(Integer[] a, Integer[] b) {
		Integer arr[]=new Integer[a.length + b.length];
		for(Integer i=0;i<a.length;i++)
			arr[i]=a[i];
		for(Integer i=0;i<b.length;i++)
			arr[a.length+i]=b[i];
		return arr;
	}
}
