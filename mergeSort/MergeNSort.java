package mergeSort;

import Main.NamedList;

public class MergeNSort {
	public MergeNSort(Integer[] a, Integer N) {
		mergeSort(a, 0, a.length, getMax(a)+1, N);
	}
	
	public void mergeSort(Integer[] a, int start, int end, Integer INF, Integer N) {
		Integer l=end-start;
		
		if(l<N) {
			Merge3Sort.mergeSort(a,start,end,INF);
		} else {
			Integer factor = (end-start)/N;
			
			//generate mids
			NamedList<Integer> mids=new NamedList<Integer>();
			mids.add(start+factor);
			for(Integer i=1; i<N-1; i++)
				mids.add(mids.getByIndex(i-1)+factor);
			
			//call (recursive) mergesort
			mergeSort(a, start, mids.getByIndex(0), INF, N);
			for(Integer i=1; i<N-1; i++)
				mergeSort(a, mids.getByIndex(i-1), mids.getByIndex(i), INF, N);
			mergeSort(a, mids.getByIndex(N-2), end, INF, N);
			
			//call to merge
			merge(a, start, mids, end, INF, N);
		}
	}

	private void merge(Integer[] a, Integer start, NamedList<Integer> mids, Integer end, Integer INF, Integer N) {
		NamedList<Integer[]> arrays=new NamedList<Integer[]>();
		//create arrays
		arrays.add("a0",new Integer[mids.getByIndex(0)-start + 1]);
		for(Integer i=1; i<N-1; i++)
			arrays.add("a"+Integer.toString(i), new Integer[mids.getByIndex(i) - mids.getByIndex(i-1) + 1]);
		arrays.add("a"+Integer.toString(N-1), new Integer[end - mids.getByIndex(N-2) + 1]);
		
		//copy arrays
		copyMergeNSortArr(a, arrays.getByIndex(0), start, mids.getByIndex(0));
		for(Integer i=1; i<N-1; i++)
			copyMergeNSortArr(a, arrays.getByIndex(i), mids.getByIndex(i-1), mids.getByIndex(i));
		copyMergeNSortArr(a, arrays.getByIndex(N-1), mids.getByIndex(N-2), end);
		
		//add INFs
		arrays.getByIndex(0)[mids.getByIndex(0)-start] = INF;
		for(Integer i=1; i<N-1; i++)
			arrays.getByIndex(i)[mids.getByIndex(i)-mids.getByIndex(i-1)] = INF;
		arrays.getByIndex(N-1)[end - mids.getByIndex(N-2)] = INF;
		
		//create pointers
		Integer[] point=new Integer[N];
		for(Integer i=0; i<N; i++)
			point[i]=0;
		
		//merge
		for(Integer i=start; i<end; i++) {
			//elements of each array
			Integer[] element=new Integer[N];
			for(Integer j=0; j<N; j++)
				element[j] = arrays.getByIndex(j)[point[j]];
			
			//get min
			Integer pos=0, min=element[0];
			for(Integer j=0; j<N; j++)
				if(min>element[j]) {
					min = element[j];
					pos = j;
				}
			
			//set min
			point[pos]++;
			a[i] = min;
		}
	}

	private void copyMergeNSortArr(Integer[] a, Integer[] a1, Integer start, Integer end) {
		for(Integer i=start; i<end; i++) {
			a1[i-start] = a[i];
		}
	}
	
	public Integer getMax(Integer[] a) {
		Integer max = a[0];
		for(Integer i=1;i<a.length;i++) {
			if(max < a[i]) {
				max = a[i];
			}
		}
		return max;
	}
	
	public void displayMergeNSortArr(Integer[] a) {
		System.out.print("[");
		for(Integer i=0; i<a.length-1; i++) {
			System.out.print(a[i]+", ");
		} System.out.print(a[a.length-1]+"]");
		System.out.println();
	}
}
