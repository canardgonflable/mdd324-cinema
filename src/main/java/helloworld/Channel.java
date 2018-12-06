package helloworld;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)

public class Channel {

    @XmlElement(name = "item")
    private List<Item> items;

    @XmlTransient
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> channel) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "items=" + items +
                '}';
    }
}
