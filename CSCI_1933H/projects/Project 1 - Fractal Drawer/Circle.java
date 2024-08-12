// Written by Rohit Poduval, poduv006
// A program that represents Circles to be used with Canvas.java and FractalDrawer.java 

import java.awt.Color;

public class Circle {
    private double xPos;
    private double yPos;
    private double radius;
    private Color color;

    /*
     * INPUTS...
     * xPos: the x position of the center of the circle (double)
     * yPos: the y position of the center of the circle (double)
     * radius: the radius of the circle (double)
     */
    public Circle(double xPos, double yPos, double radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double calculatePerimeter() {
        return Math.PI * (this.radius * 2);
    }

    public double calculateArea() {
        return Math.PI * (Math.pow(radius, 2));
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // x, y position of the center of the circle 
    public void setPos(double x, double y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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

    public double getRadius() {
        return this.radius;
    }
}