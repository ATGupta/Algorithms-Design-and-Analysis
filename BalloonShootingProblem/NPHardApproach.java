package BalloonShootingProblem;

import java.util.Scanner;

import Main.NamedList;

public class NPHardApproach {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		
		Integer T = sc.nextInt();
		Integer[] K=new Integer[T];
		NamedList<Integer[]> B=new NamedList<Integer[]>();
		for(int i=0;i<T;i++) {
			K[i] = sc.nextInt();
			Integer[] b=new Integer[K[i]];
			for(int j=0;j<K[i];j++) {
				b[j] = sc.nextInt();
			}
			B.add(b);
		}
		
		NPHardApproach ob=new NPHardApproach();
		for(int i=0;i<T;i++) {
			//System.out.println("i "+i);
			Integer[] b = B.getByIndex(i);
			Integer score = ob.getScore(b,K[i]);
			System.out.println("#" + (i+1) + " " + score);
		}
	}

	private Integer getScore(Integer[] b, Integer k) {
		Integer f=fac(k);
		//System.out.println("f "+f);
		Integer[][] mat=getPermutations(k,f);
		
		int score[]=new int[f];
		for(int i=0;i<f;i++) {
			NamedList<Integer> l=new NamedList<Integer>();
			for(int j=0;j<k;j++) {
				l.add(b[j]);
			}
			
			NamedList<Integer> pos = new NamedList<Integer>();for(Integer j=0; j<k; j++) {pos.add(j);}
			for(int j=0;j<k;j++) {
				score[i] += shootScore(l, pos, mat[i][j]);
			}
		}
		
		Integer max=score[0], maxPos=0;
		for(int i=1;i<f;i++) {
			if(max<score[i]) {
				max = score[i];
				maxPos=i;
			}
		}
		return max;
	}

	private Integer shootScore(NamedList<Integer> b, NamedList<Integer> pos, Integer p) {
		Integer k = b.size();
		Integer score = -1;
		if(k==1) {
			score = b.getByIndex(0);
			b.removeByIndex(0);
			return score;
		}
		
		p = pos.indexOfValue(p);
		//System.out.println("pos"+pos);
		//System.out.println("num"+num);
		if(p==0) {
			score = b.getByIndex(p+1);
		} else if(p == k-1) {
			score = b.getByIndex(p-1);
		} else {
			score = b.getByIndex(p-1)*b.getByIndex(p+1);
		}
		
		b.removeByIndex(p);
		pos.removeByIndex(p);
		
		return score;
	}

	private Integer[][] getPermutations(Integer k, Integer f) {
		Integer[][] mat=new Integer[f][k];
		int arr[]=new int[k];
		
		Integer count=0;
		while(count < f) {
			arr[k-1]++;
			
			for(int j=k-1;j>=0;j--) {
				if(arr[j]>=k) {
					arr[j]=0;
					arr[j-1]++;
				}
			}
			
			if(acceptable(arr,k)) {
				for(int j=k-1;j>=0;j--) {
					mat[count][j] = arr[j];
					//System.out.print(arr[j]);
				}
				//System.out.println();
				count++;
			}
		}
		return mat;
	}

	private boolean acceptable(int[] arr, Integer k) {
		NamedList<Integer> list=new NamedList<Integer>();
		for(int i=0;i<k;i++) {
			list.add(arr[i]);
		}
		
		while(list.size()>0) {
			Integer s=list.size();
			
			for(int i=0;i<s;i++) {
				if(list.getByIndex(i)==s-1) {
					list.removeByIndex(i);
					break;
				}
			}
			
			if(s-1!=list.size())return false;
		}
		
		return true;
	}

	private Integer fac(Integer k) {
		Integer f=1;
		while(k>0) {
			f*=k;
			k--;
		}
		return f;
	}
}
/*
 * 4
4
1 2 3 4
4
2 3 4 5
5
1 3 10 5 2
6
564 234 874 472 937 373
 */