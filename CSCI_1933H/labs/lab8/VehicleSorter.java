import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VehicleSorter {
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		// TODO: Show TA for Milestone 3
		//	e.g. vehicles.add(new Vehicle());
		vehicles.add(new Car(60.0));
		vehicles.add(new Helicopter());
		vehicles.add(new Car());
		vehicles.add(new Train(500));
		vehicles.add(new Helicopter(2));

		// print each vehicle
		Collections.sort(vehicles);
		for (Vehicle v : vehicles) {
			System.out.println(v);
		}
	}
}
