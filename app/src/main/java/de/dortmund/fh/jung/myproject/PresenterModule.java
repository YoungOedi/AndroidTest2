
package de.dortmund.fh.jung.myproject;


import dagger.Module;
import dagger.Provides;

/**
 * Created by hendrikjung on 29.12.16.
 */

@Module
public class PresenterModule {
    private final Contract.View mView;

    public PresenterModule(Contract.View view) {
        mView = view;
    }

    @Provides
    Contract.View provideTasksContractView() {
        return mView;
    }

}
