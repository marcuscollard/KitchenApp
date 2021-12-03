package java.kitchenapp;

import org.simpleframework.xml.Element;

@Element(name="menuitem")
public class MenuItem {
    @Element(name="foodname")
    String foodName;
    @Element(name="id")
    Integer id;
    @Element(name="price")
    Integer price;
}
