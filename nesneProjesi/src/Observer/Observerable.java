package Observer;

public interface Observerable {

    void add(Observer observer);
    void remove(Observer observer);
    void update();
}
