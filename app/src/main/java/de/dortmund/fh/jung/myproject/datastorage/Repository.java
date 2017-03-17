package de.dortmund.fh.jung.myproject.datastorage;


import de.dortmund.fh.jung.myproject.chaosview.Unit;

public interface Repository {
    long saveUnit(final Unit unit);
    void getUnit(final int id);
    void clearAll();
}
