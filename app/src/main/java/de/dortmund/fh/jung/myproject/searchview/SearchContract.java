
package de.dortmund.fh.jung.myproject.searchview;

import java.util.List;

import de.dortmund.fh.jung.myproject.mainview.Contract;
import de.dortmund.fh.jung.myproject.mvp.BasePresenter;
import de.dortmund.fh.jung.myproject.mvp.BaseView;

public interface SearchContract {
    interface View extends BaseView {
        String getSearchFieldText();

        void showResult(List<String> result);
    }

    interface Presenter extends BasePresenter<SearchContract.View> {
        void handleClickEvent(final int id);

        void calculateResult();

        void initList(List<String> list);

        List<String> search(String query);
    }

}
