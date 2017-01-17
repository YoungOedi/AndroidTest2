
package de.dortmund.fh.jung.myproject.di;

import javax.inject.Singleton;

import dagger.Component;
import de.dortmund.fh.jung.myproject.ApplicationModule;
import de.dortmund.fh.jung.myproject.mainview.MainActivity;
import de.dortmund.fh.jung.myproject.mainview.PresenterModule;

@Singleton
@Component(modules = {
        PresenterModule.class, ApplicationModule.class
})
public interface MyComponent {

    void inject(MainActivity activity);
}
