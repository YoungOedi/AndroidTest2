
package de.dortmund.fh.jung.myproject;

import android.support.v7.app.AppCompatActivity;

import de.dortmund.fh.jung.myproject.di.ComponentProvider;
import de.dortmund.fh.jung.myproject.di.MyComponent;

/**
 * Created by hendrikjung on 30.12.16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected MyComponent getAppComponent() {
        return ((ComponentProvider) getApplication()).getAppComponent();
    }
}
