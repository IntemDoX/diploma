package models;

/**
 * Created by Vladislav on 26.11.2015.
 */
public interface ModalChangeNameObservable {
    void registerObserver(ModalChangeNameObserver o);
    void removeObserver(ModalChangeNameObserver o);
    void notifyObservers();
}
