package Database;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="menuitems")
public class MenuItems {
    @ElementList(entry="menuitem", inline = true)
    List<MenuItem> menuItemList;
}
