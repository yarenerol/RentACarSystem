package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public class Car extends Vehicle{


    public Car(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        super(brand, model, vehicleType, price);
        this.hourlyPrice = BigDecimal.valueOf(100);
        this.dailyPrice  = BigDecimal.valueOf(500);
        this.weeklyPrice = BigDecimal.valueOf(3000);
        this.monthlyPrice= BigDecimal.valueOf(10000);
    }


    public Car(String vehicleBrand, String vehicleModel, int vehicleTypeId, String vehicleType) {
        super();
        this.hourlyPrice = BigDecimal.valueOf(100);
        this.dailyPrice  = BigDecimal.valueOf(500);
        this.weeklyPrice = BigDecimal.valueOf(3000);
        this.monthlyPrice= BigDecimal.valueOf(10000);
    }

    public Car() {
        this.hourlyPrice = BigDecimal.valueOf(100);
        this.dailyPrice  = BigDecimal.valueOf(500);
        this.weeklyPrice = BigDecimal.valueOf(3000);
        this.monthlyPrice= BigDecimal.valueOf(10000);
    }

    public Car(long id, String brand, String model, long vehicleTypeId, VehicleTypes type) {
        this.hourlyPrice = BigDecimal.valueOf(100);
        this.dailyPrice  = BigDecimal.valueOf(500);
        this.weeklyPrice = BigDecimal.valueOf(3000);
        this.monthlyPrice= BigDecimal.valueOf(10000);
    }

    public Car(long id, String brand, String model, long vehicleTypeId, VehicleTypes vehicleType, BigDecimal hourlyRentalFee, BigDecimal dailyRentalFee, BigDecimal weeklyRentalFee, BigDecimal monthlyRentalFee) {
        super(id, brand, model, vehicleTypeId, vehicleType);
        this.hourlyPrice = hourlyRentalFee;
        this.dailyPrice = dailyRentalFee;
        this.weeklyPrice = weeklyRentalFee;
        this.monthlyPrice = monthlyRentalFee;

    }

    public Car(String brand, String model, long vehicleTypeId, VehicleTypes type) {
        super(brand, model, vehicleTypeId, type);
        this.hourlyPrice = BigDecimal.valueOf(100);
        this.dailyPrice = BigDecimal.valueOf(500);
        this.weeklyPrice = BigDecimal.valueOf(3000);
        this.monthlyPrice = BigDecimal.valueOf(10000);
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
