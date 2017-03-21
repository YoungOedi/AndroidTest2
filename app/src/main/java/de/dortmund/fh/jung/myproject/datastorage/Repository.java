package de.dortmund.fh.jung.myproject.datastorage;


import java.util.List;

import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.chaosview.Gift;
import de.dortmund.fh.jung.myproject.chaosview.Unit;

public interface Repository {
    long saveUnit(final Unit unit);
    List<Unit> getAllUnits();
    Gift getGift(final God god, final int diceRoll);
    void clearAll();
    void deleteUnit(final int id);
}
