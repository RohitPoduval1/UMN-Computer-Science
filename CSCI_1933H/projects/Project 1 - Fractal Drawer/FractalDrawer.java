// Written by Rohit Poduval, poduv006
// FractalDrawer draws a fractal of a shape indicated by user input and calculates its area

import java.awt.Color;     // For displaying colors 
import java.util.Scanner;  // For user input

public class FractalDrawer {

    private double totalArea = 0;  

    // Blue Color Palette
    private static Color yaleBlue = new Color(29, 100, 128);
    private static Color vividSkyBlue = new Color(35, 189, 250);
    private static Color lightSkyBlue = new Color(157, 220, 245);
    private static Color pureBlue = new Color(0, 0, 255);

    // Red Color Palette
    private static Color strongRed = new Color(192, 0, 0);
    private static Color vividRed = new Color(255, 51, 52);
    private static Color flamingoPink = new Color(255, 111, 119);
    private static Color lightPink = new Color(255, 187, 193);
    private static Color pureRed = new Color(255, 0, 0);

    // Green Color Palette
    private static Color castletonLighter = new Color(47, 111, 84);
    private static Color castletonDarker = new Color(15, 81, 50);
    private static Color britishRacing = new Color(47, 111, 52);
    private static Color darkGreen = new Color(24, 57, 43);
    private static Color pureGreen = new Color(0, 255, 0);
    
    
    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {
        Canvas canvas = new Canvas(2000, 2000);
        // Thing to note: the color chosen in the call to each specific drawShapeFractal() method will only appear once,
        // so black will only appear once in the pattern as the color of the largest shape (referred to as the base shape)
        
        // Once the level is bigger than 9, the canvas struggles to display all the shapes
        int level = 9;

        switch (type) {  
            case "RECTANGLE":
                this.drawRectangleFractal(250, 250, 750, 400, Color.BLACK, canvas, level);
                return this.totalArea;

            case "TRIANGLE":
                this.drawTriangleFractal(2*200, Math.sqrt(3)*200, 750, 1000, Color.BLACK, canvas, level);
                // The ratio for an equilateral triangle's width to height is 2 to sqrt(3) so for ease of scaling,
                // the ratio is multiplied by the same number to change the size of the triangle without changing the proportions
                // Once the level is bigger than 11, the canvas struggles to display all the shapes
                return this.totalArea;

            case "CIRCLE":
                this.drawCircleFractal(150, 750, 500, Color.BLACK, canvas, level);
                return this.totalArea;

            default:
                return -1;  // -1 is an impossible area to show the user that something went wrong
                            // Ideally this should not be reached because the user input should be gotten from getFractalBaseInput()
        }
    }

