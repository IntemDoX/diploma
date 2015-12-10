package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class OccasionsStorage {
    private static OccasionsStorage instance;
    private static ObservableList<Occasion> occasions;
    private OccasionsStorage(){
        System.out.println("OccasionStorage was create");
    }

    public static OccasionsStorage getInstance(){
        if(instance == null) instance = new OccasionsStorage();
        return instance;
    }

    public ObservableList<Occasion> getOccasions(){
        if(occasions == null) occasions = FXCollections.observableArrayList();
        return occasions;
    }

    public void add(Occasion o){
        occasions.add(o);
    }
}
