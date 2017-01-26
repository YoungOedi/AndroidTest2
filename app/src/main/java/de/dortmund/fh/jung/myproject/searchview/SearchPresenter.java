
package de.dortmund.fh.jung.myproject.searchview;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {

    SearchContract.View mView;

    private CheeseSearchEngine mCheeseSearchEngine;

    @Inject
    public SearchPresenter() {
        mCheeseSearchEngine = new CheeseSearchEngine(Collections.emptyList());
    }

    @Override
    public void handleClickEvent(int id) {

    }

    @Override
    public List<String> search(String query) {
        return mCheeseSearchEngine.search(query);
    }

    @Override
    public void calculateResult() {

    }

    @Override
    public void initList(List<String> list) {
        mCheeseSearchEngine.initCheese(list);
    }

    @Override
    public void bind(SearchContract.View view) {

    }

    @Override
    public void unbind() {
        mView = null;
    }
}
