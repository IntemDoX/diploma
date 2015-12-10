package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;


public class HistoryStorage {
    private static HistoryStorage instance;
    private static ObservableList<UniqueOccasion> uniqueList;
    private  HashMap<String, Long> storyList;


    public static HistoryStorage getInstance(){
        if(instance == null) instance = new HistoryStorage();
        return instance;
    }

    public  HashMap<String,Long> getStoryList(){
        if(storyList == null) storyList = new HashMap<>();
        return storyList;
    }
    public  void put(String a, Long b){
        storyList.put(a,b);
    }
    public  Long get(String key){
        return storyList.get(key);
    }

    public ObservableList<UniqueOccasion> getUniqueList() {
        if(uniqueList == null) uniqueList =  FXCollections.observableArrayList();
        return uniqueList;
    }
    public void add(UniqueOccasion a){
            uniqueList.add(a);

        /*
            for(Iterator<UniqueOccasion> it = uniqueList.iterator(); it.hasNext();){
                UniqueOccasion uniqueOccasion = it.next();
                if(uniqueOccasion.getNameOfProgram().equals(a.getNameOfProgram())){
                    uniqueOccasion.replaceValue(uniqueOccasion.getValue()+a.getValue());
                }
                else
                    uniqueList.add(a);
            }*/

    }



    private HistoryStorage(){
        System.out.println("HistoryStorage was create");
    }
}
