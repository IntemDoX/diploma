package models;

public interface ModalChangeNameObservable {
    void registerObserver(ModalChangeNameObserver o);
    void removeObserver(ModalChangeNameObserver o);
    void notifyObservers();
}
