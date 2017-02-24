
package de.dortmund.fh.jung.myproject.create.unit;

import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

public interface CreateNewUnitContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<CreateNewUnitContract.View> {
        void saveNewPhotoLocation(String filePath);
        void createNewUnit();
    }
}
