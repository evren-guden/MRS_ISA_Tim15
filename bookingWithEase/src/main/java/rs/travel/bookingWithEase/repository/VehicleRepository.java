package rs.travel.bookingWithEase.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import rs.travel.bookingWithEase.model.Vehicle;

@Repository
public class VehicleRepository implements IVehicleRepository {

	private final ConcurrentMap<Long, Vehicle> vehicles = new ConcurrentHashMap<Long, Vehicle>();

	@Override
	public Collection<Vehicle> findAll() {
		return this.vehicles.values();
	}

	@Override
	public Vehicle update(Vehicle vehicle) {

		Long id = vehicle.getId();

		if (id != null) {
			this.vehicles.put(id, vehicle);
		}

		return vehicle;
	}

	@Override
	public Vehicle find(Long id) {
		return this.find(id);
	}

	@Override
	public void initValues() {
		
		Vehicle veh = new Vehicle();
		veh.setId((long) 1);
		veh.setColor("Red");
		veh.setGear("Automatic");
		veh.setRegistration_number("AA-1");
		veh.setType("type1");
		this.vehicles.put((long) 1, veh);
		
		veh = new Vehicle();
		veh.setId((long) 2);
		veh.setColor("Green");
		veh.setGear("Automatic");
		veh.setRegistration_number("Ab-1");
		veh.setType("type2");
		this.vehicles.put((long) 2, veh);
		
		veh = new Vehicle();
		veh.setId((long) 3);
		veh.setColor("Red");
		veh.setGear("Manual");
		veh.setRegistration_number("BA-1");
		veh.setType("type3");
		this.vehicles.put((long) 3, veh);
	}

}
