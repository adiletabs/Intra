import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Executor extends Employee implements ManagingOrders, Serializable {
    public static ArrayList<Order> orders = new ArrayList<>();

    static {
        loadOrders();
    }

    public Executor (String lastName, String firstName, String login) {
        super(lastName, firstName, login);
    }

    private static final String PATH = "/home/dontnicemebr0/IdeaProjects/Intra/src/";
    private static final String ORDERS = "orders.out";

    private static final String EXCEPT_CLASS = "Class not found!";
    private static final String EXCEPT_FILE = "File not found!";
    private static final String EXCEPT_IO = "Input / Output exception!";

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

        sendMessage(message, sender);
    }

    public static void saveOrders() {
        try {
            ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(ORDERS));

            oot.writeObject(orders);

            oot.flush();
            oot.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(ORDERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(ORDERS + ": " + EXCEPT_IO);
        }
    }

    private static void loadOrders() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDERS));

            orders = (ArrayList<Order>) ois.readObject();

            ois.close();

        }
        catch (ClassNotFoundException e) {
            System.out.println(ORDERS + ": " + EXCEPT_CLASS);
        }
        catch (FileNotFoundException e) {
            System.out.println(ORDERS + ": " + EXCEPT_FILE);
        }
        catch (IOException e) {
            System.out.println(ORDERS + ": " + EXCEPT_IO);
        }
    }
}
