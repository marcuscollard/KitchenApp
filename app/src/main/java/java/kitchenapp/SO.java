package java.kitchenapp;

import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;

public class SO {

    // singleton class for orders
    public static SO s = new SO();

    OrdersCustomAdapter customAdapter;

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
            Long obj1 = new Long(o1.getPriority());
            Long obj2 = new Long(o2.getPriority());
            int compareValue = obj1.compareTo(obj2);

            if (o1.isStarter() && o2.isStarter())
            {
                if (o1.getPriority() > o2.getPriority())
                {
                    return 1;
                }
                else if (o1.getPriority() < o2.getPriority())
                {
                    return -1;
                }
                else
                    {
                    if (o1.getTime() < o2.getTime()) {
                        System.out.println("O1!");
                        return 1;
                    } else if (o1.getTime() > o2.getTime()) {
                        System.out.println("O2");
                        return -1;
                    }
                    else {
                        return 0;
                    }
                }
            } else if (o1.isStarter() && o2.isStarter() == false)
            {
                return -1;
            }
            else if (o1.isStarter() == false && o2.isStarter())
            {
                return 1;
            }
            else if (compareValue < 0)
            {
                return -1;
            }
            else if (o1.getPriority() > o2.getPriority())
            {
                return 1;
            }
            else if (o1.getTime() < o2.getTime())
            {
                System.out.println("O1!");
                return 1;
            }
            else if (o1.getTime() > o2.getTime())
            {
                System.out.println("O2");
                return -1;
            }
            else
            {
                return 0;
            }
            //return Integer.compare(o2.getTime(), o1.getTime());



            /*if (o1.getPriority() > o2.getPriority())
            {
            return 1;
            }
            else if (o1.getPriority() < o2.getPriority())
            {
            return -1;
            }
            else
            {
                return Integer.compare(o2.getTime(), o1.getTime());
            }*/
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

    public SortedList<Order> getOrders(){return orders;}

    public void addOrders(ArrayList<Order> o) {
        orders.addAll(o);
        customAdapter.notifyDataSetChanged();
        //customAdapter.notifyItemRangeChanged();
        //customAdapter.upDateData(orders);
    }

    public void addOrder(Order o) {
        orders.add(o);
        customAdapter.notifyDataSetChanged();
    }


//    SortedList<Order> deleteOrder() {
//
//    }





    private SO() {
    }
}
