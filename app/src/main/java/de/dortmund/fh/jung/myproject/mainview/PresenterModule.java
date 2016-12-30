
package de.dortmund.fh.jung.myproject.mainview;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hendrikjung on 29.12.16.
 */

@Module
public class PresenterModule {

    public PresenterModule() {

    }

    @Provides
    Contract.Presenter providePresenter(MyPresenter presenter) {
        return presenter;
    }

}
