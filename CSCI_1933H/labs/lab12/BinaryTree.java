public class BinaryTree<V extends Comparable<V>> {
    private Node<V> root;
    private int size; 

    public BinaryTree(Node<V> root) {
        this.root = root;
    }

    public Node<V> getRoot() {
        return this.root;
    }

    public void printInorder() {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node<V> node) {
        if (node == null) {
            return;
        }
        else {
            printInOrderHelper(node.getLeft());       // traverses the left branch, 
            System.out.print(node.getValue() + " ");  // then visits the node (which means printing the value in this case),
            printInOrderHelper(node.getRight());      // then traverses the right branch
        }
    }

    public void printPreorder(){
        printPreorderHelper(root);
    }
    private void printPreorderHelper(Node<V> node) {
        // DFS style of traversal
        if (node == null) {
            return;
        }
        else {
            System.out.print(node.getValue() + " ");
            this.size++;
            printPreorderHelper(node.getLeft());
            printPreorderHelper(node.getRight());
        }
    }

    public void printPostorder() {
        printPostorderHelper(root);
    }
    private void printPostorderHelper(Node<V> node) {
        if (node == null) {
            return;
        }
        else {
            printPostorderHelper(node.getLeft());       // first traverses the left branch, 
            printPostorderHelper(node.getRight());      // then the right branch, 
            System.out.print(node.getValue() + " ");  // and then visits the root
        }
    }

    private void treeToArray(Node<V> node, V[] array, int index) {
        if (node == null) {
            return;
        }
        else {
            // TODO: Fix manual solution (I don't know how)
            // I think +3 works because of the number of recursive calls made, but I don't think +3 will scale up for different inputs
            array[index] = node.getValue();
            treeToArray(node.getLeft(), array, index+1);
            treeToArray(node.getRight(), array, index+3);
        }
    }
    // return an array of all the values in a binary tree in ascending order
    public V[] flatten() {
        @SuppressWarnings("unchecked")
        V[] treeInArrayForm = (V[]) new Comparable[this.size];
        this.treeToArray(this.getRoot(), treeInArrayForm, 0);
        this.sort(treeInArrayForm);
        return treeInArrayForm;
    }

    // bubble sort
    // useful for flatten
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void sort(Comparable[] a) {
        int i, j;
        Comparable temp;
        boolean swapped = true;
        for (i = 0; i < a.length && swapped == true; i++) {
            swapped = false;
            for (j = 1; j < a.length - i; j++) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    swapped = true;
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    public void invert() {
        invertHelper(root);
    }

    public void invertHelper(Node<V> node) {
        if (node == null) {
            return;
        }
        else {
            // swap the left and right nodes
            Node<V> temp = node.getLeft();  
            node.setLeft(node.getRight());
            node.setRight(temp);

            invertHelper(node.getLeft());
            invertHelper(node.getRight());
        }
    }

    // public boolean containsSubtree(BinaryTree<V> other) {
    //     // return whether the tree passed into the method is a subtree of the tree
    //     // that it is called from
    //     if (other == null) {
    //         return true;
    //     }
    //     else {
            
    //     }
    // }
    

    // Main contains tests for each milestone.
    // Do not modify existing tests.
    // Un-comment tests by removing '/*' and '*/' as you move through the milestones.
    public static void main (String args[]) {
        // Tree given for testing on
        BinaryTree<Integer> p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));

        // Milestone 1 (Traversing)
        System.out.println("--- Milestone 1 ---");
        // printInOrder() test
        System.out.print("Expected: 4 2 5 1 3" + System.lineSeparator() + "Actual:   ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        
        // printPreOrder() test
        System.out.print("Expected: 1 2 4 5 3" + System.lineSeparator() + "Actual:   ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        
        // printPostOrder() test
        System.out.print("Expected: 4 5 2 3 1" + System.lineSeparator() + "Actual:   ");
        p1Tree.printPostorder();
        System.out.println();


        // Milestone 2 (flatten) -- expected output: 1 2 3 4 5
        System.out.println(System.lineSeparator() + "--- Milestone 2 ---");
        System.out.print("Expected: 1 2 3 4 5" + System.lineSeparator() + "Actual:   ");
        Comparable[] array_representation = p1Tree.flatten();
        
        for (int i = 0; i < array_representation.length; i++) {
            System.out.print(array_representation[i] + " ");
        }
        System.out.println();

        // Milestone 3 (invert)
        System.out.println(System.lineSeparator() + "--- Milestone 3 ---");

        p1Tree.invert();

        System.out.print("Expected: 3 1 5 2 4" + System.lineSeparator() + "Actual:   ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 3 2 5 4" + System.lineSeparator() + "Actual:   ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 3 5 4 2 1" + System.lineSeparator() + "Actual:   ");
        p1Tree.printPostorder();
        System.out.println();

        // Milestone 4 (containsSubtree)
        /*
        System.out.println(System.lineSeparator() + "--- Milestone 4 ---");

        p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p2Tree = new BinaryTree<Integer>(new Node<Integer>(2,
                new Node<Integer>(4, null, null),
                new Node<Integer>(5, null, null)));
        BinaryTree<Integer> p3Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2, null, null),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p4Tree = null;

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p2Tree));
        System.out.println();

        System.out.print("Expected: false" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p3Tree));
        System.out.println();

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p4Tree));
        */

    }
}
