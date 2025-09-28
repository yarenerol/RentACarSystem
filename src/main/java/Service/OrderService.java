package Service;

import DAO.OrderAndPaymentDAO;
import DAO.VehicleDAO;
import Models.Enums.RentalStatuses;
import Models.Enums.RentalTypes;
import Models.Order.Order;
import Models.Users.User;
import Models.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private static OrderAndPaymentDAO orderAndPaymentDAO;

    public OrderService() {
        this.orderAndPaymentDAO = new OrderAndPaymentDAO();
    }


    public Order createRental(long id, User renterUser, long vehicleId, RentalTypes rentalType, LocalDateTime startDate, int duration, Integer age) {

        VehicleDAO vehicleDAO = new VehicleDAO();
        Vehicle v = vehicleDAO.findById(vehicleId);


        BigDecimal rentalPrice = null;

        switch (rentalType){
            case HOURLY:
                rentalPrice = v.getHourlyPrice().multiply(BigDecimal.valueOf(duration));
                break;
            case DAILY:
                rentalPrice = v.getDailyPrice().multiply(BigDecimal.valueOf(duration));
            case WEEKLY:
                rentalPrice = v.getWeeklyPrice().multiply(BigDecimal.valueOf(duration));
            case MONTHLY:
                rentalPrice = v.getMonthlyPrice().multiply(BigDecimal.valueOf(duration));
            default:
                System.out.println("Invalid rental type!");
        }
        // Şimdi depozito hesaplama işlemlerimizi yapacağız.
        // Java’da BigDecimal ile > operatörü çalışmaz, çünkü > sadece sayısal primitifler (int, long, double, vb.) için geçerlidir.
        // Bu nedenle bir eşik değer belirleyip buna göre kıyaslama yapacağız.

        BigDecimal threshold = BigDecimal.valueOf(2_000_000);
        boolean isVehicleExpensiveThan2M = v.getPrice().compareTo(threshold) > 0;

        boolean isUserOver30 = renterUser.getAge() >= 30;

        // Şimdi depozito hesaplayalım.

        BigDecimal deposit;
        if (isUserOver30 && isVehicleExpensiveThan2M) {
            deposit = rentalPrice.multiply(BigDecimal.valueOf(0.10));
        } else {
            deposit = null;
        }

        BigDecimal totalAmount = rentalPrice.add(deposit);

        Order order = new Order(id,vehicleId, rentalType, duration, startDate,deposit, totalAmount, RentalStatuses.ACTIVE);

        orderAndPaymentDAO.saveOrder(order);

        System.out.println("The vehicle was successfully rented!");

        return order;
    }


}
