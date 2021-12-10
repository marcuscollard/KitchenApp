package java.kitchenapp;


public class Order {

    final private int tableNumber;

    final private int priority;

    //private String orderNumber;
    final private String name;
    private int time;
    private boolean done = false;
    private String notes = "";

    public Order(int tableNumber, String name, int time, int priority) {
        this.tableNumber = tableNumber;
        this.name = name;
        this.time = time;
        this.priority = priority;
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
    public boolean isDone() {return done;}
    public String getNotes() {return notes;}
    public int getPriority() { return priority; }

    public void setNotes(String notes) {this.notes = notes;}
    public void setDoneAs(boolean status) {
        this.done = status;
    }

}
