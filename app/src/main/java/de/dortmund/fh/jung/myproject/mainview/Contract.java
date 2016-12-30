package de.dortmund.fh.jung.myproject.mainview;

import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

/**
 * Created by hendrikjung on 29.12.16.
 */

public interface Contract {
    interface View extends BaseView{
        void setText(String text);
    }

    interface Presenter extends BasePresenter<View>{
        void handleClickEvent();
    }

}
