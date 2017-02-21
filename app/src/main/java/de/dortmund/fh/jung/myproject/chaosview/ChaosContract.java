
package de.dortmund.fh.jung.myproject.chaosview;

import java.util.List;

import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

public interface ChaosContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<ChaosContract.View> {
        List<Unit> getDummyUnitList();
    }
}
