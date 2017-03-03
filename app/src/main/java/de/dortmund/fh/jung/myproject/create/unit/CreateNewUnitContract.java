
package de.dortmund.fh.jung.myproject.create.unit;

import de.dortmund.fh.jung.myproject.God;
import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

public interface CreateNewUnitContract {
    interface View extends BaseView {
        void updateHighlights(final int row, int[] highlighted);
        void changeThemeToKhorne();
        void changeThemeToSlaanesh();
        void changeThemeToNurgle();
        void changeThemeToTzzench();
    }


    interface Presenter extends BasePresenter<CreateNewUnitContract.View> {
        void saveNewPhotoLocation(final String filePath);
        void createNewUnit();
        void handleClickEvent(final int row, final int column);
        boolean isHighlighted(final int row, final int column);
        void changeGod(final God god);
        void setUnitName(final String name);
    }
}
