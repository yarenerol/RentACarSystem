package DAO;

import Constants.RentACarConstants;
import Models.Enums.VehicleTypes;
import Models.Vehicles.Car;
import Models.Vehicles.Helicopter;
import Models.Vehicles.Motorcycle;
import Models.Vehicles.Vehicle;
import Utils.DBUtil;
import Utils.SQLScripts;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.SQLScripts.searchByBrandScript;

public class VehicleDAO {


    public static List<Vehicle> listAllVehicles(int page) {


        List<Vehicle> vehicles = new ArrayList<>();     //fonksiyonun geri dönüşüne bu listeyi atamak için try-catch'İn dışında açıyorum.

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLScripts.listAllVehiclesScript);

            int size = RentACarConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setInt(1, size );
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                String vehicleTypeName = rs.getString("vehicle_type");

                VehicleTypes type = null;
                if (vehicleTypeName != null) {
                    switch (vehicleTypeName) {
                        case "Car": type = VehicleTypes.CAR; break;
                        case "Motorcycle": type = VehicleTypes.MOTORCYCLE; break;
                        case "Helicopter": type = VehicleTypes.HELICOPTER; break;
                    }
                }

                Vehicle v = new Vehicle(
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getLong("vehicle_type_id"),
                        rs.getString("vehicle_type")){};

                v.setId(rs.getLong("id"));
                v.setVehicleTypeId(rs.getLong("vehicle_type_id"));

                vehicles.add(v);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public int findTotalPage() {
        try {
            Connection connection = DBUtil.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SQLScripts.totalPageCountScript);

            if(rs.next()){
                int totalRows = rs.getInt(1);
                return (int) Math.ceil((double) totalRows/RentACarConstants.PAGE_SIZE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void createVehicle (Vehicle vehicle){

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLScripts.createVehicleScript);

            ps.setString(1, vehicle.getBrand());
            ps.setString(2, vehicle.getModel());
            ps.setString(3, String.valueOf(vehicle.getVehicleType()));
            ps.setBigDecimal(4, vehicle.getPrice());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Vehicle> searchByBrand(String brandToSearch, int page) {

        List<Vehicle> vehicles = new ArrayList<>();     //fonksiyonun geri dönüşüne bu listeyi atamak için try-catch'İn dışında açıyorum.

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(searchByBrandScript);

            int size = RentACarConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setString(1, "%" + brandToSearch + "%");
            ps.setInt(2, size);
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String vehicleTypeName = rs.getString("vehicle_type");

                VehicleTypes type = null;
                if (vehicleTypeName != null) {
                    switch (vehicleTypeName) {
                        case "Car":
                            type = VehicleTypes.CAR;
                            break;
                        case "Motorcycle":
                            type = VehicleTypes.MOTORCYCLE;
                            break;
                        case "Helicopter":
                            type = VehicleTypes.HELICOPTER;
                            break;
                    }
                }

                Vehicle v = new Vehicle(
                        rs.getString("brand"),
                        rs.getString("model")) {
                };
                rs.getInt("vehicle_type_id");
                rs.getString("vehicle_type");

                v.setId(rs.getLong("id"));
                v.setVehicleTypeId(rs.getLong("vehicle_type_id"));

                vehicles.add(v);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    };

    public static List<Vehicle> searchByVehicleType(int vehicleTypeToSearch, int page) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SQLScripts.searchByTypeScript);

            int size = RentACarConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setInt(1, vehicleTypeToSearch);
            ps.setInt(2, size);
            ps.setInt(3, offset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vehicleBrand = rs.getString("brand");
                String vehicleModel = rs.getString("model");
                int vehicleTypeId = rs.getInt("vehicle_type_id");
                String vehicleType = rs.getString("vehicle_type");

                switch (vehicleTypeId) {
                    case 1:
                        Car c = new Car(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(c);
                        break;

                    case 2:
                        Motorcycle m = new Motorcycle(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(m);
                        break;

                    case 3:
                        Helicopter h = new Helicopter(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(h);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return vehicles;

    }

    public List<Vehicle> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int page) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SQLScripts.searchByPriceRange);

            int size = RentACarConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setBigDecimal(1, minPrice);
            ps.setBigDecimal(2, maxPrice);
            ps.setInt(3, size);
            ps.setInt(4, offset);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vehicleBrand = rs.getString("brand");
                String vehicleModel = rs.getString("model");
                int vehicleTypeId = rs.getInt("vehicle_type_id");
                String vehicleType = rs.getString("vehicle_type");

                switch (vehicleTypeId) {
                    case 1:
                        Car c = new Car(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(c);
                        break;

                    case 2:
                        Motorcycle m = new Motorcycle(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(m);
                        break;

                    case 3:
                        Helicopter h = new Helicopter(vehicleBrand, vehicleModel, vehicleTypeId, vehicleType);
                        vehicles.add(h);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return vehicles;

    }

    public Vehicle findById (long id) {
        Vehicle vehicle = null;

        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQLScripts.findByVehicleIDScript);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                long typeId = rs.getLong("vehicle_type_id");

                switch ((int) typeId) {
                    case 1: // Car
                        vehicle = new Car();
                        break;
                    case 2: // Motorcycle
                        vehicle = new Motorcycle();
                        break;
                    case 3: // Helicopter
                        vehicle = new Helicopter();
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown vehicle type id: " + typeId);
                }

                vehicle.setId(rs.getLong("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setPrice(rs.getBigDecimal("price"));
                vehicle.setVehicleTypeId(rs.getLong("vehicle_type_id"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }

}
