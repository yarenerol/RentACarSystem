package Models.Order;

import Models.Enums.RentalStatuses;
import Models.Enums.RentalTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

long orderId;
long userId;
long vehicleId;
RentalTypes rentalType;
int duration;
BigDecimal deposit;
BigDecimal totalAmount;
RentalStatuses status;
LocalDateTime startDate;
LocalDateTime endDate;

    public Order(long userId, long vehicleId, RentalTypes rentalType, int duration, LocalDateTime startDate, BigDecimal deposit, BigDecimal totalAmount, RentalStatuses status) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.rentalType = rentalType;
        this.duration = duration;
        this.startDate = startDate;
        this.deposit = deposit;
        this.totalAmount = totalAmount;
        this.status = status;
        this.endDate = calculateEndDate(startDate, rentalType, duration);
    }

    public Order(long orderId, long userId, long vehicleId, RentalTypes rentalType, int duration, BigDecimal deposit, BigDecimal totalAmount, RentalStatuses status, LocalDateTime startDate, LocalDateTime endDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.rentalType = rentalType;
        this.duration = duration;
        this.deposit = deposit;
        this.totalAmount = totalAmount;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private LocalDateTime calculateEndDate(LocalDateTime startDate, RentalTypes rentalType, int duration) {
        switch (rentalType) {
            case HOURLY:
                return startDate.plusHours(duration);
            case DAILY:
                return startDate.plusDays(duration);
            case WEEKLY:
                return startDate.plusWeeks(duration);
            case MONTHLY:
                return startDate.plusMonths(duration);
            default:
                throw new IllegalArgumentException("Unknown rental type");
        }
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public RentalTypes getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalTypes rentalType) {
        this.rentalType = rentalType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public RentalStatuses getStatus() {
        return status;
    }

    public void setStatus(RentalStatuses status) {
        this.status = status;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }


}
