
package de.dortmund.fh.jung.myproject.chaosview;

import dagger.Module;
import dagger.Provides;

@Module
public class ChaosModule {

    // public ChaosModule() {
    //
    // }

    @Provides
    static ChaosContract.Presenter providePresenter(ChaosPresenter presenter) {
        return presenter;
    }

}
