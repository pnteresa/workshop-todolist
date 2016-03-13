package in.masukang.todolist.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by teresa on 3/13/2016.
 */
public class TodoItem {

    private String content;
    private Date dateCreated;
    private Date dateCompleted;
    private boolean isCompleted;

    public TodoItem(String content) {
        this.content = content;
        this.dateCreated = new Date();
        this.isCompleted = false;
    }

    public String getShortDateCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm");
        return sdf.format(dateCreated).toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
