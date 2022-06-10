package Bildirler;
import Observer.Observerable;

import Observer.Observer;

public class ObserverKullanci implements Observer {
    private Observerable observerable;

    public void setObserver(Observerable observerable) {
        this.observerable = observerable;
    }

    @Override
    public void bildir(String message) { System.out.println(message); }
}
