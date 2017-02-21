
package de.dortmund.fh.jung.myproject.di;

import javax.inject.Singleton;

import dagger.Component;
import de.dortmund.fh.jung.myproject.ApplicationModule;
import de.dortmund.fh.jung.myproject.chaosview.ChaosActivity;
import de.dortmund.fh.jung.myproject.chaosview.ChaosModule;
import de.dortmund.fh.jung.myproject.chaosview.ChaosPresenter;
import de.dortmund.fh.jung.myproject.mainview.MainActivity;
import de.dortmund.fh.jung.myproject.mainview.PresenterModule;
import de.dortmund.fh.jung.myproject.searchview.SearchActivity;
import de.dortmund.fh.jung.myproject.searchview.SearchModule;

@Singleton
@Component(modules = {
        PresenterModule.class,
        ApplicationModule.class,
        SearchModule.class,
        ChaosModule.class
})
public interface MyComponent {

    void inject(MainActivity activity);

    void inject(SearchActivity activity);

    void inject(ChaosActivity activity);
}
