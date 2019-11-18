/*
Working for
7
4
1 2 3 4
4
2 3 4 5
5
1 3 10 5 2
6
564 234 874 472 937 373
6
5 2 8 4 9 3
2
4 7
6
2 2 2 3 3 3
*/
/*
Not working for
1
5
2 2 1 2 2
 */

package BalloonShootingProblem;

import java.util.Scanner;

import Main.NamedList;

public class MSApproach {
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
		
		MSApproach ob=new MSApproach();
		for(Integer i=0;i<T;i++) {
			Integer[] b=B.getByIndex(i);
			Integer k=K[i];
			Integer[] seq = ob.getSequence(b,0,b.length-1,ob.getMax(b));
			System.out.println("#" + (i+1) + " " + ob.computeFinalScore(b, seq) + " " +
					ob.tightSubarrayToNamedList(seq, 0, seq.length-1).toString(null));
		}
	}

	private Integer computeFinalScore(Integer[] B, Integer[] seq) {
		Integer k=B.length,score=0;
		NamedList<Integer> b = tightSubarrayToNamedList(B, 0, k-1);
		NamedList<Integer> pos = new NamedList<Integer>(); for(Integer i=0; i<=k-1; i++) {pos.add(i);}
		
		for(Integer i=0;i<k;i++) {
			Integer p = pos.indexOfValue(seq[i]);
			Integer s = getShotScore(b, p);
			score = score + s;
			remove2NamedListsByIndex(b, pos, p);
		}
		
		return score;
	}

	private Integer[] getSequence(Integer[] b, Integer start, Integer end, Integer MAX) {
		Integer[] seq=new Integer[end-start+1];
		
		if(end==start) {
			seq[0]=start;
		} else {
			Integer mid = (end + start)/2;
			Integer[] seq1 = getSequence(b,start,mid,MAX);
			Integer[] seq2 = getSequence(b,mid+1,end,MAX);
			
			seq = mergeSequence(b, seq1, seq2, start, mid, end, MAX);
		}
		return seq;
	}

	private Integer[] mergeSequence(Integer[] B, Integer[] s1, Integer[]s2, Integer start, Integer mid, Integer end, Integer MAX) {
		Integer k1=mid-start+1,k2=end-mid,k=k1+k2;
		
		Integer[] seq = new Integer[end-start+1];
		NamedList<Integer> seq1=tightSubarrayToNamedList(s1, 0, s1.length-1), seq2=tightSubarrayToNamedList(s2, 0, s2.length-1);
		
		NamedList<Integer> b = tightSubarrayToNamedList(B, 0, B.length-1);
		NamedList<Integer> pos = new NamedList<Integer>(); for(Integer i=0; i<B.length; i++) {pos.add(i);}
		
		System.out.println("b "+b.getTightSubList(start, end));
		System.out.println("pos "+pos.getTightSubList(start, end));
		
		Integer i1=0,i2=0,i=0;
		Integer i1remaining = 0, i2remaining = 0;
		while(seq1.size() > i1 && seq2.size() > i2) {
			Integer p1 = pos.indexOfValue(seq1.getByIndex(i1)), p2 = pos.indexOfValue(seq2.getByIndex(i2));
			Integer score1 = getShotScore(b, p1), score2 = getShotScore(b, p2);
			if(score1 > score2) {
				if((b.getByIndex(p1) == MAX && (score1 >= MAX*3 || score1 >= MAX*MAX)) ||  b.getByIndex(p1) != MAX) {
					seq[i] = seq1.getByIndex(i1);
					seq1.removeByIndex(i1);
				} else {
					i1++;i1remaining++;
					continue;
				}
			} else {
				if((b.getByIndex(p2) == MAX && (score2>= MAX*3 || score2 >= MAX*MAX)) || b.getByIndex(p2) != MAX) {
					seq[i] = seq2.getByIndex(i2);
					seq2.removeByIndex(i2);//i2++;
				} else {
					i2++;i2remaining++;
					continue;
				}
			}
			
			remove2NamedListsByIndex(b, pos, pos.indexOfValue(seq[i]));
			i++;
		}
		i1=0;i2=0;
		
		while(seq1.size()>0) {
			seq[i] = seq1.getByIndex(i1);
			remove2NamedListsByIndex(b, pos, pos.indexOfValue(seq[i]));
			i++;seq1.removeByIndex(i1);
		} while(seq2.size()>0) {
			seq[i] = seq2.getByIndex(i2);
			remove2NamedListsByIndex(b, pos, pos.indexOfValue(seq[i]));
			i++;seq2.removeByIndex(i2);
		}
		
		System.out.println("s1 "+tightSubarrayToNamedList(s1, 0, s1.length-1));
		System.out.println("s2 "+tightSubarrayToNamedList(s2, 0, s2.length-1));
		System.out.println("seq "+tightSubarrayToNamedList(seq, 0, seq.length-1));
		System.out.println();
		
		return seq;
	}

	private void remove2NamedListsByIndex(NamedList<Integer> b, NamedList<Integer> pos, Integer p) {
		b.removeByIndex(p);
		pos.removeByIndex(p);
	}

	private NamedList<Integer> tightSubarrayToNamedList(Integer[] arr, Integer start, Integer end) {
		NamedList<Integer> list=new NamedList<Integer>();
		
		for(Integer i=start; i<=end; i++) {
			list.add(arr[i]);
		}
		
		return list;
	}

	private Integer getShotScore(NamedList<Integer> b, Integer pos) {
		Integer score = -1, k = b.size();
		
		if(k==1)
			return b.getByIndex(0);
		
		if(pos == 0) {
			score = b.getByIndex(pos+1);
		} else if(pos == k-1) {
			score = b.getByIndex(pos-1);
		} else {
			score = b.getByIndex(pos+1) * b.getByIndex(pos-1);
		}
		
		return score;
	}
	
	private Integer getMax(Integer[] a) {
		Integer max = a[0];
		for(Integer i=1;i<a.length;i++) {
			if(max<a[i]) {
				max=a[i];
			}
		}
		return max;
	}
}