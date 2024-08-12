// Written by Rohit Poduval, poduv006

public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] a; 
    private boolean isSorted; 
    private int size; 

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.a = (T[]) new Comparable[2];
        this.isSorted = true;
        this.size = 0;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        // the element is not null and is therefore valid
        else {
            // the list is at max capacity, so we need to add more space
            if (this.size() == this.a.length) {
                this.a = this.growAndCopy(this.a);
            }
            int endOfList = this.size();
            this.a[endOfList] = element;
            
            if (endOfList > 0 && this.a[endOfList].compareTo(this.a[endOfList - 1]) < 0) {
                this.isSorted = false;
            }
            this.size++;
            return true;
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean add(int index, T element) {
        if (!this.isWithinBounds(index) || element == null) {
            return false;
        }
        else {
            T[] newArray = null;

            // there is enough room for the element
            if (this.size() < this.a.length) {
                newArray = (T[]) new Comparable[this.a.length];
            }
            // there is not enough room for the element
            else if (this.size() == this.a.length) {
                newArray = (T[]) new Comparable[this.a.length * 2]; // make a new list with double the capacity of this.a
            }                                                      // growAndCopy() is not used because copying is done in a different way

            if (index == 0) {
                newArray[0] = element;
                for (int i = 0; i < this.size(); i++) {
                    newArray[i + 1] = this.a[i];
                }
            }
            else {
                // newArray = [0 : index] + element + [index : end]
                // The logic could be thought of in a similar way to Python's indexing where a new array contains
                // all elements of the old array, just adding 'element' in between the 2 parts of the old array 
                for (int i = 0; i < index; i++) {
                    newArray[i] = this.a[i];
                }
                newArray[index] = element;
                for (int i = index; i < this.size(); i++) {
                    newArray[i+1] = this.a[i];
                }
            }
            this.a = newArray.clone();  // set the current array to the new array with the new elements
            this.size++;
            this.isSorted = this.checkSorted();
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.isSorted = true;
        this.size = 0;
        this.a = (T[]) new Comparable[2];
    }           

    @Override
    public T get(int index) {
        if (this.isWithinBounds(index)) {
            return this.a[index];
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
            if (this.isSorted()) {
                int index = 0;

                for (T e : this.a) {
                    // there is still a chance that the element is still in the list
                    if (e != null && e.compareTo(element) <= 0) {
                        if (e == element && e != null) {
                            return index;
                        }
                        else {
                            index++;
                        }
                    }
                    // the element does not exist since the current element is greater than it in a sorted list
                    else {
                        return -1;
                    }
                }
                return -1;
            }
            else {
                int index = 0;
                for (T e : this.a) {
                    // the element has been found and the loop is done
                    if (e == element) {
                        return index;
                    }
                    // element has not been found on this iteration and so we need to keep searching
                    else {
                        index++;    
                    }
                    
                }
                return -1; // the element was not found despite looping through the entire list
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void sort() {
        if (this.isSorted || this.size() == 0 || this.size() == 1) {
            return;
        }
        else {
            boolean swapped = true; 
            // bubble sort algorithm modified for an arraylist instead of a standard array
            for (int i = 0; i < this.size() && swapped == true; i++) {
                swapped = false;
                for (int j = 1; j < this.size() - i; j++) {
                    if (this.a[j].compareTo(this.a[j - 1]) < 0) {
                        swapped = true;
                        T temp = a[j];
                        a[j] = a[j-1];
                        a[j-1] = temp;
                    }
                }
            }
            this.isSorted = true;
        }
    }

    @Override
    public T remove(int index) {
        if (! this.isWithinBounds(index)) {
            return null; 
        }
        else  {
            /*
             * make a new array to hold all elements except the removed element (a.length - 1)
             * newArray = old[0:index] + old[index + 1 : a.length] 
             * The logic could be thought of in a similar way to Python's indexing where a new array contains
             * all elements of the old array, just skipping the one that is to be removed.
             */
             T toReturn = this.a[index];
            
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Comparable[this.a.length - 1];
            for (int i = 0; i < index; i++) {
                newArray[i] = this.a[i];
            }
            for (int i = index + 1; i < this.size; i++) {
                newArray[i-1] = this.a[i];                
            }   
            this.a = newArray;
            this.size--;
            this.isSorted = this.checkSorted();
            return toReturn;
        }
    }
    
    // Custom method
    // removes all instances of 'element'
    public void removeAll(T element) {
        int count = 0;
        for (T e : this.a) {
            if ((e != null && e.compareTo(element) == 0)) {
                count++;
            }
        }
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Comparable[this.a.length - count];
        int index = 0;
    
        for (T e : this.a) {
            if ((e != null && e.compareTo(element) != 0)) {
                newArray[index] = e;
                index++;
            }
        }
        this.a = newArray;
        this.size = index; // Update size to reflect the number of elements in the new array
    }

    @Override
    public void removeDuplicates() {
        ArrayList<T> uniqueList = new ArrayList<T>();  // arrayList that will contain all unique elements from the current list
        if (this.size() == 0 || this.size() == 1) {    // it is not possible to have duplicates in a list of size 0 or 1
            return;
        }
        else {
            uniqueList.add(this.a[0]);  // add the first element from currentList to uniqueList since it will always be unique
            for (T element : this.a) {
                if (! uniqueList.contains(element)) {  // If 'uniqueList' does not have the element in it,
                    uniqueList.add(element);              // then add it because it is unique so far. 
                }
            }
            this.a = uniqueList.a;
            this.size = uniqueList.size();
            this.isSorted = this.checkSorted();
        }
    }

    @Override
    public void reverse() {
        if (this.size() == 0 || this.size() == 1) {
            return;
        }
        else {
            /*
             * The logic for reversing is very similar to bubble sort. If you take the first element as swap it as you do with bubble sort,
             * the element will make its way (bubble) to the top of the list to its final position. If you then take the first element again 
             * and swap it in the same way except stopping one before the final position in the array (since the first iteration already took care of that),
             * then you have two elements that are in their final reversed position. 
             */
            boolean swapped = true; 
            int finalPos = this.size() - 1; // used to know when to stop swapping
            int currIndex = 0;              // used to know when to stop swapping
            for (int i = 0; i < this.size() && swapped == true; i++) {
                swapped = false;
                for (int j = 1; j < this.size() - i; j++) {
                    if (currIndex != finalPos) {
                        swapped = true;
                        T temp = a[j];
                        a[j] = a[j-1];
                        a[j-1] = temp;
                    }
                    currIndex++;
                }
                finalPos--;
            }
            this.isSorted = this.checkSorted();
        }
    }

    @Override
    public void exclusiveOr(List<T> other) {
        if (other == null) {
            return;
        }
        else {
            ArrayList<T> otherList = (ArrayList<T>) other;
            this.removeDuplicates();
            otherList.removeDuplicates();
            
            ArrayList<T> combinedList = new ArrayList<T>();
            // Populate the combined list with elements from both lists (this and other)
            for (int i = 0; i < this.size(); i++) {
                combinedList.add(this.get(i));
            }
            for (int i = 0; i < otherList.size(); i++) {
                combinedList.add(otherList.get(i));
            }

            ArrayList<T> commonElements = new ArrayList<T>();
            // Populate commonElements which contains elements found in both lists
            for (int i = 0; i < this.size(); i++) {
                if (otherList.contains(this.get(i))) {
                    commonElements.add(this.get(i));
                }
            }

            // Remove elements that both lists have in common ("items in this or other but NOT BOTH")
            for (int i = 0; i < commonElements.size(); i++) {
                if (combinedList.contains(commonElements.get(i))) {
                    combinedList.removeAll(commonElements.get(i));
                }
            }
            this.size = combinedList.size();
            combinedList.sort();
            this.a = combinedList.a;
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
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).compareTo(min) < 0) {
                        min = this.a[i];
                    }
                }
                return min;
            }
        }
    }

    @Override
    public T getMax() {
        if (this.size() == 0) {
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
                for (int i = 0; i < this.size(); i++) {
                    if (max.compareTo(this.get(i)) < 0) {
                        max = this.get(i);
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
        String toReturn = "";
        for (T element : this.a) {
            if (element != null) {
                toReturn += (String.valueOf(element) + '\n');
            }
        }
        return toReturn.strip();
    }

    // Custom method
    // double the size and copy all elements into a new array
    private T[] growAndCopy(T[] arrayToGrow) {
        @SuppressWarnings("unchecked")
        T[] resizedArray = (T[]) new Comparable[arrayToGrow.length * 2];
        for (int i = 0; i < arrayToGrow.length; i++) {
            resizedArray[i] = arrayToGrow[i];
        }
        return resizedArray;
    }

    // Custom method
    // Checks whether the given index is within the bounds of the calling object's list
    public boolean isWithinBounds(int index) {
        return 0 <= index && index < this.size();
    }

    // Custom method
    // Checks whether the object calling the method has any instance of element in the list
    public boolean contains(T element) {
        for (T e : this.a) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }

    // Custom method
    // Checks whether the calling object's list is sorted
    private boolean checkSorted() {
        if (this.a == null || this.size <= 1) {
            return true; // An array with 0 or 1 element is always considered sorted
        }
        else {
            for (int i = 0; i < this.size-1; i++) {
                if (this.a[i].compareTo(this.a[i + 1]) > 0) {
                    return false; // if any element is greater than its next element, the array is not sorted
                }
            }
            return true; // If all elements are in non-decreasing order, the array is sorted
        }
    }
}
