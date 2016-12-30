package de.dortmund.fh.jung.myproject;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hendrikjung on 29.12.16.
 */
@Singleton
@Component(modules = {PresenterModule.class, ApplicationModule.class})
public interface MyComponent {

    void inject(MainActivity activity);
}
