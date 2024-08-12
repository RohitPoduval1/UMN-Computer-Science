// Milestone 3 for Lab 1
public class Circle {
    double radius;

    public static void main(String[] args) {
        Circle c = new Circle(22);
        Circle b = new Circle(22);
        System.out.println(c.getRadius());
        System.out.println(c.equals(b));
        c.setRadius(13);
        System.out.println(c.getRadius());
        System.out.println(c.equals(b));
        System.out.println(c.getArea());
        System.out.println(c.getDiameter());
        System.out.println(c.getCircumference());
    }

    public Circle(double r) {
        radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double newRadius) {
        radius = newRadius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    public boolean equals(Circle circle) {
        return this.radius == circle.radius;
    }
}
