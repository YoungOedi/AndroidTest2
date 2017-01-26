
package de.dortmund.fh.jung.myproject.mainview;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    public PresenterModule() {

    }

    @Provides
    Contract.Presenter providePresenter(MyPresenter presenter) {
        return presenter;
    }

}
