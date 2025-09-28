package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle{

    public Motorcycle(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);

    }

    public Motorcycle(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {
    }

    public Motorcycle() {

    }

    BigDecimal hourlyPrice;
    BigDecimal dailyPrice;
    BigDecimal weeklyPrice;
    BigDecimal monthlyPrice;

    public final BigDecimal getHourlyPrice() {
        return BigDecimal.valueOf(50);
    }

    public final BigDecimal getDailyPrice() {
        return BigDecimal.valueOf(250);
    }

    public final BigDecimal getWeeklyPrice() {
        return BigDecimal.valueOf(1500);
    }

    public final BigDecimal getMonthlyPrice() {
        return BigDecimal.valueOf(5000);
    }
}
