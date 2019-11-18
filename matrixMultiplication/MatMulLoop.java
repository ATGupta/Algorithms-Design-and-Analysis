package matrixMultiplication;

public class MatMulLoop {
	
	private Integer [][]sum;
	
	public MatMulLoop(Integer[][] a, Integer[][] b) {
		if(a[0].length!=b.length) {
			sum=null;
			return;
		}
		
		sum=new Integer[a.length][b[0].length];
		for(int i=0;i<sum.length;i++) {
			for(int j=0;j<sum[0].length;j++) {
				if(sum[i][j]==null)sum[i][j]=0;
			}
		}
		
		//actual matmul loop
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<b[0].length;j++) {
				for(int k=0;k<b.length;k++) {
					sum[i][j]=sum[i][j] + a[i][k] * b[k][j];
				}
			}
		}
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