// Written by Rohit Poduval, poduv006
// A program that represents Rectangles to be used with Canvas.java and FractalDrawer.java 

import java.awt.Color;

public class Rectangle {

    private double xPos;
    private double yPos;
    private double width;
    private double height;
    private Color color;

    /*
     * INPUTS...
     * x: the x position of the top left corner of the rectangle (double)
     * y: the y position of the top left corner of the rectangle (double)
     * width: the width of the rectangle (double)
     * height: the height of the rectangle (double)
     */
    public Rectangle(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public double calculatePerimeter() {
        return (width * 2) + (height * 2);
    }

    public double calculateArea() {
        return width * height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // x, y position of upper left corner
    public void setPos(double x, double y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }
}