package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Helicopter extends Vehicle{

    public Helicopter(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);
        this.hourlyPrice  = BigDecimal.valueOf(200);
        this.dailyPrice   = BigDecimal.valueOf(1000);
        this.weeklyPrice  = BigDecimal.valueOf(6000);
        this.monthlyPrice = BigDecimal.valueOf(20000);
    }

    public Helicopter(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {
        super();
        this.hourlyPrice  = BigDecimal.valueOf(200);
        this.dailyPrice   = BigDecimal.valueOf(1000);
        this.weeklyPrice  = BigDecimal.valueOf(6000);
        this.monthlyPrice = BigDecimal.valueOf(20000);
    }

    public Helicopter() {
        super();
        this.hourlyPrice  = BigDecimal.valueOf(200);
        this.dailyPrice   = BigDecimal.valueOf(1000);
        this.weeklyPrice  = BigDecimal.valueOf(6000);
        this.monthlyPrice = BigDecimal.valueOf(20000);

    }


    public Helicopter(long id, String brand, String model, long vehicleTypeId, VehicleTypes vehicleType, BigDecimal hourlyRentalFee, BigDecimal dailyRentalFee, BigDecimal weeklyRentalFee, BigDecimal monthlyRentalFee) {
        super(id, brand, model, vehicleTypeId, vehicleType);
        this.hourlyPrice = hourlyRentalFee;
        this.dailyPrice = dailyRentalFee;
        this.weeklyPrice = weeklyRentalFee;
        this.monthlyPrice = monthlyRentalFee;
    }

    public final BigDecimal getHourlyPrice() {
        return hourlyPrice;
    }

    public final BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public final BigDecimal getWeeklyPrice() {
        return weeklyPrice;
    }

    public final BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }
}
