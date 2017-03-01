
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.dortmund.fh.jung.myproject.GiftType;
import de.dortmund.fh.jung.myproject.God;

public class ChaosPresenter implements ChaosContract.Presenter {

    private ChaosContract.View view;

    @Inject
    public ChaosPresenter() {

    }

    @Override
    public List<Unit> getUnitList(){
       return this.createDummyUnitList();
    }

    @Override
    public void goToNewUnitActivity() {
        view.changeViewToNewUnitActivity();
    }

    private List<Unit> createDummyUnitList(){
        Unit dummy = new Unit("Herald 1", God.NURGLE);
        dummy.setMasteryLevel(2);
        ArrayList list = new ArrayList<Integer>();
        list.add(1);
        dummy.setGreaterGift(list);
        list.clear();
        list.add(3);
        dummy.setMiddleGift(list);

        Unit dummy2 = new Unit("Great Unclean One", God.NURGLE);
        dummy2.setMasteryLevel(3);
        list.clear();
        list.clear();
        list.add(1);
        list.add(5);
        dummy2.setMiddleGift(list);

        ArrayList unitList = new ArrayList<Unit>();
        unitList.add(dummy);
        unitList.add(dummy2);
        return unitList;
    }

    @Override
    public void bind(ChaosContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    public void generateGift(GiftType type, God god) {
    }
}
