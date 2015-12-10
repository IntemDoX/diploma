package models;

import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public class Occasion {
    private final SimpleStringProperty program;
    private final SimpleStringProperty time;
    private final Date date;

    public Occasion(String name, String workTime, Date date) {
        this.program = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(workTime);
        this.date = date;

    }

    /*public String getProgram() {
        return program.get();
    }

    public String getTime() {
        return time.get();
    }

    public Date getDate() {
        return date;
    }*/
}