public class Stack<T> {
    private T[] stack;
    private int top = -1; // the index of the last inserted element

    // Default constructor
    public Stack() {
        this.stack = (T[]) new Object[5];
    }
    
    // Constructor
    public Stack(int size) {
        this.stack = (T[]) new Object[size];
    }

    // add an item to the top of the stack and throw an exception if the stack size is exceeded
    public void push(T item) throws StackException {
        if (this.top >= this.stack.length - 1) {  // this.stack.length - 1 is the very last element in the stack, anything past
            throw new StackException(this.stack.length - 1);   // and the stack is exceeded (overflow)
        }
        else {
            top++;
            this.stack[top] = item;
        }
    }

    // remove and return the object at the top of the stack
    public T pop() throws StackException {
        if (this.top < 0) {
            throw new StackException(top);
        }
        
        else {
            int tempTop = top; 
            top--;
            return stack[tempTop];
        }
    }
    
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();
        try {
            myStack.push(10);
            myStack.push(20);
            myStack.push(30);
            System.out.println(myStack.pop()); // prints 30
            myStack.push(40);
            myStack.push(50);
            myStack.push(60);
            myStack.push(70); // causes an exception!!
        } catch (StackException e) {
            System.out.println("Stack exception occurred!");
        }
    }

    public int getTop() {
        return this.top;
    }

}
