package models;


public interface MakeScreenObservable {
    void registerScreenObserver(MakeScreenObserver o);
    void removeScreenObserver(MakeScreenObserver o);
    void notifyScreenObservers(boolean a);
}
