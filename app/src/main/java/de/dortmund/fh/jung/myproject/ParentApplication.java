
package de.dortmund.fh.jung.myproject;

import android.app.Application;

import de.dortmund.fh.jung.myproject.di.ComponentProvider;
import de.dortmund.fh.jung.myproject.di.DaggerMyComponent;
import de.dortmund.fh.jung.myproject.di.MyComponent;

public class ParentApplication extends Application implements ComponentProvider {

    private MyComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createAppComponent();
    }

    private void createAppComponent() {
        mComponent = DaggerMyComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    public MyComponent getAppComponent() {
        return mComponent;
    }
}
