import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Executor extends Employee implements ManagingOrders, Serializable {
    public static ArrayList<Order> orders;

    public Executor (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
    }

    @Override
    public ArrayList<Order> getOrders(OrderStatus status) {
        ArrayList<Order> curOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus() == status) {
                curOrders.add(o);
            }
        }
        return curOrders;
    }

    @Override
    public Order getOrder(String title, String sender, Date date) {
        Order order = null;
        for (Order o: orders) {
            if (o.getTitle().equals(title) && o.getSender().equals(sender) && o.getDate().equals(date)) {
                order = o;
                break;
            }
        }
        return order;
    }

    @Override
    public void changeOrderStatus(String title, String sender, Date date, OrderStatus status) {
        for (Order o: orders) {
            if (o.getTitle().equals(title) && o.getSender().equals(sender) && o.getDate().equals(date)) {
                o.setStatus(status);
                reply(title, sender, status);
                break;
            }
        }
    }

    private void reply(String title, String sender, OrderStatus status) {
        String text = "";
        Date now = Calendar.getInstance().getTime();
        switch (status) {
            case DONE:
                text = "Your order is done. Thank you!";
                break;
            case NOT_DONE:
                text = "Your order accepted and in progress. Thank you!";
                break;
            case REJECTED:
                text = "Your order rejected. Sorry!";
                break;
        }
        Message message = new Message("Reply to " + title, text, getLogin(), now);
        //sendMessage(message, sender);
    }
}