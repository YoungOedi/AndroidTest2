
package de.dortmund.fh.jung.myproject;

import android.app.Application;

/**
 * Created by hendrikjung on 30.12.16.
 */

public class ParentApplication extends Application {

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

    public MyComponent getAppComponent() {
        return mComponent;
    }

}
