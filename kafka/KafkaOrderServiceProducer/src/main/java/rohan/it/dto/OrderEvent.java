package rohan.it.dto;

public class OrderEvent {

    private String orderId;
    private String customerEmail;
    private double amount;
    private String status;

    // Getters and Setters (required for JSON serialization)
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}