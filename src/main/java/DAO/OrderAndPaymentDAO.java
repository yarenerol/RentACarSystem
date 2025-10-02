package DAO;

import Constants.RentACarConstants;
import Models.Enums.PaymentStatuses;
import Models.Order.Order;
import Utils.DBUtil;
import Utils.SQLScripts;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAndPaymentDAO {

    public static List<Order> listAllOrders(int page, long userId) {


        List <Order> orders = new ArrayList<>();

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQLScripts.listAllOrdersScript);

            ps.setLong(1, userId);

            int size = RentACarConstants.PAGE_SIZE;
            int offset = (page - 1) * size;
            ps.setLong(2, userId);
            ps.setInt(2, size );
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Order order = new Order(
                        rs.getLong("order_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date"),
                        rs.getString("status"),
                        rs.getBigDecimal("deposit"),
                        rs.getBigDecimal("total_price")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void saveOrder(Order order) {

        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false
            );
            //Öncelikle order kaydetme işlemiyle başlıyoruz.

            PreparedStatement psOrder = connection.prepareStatement(SQLScripts.saveOrderScript);

                psOrder.setLong(1, order.getUserId());
                psOrder.setLong(2, order.getVehicleId());
                psOrder.setBigDecimal(3, order.getTotalAmount());
                psOrder.setString(4, String.valueOf(order.getStatus()));
                psOrder.setBigDecimal(5, order.getDeposit());
                psOrder.setString(6, String.valueOf(order.getRentalType()));
                psOrder.setInt(7, order.getDuration());
                psOrder.setTimestamp(8, Timestamp.valueOf(order.getStartDate()));
                psOrder.setTimestamp(9, Timestamp.valueOf(order.getEndDate()));

            ResultSet rs = psOrder.executeQuery();// Order kaydetme işlemi tamamlandı.
            int orderId = -1;   //geçici olarak -geçersiz -1 değerini koydum.
            if (rs.next()) {
                orderId = rs.getInt("order_id");
            }

// Order kaydetme işlemi bitti, şimdi payment kaydetmeye geçebiliriz.

            PreparedStatement psPayment = connection.prepareStatement(SQLScripts.savePaymentScript);

            psPayment.setLong(1, orderId);
            psPayment.setBigDecimal(2, order.getTotalAmount());
            psPayment.setBigDecimal(3, order.getDeposit());
            psPayment.setString(4, String.valueOf(PaymentStatuses.COMPLETED));

            psPayment.executeUpdate();
            connection.commit();        // Commit ile transaction'ı tamamlıyoruz.


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isVehicleAvailable(long vehicleId, LocalDateTime startDate, LocalDateTime endDate) {

        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(SQLScripts.isVehicleAvailableScript);

            ps.setLong(1, vehicleId);
            ps.setTimestamp(2, Timestamp.valueOf(startDate));
            ps.setTimestamp(3, Timestamp.valueOf(endDate));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);     // COUNT(*) sonucunu okuyoruz
                return count == 0;            // 0 ise true (müsait), >0 ise false (çakışma var)
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public int findTotalPage() {

        try {
            Connection connection = DBUtil.getConnection();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SQLScripts.totalPageCountScript);

            if(rs.next()){
                int totalRows = rs.getInt(1);
                return (int) Math.ceil((double) totalRows/ RentACarConstants.PAGE_SIZE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

