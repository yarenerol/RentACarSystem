package Service;

import DAO.OrderAndPaymentDAO;
import DAO.UserDAO;
import DAO.VehicleDAO;
import Models.Enums.RentalStatuses;
import Models.Enums.RentalTypes;
import Models.Order.Order;
import Models.Users.User;
import Models.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderAndPaymentDAO orderAndPaymentDAO;

    public OrderService() {
        this.orderAndPaymentDAO = new OrderAndPaymentDAO();
    }

    public static List<Order> listAllRentals(int page, String email) {

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);
        long userId = user.getId();

        List <Order> orders = new ArrayList<>();

        orders = OrderAndPaymentDAO.listAllOrders(page, userId);

        return orders;
    }


    public Order createRental(String renterEmail, long vehicleId, RentalTypes rentalType, LocalDateTime startDate, LocalDateTime endDate, int duration, Integer age) {

        Order order = null;

        if (!isVehicleAvailable(vehicleId, startDate, endDate)) {

            System.out.println("This vehicle is not available on the dates you selected. Please select another time period.");

        } else {

            VehicleDAO vehicleDAO = new VehicleDAO();       // Kiralanacak araba için instance oluşturuyorum.
            Vehicle v = vehicleDAO.findById(vehicleId);

            UserDAO userDAO = new UserDAO();                // Kiralayan için instance oluşturuyorum.
            User renterUser = userDAO.findByEmail(renterEmail);


            BigDecimal rentalPrice = null;

            switch (rentalType) {
                case HOURLY:
                    rentalPrice = v.getHourlyPrice().multiply(BigDecimal.valueOf(duration));
                    break;
                case DAILY:
                    rentalPrice = v.getDailyPrice().multiply(BigDecimal.valueOf(duration));
                    break;
                case WEEKLY:
                    rentalPrice = v.getWeeklyPrice().multiply(BigDecimal.valueOf(duration));
                    break;
                case MONTHLY:
                    rentalPrice = v.getMonthlyPrice().multiply(BigDecimal.valueOf(duration));
                    break;
                default:
                    System.out.println("Invalid rental type!");
            }

            // Dpozito hesaplama metodunu çağırıyoruz.

            BigDecimal deposit = deposit(v.getPrice(), rentalPrice, renterUser.getAge());

            BigDecimal totalAmount = rentalPrice.add(deposit);

            order = new Order(renterUser.getId(), vehicleId, v.getBrand(), v.getModel(), rentalType, duration, startDate, endDate, deposit, totalAmount, RentalStatuses.ACTIVE);

            orderAndPaymentDAO.saveOrder(order);
            System.out.println("The vehicle has been successfully rented.");
        }
        return order;
    }

    public boolean isVehicleAvailable (long vehicleId, LocalDateTime startDate, LocalDateTime endDate){

        return orderAndPaymentDAO.isVehicleAvailable(vehicleId, startDate, endDate);

    }

    public BigDecimal deposit (BigDecimal vehiclePrice, BigDecimal rentalPrice, int userAge){

        // Java’da BigDecimal ile > operatörü çalışmaz, çünkü > sadece sayısal primitifler (int, long, double, vb.) için geçerlidir.
        // Bu nedenle bir eşik değer belirleyip buna göre kıyaslama yapacağız.


        BigDecimal threshold = BigDecimal.valueOf(2_000_000);
        boolean isVehicleExpensiveThan2M = vehiclePrice.compareTo(threshold) > 0;

        boolean isUserOver30 = userAge >= 30;

        // Şimdi depozito hesaplayalım.

        BigDecimal deposit;
        if (isUserOver30 && isVehicleExpensiveThan2M) {
            deposit = rentalPrice.multiply(BigDecimal.valueOf(0.10));
        } else {
            deposit = BigDecimal.valueOf(0);
        }

        return deposit;

    }

    public int getTotalPage() {
        return orderAndPaymentDAO.findTotalPage();
    }

}
