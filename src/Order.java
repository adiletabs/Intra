import java.io.Serializable;
import java.util.Date;

public class Order extends Message implements Serializable {
    private OrderStatus status;

    {
        status = OrderStatus.NEW;
    }

    public Order(String title, String text, String sender, Date date) {
        super(title, text, sender, date);
    }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    @Override
    public String toString() {
        String info = "Status: " + status.toString() + '\n';
        return super.toString() + info;
    }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() { return super.hashCode(); }
}