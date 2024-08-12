public class Helicopter extends Vehicle {
    private double mpg;

    // default constructor
    public Helicopter() {
        nVehicles++;
        this.mpg = 0.3;
    }

    public Helicopter(double mpg) {
        this.mpg = mpg;
        nVehicles++;
    }
    
    public double getMPG() {
        return this.mpg;
    }

    @Override
    public void movingForward() {
        System.out.println("Helicopter Moving Forward");
    }

    @Override
    public void movingBackward() {
        System.out.println("Helicopter Moving Backward");
    }

    public void movingUp() {
        System.out.println("Helicopter Moving Up");
    }

    public void movingDown() {
        System.out.println("Helicopter Moving Down");
    }

    @Override
    public String toString() {
        return "Helicopter: " + this.mpg;
    }
}
