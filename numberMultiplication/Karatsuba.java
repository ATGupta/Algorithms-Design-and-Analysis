package numberMultiplication;

import java.util.LinkedList;
import java.util.Scanner;

public class Karatsuba {
	
	public Karatsuba() {}
	
	public Karatsuba(String As, String Bs) {
		LinkedList<Object>
		aob=string2ArrayObject(As),
		bob=string2ArrayObject(Bs);
		
		display(aob);
		display(bob);
		
		LinkedList<Object> arrOb=k_mul(aob,bob);
		System.out.println("Answer is");
		display(arrOb);
	}

	LinkedList<Object> k_mul
			(LinkedList<Object> aob, LinkedList<Object> bob) {
		///*
		Byte[]
		a=(Byte[])aob.get(1),
		b=(Byte[])bob.get(1);
		Character
		achar=(Character)aob.get(0),
		bchar=(Character)bob.get(0);
		
		if(a.length==1 && b.length==1) {
			return singleDigitMul(aob, bob);
		}
		
		a=evenize(a);b=evenize(b);
		if(a.length>b.length)b=addPadding(b,a.length);
		else if(b.length>a.length)a=addPadding(a,b.length);
		
		aob.remove(1);bob.remove(1);aob.add(a);bob.add(b);
		
		display(aob);display(bob);
		
		int n=a.length;
		int halfn=n/2;
		
		LinkedList<Object>
		a1=positiveSplit(a, 0, halfn),
		a2=positiveSplit(a, halfn, n),
		b1=positiveSplit(b, 0, halfn),
		b2=positiveSplit(b, halfn, n);
		
		LinkedList<Object>
		p1 = k_mul(a1, b1),
		p2 = k_mul(a2, b2),
		p3 = k_mul(add_arr(a1, a2), add_arr(b1, b2));
		
		LinkedList<Object>
		pp1 = mulPow10(p1,n),
		pp3 = mulPow10(add_arr(add_arr(p3,negative(p2)), negative(p1)),halfn);

		System.out.println('-');display(a);display(b);System.out.println('-');
		LinkedList<Object> ans=add_arr(add_arr(pp1,p2),pp3);
		if(achar==bchar)
			return makeArrayObject('+',(Byte[])ans.get(1));
		else
			return makeArrayObject('-',(Byte[])ans.get(1));
	}

	public LinkedList<Object> negative(LinkedList<Object> ob) {
		if((Character)ob.get(0)=='-')
			return makeArrayObject('+', (Byte[])ob.get(1));
		else
			return makeArrayObject('-', (Byte[])ob.get(1));
	}

	private LinkedList<Object> positiveSplit(Byte[] a, int n, int m) {
		Byte[] arr=initializeArrByte(m-n);
		for(int i=n;i<m;i++)arr[i-n] = a[i];
		
		return makeArrayObject('+', arr);
	}

	private LinkedList<Object> singleDigitMul
			(LinkedList<Object> aob, LinkedList<Object> bob) {
		Byte[] a=(Byte[])aob.get(1),
		b=(Byte[])bob.get(1);
		
		Byte n = (byte)(a[0]*b[0]);
		Byte arr[]=initializeArrByte(2);
		arr[1]=(byte)(n%10);
		arr[0]=(byte)(n/10);
		
		arr = clipIf0at0(arr);
		
		char arrChar=' ';
		if((Character)aob.get(0) == (Character)bob.get(0))
			arrChar='+';
		else arrChar='-';
		
		return makeArrayObject(arrChar, arr);
	}

	private LinkedList<Object> mulPow10(LinkedList<Object> aob, int n) {
		Byte[] a=(Byte[])aob.get(1);
		
		Byte[] c = initializeArrByte(a.length+n);
		for(int i=0;i<a.length;i++) {
			c[i]=a[i];
		}
		/*for(int i=0;i<n;i++) {
			if(c[i]==null)c[i]=0;
		}*/
		
		clipStarting0s(c);
		
		return makeArrayObject((Character)aob.get(0), c);
	}

	private Byte[] addPadding(Byte[] a, int n) {
		Byte[]arr=initializeArrByte(n);
		for(int i=0;i<a.length;i++)
			arr[n-i-1] = a[a.length-i-1];
		/*for(int i=0;i<n;i++)
			if(arr[i]==null)arr[i]=0;*/
		return arr;
	}

	private Byte[] add_arr(Byte[] a, Byte[] b) {
		Byte[] arr=initializeArrByte(a.length+1);
		Byte c=0, m;
		int i;
		for(i=a.length-1;i>=0;i--) {
			m=(byte)(a[i]+b[i]+c);
			c=(byte)(m/10);
			m=(byte)(m%10);
			arr[i+1]=m;
		}
		arr[i+1]=c;
		
		arr = clipIf0at0(arr);
		
		return arr;
	}

