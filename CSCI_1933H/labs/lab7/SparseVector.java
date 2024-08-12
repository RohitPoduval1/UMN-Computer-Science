public class SparseVector {

	private Node head;
	private int length;

	public SparseVector(int len){
		this.head = null;
		this.length = len;
	}

	public int getLength() { return length; }


	// Prints out a sparse vector (including zeros)
	public String toString() {
		String result = "";
		Node currNode = head;
		int currIndex = 0;
		while (currNode != null) {
			int idx = currNode.getIndex();
			// Pad the space between nodes with zero
			while(currIndex < idx) {
				result += "0, ";
				currIndex++;
			}

			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if (currNode != null) {
				result += ", ";
			}
		}
		return result;
		/*
			TODO: Understand
			toString() will get the next index and compare it to what it should be, the expected index. If it is less,
			then it fills the gaps with zeros. If there is gap and the current index and the next index match,
			then it appends the proper number.
		 */
	}


	public void addElement(int index, double value){
		// Case 1: the index is greater than the length
		if (index > this.length) {
			System.out.println("Invalid index");
		}
		else {
			// Case 2: the element is the first one to be added because the list is empty
			if (this.head == null) {
				this.head = new Node(index, value);
			}
			// Case 3: there is already at least one element in the list, so add the element to the end
			else {
				Node currNode = this.head;			  // since we must add the element to the end, we know we are at the
													  // end if the current node
				while (currNode.getNext() != null) {  // points to null, so check for that 
					currNode = currNode.getNext();
				}
				currNode.setNext(new Node(index, value)); // once that one is found, exit the while loop and set the
														  // last node to the one we want
			}
		}
	}


	
	public static double dot(SparseVector x, SparseVector y) {

		// if both are empty or of unequal length, then return some impossible value to let the user know something went wrong
		if ((x.getLength() == 0 && y.getLength() == 0) || (x.getLength() != y.getLength())) {
			System.out.print("Invalid input. ");
			return 0.0;
		}
		// both sparse vectors are populated with at least one element and are of equal length
		else {
			double dot_product = 0;
			Node currXNode = x.head;
			Node currYNode = y.head;

			while (currXNode != null && currYNode != null) {
				if (currXNode.getIndex() == currYNode.getIndex()) {
					dot_product += currXNode.getValue() * currYNode.getValue();
					currXNode = currXNode.getNext();
					currYNode = currYNode.getNext();
				}
				else if (currXNode.getIndex() < currYNode.getIndex()) {  // y is ahead of x, so we need to advance x
					currXNode = currXNode.getNext();
				}
				else {													 // x is ahead of y, so we need to advance y
					currYNode = currYNode.getNext();
				}
			}
			return dot_product;
		}
	}


	public void removeElement(int index) {
		Node currentNode = head;

		// Case 1: remove from beginning
		if (index == 0) {
			this.head = currentNode.getNext();
			currentNode = null;
		}

		// Case 2: remove from end
		else if (index == this.length - 1) {
			// Traverse the list until currentNode is just before the last one
			// newEnd(currentNode) -> oldEnd -> None
			while (currentNode.getNext() != null && currentNode.getNext().getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);  // remove the old end
		}

		// Case 3: remove from middle
		else {
			int currentIndex = 0;
			while (currentNode.getNext() != null && currentNode.getNext().getNext() != null && currentIndex < index - 1) {
				if (currentNode.getIndex() == index - 1) {
					break;
				}
				else {
					currentNode = currentNode.getNext();
					currentIndex++;
				}
			}
			// oneBefore(currentNode) -> toRemove -> oneAfter
			currentNode.setNext(currentNode.getNext().getNext());  // set the current node to point to the one after the one we want to remove
			currentNode = currentNode.getNext();
			currentNode = null;
		}
	}


	public static void main(String[] args) {
		// SparseVector vec = new SparseVector(9);
		// vec.addElement(1, 25.0);
		// vec.addElement(7, -11.2);
		// vec.addElement(8, 32.0);
		// vec.addElement(10, 32.0);
		// System.out.println(vec);

		
		// Given test (10.0)
		// SparseVector x = new SparseVector(100000000);
		// x.addElement(0, 1.0);
		// x.addElement(10000000, 3.0);
		// x.addElement(10000001, -2.0);
		// SparseVector y = new SparseVector(100000000);
		// y.addElement(0, 2.0);
		// y.addElement(10000001, -4.0);
		// double result = dot(x, y);
		// System.out.println("Result: " + result);

		// // different length (should not work)
		// SparseVector x2 = new SparseVector(5);
		// x2.addElement(0, 1.0);
		// x2.addElement(2, -2.0);
		// SparseVector y2 = new SparseVector(7);
		// y2.addElement(0, 2);
		// y2.addElement(2, -4);
		// double result2 = dot(x2, y2);
		// System.out.println("Result 2: " + result2);


		SparseVector x = new SparseVector(5);
		x.addElement(0, 1);
		x.addElement(1, 2);
		x.addElement(2, 3);
		// x.removeElement(2);
		x.removeElement(1);
		System.out.println(x.toString());
	}
}