    // drawTriangleFractal draws a triangle fractal on the provided Canvas object using recursive techniques
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        // A level of 0 means that no shape is drawn on the canvas
        if (level == 0) {
            return;
        }
        // A level of 1 means that a single shape is drawn on the canvas
        else if (level == 1) {
            Triangle baseTriangle = new Triangle(x, y, width, height);
            baseTriangle.setColor(c);
            can.drawShape(baseTriangle);
            this.totalArea += baseTriangle.calculateArea();
        }
        // Recursion is needed for a level greater than 1
        else {
            Triangle triangle = new Triangle(x, y, width, height);
            triangle.setColor(c);
            can.drawShape(triangle);
            this.totalArea += triangle.calculateArea();
            
            double newWidth = width / 2;    // The further the levels get, the smaller the triangles get
            double newHeight = height / 2;  // The triangle size is halved for each level  

            // Conditionals ensure that triangles drawn are unique colors so that overlapping shapes are distinct from one another
            if (c.equals(yaleBlue)) {
                drawTriangleFractal(newWidth, newHeight, x-newWidth, y, pureBlue, can, level-1);                 // Bottom left 
                drawTriangleFractal(newWidth, newHeight, x+width, y, vividSkyBlue, can, level-1);                // Bottom right
                drawTriangleFractal(newWidth, newHeight, x+0.5*newWidth, y-height, lightSkyBlue, can, level-1);  // Top
            }
            else if (c.equals(vividSkyBlue)) {
                drawTriangleFractal(newWidth, newHeight, x-newWidth, y, pureBlue, can, level-1);                 // Bottom left 
                drawTriangleFractal(newWidth, newHeight, x+width, y, yaleBlue, can, level-1);                    // Bottom right
                drawTriangleFractal(newWidth, newHeight, x+0.5*newWidth, y-height, lightSkyBlue, can, level-1);  // Top
            }
            else if (c.equals(lightSkyBlue)) {
                drawTriangleFractal(newWidth, newHeight, x-newWidth, y, yaleBlue, can, level-1);                 // Bottom left 
                drawTriangleFractal(newWidth, newHeight, x+width, y, vividSkyBlue, can, level-1);                // Bottom right
                drawTriangleFractal(newWidth, newHeight, x+0.5*newWidth, y-height, lightSkyBlue, can, level-1);  // Top
            }
            else if (c.equals(pureBlue)) {
                drawTriangleFractal(newWidth, newHeight, x-newWidth, y, vividSkyBlue, can, level-1);             // Bottom left 
                drawTriangleFractal(newWidth, newHeight, x+width, y, yaleBlue, can, level-1);                    // Bottom right
                drawTriangleFractal(newWidth, newHeight, x+0.5*newWidth, y-height, lightSkyBlue, can, level-1);  // Top
            }
            else if (c.equals(Color.BLACK)) {
                drawTriangleFractal(newWidth, newHeight, x-newWidth, y, yaleBlue, can, level-1);                 // Bottom left 
                drawTriangleFractal(newWidth, newHeight, x+width, y, vividSkyBlue, can, level-1);                // Bottom right
                drawTriangleFractal(newWidth, newHeight, x+0.5*newWidth, y-height, lightSkyBlue, can, level-1);  // Top
            }
        }
    }

    // drawCircleFractal draws a circle fractal on the provided Canvas object using recursive techniques
    /* Implementation is very similar to DrawTriangleFractal() in the sense that...
        -A level of 0 means that no shape is drawn
        -A level of 1 means that only 1 shape is drawn
        -A level greater than 1 means that recursion is necessary
    */
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        if (level == 0) {
            return;
        }
        else if (level == 1) {
            Circle baseCircle = new Circle(x, y, radius);
            baseCircle.setColor(c);
            can.drawShape(baseCircle);
            this.totalArea += baseCircle.calculateArea();
        }
        else {
            Circle circle = new Circle(x, y, radius);
            circle.setColor(c);
            can.drawShape(circle);
            this.totalArea += circle.calculateArea();
            
            // The further the levels get, the smaller the circles get
            double newRadius = radius / 2;  

            // Conditionals ensure that triangles drawn are unique colors so that overlapping shapes are distinct from one another
            if (c.equals(strongRed)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), vividRed, can, level-1);      // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), flamingoPink, can, level-1);  // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, lightPink, can, level-1);       // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, pureRed, can, level-1);         // Right
            }
            else if (c.equals(vividRed)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), pureRed, can, level-1);       // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), flamingoPink, can, level-1);  // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, lightPink, can, level-1);       // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, pureRed, can, level-1);         // Right
            }
            else if (c.equals(flamingoPink)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), vividRed, can, level-1);      // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), strongRed, can, level-1);     // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, lightPink, can, level-1);       // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, pureRed, can, level-1);         // Right
            }
            else if (c.equals(pureRed)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), vividRed, can, level-1);      // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), lightPink, can, level-1);     // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, strongRed, can, level-1);       // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, flamingoPink, can, level-1);    // Right
            }
            else if (c.equals(lightPink)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), vividRed, can, level-1);      // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), strongRed, can, level-1);     // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, flamingoPink, can, level-1);    // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, pureRed, can, level-1);         // Right
            }
            else if (c.equals(Color.BLACK)) {
                drawCircleFractal(newRadius, x, y-(radius+newRadius), vividRed, can, level-1);      // Top
                drawCircleFractal(newRadius, x, y+(radius+newRadius), strongRed, can, level-1);     // Bottom
                drawCircleFractal(newRadius, x-radius-newRadius, y, lightPink, can, level-1);       // Left
                drawCircleFractal(newRadius, x+radius+newRadius, y, pureRed, can, level-1);         // Right
            }
        }
    }

    // drawRectangleFractal draws a rectangle fractal on the provided Canvas object using recursive techniques 
    /* Implementation is very similar to DrawTriangleFractal() in the sense that...
        -A level of 0 means that no shape is drawn
        -A level of 1 means that only 1 shape is drawn
        -A level greater than 1 means that recursion is necessary
    */
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if (level == 0) {
            return;
        }
        else if (level == 1) {
            Rectangle baseRectangle = new Rectangle(x, y, width, height);
            baseRectangle.setColor(c);
            can.drawShape(baseRectangle);
            this.totalArea += baseRectangle.calculateArea();
        }
        else {
            Rectangle rectangle = new Rectangle(x, y, width, height);
            rectangle.setColor(c);
            can.drawShape(rectangle);
            this.totalArea += rectangle.calculateArea();
            
            // The further the levels get, the smaller the rectangles get
            double newWidth = width / 2;
            double newHeight = height / 2;

            // Conditionals ensure that triangles drawn are unique colors so that overlapping shapes are distinct from one another
            if (c.equals(pureGreen)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, castletonLighter, can, level-1);  // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, castletonDarker, can, level-1);      // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, britishRacing, can, level-1);        // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, darkGreen, can, level-1);               // Bottom-right
            }
            else if (c.equals(castletonLighter)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, castletonDarker, can, level-1);   // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, britishRacing, can, level-1);        // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, darkGreen, can, level-1);            // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, pureGreen, can, level-1);               // Bottom-right
            }
            else if (c.equals(castletonDarker)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, britishRacing, can, level-1);    // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, castletonLighter, can, level-1);    // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, pureGreen, can, level-1);           // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, darkGreen, can, level-1);              // Bottom-right
            }
            else if (c.equals(britishRacing)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, pureGreen, can, level-1);        // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, darkGreen, can, level-1);           // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, castletonLighter, can, level-1);    // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, castletonDarker, can, level-1);        // Bottom-right
            }
            else if (c.equals(darkGreen)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, pureGreen, can, level-1);   // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, darkGreen, can, level-1);      // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, britishRacing, can, level-1);  // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, castletonLighter, can, level-1);  // Bottom-right
            }
            else if (c.equals(Color.BLACK)) {
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y-newHeight, darkGreen, can, level-1);     // Top-left
                drawRectangleFractal(newWidth, newHeight, x+width, y-newHeight, pureGreen, can, level-1);        // Top-right
                drawRectangleFractal(newWidth, newHeight, x-newWidth, y+height, castletonDarker, can, level-1);  // Bottom-left
                drawRectangleFractal(newWidth, newHeight, x+width, y+height, castletonLighter, can, level-1);    // Bottom-right
            }
        }
    }

    // Uses a while loop to guarantee that the user input for fractalBase is one of the desired shapes
    // The user is constantly prompted to choose a shape until a desired shape is selected
    // The shape, Circle, Rectangle, or Triangle is returned with all leading and trailing whitespace removed and is made to all uppercase characters
    public static String getFractalBaseInput() {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter your choice of the shape of the fractal base (choose 'Circle','Triangle', or 'Rectangle'): ");  // Prompt the user to choose a shape
        String fractalBase = myScanner.nextLine();                                                                                // and read the input
        fractalBase = fractalBase.trim().toUpperCase();                                                                           // clean up and standardize the input
        while (!(fractalBase.equals("TRIANGLE") | fractalBase.equals("RECTANGLE") | fractalBase.equals("CIRCLE"))) {
            System.out.print("Invalid input, please enter either 'Circle','Triangle', or 'Rectangle': ");
            fractalBase = myScanner.nextLine();
            fractalBase = fractalBase.trim().toUpperCase();
        }
        myScanner.close();
        return fractalBase;
    }
    
    public static void main(String[] args){
        // Be sure to enter fullscreen for seeing the entire shape
        // Otherwise the image may be cut off
        FractalDrawer daVinci = new FractalDrawer();        
        String fractalBase = FractalDrawer.getFractalBaseInput();
        double fractalArea = daVinci.drawFractal(fractalBase);
        System.out.println("The area of the fractal is " + fractalArea);
    }
}
