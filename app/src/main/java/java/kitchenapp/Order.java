package java.kitchenapp;


public class Order {

    final private int tableNumber;

    final private Long priority;

    //private String orderNumber;
    final private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int time;
    private boolean done = false;
    private String notes = "";
    private boolean starter = false;

    public Order(int tableNumber, String name, int time, Long priority, boolean starter, int id) {
        this.tableNumber = tableNumber;
        this.name = name;
        this.time = time;
        this.priority = priority;
        this.starter = starter;
        this.id = id;
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
    public Long getPriority() { return priority; }
    public boolean isStarter() {return starter;}

    public void setNotes(String notes) {this.notes = notes;}
    public void setDoneAs(boolean status) {
        this.done = status;
    }

}
