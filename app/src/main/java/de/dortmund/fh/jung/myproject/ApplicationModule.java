
package de.dortmund.fh.jung.myproject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hendrikjung on 30.12.16.
 */

@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(final Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
