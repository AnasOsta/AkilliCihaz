package Bildirler;

import Observer.Observer;
import Observer.Observerable;

import java.util.ArrayList;
import java.util.List;

public class Bildir implements Observerable {

    private List<Observer> observerList = new ArrayList<>();
    private String message = "Sicaklik Degeri Degisti";

    @Override
    public void add(Observer observer) { observerList.add(observer); }

    @Override
    public void remove(Observer observer) { observerList.remove(observer); }

    @Override
    public void update() {
        for (Observer observer : observerList)
            observer.bildir(message);
    }
}
