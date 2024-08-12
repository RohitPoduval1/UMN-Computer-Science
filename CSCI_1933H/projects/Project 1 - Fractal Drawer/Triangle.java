// Written by Rohit Poduval, poduv006
// A program that represents Triangles to be used with Canvas.java and FractalDrawer.java 

import java.awt.Color;

public class Triangle {
    // Assume the triangles we want to draw are isosceles

    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Color color;

    /*
     * INPUTS...
     * xPos: the x position of the bottom left corner of the triangle (double)
     * yPos: the y position of the bottom left corner of the triangle (double)
     * width: the width of the triangle (double)
     * height: the height of the triangle (double)
     */
    public Triangle(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    // You can assume the triangles we want to draw are isosceles triangles
    public double calculatePerimeter() {
        double sideLength = Math.sqrt(Math.pow(width, 2)/4 + Math.pow(height, 2));  // found using the pythagorean theorem using half the width (width/2) squared 
        return (width + 2*sideLength);                                                  // and the height squared to find the length of one side
    }

    public double calculateArea() {
        return (0.5 * this.width * this.height);  
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // x, y position of bottom left corner 
    public void setPos(double x, double y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public void setWidth(double w) {
        this.width = w;
    }

    public Color getColor() {
        return this.color;
    }

    public double getXPos() {
        return this.xPos;
    }

    public double getYPos() {
        return this.yPos;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}
