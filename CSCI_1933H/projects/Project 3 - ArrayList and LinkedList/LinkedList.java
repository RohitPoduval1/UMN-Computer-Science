// Written by Rohit Poduval, poduv006

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private boolean isSorted;
    private int size = 0; 

    // Constructor to initialize list to an empty list
    public LinkedList() {
        this.head = null;
        this.isSorted = true;
    }

    @Override
    public boolean add(T element) {
        // if element is null, it will not be added to the list
        if (element == null) {
            return false;
        }
        else {
            // Case 1: adding to an empty list
            if (this.head == null) {
                this.head = new Node<T>(element, null); // just put the element in the head since the list is empty
            }
            // Case 2: the list has elements already in it
            else {
                T secondToLastElement = null; // the old last element before the new one was added

                // Traversing the list to the last element
                Node<T> current = this.head; 
                while (current != null) {
                    // Navigate to the last element
                    if (current.getNext() != null) {
                        current = current.getNext();
                    }
                    // At the last element
                    else {
                        secondToLastElement = current.getData();
                        current.setNext(new Node<T>(element, null)); // set the old last element to be the new last element with the new data
                        break;
                    }
                }
                // The last element in the list should be larger than the one before it.
                // If it is not, then it breaks sorted order
                if (element.compareTo(secondToLastElement) < 0) {
                    this.isSorted = false;
                }
            }
            this.size++;
            return true;
        }
    }

    @Override
    public boolean add(int index, T element) {
        // The index is out of bounds
        if (element == null || index < 0 || index >= this.size()) {
            return false;
        }
        // The index is in bounds
        else {
            // Case 1: the incoming node is added to the beginning of the list
            if (index == 0) {
                Node<T> prevHeadNode = this.head;
                this.head = new Node<T>(element, prevHeadNode); // the current head (the incoming node) is the new head that
            }
            // Case 2: the incoming Node is added to the end of the list
            else if (index == this.size() - 1) { 
                Node<T> current = this.head; 
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                Node<T> prevEndNode = current; // the while loop gets us to the last element 
                prevEndNode.setNext(new Node<T>(element)); // set the previous last element to point to the incoming last element
            }
            // Case 3: the incoming Node is added somewhere in between the start and end
            else {
                int ind = 0;
                Node<T> current = this.head; 
                
                while (ind != index - 1) {
                    current = current.getNext();
                    ind++;
                }
                // while loop gets us to ONE BEFORE the Node that should be replaced
                // so current is one before the node that is at the desired index
                // current -> desiredIndexNode -> whatCurrentPointedToBefore
                Node<T> oldNodeAtIndex = current.getNext();
                current.setNext(new Node<T>(element, oldNodeAtIndex));
            }
            this.size++;
            this.isSorted = this.checkSorted();
            return true;
        }
    }

    @Override
    public void clear() {
        this.head = null;
        this.isSorted = true;
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (this.isWithinBounds(index)) {
            // Accessing the data in the head node
            if (index == 0) { 
                return this.head.getData();
            }
            // Accessing data past the head node
            else { 
                int counter = 0;
                Node<T> current = this.head; 
                while (counter != index) {
                    current = current.getNext();
                    counter++;
                }
                return current.getData();
            }
        }
        else {
            return null;
        }
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }
        else {
            // There can be optimization if the list is sorted
            if (this.isSorted()) {
                int index = 0;

                Node<T> current = this.head; 
                while (current != null) {
                    // check all elements except the last
                    if (current.getNext() != null) {
                        // there is still a chance that the element is still in the list
                        if (current.getData().compareTo(element) <= 0) {
                            if (current.getData() == element) {
                                return index;
                            }
                            else {
                                current = current.getNext();
                                index++;
                            }
                        }
                        // the element does not exist since the current element is greater than it in a sorted list
                        else {
                            return -1;
                        }
                    }
                    // check the last element
                    else {
                        if (current.getData() == element) {
                            return index;
                        }
                        else {
                            return -1;
                        }
                    }
                }
                return -1;
            }
            else {
                int index = 0; 
                Node<T> current = this.head; 
                
                while(index < this.size()) {
                    // the element has been found and the loop is done
                    if (current.getData() == element) {
                        return index;
                    }
                    // element has not been found on this iteration and so we need to keep searching
                    else {
                        current = current.getNext();
                        index++;
                    }
                }
                return -1; // the element was not found despite looping through each Node in the list so return -1
            }
        }
    }

    @Override
    public boolean isEmpty() {
        // an empty linked list means that the head node is nulls since the head is the key to accessing the rest of the list
        return this.head == null;
    }

    // return the number of elements in the list
    // (null elements do not count as elements)
    // [4, 5, 3, null, null] would return 3
    @Override
    public int size() {
        return this.size;
    }

    // Sorts the elements in ascending order usng bubble sort
    @Override
    public void sort() {
        // there is no need to sort if the list is empty or only contains one element or it's already sorted
        if (head == null || head.getNext() == null || this.isSorted()) {
            return;
        }
        else {
            for (int i = 0; i < this.size(); i++) {   // iterate through each element in the list
                Node<T> current = this.head;
                Node<T> previous = null;

                while (current.getNext() != null) {
                    Node<T> next = current.getNext();
                    // the nodes current and one after current need to be swapped
                    if (current.getData().compareTo(next.getData()) > 0) {
                        
                        // perform the swap
                        Node<T> temp = next;
                        current.setNext(temp.getNext());
                        temp.setNext(current);
                        
                        if (previous == null) {  // if current is the head of the list
                            head = temp;         // the head node must be changed
                                                 // where temp is the node after the head node
                        }
                        else {
                            previous.setNext(temp);
                        }
                        previous = temp;
                    }
                    // the nodes do not need to be swapped since they are in the correct order
                    else {
                        // so all that needs to be done is to advance each of the variables
                        previous = current;
                        current = current.getNext();
                    }
                }
            }
            isSorted = true;
        }
    }

    @Override
    public T remove(int index) {
        if (this.isWithinBounds(index)) {
            T valueAtIndex = null;
            // Case 1: removing the head node
            if (index == 0) {
                Node<T> oldHead = this.head;
                this.head = oldHead.getNext();
                valueAtIndex = oldHead.getData();
            }
            // Case 2: removing the last element 
            else if (index == this.size() - 1) {
                Node<T> current = this.head;
                while (current.getNext().getNext() != null) {
                    current = current.getNext();
                }
                valueAtIndex = current.getNext().getData();
                current.setNext(null); // one before the last element in the list becomes the new last element
            }
            // Case 3: removing some intermediate element
            else {
                int ind = 0;
                Node<T> current = this.head; 
                
                while (ind != index - 1 && current.getNext() != null) {
                    current = current.getNext();
                    ind++;
                }
                // while loop gets us to ONE BEFORE the Node that should be removed
                // so current is one before the node that will be removed
                // current -> nodeThatWillBeRemoved -> someNode becomes current -> someNode
                valueAtIndex = current.getNext().getData();
                current.setNext(current.getNext().getNext());
            }
            // In any case that the removal is successful
            this.size--;          // the size is reduced
            this.isSorted = this.checkSorted();
            return valueAtIndex;  // and the element that is removed is returned
        }
        else {
            return null;
        }
    }

    @Override
    public void removeDuplicates() {
        if (this.size() <= 1) {
            return;
        }
        else {
            LinkedList<T> uniqueList = new LinkedList<T>(); // linked list that will contain all unique elements from the current list
            uniqueList.add(this.head.getData());

            Node<T> current = this.head;
            while (current != null) {  // loop through the entire current list
                // handle all elements except the last
                if (current.getNext() != null) {
                    if (! uniqueList.contains(current.getData())) {  // if the element in the current list is not in the uniqueList
                        uniqueList.add(current.getData());                 // then add the element to the uniqueList since it is unique so far
                    }                                                      // otherwise do nothing with the element since it is not unique
                    current = current.getNext();
                }
                // handle the last element in the same way as above
                else {
                    if (! uniqueList.contains(current.getData())) {
                        uniqueList.add(current.getData());
                    }
                    break;
                }
            }
            this.head = uniqueList.head;   // make the current list the unique list
            this.size = uniqueList.size(); // adjust the size of the current list to match the size of the uniqueList
            this.isSorted = this.checkSorted();
        }
    }

    @Override
    public void reverse() {
        Node<T> current = this.head;
        Node<T> previous = null;
        Node<T> next = null; 
        
        // 'next' is used to progress in the list
        // the reversing of pointers is done with current and previous since if you are looking at an unreversed list,
        // the current element will point to the previous element in the reversed list
        while (current != null) {
            next = current.getNext();
            current.setNext(previous); // reverse the pointer of current node
            
            // Move pointers one position ahead
            previous = current;
            current = next;
        }

        // current is null, but previous is the last node in the unreversed list
        // which is the first node (head) in the reversed list
        head = previous;

        this.isSorted = this.checkSorted();
    }

    @Override
    public void exclusiveOr(List<T> other) {
        if (other == null) {
            return;
        }
        else {
            LinkedList<T> otherList = (LinkedList<T>) other;
            this.removeDuplicates();
            otherList.removeDuplicates();

            // Create and populate a combined list with elements from both lists (this and other)
            LinkedList<T> combinedList = new LinkedList<T>();
            Node<T> current = this.head; 
            while (current.getNext() != null) {
                combinedList.add(current.getData());
                current = current.getNext();
            }
            combinedList.add(current.getData());

            current = otherList.head; 
            while (current.getNext() != null) {
                combinedList.add(current.getData());
                current = current.getNext();
            }
            combinedList.add(current.getData());

            // Create and populate commonElements which contains elements found in both lists
            LinkedList<T> commonElements = new LinkedList<T>();
            current = this.head;
            while (current != null) {
                if (current.getNext() != null) {
                    if (otherList.contains(current.getData())) {
                        commonElements.add(current.getData());
                    }
                    current = current.getNext();
                }
                else {
                    if (otherList.contains(current.getData())) {
                        commonElements.add(current.getData());
                    }
                    break;
                }
            }

            // Remove elements that both lists have in common ("items in this or other but NOT BOTH")
            // this or other is covered by combining the lists
            // the step takes care of the but not both step
            current = commonElements.head;
            while (current != null) {
                if (current.getNext() != null) {
                    if (combinedList.contains(current.getData())) {
                        combinedList.removeAll(current.getData());
                    }
                    current = current.getNext();
                }
                else {
                    if (combinedList.contains(current.getData())) {
                        combinedList.removeAll(current.getData());
                    }
                    break;
                }
            }
            this.size = combinedList.size();
            combinedList.sort();
            this.head = combinedList.head;
        }
    }

    @Override
    public T getMin() {
        if (this.size() == 0) {
            return null;
        }
        else if (this.size() == 1) {
            return this.get(0);
        }
        else {
            if (this.isSorted()) {
                return this.get(0);
            }
            else {
                T min = this.get(0);
                Node<T> current = this.head;
                while (current != null) {
                    if (current.getNext() != null) {
                        if (current.getData().compareTo(min) < 0) {
                            min = current.getData();
                        }
                        current = current.getNext();
                    }
                    else {
                        if (current.getData().compareTo(min) < 0) {
                            min = current.getData();
                        }
                        break;
                    }
                }
                return min;
            }
            // the smallest value in a sorted list by ascending order is just the first item
        }
    }

    @Override
    public T getMax() {
        if (this.isEmpty()) {
            return null;
        }
        else if (this.size() == 1) {
            return this.get(0);
        }
        else {
            if (this.isSorted()) {
                return this.get(this.size() - 1);
            }
            else {
                T max = this.get(0);
                Node<T> current = this.head;
                while (current != null) {
                    if (current.getNext() != null) {
                        if (current.getData().compareTo(max) > 0) {
                            max = current.getData();
                        }
                        current = current.getNext();
                    }
                    else {
                        if (current.getData().compareTo(max) > 0) {
                            max = current.getData();
                        }
                        break;
                    }
                }
                return max;
            }
        }
    }

    @Override
    public boolean isSorted() {
        return this.isSorted;
    }

    public String toString() {
        String result = "";
        if (this.size() == 0) {
            ;
        }
        else if (this.size() == 1) {
            result += String.valueOf(this.head.getData());
        }
        else {
            Node<T> current = this.head;
            while (current.getNext() != null) {
                if (current.getData() != null) { // there is actual data in the current node
                    result += (String.valueOf(current.getData()) + " ");
                }
                else { // the data in the current node is null, so add nothing to the result
                    ;
                }
                current = current.getNext();
            }
            if (current.getData() != null) {
                result += (String.valueOf(current.getData()));
            }
        }
        return result;
    }   

    // Custom method
    // Checks whether the given index is within the bounds of the calling object's list
    private boolean isWithinBounds(int index) {
        return 0 <= index && index < this.size();
    }

    // Custom method
    // Checks whether the calling object's list is sorted
    private boolean checkSorted() {
        if (this.head == null || this.size() <= 1) {
            return true; // An array with 0 or 1 element is always considered sorted
        }
        else {
            Node<T> current = this.head;
            while (current.getNext() != null) {
                if (current.getData().compareTo(current.getNext().getData()) > 0) {
                    return false;  // if any element is greater than its next element, the array is not sorted
                }
                current = current.getNext();
            }
            return true; // if all elements are in non-decreasing order, the array is sorted
        }
    }
    
    // Custom method
    // remove all instances of element in the list
    public void removeAll(T element) {
        LinkedList<T> newList = new LinkedList<T>();
        Node<T> current = this.head;
        while (current != null) {
            if (current.getNext() != null) {
                if ((current.getData() != null && current.getData().compareTo(element) != 0)) {
                    newList.add(current.getData());
                }
                current = current.getNext();
            }
            else {
                if ((current.getData() != null && current.getData().compareTo(element) != 0)) {
                    newList.add(current.getData());
                }
                break;
            }
        }
        this.head = newList.head;
        this.size = newList.size;
    }

    // Custom method
    // Checks whether the object calling the method has any instance of element in the list
    public boolean contains(T data) {
        Node<T> current = this.head;
        while (current != null) {
            if (current.getNext() != null) {
                if (data.compareTo(current.getData()) == 0) {
                    return true;
                }
                current = current.getNext();
            }
            else {
                if (data.compareTo(current.getData()) == 0) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
