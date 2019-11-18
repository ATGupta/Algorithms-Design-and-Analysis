package mergeSort;

public class Merge3Sort {
	public static void mergeSort(Integer[] a, int start, int end, Integer INF) {
		Integer l=end-start;
		
		if(l<=1) {
		} else if(l==2) {
			if(a[start+1]<a[start]) {
				Integer temp = a[start+1];
				a[start+1] = a[start];
				a[start] = temp;
			}
		} else {
			Integer factor = (end-start)/3;
			Integer mid1 = start + factor;
			Integer mid2 = mid1 + factor;
			
			mergeSort(a, start, mid1, INF);
			mergeSort(a, mid1, mid2, INF);
			mergeSort(a, mid2, end, INF);
			
			new Merge3Sort().merge(a, start, mid1, mid2, end, INF);
		}
	}

	private void merge(Integer[] a, Integer start, Integer mid1, Integer mid2, Integer end, Integer INF) {
		Integer[] a1 = new Integer[mid1-start+1], a2 = new Integer[mid2-mid1+1], a3 = new Integer[end-mid2+1];
		
		copyMerge3SortArr(a, a1, start, mid1); copyMerge3SortArr(a, a2, mid1, mid2); copyMerge3SortArr(a, a3, mid2, end);
		a1[mid1-start] = INF; a2[mid2-mid1] = INF; a3[end-mid2] = INF;
		
		Integer i1=0, i2=0, i3=0;
		for(Integer i=start; i<end; i++) {
			Integer one = a1[i1], two = a2[i2], three = a3[i3];
			if(one <= two && one <= three) {
				a[i] = one;
				i1++;
			} else if(two <= three && two <= one) {
				a[i] = two;
				i2++;
			} else if(three <= two && three <= one) {
				a[i] = three;
				i3++;
			}
		}
	}

	private void copyMerge3SortArr(Integer[] a, Integer[] a1, Integer start, Integer end) {
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
	
	public void displayMerge3SortArr(Integer[] a) {
		System.out.print("[");
		for(Integer i=0; i<a.length-1; i++) {
			System.out.print(a[i]+", ");
		} System.out.print(a[a.length-1]+"]");
		System.out.println();
	}
}
