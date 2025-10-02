package Service;

import DAO.VehicleDAO;
import Models.Enums.VehicleTypes;
import Models.Vehicles.Car;
import Models.Vehicles.Helicopter;
import Models.Vehicles.Motorcycle;
import Models.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    private static VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public static List<Vehicle> listAllVehicles(int page) {

        List<Vehicle> vehicles = new ArrayList<>();

        vehicles = VehicleDAO.listAllVehicles(page);

        return vehicles;

    }

    public static void deleteVehicle(long vehicleId) {

        vehicleDAO.deleteVehicle(vehicleId);
    }

    public int getTotalPage() {
        return vehicleDAO.findTotalPage();
    }


    public void createVehicle(String brand, String model, BigDecimal price, VehicleTypes vehicleType) {

        Vehicle vehicle = null;

        switch (vehicleType) {
            case CAR -> {
                vehicle = new Car(brand, model, vehicleType, price);
                vehicle.setVehicleTypeId(Long.valueOf(1));
                break;
            }
            case MOTORCYCLE -> {
                vehicle = new Motorcycle(brand, model, vehicleType, price);
                vehicle.setVehicleTypeId(Long.valueOf(2));
                break;
            }
            case HELICOPTER -> {
                vehicle = new Helicopter(brand, model, vehicleType, price);
                vehicle.setVehicleTypeId(Long.valueOf(3));
                break;
            }
            default -> System.out.println("Invalid vehicle type!");
        }

        vehicleDAO.createVehicle(vehicle);
    }

    public static List<Vehicle> searchByBrand(String brandToSearch, int page) {

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleDAO.searchByBrand(brandToSearch, page);
        return vehicles;
    }

    public static List<Vehicle> searchByVehicleType(int vehicleTypeToSearch, int page) {

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleDAO.searchByVehicleType(vehicleTypeToSearch, page);

        return vehicles;
    }

    public static List<Vehicle> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int page) {

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = vehicleDAO.searchByPriceRange(minPrice, maxPrice, page);

        return vehicles;

    }

    public static void updateVehicle(long vehicleId, String columnName, String update){

        Vehicle v = vehicleDAO.findById(vehicleId);
        vehicleDAO.updateVehicle (v, columnName, update);
    }
}

