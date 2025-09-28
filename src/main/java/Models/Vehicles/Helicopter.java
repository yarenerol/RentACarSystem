package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Helicopter extends Vehicle{

    public Helicopter(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);
    }

    public Helicopter(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {
    }

    public Helicopter() {

    }

    BigDecimal hourlyPrice;
    BigDecimal dailyPrice;
    BigDecimal weeklyPrice;
    BigDecimal monthlyPrice;

    public final BigDecimal getHourlyPrice() {
        return BigDecimal.valueOf(1000);
    }

    public final BigDecimal getDailyPrice() {
        return BigDecimal.valueOf(5000);
    }

    public final BigDecimal getWeeklyPrice() {
        return BigDecimal.valueOf(30000);
    }

    public final BigDecimal getMonthlyPrice() {
        return BigDecimal.valueOf(100000);
    }
}
