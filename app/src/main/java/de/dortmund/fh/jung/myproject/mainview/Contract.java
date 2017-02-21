
package de.dortmund.fh.jung.myproject.mainview;

import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

public interface Contract {
    interface View extends BaseView {
        void setText(String text);
        void goToSearchActivity();
        void goToChaosActivity();
    }

    interface Presenter extends BasePresenter<View> {
        void handleClickEvent(final int id);
    }

}
