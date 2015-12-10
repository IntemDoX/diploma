package models;

import javafx.beans.property.SimpleStringProperty;

public class UniqueOccasion {
    private final SimpleStringProperty uniqueProgram;
    private SimpleStringProperty totalTime;

    public UniqueOccasion(String prog, String time){
        this.uniqueProgram = new SimpleStringProperty(prog);
        this.totalTime = new SimpleStringProperty(time);
    }

    public void replaceValue(long a){
        totalTime = new SimpleStringProperty(String.valueOf(totalTime.getValue()) + a);
    }

    public long getValue(){
        return Long.parseLong(totalTime.getValue());
    }
    public String getStringValue(){
       return totalTime.getValue();
    }
    public String getNameOfProgram(){
        return uniqueProgram.get();
    }
}
