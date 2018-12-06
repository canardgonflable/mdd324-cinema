package helloworld;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)

public class Item {
    private String link;
    private String title;
    private String description;
    private String pubDate;
    private String enclosure;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"link\":\"" + link + '\"' +
                ", \"title\":\"" + title + '\"' +
                ", \"description\":\"" + description + '\"' +
                ", \"pubDate\":\"" + pubDate + '\"' +
                '}';
    }
}
