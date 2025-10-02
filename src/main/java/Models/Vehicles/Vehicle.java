package Models.Vehicles;

import Models.Enums.VehicleTypes;

import java.math.BigDecimal;

public abstract class Vehicle {

    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private VehicleTypes vehicleType;
    private Long vehicleTypeId;
    BigDecimal hourlyPrice;
    BigDecimal dailyPrice;
    BigDecimal weeklyPrice;
    BigDecimal monthlyPrice;


    public Vehicle(String brand, String model, VehicleTypes vehicleType, BigDecimal price) {
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
        this.price = price;
    }

    public Vehicle() {
    }

    public Vehicle(long id, String brand, String model, long vehicleTypeId, VehicleTypes vehicleType) {
    }

    public Vehicle(String brand, String model) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleTypes getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypes vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public BigDecimal getHourlyPrice() {
        return hourlyPrice;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public BigDecimal getWeeklyPrice() {
        return weeklyPrice;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setHourlyPrice(BigDecimal hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public void setWeeklyPrice(BigDecimal weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
