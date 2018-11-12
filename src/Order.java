import java.util.Date;

public class Order extends Message {
    private OrderStatus status;

    {
        status = OrderStatus.New;
    }

    public Order(String title, String text, String sender, Date date) {
        super(title, text, sender, date);
    }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) { this.status = status; }
}
