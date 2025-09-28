package DAO;

import Models.Enums.PaymentStatuses;
import Models.Order.Order;
import Utils.DBUtil;
import Utils.SQLScripts;

import java.sql.*;

public class OrderAndPaymentDAO {

    public void saveOrder(Order order) {

        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
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

            psOrder.executeUpdate();   // Order kaydetme işlemi tamamlandı.
            ResultSet rs = psOrder.getGeneratedKeys(); //Order kaydetme sırasında oluşturulan id'yi alıyorum. Payment için kullanacağız.

            long generatedOrderId = 0;

            if(rs.next()){
                generatedOrderId = rs.getLong("order_id");
                order.setOrderId(generatedOrderId);
            }



        PreparedStatement psPayment = connection.prepareStatement(SQLScripts.savePaymentScript);

                psPayment.setLong(1, order.getOrderId());
                psPayment.setBigDecimal(2, order.getTotalAmount());
                psPayment.setBigDecimal(3, order.getDeposit());
                psPayment.setString(4, String.valueOf(PaymentStatuses.COMPLETED));

                psPayment.executeUpdate();
                connection.commit();        // Commit ile transaction'ı tamamlıyoruz.

        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback(); // hata olursa rollback ile tüm işlemi geri alma
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
        try {
            if (connection != null) connection.setAutoCommit(true);
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}

