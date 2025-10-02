import DAO.UserDAO;
import DAO.VehicleDAO;
import Exceptions.RentACarException;
import Models.Enums.RentalTypes;
import Models.Enums.UserRoles;
import Models.Enums.VehicleTypes;
import Models.Order.Order;
import Models.Users.User;
import Models.Vehicles.Vehicle;
import Service.OrderService;
import Service.UserService;
import Service.VehicleService;
import UI.Menus;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static UI.Menus.userMenu;

public class RentACarMain {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws RentACarException {

        Menus menus = new Menus();


        while(true){

                menus.getMainMenu();

                String choice = input.nextLine();

                switch (choice){
                    case "1":
                        createUser();
                    case "2":
                        loginUser();
                        break;
                    case "3":
                        System.out.println("Closing...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input! Please try again.");
            }

        }
    }


    private static void createUser() throws RentACarException {
        System.out.print("First name: ");
        String name = input.nextLine();

        System.out.print("Last name: ");
        String surname = input.nextLine();

        System.out.print("E-mail: ");
        String email = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.print("Age (leave empty if corporate user): ");
        String ageStr = input.nextLine().trim();
        Integer age = null;
        if (!ageStr.isEmpty()) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age, setting as null");
                age = null;
            }
        }



        System.out.println("Company name (leave empty if individual user): ");
        String companyName = input.nextLine().trim();
        if(companyName.isEmpty()){
            companyName = null;
        }

        UserRoles role = (age != null) ? UserRoles.INDIVIDUAL : UserRoles.CORPORATE;

        UserService userService = new UserService();
        userService.createUser(name, surname, email, password, role, age, companyName);

    }

    private static void loginUser() {

        System.out.println("Please Log in.");

        System.out.println("E-mail: ");
        String email = input.nextLine();

        System.out.println("Password: ");
        String password = input.nextLine();

        UserService userService = new UserService();
        User user = userService.loginUser(email, password);

        System.out.println("Welcome");

       if (user.getUserRole() == UserRoles.ADMIN) {

           adminFlow(user, input);
       } else {
           userFlow(user, input);
       }

    }


    public static void adminFlow (User user, Scanner input){

        UserService userService = new UserService();
        VehicleService vehicleService = new VehicleService();
        boolean x = true;
        while (x){

            Menus.adminMenu(user);

            String choice = input.nextLine();


            switch (choice){
                case "1":
                    createVehicle();
                    break;
                case "2":
                    listAllVehicles();
                    break;
                case "3":
                    updateVehicle();
                    break;
                case "4":
                    deleteVehicle();
                    break;
                case "5":
                    System.out.println("Log out...");
                    x = false;
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Invalid input! Please try again.");
                    break;
            }
        }

    }

    private static void deleteVehicle() {

        listAllVehicles();
        System.out.println("--------------");
        System.out.print("Please enter the id of the vehicle you would like to delete: ");
        long vehicleId = input.nextLong();
        input.nextLine();   //buffer

        VehicleService.deleteVehicle(vehicleId);

        System.out.println("The vehicle has been successfully deleted!");
    }

    private static void updateVehicle() {

        listAllVehicles();
        System.out.println("--------------");
        System.out.print("Please enter the id of the vehicle you would like to update: ");
        long vehicleId = input.nextLong();
        input.nextLine();   //buffer

        String update = null;
        String columnName = null;

        boolean x = true;
        while (x) {
            System.out.println("What data about the vehicle would you like to change?");
            System.out.println("1. Brand");
            System.out.println("2. Model");
            System.out.println("3. Price");
            System.out.println("4. Vehicle type");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Updated brand: ");
                    update = input.nextLine();
                    columnName = "brand";
                    break;
                case "2":
                    System.out.print("Updated model: ");
                    update = input.nextLine();
                    columnName = "model";
                    break;
                case "3":
                    System.out.print("Updated price: ");
                    update = input.nextLine();
                    columnName = "price";
                    break;
                case "4":
                    System.out.println("= VEHICLE TYPE ID'S =");
                    System.out.println("1. Car");
                    System.out.println("2. Motorcycle");
                    System.out.println("3. Helicopter");
                    System.out.println("Updated vehicle type id: ");
                    update = input.nextLine();
                    columnName = "vehicle_type_id";
                    break;
                default:
                    System.out.println("Invalid input!");

            }
            x = false;
        }

        VehicleService.updateVehicle(vehicleId, columnName, update);

        System.out.println("The vehicle has been successfully updated!");

    }

    private static void createVehicle() {

        System.out.println("Brand: ");
        String brand = input.nextLine();

        System.out.println("Model: ");
        String model = input.nextLine();

        System.out.println("Sale price: ");
        BigDecimal price = input.nextBigDecimal();
        input.nextLine();   //buffer

        VehicleTypes vehicleType = null;
        do {
            System.out.println("Vehicle type id (1: Car, 2: Motorcycle, 3: Helicopter");
            int vehicleTypeId = input.nextInt();
            input.nextLine();   //Buffer'ı önlemek için

            switch (vehicleTypeId) {
                case 1:
                    vehicleType = VehicleTypes.CAR;
                    break;
                case 2:
                    vehicleType = VehicleTypes.MOTORCYCLE;
                    break;
                case 3:
                    vehicleType = VehicleTypes.HELICOPTER;
                    break;
                default:
                    System.out.println("Invalid vehicle type id!");
            }
        }
        while (vehicleType == null);


        VehicleService vehicleService = new VehicleService();
        vehicleService.createVehicle(brand, model, price, vehicleType);

        System.out.println("Vehicle was successfully added to the inventory!");

    }

    private static void listAllVehicles() {

        VehicleService vehicleService = new VehicleService();
        int totalPage = vehicleService.getTotalPage();
        int page = 1;

        do{
            List<Vehicle> vehicles = VehicleService.listAllVehicles(page);

            System.out.println("=== VEHICLE LIST (Page " + page + "/" + totalPage + ") ===");

            for (Vehicle vehicle : vehicles) {
                System.out.printf("%s - %s - %s - %s - %s%n",
                        vehicle.getId(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getVehicleTypeId(),
                        vehicle.getVehicleType());
            }

            System.out.println("(To exit the list and make a selection, please enter a number that is one more than the total number of pages.)");
            System.out.println("Next page: ");

            String pageStr = input.nextLine();
            page = Integer.parseInt(pageStr);

            System.out.println("===========");


        }while(page<=totalPage);

    }

    public static void userFlow (User user, Scanner input){

        boolean x = true;
        while (x){

            userMenu(user);

            String choice = input.nextLine();


            switch (choice){
                case "1":
                    listAllVehicles();
                    break;
                case "2":
                    searchByBrand();
                    break;
                case "3" :
                    searchByType();
                    break;
                case "4" :
                    searchByPriceRange();
                    break;
                case "5" :
                    rent();
                    break;
                case "6":
                    listAllRentals();
                break;
                case "7":
                    System.out.println("Log out");
                    x = false;
                    break;
                case "0" :
                    System.exit(0);
                default:
                    System.out.println("Invalid input! Please try again.");
                    break;
            }
        }
    }



    private static void searchByBrand() {

        System.out.println("Brand you would like to search: ");
        String brandToSearch = input.nextLine();

        VehicleService vehicleService = new VehicleService();
        int totalPage = vehicleService.getTotalPage();
        int page = 1;

        do{
            List<Vehicle> vehicles = VehicleService.searchByBrand(brandToSearch, page);

            System.out.println("=== VEHICLE LIST (Page " + page + "/" + totalPage + ") ===");

            vehicles.forEach(vehicle ->{
                System.out.printf("%s - %s - %s - %s - %s%n",
                        vehicle.getId(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getPrice());
            });

            System.out.println("(To exit the list and make a selection, please enter a number that is one more than the total number of pages.)");
            System.out.println("Next page: ");

            String pageStr = input.nextLine();
            page = Integer.parseInt(pageStr);

            System.out.println("===========");


        }while(page<=totalPage);

    }

    private static void searchByType() {

        System.out.println("1) Car" +
                "2) Motorcycle" +
                "3) Helicopter");
        System.out.println("Vehicle type you would like to search: ");
        int vehicleTypeToSearch = input.nextInt();
        input.nextLine();       //Scanner buffer'ını temizlemek için

        VehicleService vehicleService = new VehicleService();
        int totalPage = vehicleService.getTotalPage();
        int page = 1;

        do{
            List<Vehicle> vehicles = VehicleService.searchByVehicleType(vehicleTypeToSearch, page);

            System.out.println("=== VEHICLE LIST (Page " + page + "/" + totalPage + ") ===");

            vehicles.forEach(vehicle ->{
                System.out.printf("%s - %s - %s - %s - %s%n",
                        vehicle.getId(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getPrice());
            });

            System.out.println("(To exit the list and make a selection, please enter a number that is one more than the total number of pages.)");
            System.out.println("Next page: ");

            String pageStr = input.nextLine();
            page = Integer.parseInt(pageStr);

            System.out.println("===========");

        }while(page<=totalPage);


    }

    private static void searchByPriceRange() {

        System.out.println("Minimum vehicle price you would like to search: ");
        BigDecimal minPrice = input.nextBigDecimal();

        System.out.println("Maximum vehicle price you would like to search: ");
        BigDecimal maxPrice = input.nextBigDecimal();
        input.nextLine();       // Scanner buffer'ını temizlemek için

        VehicleService vehicleService = new VehicleService();
        int totalPage = vehicleService.getTotalPage();
        int page = 1;

        do{
            List<Vehicle> vehicles = VehicleService.searchByPriceRange(minPrice, maxPrice, page);

            System.out.println("=== VEHICLE LIST (Page " + page + "/" + totalPage + ") ===");

            vehicles.forEach(vehicle ->{
                System.out.printf("%s - %s - %s - %s - %s%n",
                        vehicle.getId(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getPrice());
            });

            System.out.println("(To exit the list and make a selection, please enter a number that is one more than the total number of pages.)");
            System.out.println("Next page: ");

            String pageStr = input.nextLine();
            page = Integer.parseInt(pageStr);

            System.out.println("===========");


        }while(page<=totalPage);

    }
    private static void listAllRentals() {

        System.out.print("Your e-mail: ");
        String email = input.nextLine();

        OrderService orderService = new OrderService();
        int totalPage = orderService.getTotalPage();
        int page = 1;

        do{
            List<Order> orders = OrderService.listAllRentals(page, email);

            System.out.println("=== RENTAL LIST (Page " + page + "/" + totalPage + ") ===");

            orders.forEach(order ->{
                System.out.printf("%s - %s - %s - %s - %s - %s - %s - %s%n",
                        order.getOrderId(),
                        order.getBrand(),
                        order.getModel(),
                        order.getStartDate(),
                        order.getEndDate(),
                        order.getStatus(),
                        order.getDeposit(),
                        order.getTotalAmount());

            });

            System.out.println("(To exit the list, please enter a number that is one more than the total number of pages.)");
            System.out.println("Next page: ");

            String pageStr = input.nextLine();
            page = Integer.parseInt(pageStr);

            System.out.println("===========");


        }while(page<=totalPage);


    }

    private static void rent() {

        // Öncelikle kiralama yapacak kullanıcının bilgilerini bulalım.
        System.out.print("Your email: ");
        String renterEmail = input.nextLine();

        UserDAO userDAO = new UserDAO();                // Kiralayan için instance oluşturuyorum.
        User renterUser = userDAO.findByEmail(renterEmail);


        listAllVehicles();  // Şimdi araç listesini yazdırarak kullanıcının birini seçmesini isteyelim.

        System.out.print("ID of the car you would like to rent: ");
        long vehicleId = input.nextLong();
        input.nextLine(); //Buffer

        System.out.println("Start date (dd-MM-yyyy HH:mm): ");
        String startDateInput = input.nextLine();

        System.out.println("End date (dd-MM-yyyy HH:mm): ");
        String endDateInput = input.nextLine();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        try {
            // String → LocalDateTime dönüşümü
            startDate = LocalDateTime.parse(startDateInput, formatter);
            endDate = LocalDateTime.parse(startDateInput, formatter);

        } catch (Exception e) {
            System.out.println("Invalid format! Please use dd-MM-yyyy HH:mm (e.g., 2025-09-28 14:30)");
        }

        // Şimdi kiralama türü alalım.
        boolean x = true;
        RentalTypes rentalType = null;

        while (x) {
            System.out.println("Please select the rental type.");
            System.out.println("1. Hourly");
            System.out.println("2. Daily");
            System.out.println("3. Weekly");
            System.out.println("4. Monthly");
            System.out.println("Note: Corporate users can only rent on a monthly basis.");
            System.out.println("Your choice: ");
            String rentalTypeId = input.nextLine();

            switch (rentalTypeId) {
                case "1":
                    rentalType = RentalTypes.HOURLY;
                    break;
                case "2":
                    rentalType = RentalTypes.DAILY;
                    break;
                case "3":
                    rentalType = RentalTypes.WEEKLY;
                    break;
                case "4":
                    rentalType = RentalTypes.MONTHLY;
                    break;
                default:
                    System.out.println("Invalid input!");
                    continue;
            }

            if (renterUser.getUserRole().equals(UserRoles.CORPORATE) && rentalType != RentalTypes.MONTHLY){
                System.out.println("Corporate users can only rent on a monthly basis!");
            }
            x = false;
        }

        // Şimdi süre alıyoruz.
        System.out.println("Duration (" + rentalType + "):");
        int duration = input.nextInt();
        input.nextLine();   //buffer

        OrderService orderService = new OrderService();
        orderService.createRental(renterEmail, vehicleId, rentalType, startDate, endDate, duration, renterUser.getAge());

    }


}
