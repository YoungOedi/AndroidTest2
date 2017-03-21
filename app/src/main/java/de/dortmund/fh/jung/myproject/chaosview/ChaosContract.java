
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.List;

import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;
import io.reactivex.functions.Consumer;

public interface ChaosContract {
    interface View extends BaseView {
        void changeViewToNewUnitActivity();
    }

    interface Presenter extends BasePresenter<ChaosContract.View>, Consumer<Unit>{
        void goToNewUnitActivity();
        List<Unit> provideUnitList();
        void removeUnit(final int id);
    }
}
