package Main;

import numberMultiplication.KaratsubaString;

public class NamedList <E> {
	
	private NamedObject<E> head, tail;
	private Integer length;
	private String lengthString;
	
	public NamedList() {
		head=null;
		tail=null;
		length=0;
		lengthString="0";
	}
	
	public NamedList(E[] ob) {
		head=null;
		tail=null;
		length=0;
		lengthString="0";
		for(Integer i=0;i<ob.length;i++) {
			add(ob[i]);
		}
	}
	
	public void add(String name, E val) {
		NamedObject<E> ob=new NamedObject<E>(val, name);
		if(head==null) {
			head=tail=ob;
		}
		else {
			tail.next=ob;
			tail=ob;
		}
		++length;
		lengthString = new KaratsubaString().add(lengthString, "1");
	}
	
	public void add(E val) {
		NamedObject<E> ob=new NamedObject<E>(val, null);
		if(head==null) {
			head=tail=ob;
		}
		else {
			tail.next=ob;
			tail=ob;
		}
		++length;
		lengthString = new KaratsubaString().add(lengthString, "1");
	}
	
	public NamedList<E> copy(NamedList<E> ob){
		NamedList<E> copied = new NamedList<E>();
		for(Integer i=0;i<ob.size();i++) {
			copied.add(ob.getNameByIndex(i),ob.getByIndex(i));
		}
		
		return copied;
	}
	
