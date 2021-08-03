/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dell Inc
 */
public class Article {
    private int id;
    private String title;
    private String image;
    private String content;
    private Timestamp date;
    private String author;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String content) {
        String subContent[] = content.split("\\.");
        this.description = subContent[0]+"."+subContent[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInformation() {
        String dateTime = new SimpleDateFormat("MMM dd yyy - H:mm").format(this.date);
        String ampm = new SimpleDateFormat("a").format(this.date).toLowerCase();
        String info = "By " + this.author + " | " + dateTime + ampm;
        return info;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}
