package models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Класс события, которое мы записываем.
 */
public class Occasion {
    private final SimpleStringProperty program;
    private final SimpleStringProperty time;
    private final SimpleStringProperty date;

    public Occasion(String name, String workTime, String date) {
        this.program = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(workTime);
        this.date = new SimpleStringProperty(date);

    }

    public String getProgram() {
        return program.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getDate() {
        return date.get();
    }
}