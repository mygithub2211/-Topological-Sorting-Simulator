import java.util.Iterator;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * PriorityQueue class implemented via the binary heap.
 * From your textbook (Weiss).
 * @param <K> any type
 * @author Phat Tran
 */
public class WeissPriorityQueue<K> extends WeissAbstractCollection<K> {

	/**
	 * defaul capacity for heap.
	 */
	private static final int DEFAULT_CAPACITY = 100;

	/**
	 * Number of elements in heap.
	 */
	private int currentSize;
	/**
	 * The heap array.
	 */
	private K[] array; 
	/**
	 * Comparator.
	 */
	private Comparator<? super K> cmp;
	/**
	 * indexMap.
	 */
	private HashMap<K, Integer> indexMap; 

	/**
	 * test cases.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		/**
		 * inner class Student.
		 */
		class Student {
			/*
			 * strung gnum.
			 */
			String gnum;
			/**
			 * string name.
			 */
			String name;

			/**
			 * constructor.
			 * @param gnum takes gnum
			 * @param name takes name
			 */
			Student(String gnum, String name) {
				this.gnum = gnum;
				this.name = name;
			}

			/**
			 * to compare.
			 * @param o takes any object
			 * @return false or true
			 */
			public boolean equals(Object o) {
				if (o instanceof Student)
					return this.gnum.equals(((Student) o).gnum);
				return false;
			}

			/**
			 * toString().
			 * @return a string
			 */
			public String toString() {
				return name + "(" + gnum + ")";
			}

			/**
			 * to find hash code.
			 * @return a hash code
			 */
			public int hashCode() {
				return gnum.hashCode();
			}
		}

		Comparator<Student> comp = new Comparator<>() {
			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		};

		// TESTS FOR INDEXING -- you'll need more testing...

