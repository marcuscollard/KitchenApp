package java.kitchenapp;


public class Order {

    final private int tablePrio;
    //private String orderNumber;
    final private String name;
    private int time;
    private boolean done = false;
    private String notes = "test test test test test test test test tes" +
            "t test test test test test test test test test test test test ";

    public Order(int tablePrio, String name, int time) {
        this.tablePrio = tablePrio;
        this.name = name;
        this.time = time;
    }
    public int getTablePrio() {
        return tablePrio;
    }
    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }
    public boolean isDone() {return done;}
    public String getNotes() {return notes;}

    public void setTime(int time) {
        this.time = time;
    }

    public void setDoneAs(boolean status) {
        this.done = status;
    }

}