	private Byte[] clipIf0at0(Byte[] arr) {
		if(arr[0] == 0) {
			Byte[]arrF=initializeArrByte(arr.length-1);
			for(int i=1;i<arr.length;i++)
				arrF[i-1]=arr[i];
			return arrF;
		}
		return arr;
	}
	
	private void display(Byte[] arr) {
		for(int i=0;i<arr.length;i++)System.out.print(arr[i]);
		System.out.println();
	}
	
	private void display(LinkedList<Object> aob) {
		Character c = (Character)aob.get(0);
		Byte[] arr = (Byte[])aob.get(1);
		
		System.out.print(c);
		for(int i=0;i<arr.length;i++)System.out.print(arr[i]);
		System.out.println();
	}
	
	private Byte[] evenize(Byte[] a) {
		int n=a.length;
		if(n%2==1) {
			n=n+1;
		}
		
		Byte[] arr=initializeArrByte(n);
		for(int i=0;i<a.length;i++) {
			arr[i+n-a.length]=a[i];
		}
		/*for(int i=0;i<n;i++) {
			if(arr[i]==null)arr[i]=0;
		}*/
		
		return arr;
	}

	private boolean checkGreaterArray(Byte[] a, Byte[] b) {
		System.out.println("-cga");display(a);display(b);System.out.println('-');
		for(int i=0;i<a.length;i++) {
			if(a[i]
					>
			b[i])return true;
			else if(a[i]<b[i]) return false;
		}
		return true;
	}
	
	public LinkedList<Object> makeArrayObject(Character c, Byte[]a){
		LinkedList<Object> ob=new LinkedList<Object>();
		ob.add(c);
		ob.add(a);
		
		return ob;
	}
	
	public LinkedList<Object> add_arr(LinkedList<Object> aob, LinkedList<Object> bob) {
		Character achar=(Character)aob.get(0),
		bchar=(Character)bob.get(0),
		arrChar='+';
		
		Byte[]
		a=(Byte[])aob.get(1),
		b=(Byte[])bob.get(1);
		
		System.out.println("-aa");display(aob);display(bob);
		if(a.length>b.length)b=addPadding(b,a.length);
		else if(b.length>a.length)a=addPadding(a,b.length);
		
		Byte[] arr=initializeArrByte(a.length+1);
		
		if(achar==bchar) {
			arrChar=achar;
			
			Byte c=0, m;
			int i;
			for(i=a.length-1;i>=0;i--) {
				m=(byte)(a[i]+b[i]+c);
				c=(byte)(m/10);
				m=(byte)(m%10);
				arr[i+1]=m;
			}
			arr[i+1]=c;
			
			arr = clipStarting0s(arr);
		}
		else {
			if(checkGreaterArray(a,b))arrChar=achar;
			else {
				arrChar=bchar;
				Byte[]c=a;a=b;b=c;
			}
			Byte[]aa=copyArr(a),bb=copyArr(b);
			
			Byte c=0, m;
			int i;
			for(i=bb.length-1;i>=0;i--) {
				if(aa[i]<bb[i]) {
					aa[i]=(byte)(aa[i]+10);
					aa[i-1]=(byte)(aa[i-1]-1);
				}
				arr[i+1]=(byte)(aa[i]-bb[i]);
			}
			
			arr = clipStarting0s(arr);
		}
		System.out.print("aa's answer");display(makeArrayObject(arrChar, arr));System.out.println("-");
		return makeArrayObject(arrChar, arr);
	}

	private Byte[] copyArr(Byte[] a) {
		Byte[] arr=initializeArrByte(a.length);
		for(int i=0;i<a.length;i++)arr[i]=a[i];
		return arr;
	}
	
	Byte[] initializeArrByte(int n) {
		Byte[] a=new Byte[n];
		for(int i=0;i<n;i++) {
			a[i]=0;
		}
		return a;
	}

	public LinkedList<Object> string2ArrayObject(String st) {
		Character arrChar='+';
		if(st.charAt(0)=='-' || st.charAt(0)=='+') {
			arrChar = st.charAt(0);
			st=st.substring(1);
		}
		
		Byte[] arr=initializeArrByte(st.length());
		
		for(int i=0;i<st.length();i++) 
			arr[i] = (byte)((int)st.charAt(i)-48);
		
		return makeArrayObject(arrChar, arr);
	}
	
	private Byte[] clipStarting0s(Byte[] a) {
		LinkedList<Byte> arrob=new LinkedList<Byte>();
		int i=0;
		for(;i<a.length;i++) if(a[i]!=0) break;
		for(;i<a.length;i++) arrob.add(a[i]);
		
		Byte[] arr=initializeArrByte(arrob.size());
		for(i=0;i<arr.length;i++) arr[i] = arrob.get(i);
		
		if(arr.length==0)arr=initializeArrByte(1);
		
		return arr;
	}
}