		WeissPriorityQueue<Student> q = new WeissPriorityQueue<>(comp);
		q.add(new Student("G00000000", "Robert"));
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); // -1, no index
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); // 1, at root
		System.out.println();

		q.add(new Student("G00000001", "Cindi"));
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); // 1, at root
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); // 2, lower down
		System.out.println();

		q.remove(); // remove Cindi
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); // -1, no index
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); // 1, at root
		System.out.println();
		System.out.println();

		// TESTS FOR UPDATING -- you'll need more testing...

		q = new WeissPriorityQueue<>(comp);
		Student s1 = new Student("G00000000", "Robert");
		q.add(s1);
		Student s2 = new Student("G00000001", "Cindi");
		q.add(s2);

		for (Student s : q)
			System.out.print(q.getIndex(s) + " "); // 1 2
		System.out.println();
		for (Student s : q)
			System.out.print(s.name + " "); // Cindi Robert
		System.out.println();

		s1.name = "Bobby";
		q.update(s1);

		for (Student s : q)
			System.out.print(q.getIndex(s) + " "); // 1 2
		System.out.println();
		for (Student s : q)
			System.out.print(s.name + " "); // Bobby Cindi
		System.out.println();

		s1.name = "Robert";
		q.update(s1);

		for (Student s : q)
			System.out.print(q.getIndex(s) + " "); // 1 2
		System.out.println();
		for (Student s : q)
			System.out.print(s.name + " "); // Cindi Robert
		System.out.println();

	}

	// --------------------------------------------------------
	// end of testing code
	// --------------------------------------------------------

	/**
	 * average case O(1).
	 * @param x takes value x
	 * @return the index of item x in the heap, or -1 if it isn't in the heap
	 */
	public int getIndex(K x) {
		if (indexMap.get(x) == null) {
			return -1;
		}
		return indexMap.get(x);
	}

	/**
	 * O(lg n) average case.
	 * or O(lg n) worst case if getIndex() is guarenteed O(1).
	 * return false if x is not in heap.
	 * otherwise check whether the location of x still satisfies the heap order.
	 * Note that item x might might have its priority changed.
	 * perform necessary location adjustment and return true.
	 * @param x new value to be updated
	 * @return true if update successfully, false otherwise
	 */
	public boolean update(K x) {
		K temp = null;
		int parent = 0;
		int child = 0;

		int index = getIndex(x);
		if (index == -1) {
			return false;
		}

		array[index] = x;
		parent = index / 2;
		if (compare(x, array[parent]) < 0) {// if x is less than parent
			// percolate up
			for (int hole = parent; compare(array[index], array[hole]) < 0 && hole >= 1; hole /= 2) {
				// now swap x and its parent
				temp = array[index];// store x in xHolder
				array[index] = array[hole];
				array[hole] = temp;
				indexMap.put(array[index], index);
				indexMap.put(array[hole], hole);
				index = hole;
			}
		} else {
			percolateDown(index);
		}
		return true;
	}

	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (K[]) new Object[DEFAULT_CAPACITY + 1];
		indexMap = new HashMap<K, Integer>(DEFAULT_CAPACITY);
	}

	/**
	 * Construct an empty PriorityQueue with a specified comparator.
	 * @param c takes comparator object c
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue(Comparator<? super K> c) {
		currentSize = 0;
		cmp = c;
		array = (K[]) new Object[DEFAULT_CAPACITY + 1];
		indexMap = new HashMap<K, Integer>(DEFAULT_CAPACITY);
	}

	/**
	 * Construct a PriorityQueue from another Collection.
	 * @param coll takes WeissCollection object
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue(WeissCollection<? extends K> coll) {
		cmp = null;
		currentSize = coll.size();
		array = (K[]) new Object[(currentSize + 2) * 11 / 10];
		indexMap = new HashMap<K, Integer>(DEFAULT_CAPACITY);
		int i = 1;
		for (K item : coll)
			array[i++] = item;
		buildHeap();
	}

	/**
	 * Compares lhs and rhs using comparator if.
	 * provided by cmp, or the default comparator.
	 * @param lhs left handside of the heap
	 * @param rhs right handside of the heap
	 * @return an int
	 */
	@SuppressWarnings("unchecked")
	private int compare(K lhs, K rhs) {
		if (cmp == null)
			return ((Comparable) lhs).compareTo(rhs);
		else
			return cmp.compare(lhs, rhs);
	}

	/**
	 * Adds an item to this PriorityQueue.
	 * @param x any object
	 * @return true
	 */
	public boolean add(K x) {
		if (currentSize + 1 == array.length)
			doubleArray();

		// Percolate up
		int hole = ++currentSize;
		array[0] = x;

		for (; compare(x, array[hole / 2]) < 0; hole /= 2) {
			array[hole] = array[hole / 2];
			indexMap.put(array[hole / 2], hole);

		}

		array[hole] = x;

		// update indexMap
		indexMap.put(x, hole);
		return true;
	}

	/**
	 * Returns the number of items in this PriorityQueue.
	 * @return the number of items in this PriorityQueue
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * Returns an iterator over the elements in this PriorityQueue.
	 * The iterator does not view the elements in any particular order.
	 * @return an iterator
	 */
	public Iterator<K> iterator() {
		return new Iterator<K>() {
			int current = 0;

			public boolean hasNext() {
				return current != size();
			}

			@SuppressWarnings("unchecked")
			public K next() {
				if (hasNext())
					return array[++current];
				else
					throw new NoSuchElementException();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * Returns the smallest item in the priority queue.
	 * @return the smallest item
	 * @throws NoSuchElementException if empty
	 */
	public K element() {
		if (isEmpty())
			throw new NoSuchElementException();
		return array[1];
	}

	/**
	 * Removes the smallest item in the priority queue.
	 * @return the smallest item
	 * @throws NoSuchElementException if empty
	 */
	public K remove() {

		K minItem = element();
		array[1] = array[currentSize--];
		percolateDown(1);

		// update indexMap
		indexMap.remove(minItem);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary.
	 * arrangement of items. Runs in linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Internal method to percolate down in the heap.
	 * @param hole the index at which the percolate begins
	 */
	private void percolateDown(int hole) {
		int child;
		K tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && compare(array[child + 1], array[child]) < 0)
				child++;
			if (compare(array[child], tmp) < 0) {
				array[hole] = array[child];
				// update indexMap
				indexMap.put(array[child], hole);
			} else
				break;

		}
		array[hole] = tmp;
		// update indexMap
		indexMap.put(tmp, hole);
	}

	/**
	 * Internal method to extend array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArray() {
		K[] newArray;

		newArray = (K[]) new Object[array.length * 2];
		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];
		array = newArray;
	}
}
