package de.dortmund.fh.jung.myproject.chaosview;


import io.reactivex.Observable;

public interface AdapterInteraction {
    void subscribeAsObserver(Observable observable);
}
