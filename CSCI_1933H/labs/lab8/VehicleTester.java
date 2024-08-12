public class VehicleTester {
    public static void main(String[] args) {
        Bike mongoose = new Bike();
        Scooter moped = new Scooter();

        mongoose.movingForward();
        moped.movingForward();

        mongoose.movingBackward();
        moped.movingBackward();



        Car car = new Car(30.0);
        System.out.println(car);
        car.movingForward();
        car.movingBackward();
        System.out.println("Total Number of Vehicles: " + Vehicle.getNumVehicles());


        Train train = new Train();
        System.out.println(train);
        train.movingForward();
        train.movingBackward();

        Train train2 = new Train(400);
        System.out.println("Miles per gallon: " + train2.getMPG());
        System.out.println("Total Number of Vehicles: " + Vehicle.getNumVehicles());  // 3 for car, train1, train2


        Helicopter helicopter = new Helicopter();
        System.out.println(helicopter);
        helicopter.movingForward();
        helicopter.movingBackward();

        Helicopter helicopter2 = new Helicopter(1);
        System.out.println("Miles per gallon: " + helicopter2.getMPG());
        System.out.println("Total Number of Vehicles: " + Vehicle.getNumVehicles());  // 5 for car, train1, train2, heli1, heli2
    }
}