	public E getByIndex(Integer index) {
		NamedObject<E> ob=head;Integer i=0;
		while(ob!=null) {
			if(i==index)
				return ob.val;
			ob=ob.next;
			i++;
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public E getByIndex(String index) {
		NamedObject<E> ob=head;String i="0";
		while(ob!=null) {
			if(i.equals(index))
				return ob.val;
			ob=ob.next;
			i=new KaratsubaString().add(i, "1");
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}

	public E getByName(String name) {
		NamedObject<E> ob=head;
		while(ob!=null) {
			if(ob.name.equals(name))
				return ob.val;
			ob=ob.next;
		}
		try {
			throw new NamedListException("NoSuchNameException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getNameByIndex(Integer index) {
		NamedObject<E> ob=head;Integer i=0;
		while(ob!=null) {
			if(i==index)
				return ob.name;
			ob=ob.next;
			i++;
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getNameByIndex(String index) {
		NamedObject<E> ob=head;String i="0";
		while(ob!=null) {
			if(i==index)
				return ob.name;
			ob=ob.next;
			i=new KaratsubaString().add(i, "1");
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public NamedList<E> getSubList(Integer start, Integer end){
		try {
			if(start<0 || end>length)
				throw new NamedListException("IndexOutOfBoundsException");
			else if(end < start) {
				throw new NamedListException("IndexNotProperException");
			}
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		
		NamedList<E> o=new NamedList<E>();
		
		NamedObject<E> ob=head;
		Integer i=0;
		while(i<start) {
			ob=ob.next;
			i++;
		} while(i<end) {
			o.add(ob.name, ob.val);
			ob=ob.next;
			i++;
		}
		
		return o;
	}
	
	public NamedList<E> getTightSubList(Integer start, Integer end){
		try {
			if(start<0 || end>=length)
				throw new NamedListException("IndexOutOfBoundsException");
			else if(end < start) {
				throw new NamedListException("IndexNotProperException");
			}
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		
		NamedList<E> o=new NamedList<E>();
		
		NamedObject<E> ob=head;
		Integer i=0;
		while(i<start) {
			ob=ob.next;
			i++;
		} while(i<=end) {
			o.add(ob.name, ob.val);
			ob=ob.next;
			i++;
		}
		
		return o;
	}
	
	public Integer indexOfValue(E val) {
		Integer i = 0;
		NamedObject<E> ob=head;
		while(ob!=null) {
			if(ob.val == val) {
				return i;
			}
			ob=ob.next;
			i++;
		}
		try {
			throw new NamedListException("NoSuchValueException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeByIndex(Integer index){
		if(index==0) {
			head = head.next;
			lengthString = new KaratsubaString().add(lengthString, "-1");
			--length;
			return;
		} else if(index==length-1) {
			NamedObject<E> ob=head;Integer i=0;
			NamedObject<E> o=head;
			while(i<index-1) {
				o=o.next;
				i++;
			}
			tail = o;
			tail.next = null;
			lengthString = new KaratsubaString().add(lengthString, "-1");
			--length;
			return;
		} else if(index>0 && index<length-1) {
			NamedObject<E> ob=head, prevo=null;;Integer i=0;
			while(i<index) {
				prevo=ob;
				ob=ob.next;
				i++;
			}
			prevo.next = ob.next;
			lengthString = new KaratsubaString().add(lengthString, "-1");
			--length;
			return;
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
	}
	
	public void removeByName(String name){
		NamedObject<E> ob=head;Integer i=0;
		while(ob!=null) {
			if(ob.name.equals(name)) {
				removeByIndex(i);
				return;
			}
			ob=ob.next;
			i++;
		}
		try {
			throw new NamedListException("NoSuchNameException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
	}
	
	public void removeByValue(E val){
		NamedObject<E> ob=head;Integer i=0;
		while(ob!=null) {
			if(ob.val==val) {
				removeByIndex(i);
				return;
			}
			ob=ob.next;
			i++;
		}
		try {
			throw new NamedListException("NoSuchValueException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
	}
	
	public void replaceByIndex(String name, E val, Integer index) {
		NamedObject<E> ob=new NamedObject<E>(val, name);
		if(index==0) {
			head = head.next;
			ob.next = head;
			head = ob;
			return;
		} else if(index==length-1) {
			Integer i=0;
			NamedObject<E> o=head;
			while(i<index-1) {
				o=o.next;
				i++;
			}
			tail = o;
			tail.next = ob;
			tail=ob;
			return;
		} else if(index>0 && index<length-1) {
			NamedObject<E> o=head, prevo=null;;Integer i=0;
			while(i<index) {
				prevo=o;
				o=o.next;
				i++;
			}
			prevo.next = ob;
			ob.next = o.next;
			return;
		}
		try {
			throw new NamedListException("IndexOutOfBoundsException");
		} catch(NamedListException e) {
			e.printStackTrace();
		}
	}
	
	public void replaceByIndex(E val, Integer index) {
		replaceByIndex(null, val, index);
	}
	
	public String toString() {
		String st="[";
		
		NamedObject<E> ob=head;
		while(ob!=null) {
			if(ob.next==null) {
				st = st + ob.name + ": " + ob.val;
			} else {
				st = st + ob.name + ": " + ob.val + ", ";
			}
			ob=ob.next;
		} st = st + "]";
		
		return st;
	}
	
	public String toString(String name) {
		if(name!=null) {
			return null;
		}
		String st="[";
		
		NamedObject<E> ob=head;
		while(ob!=null) {
			if(ob.next==null) {
				st = st + ob.val;
			} else {
				st = st + ob.val + ", ";
			}
			ob=ob.next;
		} st = st + "]";
		
		return st;
	}
	
	public Integer size() {
		return length;
	}
	
	public String sizeString() {
		return lengthString;
	}
}

class NamedObject <E> {
	NamedObject<E> next;
	String name;
	E val;
	
	public NamedObject(E val, String name) {
		this.val=val;
		this.name=name;
		this.next=null;
	}
}

class NamedListException extends Exception {
	
	private static final long serialVersionUID = -3519327334893405938L;
	private String ERROR_NAME;
	
	public NamedListException(String st) {
		ERROR_NAME=st;
	}
	
	public void printStackTrace() {
		System.err.println(ERROR_NAME);
		super.printStackTrace();
		System.exit(1);
	}
}
