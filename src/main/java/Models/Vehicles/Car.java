package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Car extends Vehicle{

    public Car(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);

    }


    public Car(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {

    }

    public Car() {

    }


    public final BigDecimal getHourlyPrice() {
        return BigDecimal.valueOf(100);
    }

    public final BigDecimal getDailyPrice() {
        return BigDecimal.valueOf(500);
    }

    public final BigDecimal getWeeklyPrice() {
        return BigDecimal.valueOf(3000);
    }

    public final BigDecimal getMonthlyPrice() {
        return BigDecimal.valueOf(10000);
    }
}
