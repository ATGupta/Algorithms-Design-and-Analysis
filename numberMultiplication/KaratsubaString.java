package numberMultiplication;

public class KaratsubaString {
	
	private String ans;
	
	public KaratsubaString() {}
	
	public KaratsubaString(String a, String b) {
		Character achar = '+', bchar = '+';
		if(a.charAt(0)=='-') {
			achar = '-';
			a=a.substring(1);
		} else if(a.charAt(0)=='+')a.substring(1);
		
		if(b.charAt(0)=='-') {
			bchar = '-';
			b=b.substring(1);
		} else if(b.charAt(0)=='+')b.substring(1);
		
		String ans = multiply(a, b);
		
		if(achar==bchar)
			ans = "+" + ans;
		else
			ans = "-" + ans;
		
		this.ans=ans;
	}

	private String multiply(String a, String b) {
		if(a.length()==1 && b.length()==1) {
			return singleDigitMul(a,b);
		}
		a=evenize(a);b=evenize(b);
		if(a.length()>b.length())b=addPadding(b,a.length());
		else if(b.length()>a.length())a=addPadding(a,b.length());
		
		display(a); display(b);
		
		int n=a.length();
		int halfn=n/2;
		
		String a1=positiveSplit(a, 0, halfn), a2=positiveSplit(a, halfn, n),
		b1=positiveSplit(b, 0, halfn), b2=positiveSplit(b, halfn, n);
		
		String
		p1 = multiply(a1, b1),
		p2 = multiply(a2, b2),
		p3 = multiply(add(a1, a2), add(b1, b2));
		
		String
		pp1 = mulPow10(p1,n),
		pp3 = mulPow10(add(add(p3,negative(p2)), negative(p1)),halfn);
		
		String ans=add(add(pp1,p2),pp3);
		
		return ans;
	}

	public String negative(String a) {
		Character ch = '+';
		if(a.charAt(0)=='-') {
			ch = '-';
			a = a.substring(1);
		} else if(a.charAt(0)=='+') a = a.substring(1);
		
		if(ch == '+')a='-'+a;
		return a;
	}

	private String mulPow10(String a, Integer n) {
		for(Integer i=0; i<n; i++) {
			a = a+"0";
		}
		return a;
	}

	public String add(String a, String b) {
		Character achar=getSign(a), bchar=getSign(b);
		if(a.charAt(0)=='-' || a.charAt(0)=='+')a=a.substring(1);
		if(b.charAt(0)=='-' || b.charAt(0)=='+')b=b.substring(1);
		
		if(a.length()>b.length())b=addPadding(b,a.length());
		else if(b.length()>a.length())a=addPadding(a,b.length());
		
		String ans="";
		Character ansChar='+';
		
		if(achar==bchar) { //System.out.println(a+" "+b);
			ansChar = achar;
			Integer c=0, m;
			int i;
			for(i=a.length()-1;i>=0;i--) {
				m=(a.charAt(i)-48) + (b.charAt(i)-48) + c;
				c=m/10;
				m=m%10;
				ans = (char)(m+48) + ans;
			}
			ans = (char)(c+48) + ans;
			
			ans = clipIf0at0(ans);
		}
		else {
			ansChar='+';
			if(checkGreater(a,b))ansChar=achar;
			else {
				ansChar=bchar;
				String c=a;a=b;b=c;
			}
			String aa=a, bb=b;
			
			Integer c=0, m;
			int i;
			for(i=bb.length()-1;i>=0;i--) {
				m = (aa.charAt(i)-48)-(bb.charAt(i)-48)-c;
				
				if(m<0) {
					m=m+10;
					c=1;
				} else c=0;
				ans = (char)(m+48) + ans;
			}
			
			ans = clipStarting0s(ans);
		}
		
		if(ansChar=='-')ans = ansChar+ans;
		
		return ans;
	}

	private String clipStarting0s(String a) {
		String ans = "";
		Integer i=0;
		while(a.charAt(i)=='0' && i<a.length()-1)
			i++;
		for(;i<a.length();i++) {
			ans = ans+a.charAt(i);
		}
		return ans;
	}

	public boolean checkGreater(String a, String b) {
		if(a.length()>b.length())b=addPadding(b,a.length());
		else if(b.length()>a.length())a=addPadding(a,b.length());
		
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)>b.charAt(i))return true;
			else if(a.charAt(i)<b.charAt(i)) return false;
		}
		return true;
	}

	private Character getSign(String a) {
		Character cha = '+';
		if(a.charAt(0)=='-')
			cha='-';
		return cha;
	}

	private String positiveSplit(String a, int n, int m) {
		String b = "";
		for(Integer i=n; i<m;i++)
			b = b+a.charAt(i);
		return b;
	}

	private void display(String a) {
		System.out.println(a);
	}

	private String addPadding(String a, int n) {
		Integer l = a.length();
		for(Integer i=0; i<n-l; i++)
			a = "0" + a;
		return a;
	}

	private String evenize(String a) {
		if(a.length()%2==1)
			a = "0" + a;
		return a;
	}

	private String singleDigitMul(String a, String b) {
		Integer n1 = a.charAt(0) - 48;
		Integer n2 = b.charAt(0) - 48;
		
		Integer n = n1*n2;
		
		String ans = "" + (n/10);
		ans = ans + (n%10);
		ans = clipIf0at0(ans);
		
		return ans;
	}
	
	private String clipIf0at0(String st) {
		if(st.charAt(0) == '0') {
			return st.substring(1);
		}
		return st;
	}
	
	public String toString() {
		return ans;
	}
}
