package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle{

    public Motorcycle(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);

        this.hourlyPrice  = BigDecimal.valueOf(50);
        this.dailyPrice   = BigDecimal.valueOf(250);
        this.weeklyPrice  = BigDecimal.valueOf(1500);
        this.monthlyPrice = BigDecimal.valueOf(5000);

    }

    public Motorcycle() {

        this.hourlyPrice  = BigDecimal.valueOf(50);
        this.dailyPrice   = BigDecimal.valueOf(250);
        this.weeklyPrice  = BigDecimal.valueOf(1500);
        this.monthlyPrice = BigDecimal.valueOf(5000);
    }


    public Motorcycle(long id, String brand, String model, long vehicleTypeId, VehicleTypes vehicleType, BigDecimal hourlyRentalFee, BigDecimal dailyRentalFee, BigDecimal weeklyRentalFee, BigDecimal monthlyRentalFee) {
        super(id, brand, model, vehicleTypeId, vehicleType);
        this.hourlyPrice = hourlyRentalFee;
        this.dailyPrice = dailyRentalFee;
        this.weeklyPrice = weeklyRentalFee;
        this.monthlyPrice = monthlyRentalFee;

    }

    public Motorcycle(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {
        super();
        this.hourlyPrice  = BigDecimal.valueOf(50);
        this.dailyPrice   = BigDecimal.valueOf(250);
        this.weeklyPrice  = BigDecimal.valueOf(1500);
        this.monthlyPrice = BigDecimal.valueOf(5000);
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
