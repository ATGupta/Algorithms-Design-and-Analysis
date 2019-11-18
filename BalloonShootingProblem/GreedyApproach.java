package BalloonShootingProblem;

import java.util.Scanner;

import Main.NamedList;

public class GreedyApproach {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		Integer T = sc.nextInt();
		NamedList<Integer[]> B=new NamedList<Integer[]>();
		Integer[] K=new Integer[T];
		for(int i=0;i<T;i++) {
			K[i] = sc.nextInt();
			Integer[] b=new Integer[K[i]];
			for(int j=0;j<K[i];j++) {
				b[j] = sc.nextInt();
			}
			B.add(b);
		}
		
		GreedyApproach ob=new GreedyApproach();
		for(int i=0;i<T;i++) {
			Integer[] b=B.getByIndex(i);
			Integer k = K[i];
			Integer score = ob.getScore(b, k, ob.getMax(b,k));
			System.out.println("#" + (i+1) + " " + score);
		}
	}

	private Integer getScore(Integer[] B, Integer k, Integer MAX) {
		Integer score = 0;
		
		NamedList<Integer> b=new NamedList<Integer>();
		for(int i=0;i<k;i++) {
			b.add(B[i]);
		}
		
		while(b.size()>0) {
			score = score + shootScore(b, B, MAX);
		}
		
		return score;
	}

	private Integer shootScore(NamedList<Integer> b, Integer[] B, Integer MAX) {
		Integer k = b.size();
		Integer score = -1;
		
		Integer pos = -1;
		if(k==1) {
			score = b.getByIndex(0);
			b.removeByIndex(0);
			return score;
		}
		
		Integer[] arr=new Integer[k];
		for(int i=0; i<k; i++) {
			if(i==0) {
				arr[i] = b.getByIndex(i+1);
			} else if(i==k-1) {
				arr[i] = b.getByIndex(i-1);
			} else {
				arr[i] = b.getByIndex(i-1) * b.getByIndex(i+1);
			}
		}
		
		NamedList<Integer> leftOver=new NamedList<Integer>();
		for(Integer i=0; i<k; i++) {
			if(score < arr[i]) {
				if((b.getByIndex(i) == MAX && (arr[i] >= MAX*3 || arr[i] >= MAX*MAX)) ||  b.getByIndex(i) != MAX || k == 2) {
					score = arr[i];
					pos = i;
				}
			}
		}
		
		b.removeByIndex(pos);
		
		return score;
	}
	
	Integer getMax(Integer[] b, Integer k) {
		Integer max = b[0];
		for(Integer i=1; i<k; i++) {
			if(max<b[i])
				max = b[i];
		}
		
		return max;
	}
}
/*
5
4
1 2 3 4
4
2 3 4 5
5
1 3 10 5 2
6
564 234 874 472 937 373
2
4 7
*/