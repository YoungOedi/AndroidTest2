
package de.dortmund.fh.jung.myproject.searchview;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    public SearchModule() {

    }

    @Provides
    SearchContract.Presenter providePresenter(SearchPresenter presenter) {
        return presenter;
    }

}
