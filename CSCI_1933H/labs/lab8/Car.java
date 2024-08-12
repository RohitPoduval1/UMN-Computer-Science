public class Car extends Vehicle {
    private double mpg;

    // default constructor
    public Car() {
        this.mpg = 30.0;
        nVehicles++;
    }

    public Car(double mpg) {
        this.mpg = mpg;
        nVehicles++;
    }
    
    public double getMPG() {
        return this.mpg;
    }

    @Override
    public void movingForward() {
        System.out.println("Car Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println("Car Moving Backward");
    }

    @Override
    public String toString() {
        return "Car: " + this.mpg;
    }
}