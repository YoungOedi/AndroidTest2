
package de.dortmund.fh.jung.myproject;

import android.app.Application;

import dagger.Component;
import de.dortmund.fh.jung.myproject.di.ComponentProvider;
import de.dortmund.fh.jung.myproject.di.MyComponent;
import de.dortmund.fh.jung.myproject.di.DaggerMyComponent;

/**
 * Created by hendrikjung on 30.12.16.
 */

public class ParentApplication extends Application implements ComponentProvider{

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

    public void test(){
        mComponent.hashCode();
    }

    @Override
    public MyComponent getAppComponent() {
        return mComponent;
    }
}
