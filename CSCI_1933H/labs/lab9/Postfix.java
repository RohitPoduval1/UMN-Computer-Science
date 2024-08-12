public class Postfix {
    static double evaluate(String[] expression) throws StackException{
        Stack<String> stack = new Stack<String>();
        for (String token : expression) {
            try {
                Double.parseDouble(token);
                stack.push(token);
            }
            catch (NumberFormatException e) {
                double num1 = Double.parseDouble(stack.pop()); 
                double num2 = Double.parseDouble(stack.pop()); 
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(num2 + num1));
                        break;
                    case "-" :
                        stack.push(String.valueOf(num2 - num1));
                        break;
                    case "*":
                        stack.push(String.valueOf(num2 * num1));
                        break;
                    case "/":
                        stack.push(String.valueOf(num2 / num1));
                        break;
                }
            }
        }
        return Double.parseDouble(stack.pop());
    }

    public static void main(String[] args) {
        String[] test = {"1", "2", "+", "3", "4", "+", "/" + "4"};
        try{
            System.out.println(Postfix.evaluate(test));
        }
        catch (StackException e) {
            System.out.println("Error: " + e.getSize());
        }
    }
}
