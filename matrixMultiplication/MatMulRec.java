package matrixMultiplication;

import java.util.ArrayList;

public class MatMulRec {
	
	private Integer[][]sum;
	
	public MatMulRec() {}
	
	public MatMulRec(Integer[][] a, Integer[][] b) {
		if(a[0].length!=b.length) {
			sum=null;
			return;
		}
		
		sum = mulRec(a, b);
	}

	private Integer[][] appendMat(Integer[][] a) {
		int n=a.length>a[0].length?a.length:a[0].length;
		if(n%2==1)n=n+1;
		
		Integer[][] sum=new Integer[n][n];
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[0].length;j++) {
				sum[i][j]=a[i][j];
			}
		}
		for(int i=0;i<sum.length;i++) {
			for(int j=0;j<sum[0].length;j++) {
				if(sum[i][j]==null)sum[i][j]=0;
			}
		}
		
		return sum;
	}

	private Integer[][] mulRec(Integer[][] a, Integer[][] b) {
		if(a.length==1 && b.length==1 && a[0].length==1 && b[0].length==1) {
			Integer[][] sum = new Integer[1][1];
			sum[0][0]=a[0][0]*b[0][0];
			return sum;
		}
		Integer
		aOldRow=a.length,
		aOldCol=a[0].length,
		bOldRow=b.length,
		bOldCol=b[0].length;
		
		a=appendMat(a);
		b=appendMat(b);
		
		ArrayList<Integer[][]> A=divide(a);
		ArrayList<Integer[][]> B=divide(b);
		
		Integer[][] c11,c12,c21,c22;
		
		c11 = matAdd(mulRec(A.get(0),B.get(0)), //11 and 11
		mulRec(A.get(1),B.get(2)));//12 and 21
		
		c12 = matAdd(mulRec(A.get(0),B.get(1)),//11 and 12
		mulRec(A.get(1),B.get(3)));//12 and 22
		
		c21 = matAdd(mulRec(A.get(2),B.get(0)),//21 and 11
		mulRec(A.get(3),B.get(1)));//22 and 12
		
		c22 = matAdd(mulRec(A.get(2),B.get(1)),//21 and 12
		mulRec(A.get(3),B.get(3)));//22 and 22
		
		Integer[][]C=join(c11,c12,c21,c22);
		C=clip(C, aOldRow, bOldCol);
		return C;
	}

	private Integer[][] clip(Integer[][] arr, Integer row, Integer col) {
		Integer[][]ans=new Integer[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				ans[i][j]=arr[i][j];
		
		return ans;
	}

	private Integer[][] join(Integer[][] c11, Integer[][] c12, Integer[][] c21, Integer[][] c22) {
		int n=c11.length;
		Integer[][] sum=new Integer[n*2][n*2];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sum[i][j]=c11[i][j];
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=n;j<n*2;j++) {
				sum[i][j]=c12[i][j-n];
			}
		}
		for(int i=n;i<n*2;i++) {
			for(int j=0;j<n;j++) {
				sum[i][j]=c21[i-n][j];
			}
		}
		for(int i=n;i<n*2;i++) {
			for(int j=n;j<n*2;j++) {
				sum[i][j]=c22[i-n][j-n];
			}
		}
		return sum;
	}

	private Integer[][] matAdd(Integer[][] a, Integer[][] b) {
		Integer[][] sum=new Integer[a.length][a[0].length];
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[0].length;j++) {
				sum[i][j]=a[i][j]+b[i][j];
			}
		}
		return sum;
	}

	public ArrayList<Integer[][]> divide(Integer[][] a) {
		int n=a.length;
		Integer[][] m11,m12,m21,m22;
		
		m11=new Integer[n/2][n/2];//11
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<n/2;j++) {
				m11[i][j]=a[i][j];
			}
		}
		m12=new Integer[n/2][n/2];//12
		for(int i=0;i<n/2;i++) {
			for(int j=n/2;j<n;j++) {
				m12[i][j-n/2]=a[i][j];
			}
		}
		m21=new Integer[n/2][n/2];//21
		for(int i=n/2;i<n;i++) {
			for(int j=0;j<n/2;j++) {
				m21[i-n/2][j]=a[i][j];
			}
		}
		m22=new Integer[n/2][n/2];//22
		for(int i=n/2;i<n;i++) {
			for(int j=n/2;j<n;j++) {
				m22[i-n/2][j-n/2]=a[i][j];
			}
		}

		ArrayList<Integer[][]> ob=new ArrayList<Integer[][]>();
		ob.add(m11);ob.add(m12);ob.add(m21);ob.add(m22);
		return ob;
	}
	
	public void output() {
		if(sum==null) {
			System.err.println("Error!\nMatrix length mismatch.\n");
		}
		else {
			display(sum);
		}
	}
	
	public static void display(Integer[][] a) {
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
