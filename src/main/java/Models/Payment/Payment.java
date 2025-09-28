package Models.Payment;

import Models.Enums.PaymentStatuses;

import java.math.BigDecimal;

public class Payment {

long orderId;
BigDecimal amount;
BigDecimal deposit;
PaymentStatuses paymentStatus;


    public Payment(long orderId, BigDecimal amount, BigDecimal deposit, PaymentStatuses paymentStatus) {
        this.orderId = orderId;
        this.amount = amount;
        this.deposit = deposit;
        this.paymentStatus = paymentStatus;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public PaymentStatuses getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatuses paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}


