package Main;

public class Array<E> {
	
	private Integer length;
	private E array[];
	
	public Array() {
		length = 0;
		array=(E[]) new Object[1];
	}
	
	public Array(Integer length) {
		this.length = 0;
		array=(E[]) new Object[length];
	}
	
	public Array(E[] array) {
		this.array = array;
		this.length = this.array.length;
	}
	
	public void add(E val) {
		if(length == array.length) {
			E[] arr = (E[]) new Object[length*2];
			for(Integer i=0; i<length; i++)
				arr[i]=array[i];
			array = arr;
			length*=2;
		}
		
		array[length] = val;
	}
}