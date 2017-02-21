
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ChaosPresenter implements ChaosContract.Presenter {

    public enum GiftType {
        SMALL, MIDDLE, GREAT
    }

    public enum God {
        TZZENCH, NURGLE, SLAANESCH
    }

    private ChaosContract.View view;

    @Inject
    public ChaosPresenter() {

    }

    public List<Unit> getDummyUnitList(){
        //Create Dummy.
        //Master Level 2, MidleReward 3 and Greater Reward 1
        Unit dummy = new Unit("Herald 1", God.NURGLE);
        dummy.setMasteryLevel(2);
        ArrayList list = new ArrayList<Integer>();
        list.add(1);
        dummy.setGiftGreat(list);
        list.clear();
        list.add(3);
        dummy.setGitftMidle(list);

        Unit dummy2 = new Unit("Great Unclean One", God.NURGLE);
        dummy2.setMasteryLevel(3);
        list.clear();
        list.clear();
        list.add(1);
        list.add(5);
        dummy2.setGitftMidle(list);

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
