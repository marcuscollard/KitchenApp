package java.kitchenapp;

import java.util.ArrayList;

public class Order {

    public static ArrayList<Order> populateOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();

        for (int i = 1; i <= 5; i++) {
            orders.add(new Order(i, "fish", 20));
        }

        return orders;
    }

    private int tableNumber;
    //private String orderNumber;
    private String name;
    private int time;

    public Order(int tableNumber, String name, int time) {
        this.tableNumber = tableNumber;
        this.name = name;
        this.time = time;
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }

}
