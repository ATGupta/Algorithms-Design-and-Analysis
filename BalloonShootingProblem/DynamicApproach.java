package BalloonShootingProblem;

import Main.NamedList;

public class DynamicApproach {
	
	private Integer maxScore;
	
	public DynamicApproach(Integer[] balSeq) {
		maxScore = -1;
		NamedList<Integer> seq=new NamedList<Integer>(balSeq);
		maxScore = shootAndCompute(seq,balSeq.length);
	}

	private Integer shootAndCompute(NamedList<Integer> seq, int n) {
		if(n==1)
			return seq.getByIndex(0);
		
		Integer max=-1;
		for(Integer i=0;i<n;i++) {
			
			Integer score = 0;
			if(i==0)score = seq.getByIndex(1);
			else if(i==n-1)score = seq.getByIndex(n-2);
			else score = seq.getByIndex(i-1)*seq.getByIndex(i+1);
			
			NamedList<Integer> list=new NamedList<Integer>();
			for(Integer j=0;j<n;j++) {
				if(j==i) continue;
				list.add(seq.getByIndex(j));
			}
			
			score += shootAndCompute(list, n-1);
			if(score>max)max = score;
		}
		
		return max;
	}

	public String toString() {
		return Integer.toString(maxScore);
	}
	
}
