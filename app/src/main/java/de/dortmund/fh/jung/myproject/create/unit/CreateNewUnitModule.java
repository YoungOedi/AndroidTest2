
package de.dortmund.fh.jung.myproject.create.unit;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateNewUnitModule {

    @Provides
    static CreateNewUnitContract.Presenter providePresenter(CreateNewUnitPresenter presenter) {
        return presenter;
    }

}
