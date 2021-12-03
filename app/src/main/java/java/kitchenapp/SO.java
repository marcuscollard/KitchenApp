package java.kitchenapp;

import androidx.recyclerview.widget.SortedList;

public class SO {

    // singleton class for orders
    public static SO s = new SO();


    public final SortedList<Order> orders = new SortedList<>(Order.class, new SortedList.Callback<Order>() {
        @Override
        public void onInserted(int position, int count) {
            // put x2 if same
        }
        @Override
        public void onRemoved(int position, int count) {

        }
        @Override
        public void onMoved(int fromPosition, int toPosition) {

        }
        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getTableNumber() > o2.getTableNumber()) { return 1; }
            else if (o1.getTableNumber() < o2.getTableNumber()) { return -1; }
            else {
                return Integer.compare(o1.getTime(), o2.getTime());
            }
        }
        @Override
        public void onChanged(int position, int count) {

        }
        @Override
        public boolean areContentsTheSame(Order oldItem, Order newItem) {
            return false;
        }
        @Override
        public boolean areItemsTheSame(Order item1, Order item2) {
            return false;
        }
    });

    void addOrder(Order order) {
        orders.add(order);

        //notifyItemInserted();
    }

//    SortedList<Order> deleteOrder() {
//
//    }





    private SO() {
    }
}
