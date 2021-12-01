package java.kitchenapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name="bordorders")
public class TableList {
    @ElementList(name="bordorder", inline = true)

    List<Bordorder> bordorder;
}
