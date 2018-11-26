import java.util.ArrayList;
import java.util.Date;

public interface ManagingOrders {
    ArrayList<Order> getOrders(OrderStatus status);

    Order getOrder(String title, String sender, Date date);

    void changeOrderStatus(Order order, OrderStatus status);
}
