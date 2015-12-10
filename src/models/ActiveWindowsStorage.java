package models;

import java.util.ArrayList;
import java.util.List;

public class ActiveWindowsStorage {
    private static ActiveWindowsStorage instance;
    private ArrayList<String> winList;
    private ActiveWindowsStorage(){
        winList = new ArrayList<>();
    }

    public static ActiveWindowsStorage getInstance(){
        if(instance == null)instance = new ActiveWindowsStorage();
        return instance;
    }
    public ArrayList getActiveWindowsList(){
        if(instance !=null) {
            return winList;
        }
        else{
            instance = new ActiveWindowsStorage();
            return winList;
        }
    }
    public String get(int a){
        return winList.get(a);
    }
    public String getActiveWindow(){
        if(winList!=null){
            return winList.get(0);
        }
        return "Активного окна нет";
    }

    public void s(){}

    public void add(String a){
        winList.add(0,a);
    }

}
